# 第三章 JDBC Template

> 为了使`JDBC`更加易于使用，`Spring`在`JDBC API`上定义了一个抽象层，以此建立一个`JDBC`存取框架
>
> 可以将`Spring`的`dbcTemplate`看作是一个小型的轻量级持久化层框架，和我们之前使用过的`DBUtils`风格非常接近

## 配置环境

> 需要的JAR包
>
> 除了Spring所需的基础包

+ `spring-jdbc.jar`
+ `spring-orm.jar`
+ `spring-tx.jar`
+ `c3p0-0.9.1.2.jar`：数据库连接池
+ `mysql-connector-java-5.1.7-bin.jar`：数据库驱动

## 数据库配置文件

> `dbconfig.properties`

``` properties
jdbc.username=root
jdbc.password=CAI_mysql
jdbc.jdbcUrl=jdbc:mysql:localhost:3306/test
jdbc.driverClass=com.mysql.cj.jdbc.Driver
```

## Spring配置文件

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    
    <context:component-scan base-package="edu.fdzc"/>

    <!-- 引入外部配置文件 -->
    <context:property-placeholder location="classpath:conf/dbconfig.properties"/>

    <!-- 配置数据源 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
        <property name="driverClass" value="${jdbc.driverClass}"/>
    </bean>

    <!-- 使用JdbcTemplate操作数据库 -->
    <bean class="org.springframework.jdbc.core.JdbcTemplate">
        <constructor-arg name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 配置具名参数功能的JdbcTemplate -->
    <bean id="namedParameterJdbcTemplate" class="org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate">
        <!-- 没有无参构造器，必须传入数据源或JdbcTemplate对象 -->
        <constructor-arg name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```

### 持久化操作

#### 增删改

> `JdbcTemplate.update(String, Object...)`
>
> 将`emp_id = 5`的记录的`salary`字段更新为`1300.00`

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

public void test() {
    String sql = "UPDATE employee SET salary = ? WHERE emp_id = ?";
    int update = jdbcTemplate.update(sql, 1300.00, 5);
    System.out.println("更新影响了" + update + "行");
}
```

#### 批量增删改

> `JdbcTemplate.batchUpdate(String, List<Object[]>)`
>
> 批量插入数据到`employ`表中

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

public void test() {
    String sql = "INSERT INTO employee(emp_name, salary) VALUES (?, ?)";
    // List的长度就是sql语句执行的次数
    // Object[]：每次执行要用的参数
    List<Object[]> batchArgs = new ArrayList<>();
    batchArgs.add(new Object[]{"张三", 1300});
    batchArgs.add(new Object[]{"李四", 2300});
    batchArgs.add(new Object[]{"王五", 4400});
    batchArgs.add(new Object[]{"赵六", 5500});

    int[] update = jdbcTemplate.batchUpdate(sql, batchArgs);
    for (int i : update) {
        System.out.println(i);
    }
}
```

#### 查询单行

> `JdbcTemplate.queryForObject(String, RowMapper<Department>, Object...)`
>
> `查询emp_id = 5`的数据库记录，封装一个`Java对象`返回
>
> `javabean`需要和数据库字段名一致，否则无法封装

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

public void test() {
    String sql = "SELECT emp_id empId, emp_name empName, salary empSalary FROM employee WHERE emp_id = ?";
     
    // RowMapper：每一行记录和JavaBean的属性如何映射
    Employee employee = null;
    
    try {
        employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 5);
    } catch (DataAccessException e) {
        e.printStackTrace();
    }
    
    System.out.println(employee);
}
```

#### 查询多行

> `JdbcTemplate.query(String, RowMapper<Department>, Object...)`
>
> 查询`salary > 4000`的数据库记录，封装为`List集合`返回

#### 查询单个数据

> `查询最多的工资`

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);

public void test() {
    String sql = "SELECT MAX(salary) FROM employee";
    // 无论返回单个数据或是对象，都使用queryForObject()
    Double salary = jdbcTemplate.queryForObject(sql, Double.class);
    System.out.println(salary);
}
```

#### 带有具名参数的SQL语句

> 具名参数：具有名字的参数
>
> 不使用占位符而是变量名`:参数名`
>
> `Spring`有一个能支持具名参数的`JdbcTemplate`:`NamedParameterJdbcTemplate`
>
> 与占位符区别：占位符参数：？的位置不能乱，按位置传值；具名参数按参数名传值

> 使用带有`具名参数的SQL语句`插入一条员工记录，并以`Map形式`传入参数值

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
NamedParameterJdbcTemplate named = ioc.getBean(NamedParameterJdbcTemplate.class);

public void test() {
    String sql = "INSERT INTO employee(emp_name, salary) VALUES (:empName, :salary)";
    Map<String, Object> map = new HashMap<>();
    
    // 将所有具名参数都放在map中
    map.put("empName", "CAI");
    map.put("salary", 30000);
    int update = named.update(sql, map);
    
    System.out.println(update);
}
```

##### 以SqlParameterSource形式传值

> 除了使用`Map`为具名参数传值，还可以使用` SqlParameterSource 参数`

``` java
ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
NamedParameterJdbcTemplate named = ioc.getBean(NamedParameterJdbcTemplate.class);

public void test() {
    String sql = "INSERT INTO employee(emp_name, salary) VALUES (:empName, :empSalary)";
    Employee employee = new Employee();
    employee.setEmpName("Pork");
    employee.setEmpSalary(12344.2);
    int update = named.update(sql, new BeanPropertySqlParameterSource(employee));
    System.out.println(update);
}
```

> 使用具名参数的好处：若有多个参数, 则不用再去对应位置, 直接对应参数名, 便于维护
>
> 缺点：配置麻烦

### JdbcTemplate实现Dao

> 通过`IOC容器`自动注入

``` java
import edu.fdzc.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/19
 */
@Repository
public class EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveEmployee(Employee employee) {
        .....
    }
}
```

