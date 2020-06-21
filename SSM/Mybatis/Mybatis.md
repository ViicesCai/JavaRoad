# Mybatis

> 简化JDBC操作，实现数据的持久化

## ORM

> ORM：Object Relational Mapping
>
> Mybatis：是ORM的一个实现（Hibernate）
>
> ORM的理念是：将 数据表映射为一个对象：对象名 -> 表名，属性 -> 字段：像操作对象一样操作数据库表

## 配置

[下载地址](https://github.com/mybatis/mybatis-3/releases/download/mybatis-3.5.5/mybatis-3.5.5.zip)

### 基础方式：CRUD

> config.xml : 配置数据库信息和需要加载的映射文件

``` xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 通过 environments 的 default 和 environment 的 id 来指导 Mybatis 运行时的数据库环境 -->
	<environments default="development">
        <!-- 开发环境 -->
		<environment id="development">
            <!-- 
				配置事务的提交方式：JDBC(手工方式)
 				MANAGED：将事务交由其他组件托管（Spring、Jobss）：默认会关闭连接
			-->
            <!-- <property name="closeConnection" value="false" />：默认不关闭 -->
			<transactionManager type="JDBC" />
            <!-- 数据源类型：
					POOLED：数据库连接池：省略了数据库打开和关闭：提高效率
 					UNPOOLED：传统的JDBC模式：每次访问数据库都需要打开、关闭：损耗性能
					JNDI：从 Tomcat 获取内置的数据库连接池（即：数据源）
			-->
			<dataSource type="POOLED">
				<!-- 配置数据库信息 -->
				<property name="driver" value="com.mysql.cj.jdbc.Driver" />
				<property name="url" value="jdbc:mysql://localhost:3306/mydatabase" />
				<property name="username" value="root" />
				<property name="password" value="abc123" />
			</dataSource>
		</environment>
        
        <!-- 测试环境 -->
        <environment id="test">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<!-- 配置数据库信息 -->
				<property name="driver" value="" />
				<property name="url" value="" />
				<property name="username" value="" />
				<property name="password" value="" />
			</dataSource>
		</environment>
	</environments>
	
	<mappers>
		<mapper resource="edu/fdzc/entity/personMapper.xml" />
	</mappers>
</configuration>
```

> xxMapper.xml : 映射文件

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 映射文件的路径：唯一标识符 -->
<mapper namespace="edu.fdzc.entity.personMapper">
    <!-- 增删改查通过标签的形式 -->
    
    <!-- id:用来标识查询语句，resultType:结果集类型，parameterType:占位符类型 -->
    <!-- Mybatis 约定：resultType 与 parameterType 在形式上只能有一个：可以通过数组包含多个 -->
    <!-- 
		parameterType：为简单类型（8个基本类型 + String）可以使用任意占位符：#{xx}
					  如果为对象类型，则必须是对象的属性 #{属性名}	
 	-->
    <!-- select:查标签 -->
	<select id="queryPersonById" resultType="edu.fdzc.entity.Person" parameterType="int">
		Select * From Person Where id = #{id}
	</select>
    
    <!-- 增加 -->
	<insert id="addPerson" parameterType="edu.fdzc.entity.Person">
		Insert into person(id, name, age) values(#{id}, #{name}, #{age})
	</insert>
	
	<!-- 修改 -->
	<update id="updatePersonById" parameterType="edu.fdzc.entity.Person">
		Update person set name = #{name}, age = #{age} where id = #{id}
	</update>
	
	<!-- 删除 -->
	<delete id="deletePersonById" parameterType="int">
		Delete from person where id = #{id}
	</delete>
	
	<!-- 查询所有 -->
	<select id="queryAllPersons" resultType="edu.fdzc.entity.Person">
		Select * from person
	</select>
</mapper>
```

> 测试类

``` java
package edu.fdzc.entity;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {

	/**
	 * 根据 id 查询人
	 * 
	 * @throws IOException
	 */
	public static void queryPersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper.queryPersonById";
		Person person = session.selectOne(statement, 1);

		System.out.println(person);
		session.close();
	}

	/**
	 * 查询所有人
	 * 
	 * @throws IOException
	 */
	public static void queryAllPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "queryAllPersons";
		List<Person> persons = session.selectList(statement);

		System.out.println(persons);
		session.close();
	}

	/**
	 * 增加人物
	 * 
	 * @throws IOException
	 */
	public static void addPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "addPerson";
		Person persons = new Person(10, "嬴政", 44);
		int count = session.insert(statement, persons);
		session.commit();// 提交事务
		System.out.println("增加 " + count + " 个学生");
		session.close();
	}

	/**
	 * 根据 id 删除学生
	 * 
	 * @throws IOException
	 */
	public static void deletePersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "deletePersonById";
		int count = session.delete(statement, 1);

		session.commit(); //提交事务
		
		System.out.println("删除 " + count + " 个学生");
		session.close();

	}

	/**
	 * 根据 id 修改人物
	 * 
	 * @throws IOException
	 */
	public static void updatePersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取定义的 sql 语句
		String statement = "edu.fdzc.entity.personMapper." + "updatePersonById";
		Person person = new Person(); // 修改的对象
		person.setId(1); // 指定修改的 id
		
		// 修改的参数
		person.setName("凯文");
		person.setAge(54);
		
		// 执行
		int count = session.update(statement, person);
		
		session.commit(); //提交事务
		
		System.out.println("修改 " + count + " 个学生");
		session.close();
	}

	public static void main(String[] args) throws IOException {
		updatePersonById();
	}
}

```

> 注意：如果采用的事务方式为 JDBC 则需要手工提交：session.commit();

### Mapper 动态代理方式：CRUD(推荐)

> 基于 Mybatis 接口开发
>
> 原则：约定优于配置
>
> 不同处：省略掉 statement：根据约定可以直接定位出 sql 语句

> 定义接口

``` java
/**
 * 
 */
package edu.fdzc.mapper;

import edu.fdzc.entity.Person;

/**
 * Person 表映射接口
 * 
 * @author CAI
 *
 */
public interface PersonMapper {
	/**
	 * 约定
	 *	1.方法名 和 mapper.xml 文件中的标签 id 值相同
	 *  2.方法的输入参数 和 mapper.xml 文件中标签的 parameterType 类型一致
	 *  3.方法的返回值 和  mapper.xml 文件中标签的 resultType 类型一致
	 */

	Person queryPersonById(int id);
    
	int addPerson(Person person);
    
    List<Person> queryAllPersons();
}

```

> Mapper.xml：接口方法 与 SQL标签对应

``` xml
<!-- 
	约定：namespace 的值即：接口的全类名（接口 <=> Mapper.xml）
 -->
<mapper namespace="edu.fdzc.mapper.PersonMapper">
    <!-- 查询 -->
	<select id="queryPersonById" resultType="edu.fdzc.entity.Person" parameterType="int">
		Select * From Person Where id = #{id}
	</select>
</mapper>
```

> namespace = 接口全类名；方法名 = SQL 标签 id 值
>
> 保证：当我们调用接口中的方法时，程序能自动定位到相应 mapper.xml 文件中的 sql 标签

> 习惯：将 SQL映射文件（mapper.xml） 与 接口放在同一包下

> 测试类

``` java
package edu.fdzc.entity;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.fdzc.mapper.PersonMapper;

/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {

	/**
	 * 根据 id 查询人
	 * 
	 * @throws IOException
	 */
	public static void queryPersonById() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
		
		// 通过接口方法 执行对应的 SQL 语句
		Person person =  mapper.queryPersonById(2);
		
		System.out.println(person);
		session.close();
	}
    
    /**
	 * 增加人物
	 * 
	 * @throws IOException
	 */
	public static void addPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();
		
		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 执行相应的 SQL 语句
		int count = mapper.addPerson(new Person(4, "Cai", 22));

		session.commit();// 提交事务
		System.out.println("增加 " + count + " 个学生");
		session.close();
	}
    
    /**
	 * 查询所有人
	 * 
	 * @throws IOException
	 */
	public static void queryAllPerson() throws IOException {
		// 加载 Mybatis 配置文件
		Reader reader = Resources.getResourceAsReader("config.xml");

		// SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

		// session - connection
		SqlSession session = sessionFactory.openSession();

		// 获取接口
		PersonMapper mapper = session.getMapper(PersonMapper.class);
						
		// 通过接口方法 获取 SQL语句
		List<Person> persons = mapper.queryAllPersons();

		System.out.println(persons);
		session.close();
	}
}
```

> tips：如果不确定 接口中的返回值与参数如何定义：参照 mapper 文件：
>
> resultType 即 返回值（缺省则为：void）：如果是 增删改操作：则可以在声明返回值为 int ：查看执行的结果
>
> parameterType 即 参数（缺省则：无参数）

## 进一步优化

### 数据库配置

> 将数据库的配置信息单独存放于配置文件：db.properties 动态引入

``` properties
driver = com.mysql.cj.jdbc.Driver
url = jdbc:mysql://localhost:3306/mydatabase
username = root
password = abc123
```

> config.xml 文件动态引入

``` xml
<!-- 引入 数据库配置 -->
<properties resource="db.properties">
</properties>

<!-- 配置数据库信息 -->
<property name="driver" value="${driver}" />
<property name="url" value="${url}" />
<property name="username" value="${username}" />
<property name="password" value="${password}" />
```

### 全局参数

> 在 config.xml 中设置全局参数：初学者慎用

``` xml
<configuration>
    <!-- 配置全局参数 -->
    <settings>
        <!-- 相关参数查阅文档 -->
        <setting name="cacheEnabled" value="false" />
    </settings>
</configuration>
```

### 定义别名

> 在 config.xml  中定义别名：可以简化 mapper 中的配置：Mybatis 也提供了一些内置的别名

``` xml
<configuration>
<!-- 定义别名 -->
<typeAliases>
	<!-- 定义单个别名:别名 忽略大小写 -->
	<typeAlias type="edu.fdzc.entity.Person" alias="person"/>
		
	<!-- 批量定义:会将该包下的所有类定义别名：别名 = 类名（不带包名）：忽略大小写 -->
	<!-- <package name="edu.fdzc.entity"/> -->
	</typeAliases>
</configuration>
```

``` xml
<!-- 查询 -->
<!-- mapper.xml 文件中使用了该对象的标签，可以只写别名无需写全路径：简化了代码 -->
<select id="queryPersonById" resultType="person" parameterType="int">
	Select * From Person Where id = #{id}
</select>
```

### 类型转换器

> 类型转换器：java数据类型 <-> JDBC 类型（数据库类型）
>
> Mybatis 自带了一些常见的类型转换器 如：int <-> number

#### 自定义

> 示例

+ 实体类 Person : 性别属性 : boolean sex
  + true : 男
  + false : 女
+ 数据表 Person : 性别字段 : number sex
  + 1 : 男
  + 0 : 女

> 创建转换器：
>
> 实现 TypeHandler 接口
>
> 或：继承 BaseTypeHandler 类

![image-20200616123234321](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200616123234321.png)

``` java
package edu.fdzc.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * boolean 与 int 转换器 
 * 
 * @author CAI
 *
 */
public class BooleanAndIntConverter extends BaseTypeHandler<Boolean> {

	/**
	 * java 类型 转 数据库类型
	 * 
	 * ps : PreparedStatement对象
	 * i : PreparedStatement对象操作参数的位置
	 * parameter : java 值
	 * JdbcType : 数据库类型
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		
		if (parameter) { // 如果 java 值为 true
			ps.setInt(i, 1); // 将该变量值变为 1
			
		} else {
			ps.setInt(i, 0);
		}
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// 根据列名取值
		int sex = rs.getInt(columnName);
		
		return sex == 1 ? true:false;
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// 根据列下标
		int sex = rs.getInt(columnIndex);
				
		return sex == 1 ? true:false;
	}

	/**
	 * 数据库类型 转 java 类型
	 */
	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int sex = cs.getInt(columnIndex);
		return sex == 1 ? true:false;
	}
}
```

> config.xml

``` xml
<!-- 配置转换器 -->
<typeHandlers>
    <!-- JDBC 类型为全大写 -->
	<typeHandler handler="edu.fdzc.converter.BooleanAndIntConverter" javaType="Boolean" jdbcType="INTEGER"/>
</typeHandlers>
```

> mapper.xml

``` xml
<!-- 
	查询：使用类型转换器
	1.如果类中的属性 和 表中的字段能够合理识别（如：String <-> varchar）则使用 resultType
	否则使用 resultMap
	2.如果类中的属性名 与 表中字段名 一一对应（id <-> ID）使用 resultType
	否则使用resultMap
-->
<select id="queryPersonByIdWithConverter" parameterType="int" resultMap="personResult">
	Select * from person where id = #{id}
</select>
	
<insert id="addPersonWithConverter" parameterType="person">
		Insert into person(id, name, age, sex) values(#{id}, #{name}, #{age}, #{sex, javaType=boolean,
    jdbcType=INTEGER})
</insert>

<resultMap type="person" id="personResult">
	<!-- 主键 -->
	<!-- <id/> -->
	<!-- 非主键 -->
	<!-- 属性与字段绑定 -->
	<result property="id" column="id" />
	<result property="name" column="name" />
	<result property="age" column="age" />
    <!-- JDBC类型为全大写 -->
	<result property="sex" column="sex" javaType="boolean" jdbcType="INTEGER" />
</resultMap>
```

## 输入参数

``` xml
<!-- 查询 -->
<!-- parameterType -->
<select id="queryPersonById" resultType="person" parameterType="int">
	Select * From Person Where id = #{id}
</select>
```

> 简单类型：八个基本类型 + String
>
> #{任意值} : 此种类型会为 String 类型 加上 ''
>
> ${value} : 此种形式接收值标识符只能为 value : 原样输出
>
> 即：String name = "Cai"; #{name} = 'Cai'; ${value} = Cai
>
> 区别:
>
> #{} : 自动类型转换; ${} 原样输出：适合用于 动态排序（动态字段）
>
> #{} : 可以防止 SQL 注入; ${} : 不防止
>
> 对象类型：#{对象属性名}; ${对象属性名}
>

``` xml
<!-- 查询所有，并根据字段名排序 -->
<select id="queryAllPersonsOrderByColumn" parameterType="string"  resultType="person">
	Select * from person order by ${value} desc
</select>
	
<!-- 根据年龄或姓名模糊查询 -->
<select id="queryPersonsByAgeOrName" parameterType="person" resultType="person">
	Select * from person where age = #{age} or name like '%${name}%'
</select>

<!-- 输入参数为级联属性：属性的属性 -->
<select id="queryPersonsByAddress" parameterType="person" resultType="person">
	Select * from person where homeaddress = #{address.homeAddress} or workaddress = #{address.workAddress}
</select>
```

``` java
/**
* 根据姓名或年龄 模糊查询
* 
* @throws IOException
*/
public static void queryPersonsByAgeOrName() throws IOException {
	// 加载 Mybatis 配置文件
	Reader reader = Resources.getResourceAsReader("config.xml");

	// SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

	// session - connection
	SqlSession session = sessionFactory.openSession();

	// 获取接口
	PersonMapper mapper = session.getMapper(PersonMapper.class);
						
	Person person = new Person();
	person.setAge(54);
	person.setName("z");
		
	// 通过接口方法 获取 SQL语句
	List<Person> persons = mapper.queryPersonsByAgeOrName(person);

	System.out.println(persons);
	session.close();
}

/**
* 查询所有人按 id 排序
* 
* @throws IOException
*/
public static void queryAllPersonsOrderById() throws IOException {
	// 加载 Mybatis 配置文件
	Reader reader = Resources.getResourceAsReader("config.xml");

	// SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

	// session - connection
	SqlSession session = sessionFactory.openSession();

	// 获取接口
	PersonMapper mapper = session.getMapper(PersonMapper.class);
						
	// 通过接口方法 获取 SQL语句
	List<Person> persons = mapper.queryAllPersonsOrderByColumn("id");

	System.out.println(persons);
	session.close();
}
```

### 参数为 HashMap

> 用 map 中 key 的值匹配占位符 #{}，如果匹配成功 就用 map 的 value 替换占位符

``` xml
<!--  输入参数的类型为 HashMap -->
<select id="queryPersonByAgeOrNameWithHashMap" parameterType="HashMap" resultType="person">
    Select * from person where age = #{age} or name like '%${name}%'
</select>
```

``` java
/**
* 根据 HashMap 查询
* 
* @throws IOException
*/
public static void queryPersonByAgeOrNameWithHashMap() throws IOException {
	// 加载 Mybatis 配置文件
	Reader reader = Resources.getResourceAsReader("config.xml");

	// SqlSessionFactory
	SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

	// session - connection 
	SqlSession session = sessionFactory.openSession();

	// 获取接口
	PersonMapper mapper = session.getMapper(PersonMapper.class);
	Map<String, Object> personMap = new HashMap<String, Object>();
	personMap.put("age", 54);
	personMap.put("name", "zs");
		
	// 通过接口方法 获取 SQL语句
	List<Person> persons = mapper.queryPersonByAgeOrNameWithHashMap(personMap);

	System.out.println(persons);
	session.close();
}
```

## 输出参数

``` xml
<!-- 查询人物总数 -->
<!-- resultType -->
<select id="queryPersonCount" resultType="int">
	Select count(*) from person
</select>
```

> 简单类型：八个基本类型 + String
>
> 实体对象类型：该类的类名 + 包名
>
> 当实体类的属性与数据表的字段：类型不同 或 名字不同 ：使用 resultMap
>
> 实体对象类型的集合：resultType 依然些 集合的元素类型：但是具体方法的返回值应为 集合
>
> 返回值为 HashMap时仅能容纳一个或空：即执行的返回值结果不能超过一个对象

``` xml
<!-- 输出参数为 HashMap -->
<select id="queryPersonByIdOutByHashMap" parameterType="int" resultType="HashMap">
	<!-- 通过别名作为 Map 的 Key -->
	Select id "id", name "name" from person where id = #{id}
</select>
	
<!-- 输出参数为 HashMap -->
<select id="queryAllPersonsOutByHashMap" parameterType="int" resultType="HashMap">
	<!-- 通过别名作为 Map 的 Key -->
	Select id "id", name "name" from person
</select>
```

``` java
/**
* 根据 id 查询的返回结果为 HashMap
* 
* @return
*/
HashMap<String, Object> queryPersonByIdOutByHashMap(int id);
	
/**
* 查询返回结果为 HashMap
* 
* @return
*/
List<HashMap<String, Object>>queryAllPersonsOutByHashMap();
```

### 当属性名与字段名不一致

> 除了定义 resultMap

> 使用 resultType + HashMap

``` xml
<select id="...">
    <!-- 此种形式会将 字段名 与 属性名对应起来：如果发现查找的某个返回值属性一直为 null 大概率是对象的属性名与数据表的字段没有对应 -->
    select 数据库字段名（id） "属性名"（"stuId"） from 数据表
</select>
```

## SQL 标签

### `<if>` `<where>`

``` xml
<!-- if and where -->
<select id="queryPersonByIdOrAgeWishSQLTag" parameterType="person" resultType="person">
    Select id, name, age from person
	<where>
        <!-- 如果 person 有  name 属性且不为 null :where 标签会自动加上 and-->
	 	<if test="name != null and name != ''">
	 		name = #{name}
		</if>
		
		<!-- 如果 person 有  age 属性且不为 null -->
		<if test="age != null and age != ''">
			and age = #{age}
		</if>
	</where>
</select>
```

### `<foreach>`

> 输入参数为对象

``` java
/**
 * 
 */
package edu.fdzc.entity;

import java.util.List;

/**
 * 社区类
 * 
 * @author CAI
 *
 */
public class Community {
	// id = 标识身份
	private List<Integer> ids;

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}

```

``` xml
<!-- 查询社区中包含的人 -->
<select id="queryPersonsWithIdInCommunity" parameterType="community" resultType="person">
 	Select * from person
 		
 	<where>
 		<!-- 判断 community 是否有值在进行遍历 -->
 		<if test="ids != null and ids.size > 0">
 			<!-- open+close = and id in("item的遍历值 + separator") -->
 			<foreach collection="ids" open=" and id in (" close=")" item="id" separator=",">
 				#{id}
 			</foreach>
 		</if>
 	</where>
</select>
```

> 输入参数为数组

``` xml
<!-- 将多个元素放入数组 -->
<select id="queryPersonsWithArray" parameterType="int[]" resultType="person">
	Select * from person
 		
 	<where>
 		<!-- 约定：无论该数组的名是什么，在 mapper.xml 必须使用 array 代替该数组 -->
 		<!-- 集合同理 -->
 		<if test="array != null and array.length > 0">
 			
 			<!-- 这里主要是对 sql 语句进行一个拼接 -->
 			<foreach collection="array" open=" and id in (" close=")" item="id" separator=",">
 				#{id}
 			</foreach>
 		</if>
 	</where>
</select>
```

> 输入参数为集合

``` xml
<!-- 将多个元素放入集合 -->
<select id="queryPersonsWithList" parameterType="list" resultType="person">
	Select * from person
 		
 	<where>
 		<!-- 约定：无论该数组的名是什么，在 mapper.xml 必须使用 array 代替该数组 -->
 		<!-- 集合同理 -->
 		<if test="list != null and list.size > 0">
 			
 			<!-- 这里主要是对 sql 语句进行一个拼接 -->
 			<foreach collection="list" open=" and id in (" close=")" item="id" separator=",">
 				#{id}
 			</foreach>
 		</if>
 	</where>
</select>
```

> 输入对象为对象数组

``` xml
<!-- 将多个元素放入对象数组 -->
<!-- 此处的输入对象有别于普通数组 -->
<select id="queryPersonsWithObjectArray" parameterType="Object[]" resultType="person">
	Select * from person
 		
 	<where>
 		<if test="array != null and array.length > 0">
 				
 			<foreach collection="array" open=" and id in (" close=")" item="person" separator=",">
 				#{person.id}
 			</foreach>
 		</if>
 	</where>
</select>
```

### 引用 SQL

> 提取

``` xml
<sql id="">
    SQL 代码
</sql>

<!-- 通过 sql的id 可以获取到 该 sql语句片段 -->
<!-- 外文件引用需要加上 namespace -->
<include refid="sql-id">
</include>
```

## 关联查询

### 一对一

#### 业务拓展类

``` java
/**
 * 人物业务扩展类
 * 
 * @author CAI
 *
 */
public class PersonBusiness extends Person { // 一个类中要想包含两个已有的类：继承属性多的，重写属性少的
	private int cardId;
	private String cardInfo;
	
	public int getCardId() {
		return cardId;
	}
	
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	
	public String getCardInfo() {
		return cardInfo;
	}
	
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	@Override
	public String toString() {
		return super.toString() + ", [cardId=" + cardId + ", cardInfo=" + cardInfo + "]";
	}
}

```

``` xml
<!-- 一对一关联 -->
<select id="queryPersonByIdWithOneToOne" parameterType="int" resultType="PersonBusiness">
	Select p.*, c.* from person p inner join personcard c 
	on p.cardid = c.cardid
	where p.id = #{id}
</select>
```

### ResultMap

``` xml
<select id="queryPersonByIdWithOneToOne" parameterType="int" resultMap="person_card_map">
	...
</select> 

<resultMap type="类名" id="person_card_map">
	<!-- 人物的信息：必须与 type 对应的类一致 -->
    <!-- 主键 -->
    <id property="属性名" column="数据库字段" />
    <result property="属性名" column="数据库字段" />
    <result property="属性名" column="数据库字段" />
    
    <!-- 如果该属性是一个引用类型 -->
    <!-- 一对一时，对象成员使用 association 映射 -->
    <association property="属性名" javaType="该属性的类型">
        <id property="属性名" column="数据库字段" />
        <result property="属性名" column="数据库字段" />
    </association>
</resultMap>
```

### 一对多

> person 与 community 通过外键关联：communityid

``` java
import java.util.List;

/**
 * 社区数据表
 * 
 * @author CAI
 *
 */
public class Communitys {
	private int communityId; // 社区 id
	private String communityName; // 社区名
	List<Person> persons; // 包含的人们（使两个类关联）
	
	public int getCommunityId() {
		return communityId;
	}
	
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	
	public String getCommunityName() {
		return communityName;
	}
	
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "[communityId=" + communityId + ", communityName=" + communityName + ", persons=\n" + persons
				+ "]";
	}
}
```

``` xml
 <!-- 一对多关联 -->
<select id="queryCommunityAndPersons" parameterType="int" resultMap="community_person_map">
	 <!-- 查询某个社区的信息与包含的所有的人 -->
	 Select c.*, p.* from person p
	 inner join community c
	 on c.communityid = p.communityid
	 where c.communityid = #{communityid}
</select>
	 
<resultMap type="communitys" id="community_person_map">
	 <!-- 配置 type 的 属性 -->
	 <id property="communityId" column="communityId" />
	 <result property="communityName" column="communityName" />
	 
	 <!-- 配置成员属性：一对多：使用 collection 集合 -->
	 <!-- 属性类型：javaType, 属性的元素类型(为一个集合中的某个元素)：ofType -->
	 <collection property="persons" ofType="person">
	 	<id property="id" column="id"/>
	 	<result property="name" column="name"/>
	 	<result property="age" column="age"/>
	 </collection>
</resultMap>
```

## 日志

> log4j.jar

### 开启

> 配置 config.xml

``` xml
<settings>
    <!-- 开启日志，并指定使用的具体日志 -->
	<!--
		如果未指定日志 则 Mybatis 自动根据以下顺序寻找日志
		SLF4J -> Apache Commons Logging -> Log4j 2 -> Log4j -> JDK logging 
	-->
	<setting name="logImpl" value="LOG4J"/>
		
	<!-- 开启延迟加载 -->
	<setting name="lazyLoadingEnabled" value="true"/>
		
	<!-- 关闭立即加载 -->
	<setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

### 编写配置日志输出文件

> log4j.properties

``` properties
log4j.rootLogger=DEBUG, stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender // 显示方式
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout // 排列方式
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n // 显示格式
```

#### 日志级别

+ DEBUG < INFO < WARN < ERROR : 调试 - 提示 - 警告 - 错误
+ DEBUG, stdout 表示：打印 DEBUG 及 以上的错误信息，输入到控制台
+ 建议：开发时设置为 DEBUG, 运行时设置为 INFO 或 以上

> 可以通过日志信息，详细的阅读 mybatis 执行情况（观察 mybatis 实际执行 sql 语句，以及 sql 中的参数以及返回结果）

## 延迟加载

> 如：社区 - 人 是一个一对多的关系
>
> 如果采用立即加载（默认）查询时，会将 一 和 多 都查询，即 查询了社区 与 社区中的所有人
>
> 当我们只想知道 社区表中存在多少个社区，只先查询当前所有的社区，当需要知道社区中具体的人时再进行查询：即延迟加载

### 一对一

``` xml
<!-- 一对一关联：懒加载 -->
<select id="queryPersonByIdWithLazyLoad" parameterType="int" resultMap="person_card_lazyLoad_map">
	Select * from person where id = #{id}
</select> 
	 
<resultMap type="person" id="person_card_lazyLoad_map">
	 <id property="id" column="id" />
	 <result property="name" column="name"/>
	 <result property="age" column="age"/>
	 	
	 <!-- 查询人物信息时，不立即加载身份证信息 -->
	 <!-- 通过 select 在需要的时候 查询身份证信息 -->
	 <association property="card" javaType="PersonCard" select="edu.fdzc.mapper.PersonCardMapper.queryCardById" column="cardid">
	 	<!-- 
	 	<id property="cardId" column="cardId"/>
	 	<result property="cardInfo" column="cardInfo"/>
	 	 -->
	 </association>
</resultMap>
```

``` xml
<!--  查询人物对应的身份证信息 -->
<select id="queryCardById" parameterType="int" resultType="personCard">
	Select * from personCard where cardId = #{cardId}
</select>
```

> 通过 Debug 发现：如果方法只需要任务信息，则只执行 "Select * from person where id = #{id}"，而没有执行 queryCarById
>
> 当我们需要使用到 card 时，才会第二次发送查询身份证的 "Select * from personCard where cardId = #{cardId}"

### 一对多

``` xml
<!-- CommunityMapper.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 
	约定：namespace 的值即：接口的全类名（接口 <=> Mapper.xml）
 -->
<mapper namespace="edu.fdzc.mapper.CommunityMapper">
	<!-- 一对多关联：懒加载 -->
	 <select id="queryCommunityAndPersonsWithLazyLoad" resultMap="community_person_map">
	 	<!-- 查询社区 -->
	 	Select c.* from community c
	 </select>
	 
	 <resultMap type="communitys" id="community_person_map">
	 	<!-- 配置 type 的 属性 -->
	 	<id property="communityId" column="communityId" />
	 	<result property="communityName" column="communityName" />
	 
	 	<!-- 延迟加载 -->
	 	<collection property="persons" ofType="person" select="edu.fdzc.mapper.PersonMapper.queryPersonByCommunityId" column="communityid">
	 		<id property="id" column="id"/>
	 		<result property="name" column="name"/>
	 		<result property ="age" column="age"/>
	 	</collection>
	 </resultMap>
</mapper>
```

``` xml
<!-- PersonMapper.xml -->
<!-- 根据 communityid 查询社区中的所有人 -->
<select id="queryPersonByCommunityId" parameterType="int" resultType="person">
	 Select * from person where communityId = #{communityId}
</select>
```

``` java
// CommunityMapper.java
package edu.fdzc.mapper;

import java.util.List;

import edu.fdzc.entity.Communitys;

/**
 * Community 表映射接口
 * 
 * @author CAI
 *
 */
public interface CommunityMapper {
	
	/**
	 * 查询全部社区
	 * 
	 * @return
	 */
	List<Communitys> queryCommunityAndPersonsWithLazyLoad();
}
```

## 缓存

### 一级缓存

> 同一个 SqlSession 对象：Mybatis 默认开启一级缓存
>
> 如使用同一个 SqlSession 对象查询相同的数据，则只会在第一次查询时向数据库发送 SQL 语句，并将查询的结果 放入 SQLSESSION（作为缓存）;后续对象查询同样的对象时，则直接从缓存中查询对象（省略了数据库的访问，提高了性能）

![image-20200621162908249](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200621162908249.png)

### 二级缓存

> Mybatis：自带二级缓存：同一个 namespace 生成的 mapper 对象
>
> 只要产生的 mapper 对象来自同一个 namespace，则这些对象共享二级缓存

![image-20200621165724706](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200621165724706.png)

> 同一个 SQLSession 对象进行多次相同的查询，则直接进入一级缓存中查询
>
> 如果非同一个 SQL Session 对象进行多次相同的查询（但均来自同一个 namespace），则直接进入二级缓存中查询

> Mybatis：默认开启一级缓存，默认关闭二级缓冲

#### 开启

> config.xml

``` xml
<settings>
	<!-- 开启二级缓存 -->
	<setting name="cacheEnabled" value="true"/>
</settings>
```

> 在具体的 mapper.xml 文件中声明开启

``` xml
<mapper namespace="edu.fdzc.mapper.PersonMapper">
	<!-- 声明此 namespace 开启二级缓存 -->
	<cache />
</mapper>
```

> 注意：准备缓存的对象需要实现序列化接口，本类、父类、级联属性都必须实现序列化
>
> 触发将对象写入二级缓存的时机：SQL Session 的 close() 时
>
> commit() : 同样的也会清理二级缓存

> 一般地：在执行增删改时，会清理掉缓存，这样的原因是为了防止脏数据的产生

#### 禁用

``` xml
<select useCache="false"> <!-- 默认为：true -->
</select>
```

#### 清理

> 1. commit()：会清理掉一级和二级缓存
>
>    注意：清理二级缓存时不能是查询自身的 commit()
>
> 2. <select flushCache="true"> ：默认为 false

#### 命中率

> 如：查询 "小明"
>
> 第一次：0% -> 加入缓存
>
> 第二次：50%
>
> 第三次：2/3
>
> 第n次：n-1/n

### 第三方提供的缓存

> 要想整合 第三方提供的缓存 或者是 自定义的缓存 都必须实现 Cache

#### 整合 Ehcache 二级缓存
> Ehcache-core.jar 
>
> mybatis-Ehcache.jar
>
> slf4j-api.jar

> Ehcache.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">
	<!-- 硬盘存储路径：当二级缓存对象超过内存限制时，存入该地址 -->
	<diskStore path="E:\Ehcache" />
	
	<!-- 
	maxElementsInMemory: 内存中缓存对象的个数：1000
	maxElementsOnDisk: 硬盘中缓存对象的个数：1000000
	eternal: 缓存是否永远不过期（失效）
	overflowToDisk: 当内存中缓存的对象个数超过该值，是否转移到硬盘中
	timeToIdleSeconds: 当两次访问时间 超过该值时，缓存对象失效
	timeToLiveSeconds: 缓存对象最长存放的时间（生命周期）
	diskExpiryThreadIntervalSeconds: 设置每隔多长时间通过一个线程来清理硬盘中的缓存
	memoryStoreEvictionPolicy: 当超过缓存对象的最大值时，处理的策略
	LRU: 最近最少使用的对象
	-->
	<defaultCache maxElementsInMemory="1000" 
		maxElementsOnDisk="1000000" eternal="false" overflowToDisk="false"
		timeToIdleSeconds="100" timeToLiveSeconds="100"
		diskExpiryThreadIntervalSeconds="120" memoryStoreEvictionPolicy="LRU">
	</defaultCache>
</ehcache>  
```

> mapper.xml

``` xml
<!-- 使用第三方的缓存 -->
<cache type="org.mybatis.caches.ehcache.EhcacheCache">
    <!-- 单独配置属性，会覆盖 Ehcache.xml 中对应的参数：可以省略 -->
	<property name="maxElementsInMemory" value="2000"/>
</cache>
```

## 逆向工程

> 表、类、接口、mapper.xml 四者密切相关，因此 当知道一个的时候 其他三个应该可以自动生成

### 实现

> mybatis-generator-core.jar
>
> mybatis.jar
>
> 数据库驱动.jar

> 逆向工程配置文件：generator.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
	<!-- 数据库驱动:选择你的本地硬盘上面的数据库驱动包 -->
	<!-- 如果已经将驱动包导入项目则省略 -->
	<!--  
	<classPathEntry
		location="D:\maven-repository\mysql\mysql-connector-java\8.0.11\mysql-connector-java-8.0.11.jar" />
	-->
	<!-- targetRuntime:对应 mybatis 版本 -->
	<context id="DB2Tables" targetRuntime="MyBatis3">
		<commentGenerator>
			<!-- 
				suppressAllComments
				true: 自动生成实体类、SQL映射文件时没有注释
				false: 自动生成实体类、SQL映射文件时有注释
			 -->

			<!-- 自动生成的日期：同上 -->
			<property name="suppressDate" value="true" />
			<property name="suppressAllComments" value="true" />
		</commentGenerator>
		<!--数据库链接信息：URL、用户名、密码 -->
		<jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
			connectionURL="jdbc:mysql://localhost:3306/mydatabase"
			userId="root" password="abc123">
		</jdbcConnection>
		
		<!--
			 forceBigDecimals
			 true: 把数据表中的 DECIMAL 和 NUMERIC 类型解析为 JAVA中的 java.math.BigDecimal 类型
			 false(默认):  把数据表中的 DECIMAL 和 NUMERIC 类型解析为 JAVA中的 Integer 类型
		 -->
		<javaTypeResolver>
			<property name="forceBigDecimals" value="false" />
		</javaTypeResolver>
		
		<!--
			targetProject: 实体类的生成位置
			targetPackage: 实体类所在包的位置
		 -->
		<javaModelGenerator
			targetPackage="edu.fdzc.entiry" targetProject=".\src">
			
			<!-- 
				trimStrings
				true: 对数据库的查询结果进行 trim 操作（去空格）
				false(默认): 不对数据库的查询结果进行 trim 操作
			 -->
			<property name="trimStrings" value="true" />
		</javaModelGenerator>
		
		<!-- 
			targetProject: SQL映射文件的生成位置
			targetPackage: SQL映射文件所在包的位置
		-->
		<sqlMapGenerator
			targetPackage="edu.fdzc.mapper" targetProject=".\src">
			<property name="enableSubPackages" value="true" />
		</sqlMapGenerator>
		
		<!-- 生成动态代理的接口 -->
		<javaClientGenerator type="XMLMAPPER"
			targetPackage="edu.fdzc.mapper" targetProject=".\src">
		</javaClientGenerator>
		
		<!-- 指定数据表 -->
		<table tableName="Person"/>
		<table tableName="PersonCard"/>
	</context>
</generatorConfiguration>
```

> 测试类

``` java
package edu.fdzc.test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;


/**
 * 测试类
 * 
 * @author CAI
 *
 */
public class Tester {
	
	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		
		File file = new File("src/generator.xml"); // 配置文件
		List<String> warnings = new ArrayList<String>(); // 保存警告信息
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(file);
		DefaultShellCallback callback = new DefaultShellCallback(true); // 回调函数
		
		// 逆向工程的核心类
		MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
		generator.generate(null);
	}
}
```

![image-20200621193122018](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200621193122018.png)

