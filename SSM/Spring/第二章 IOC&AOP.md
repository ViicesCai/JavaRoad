## 第二章 IOC&AOP

![image-20210102234130755](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210102234130755.png)

## IOC

> Inversion of Control：控制反转

> 控制：资源的获取方式

> 将创建对象、赋值的方式进行反转（改变），从 `new`、`setXxx()` 反转为从`SpringIOC`容器中`getBean()`得到

+ 主动式：资源由自己手动创建

  ``` java
  Student  {
      JavaCourse java = new JavaCourse();
      HtmlCourse html = new HtmlCourse();
  }
  ```
  
+ 被动式：资源无需手动创建，交由容器创建和管理

### 容器

![image-20200612201825466](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612201825466.png)                                                

> 管理所有的组件（功能类）即：若`Student`受容器管理，`JavaCourse和HtmlCourse`也受容器管理；容器可以自动探查处哪些组件（类）需要用到另一些组件（类）；容器创建`JavaCourse和HtmlCourse`对象，并将其赋给`Student`

#### 实现方式

> 在通过`IOC容器`读取`Bean`的实例之前，需要先将`IOC容器`本身实例化

##### BeanFactory

> `BeanFactory`是接口，提供了`IOC容器`最基本的形式，给具体的`IOC容器`的实现提供了规范，面向`Spring`

##### ApplicationContext

> `BeanFactory`的子接口，提供更多高级特性。面向`Spring使用者`

![image-20210128095617041](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210128095617041.png)

+ 主要实现类
  1. `ClassPathXmlApplicationContext`对应类路径下的XML格式的配置文件
  2. `FileSystemXmlApplicationContext`对应文件系统中的XML格式的配置文件
  3. 在初始化时就创建单例的`bean`，也可以通过配置的方式指定创建的`Bean`是多实例的
+ `ConfigurableApplicationContext`
  1. `ApplicationContext`的子接口，包含一些扩展方法
  2. `refresh()`和`close()`让`ApplicationContext`具有启动、关闭和刷新上下文的能力
+ `WebApplicationContext`
  1. 专门为WEB应用而准备的，它允许从相对于WEB根目录的路径中完成初始化工作

### DI

> Dependency Injection：依赖注入
>
> 在创建类的过程中为属性赋值

> `IOC`的具体实现：容器能知道那个组件（类）在运行时，需要另外一个类；容器通过反射的形式，将容器中准备好的`JavaCourse和HtmlCourse`对象注入（利用反射给属性赋值）到`Student`中

> `IOC`是`bean`的注册，`DI`是`bean`的初始化
>
> 只要容器管理的组件，都能使用容器提供的功能
>
> 总结：无论要获取任何对象，都可以直接从`SpringIOC`容器中获取，而不需要自己操作

#### 获取Bean

> 1. `ApplicationContext(IOC容器接口)`
> 2. 给容器中注册一个组件（`Bean`）：组件的创建工作由容器完成，容器中的对象在容器被创建时都创建了
> 3. 同一个组件在`IOC容器`中都是单例的，仅会被创建一次
> 4. 若容器中不存在某个组件，尝试获取该组件时报异常：`org.springframework.beans.factory.NoSuchBeanDefinitionException`
> 5. `IOC容器`在创建这个组件对象时，`property`会利用`setter`方法为`JavaBean`的属性进行赋值
> 6. `JavaBean`的属性由`getter/setter`的方法决定的，所有的`getter/setter`建议都由编译器自动生成

##### 通过类型获取

``` java
// ApplicationContext：代表ioc容器
// ClassPathXmlApplicationContext：当前应用的xml配置文件在ClassPath下
// 根据Spring的配置文件得到IOC容器对象
// 此种方式获取Bean，只允许拥有一个此种类型的Bean，否则报此异常：
// org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type
ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
// 使用容器创建的对象
Person bean = (Person) ioc.getBean(Person.class);
```

##### 通过name获取

``` java
// name对应ioc.xml中bean的id
ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");
Person bean = (Person) ioc.getBean("person");
```

#### 注入方式

> 必须存在无参构造方式

+ id：唯一标识符
+ class：指定类型(填写全类名)
+ property：该 class 所代表的类的属性
  + name：属性名
  + value：属性值
    + 简单类型：（基本数据类型 + String）
    + 对象类型（引用类型）：ref = "需要引用的id值"

##### 通过setXxx()赋值

``` xml
<bean id="person01" class="edu.fdzc.beans.Person">
    <!--
		使用Property标签为Person对象的属性赋值
        name="lastName"：指定属性名
        value="张三"：为这个属性赋值
	-->
    <!-- 对应：setLastName(String lastName) -->
    <property name="lastName" value="张三"/>
    <property name="age" value="20"/>
    <property name="email" value="zs@fdzc.edu"/>
    <property name="gender" value="男"/>
</bean>
```

##### 通过构造器赋值

``` xml
<!-- 调用有参构造函数进行创建并赋值 -->
<bean id="person02" class="edu.fdzc.beans.Person">
    <constructor-arg name="lastName" value="李四"/>
    <constructor-arg name="age" value="21"/>
    <constructor-arg name="email" value="ls@fdzc.edu"/>
    <constructor-arg name="gender" value="女"/>
</bean>
<!-- name可以省略，但必须按照有参构造函数的参数位置赋值 -->
```

+ 通过索引值指定参数位置

  ``` xml
  <!-- index 指定参数位置 -->
  <bean id="person04" class="edu.fdzc.beans.Person">
      <constructor-arg value="王五"/>
      <constructor-arg value="23"/>
      <constructor-arg value="ww@fdzc.edu" index="3"/>
      <constructor-arg value="男" index="2"/>
  </bean>
  ```

+ 通过类型不同区分重载的构造器

  > `<constructor-arg value="" type="">`：按照属性的类型赋值
  >
  > `<constructor-arg value="" index="" type="">`：用此种方式区分重载的构造器

+ 匹配机制

  > 自顶向下的，如果第一个构造器匹配则使用，并不会根据其类型自动匹配，如果参数个数一致则匹配

##### P命名空间赋值

> 简单类型：`<bean id="" class="" p:属性名="">`
>
> 引用类型：`<bean id="" class="" p:属性名-ref="">`

``` xml
<!-- 通过P名称空间为Bean赋值 -->
<!-- 名称空间：在xml中名称空间是用来防止标签重复的 -->
<bean id="person06" class="edu.fdzc.beans.Person"
      p:age="18"
      p:email="ll@fdzc.edu"
      p:lastName="李丽"
      p:gender="女">
</bean>
```

##### 使用的值类型

###### 字面量

> 若字面值中包含特殊字符，可以使用`![CDATA[]]>`把字面值包裹起来

###### null

> 使用`value=""`都为`String`类型，即使是空的也不是`null`,必须使用`<null/>`赋空值

``` xml
<bean id="person01" class="edu.fdzc.beans.Person">
    <property name="lastName">
        <!-- 进行复杂赋值 -->
        <null/>
    </property>
</bean>
```

###### 引用类型

``` xml
<bean id="car" class="edu.fdzc.beans.Car">
    <property name="carName" value="BMW"/>
    <property name="color" value="gray"/>
    <property name="price" value="5000000"/>
</bean>

<bean id="person01" class="edu.fdzc.beans.Person">
    <!-- ref：引用外部的bean -->
    <property name="car" ref="car01"/>
</bean>
```

###### 内部bean

> 不需要设置`id`或`name`，只能在该`bean`下使用，不能通过`id`获取该`bean`

``` xml
<bean id="person01" class="edu.fdzc.beans.Person">
    <property name="car">
        <!-- 定义内部bean -->
            <bean class="edu.fdzc.beans.Car">
                <property name="carName" value="Audi"/>
                <property name="color" value="red"/>
                <property name="price" value="1300000"/>
             </bean>
    </property>
</bean>
```

##### 集合属性赋值

###### 列表和数组

> 使用`<list>`配置，且支持嵌套其他集合
>
> set的定义同`<list>`，使用`<set>`

``` xml
<property name="books">
    <!-- books = new ArrayList<Book>(); -->
    <list>
        <!-- list标签中添加每一个元素 -->
        <!-- 内部bean不能被外部获取，仅能在内部使用 -->
        <value>三国演义</value>
        <bean id="book000x" class="edu.fdzc.beans.Book" p:bookName="水浒传"/>
        <ref bean="book01"/>
    </list>
</property>
```

###### map

> 使用`<map>`配置，可以使用多个`<entry>`作为子标签，每个条目包含一个键和一个值
>
> `<key>`表示键，值的类型没有限制，可以通过`<key-ref>`或`<value-ref>`引用外部定义

``` xml
<!-- Map<String, Object> maps -->
<property name="map">
    <!-- map = new LinkedHashMap<>(); -->
    <map>
        <!-- 一个entry代表一个键值对 -->
        <entry key="key01" value="张三"/>
        <entry key="key02" value="18"/>
        <entry key="key03" value-ref="book01"/>
        <entry key="key04">
            <!-- 定义内部bean -->
            <bean class="edu.fdzc.beans.Car">
                <property name="carName" value="BMW"/>
            </bean>
        </entry>
        <!--
			支持嵌套
			<entry key="key05">
				<map></map>
			</entry>
		-->
    </map>
</property>
```

###### properties

> 使用多个`<prop>`作为子标签，每个`<prop>标`签必须定义`key`属性

``` xml
<property name="properties">
    <!-- properties = new properties(); 所有key-value都是String类型-->
    <props>
        <!-- k=v都是String类型：值直接写在标签体中 -->
        <prop key="userName">root</prop>
        <prop key="passWord">123456</prop>
    </props>
</property>
```

###### 集合类型的bean

> 使用`<util>`创建集合类型的bean，供其他`bean`引用

``` xml
<!-- 相当于new LinkedHashMap<>(); -->
<util:map id="myMap">
    <!-- 添加元素 -->
    <entry key="key01" value="张三"/>
    <entry key="key02" value="18"/>
    <entry key="key03" value-ref="book01"/>
    <entry key="key04">
        <bean class="edu.fdzc.beans.Car">
            <property name="carName" value="BMW"/>
        </bean>
    </entry>
</util:map>

<!--
	内容组成：四个元素
    [], Person, 12, {}
-->
<util:list id="myList">
    <list/>
    <bean class="edu.fdzc.beans.Person"/>
    <value>12</value>
    <ref bean="myMap"/>
</util:list>

<!-- util名称空间创建集合类型的Bean：方便他人引用 -->
<bean id="person02" class="edu.fdzc.beans.Person">
    <property name="map" ref="myMap"/>
</bean>
```

##### 级联属性赋值

``` xml
<!-- 级联属性 -->
<bean id="person03" class="edu.fdzc.beans.Person">
    <!-- 为car赋值时，并改变其价格 -->
    <property name="car" ref="car01"/>
    <property name="car.price" value="900000"/>
</bean>
```

#### 通过工厂创建Bean

> 工厂模式：工厂帮我们创建对象：有一个专门帮我们创建对象的类，这个类就是工厂

##### 静态工厂

> 调用静态工厂方法创建bean是将对象创建的过程封装到静态方法中
>
> 工厂本身不用创建对象；通过静态方法调用，`工厂类.工厂方法名()`

``` xml
<!--
	静态工厂（不需要创建工厂实例）
	class：指定静态工厂全类名
	factory-method：指定工厂方法
	constructor-arg：为方法传参
-->
<bean id="airPlane01" class="edu.fdzc.factory.AirPlaneStaticFactory"
      factory-method="getAirPlane">
    <!-- 为方法指定参数 -->
    <constructor-arg value="CAI"/>
</bean>
```

``` java
import edu.fdzc.beans.AirPlane;

/**
 * 静态工厂
 *
 * @author CAI
 * @time 2021/1/7
 */
public class AirPlaneStaticFactory {

    public static AirPlane getAirPlane(String captain) {
        System.out.println("静态工厂启动");
        AirPlane airPlane = new AirPlane();
        airPlane.setCaptain(captain);
        airPlane.setEngine("V8");
        airPlane.setSubCaption("Jack");
        airPlane.setCapacity(300);
        airPlane.setWingLen("200.00cm");

        return  airPlane;
    }
}
```

##### 实例工厂

> 将对象的创建过程封装到另外一个对象实例的方法里
>
> 工厂本身需要创建对象，需要创建工厂类，通过工厂类对象的方法获得对象

``` xml
<!--
	实例工厂
	factory-bean：配置实例工厂对象
	factory-method：配置使用的工厂方法
-->
<!-- 配置工厂类实例的bean -->
<bean id="airPlaneInstanceFactory" class="edu.fdzc.factory.AirPlaneInstanceFactory"/>
<!-- 在factory-method属性里指定该工厂方法的名称 -->
<bean id="airPlane02" class="edu.fdzc.beans.AirPlane"
      factory-bean="airPlaneInstanceFactory"
      factory-method="getAirPlane">
    <!-- 使用 construtor-arg 元素为工厂方法传递方法参数 -->
    <constructor-arg value="Kay"/>
</bean>
```

``` java
import edu.fdzc.beans.AirPlane;

/**
 * 实例工厂
 *
 * @author CAI
 * @time 2021/1/7
 */
public class AirPlaneInstanceFactory {

    public AirPlane getAirPlane(String captain) {
        System.out.println("实例工厂启动");
        AirPlane airPlane = new AirPlane();
        airPlane.setCaptain(captain);
        airPlane.setEngine("V8");
        airPlane.setSubCaption("Jack");
        airPlane.setCapacity(300);
        airPlane.setWingLen("200.00cm");

        return  airPlane;
    }
}
```

##### FactoryBean

> `Spring`中有两种类型的`bean`，一种是`普通bean`，另一种是`工厂bean`，即`FactoryBean`

> `FactoryBean`：是`Spring`规定的一个接口：`org.springframework.beans.factory.FactoryBean`
>
> 只要是这个接口实现的类，`Spring`都认为是一个工厂且在`IOC容器`启动时不会自动创建
>
> 其返回的对象不是指定类的一个实例，其返回的是该`工厂bean`的`getObject`方法所返回的对象

![image-20210128115844848](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210128115844848.png)

``` xml
<bean id="myFactoryBeanImple" class="edu.fdzc.factory.MyFactoryBeanImple"/>
```

``` java
import edu.fdzc.beans.Book;
import org.springframework.beans.factory.FactoryBean;

import java.util.UUID;

/**
 * 实现FactoryBean接口的类为Spring的工厂类
 * Spring会自动调用工厂方法创建的实例
 *
 * @author CAI
 * @time 2021/1/7
 */
public class MyFactoryBeanImple implements FactoryBean<Book> {

    /**
     * 工厂方法：返回创建对象：Spring自动调用
     */
    @Override
    public Book getObject() throws Exception {
        System.out.println("FactoryBeanImple创建对象");
        Book book = new Book();
        book.setBookName(UUID.randomUUID().toString());
        return book;
    }

    /**
     * 工厂方法：返回创建的对象的类型：Spring自动调用该方法来确认创建对象的类型
     */
    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    /**
     * 返回的对象是否是单例：false：不是，true：是
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
```

#### Bean高级配置

##### 配置信息的继承

> `Spring`允许继承`bean`的配置，被继承的`bean`称为`父bean`，继承这个`父bean`的`bean`称为`子bean`
>
> `子bean`从`父bean`中继承配置，包括`bean`的属性配置
>
> `子bean`也可以覆盖从`父bean`继承过来的配置

``` xml
<!-- 通过继承实现bean配置信息的重用 -->
<!-- abstract="true"：这个bean的配置是抽象的，不能获取它的实例，其只能被继承 -->
<bean id="person04" class="edu.fdzc.beans.Person" abstract="true">
    <property name="lastName" value="CAI"/>
    <property name="gender" value="男"/>
    <property name="age" value="22"/>
    <property name="email" value="viicescai@outlook.com"/>
</bean>

<!-- parent：指定当前 bean 的配置信息继承于哪个 -->
<bean id="person05" class="edu.fdzc.beans.Person" parent="person04">
    <property name="lastName" value="Viices"/>
</bean>
```

##### bean间的依赖

> 有的时候创建一个`bean`的时候需要保证另外一个`bean`也被创建，这时我们称前面的`bean`对后面的`bean`有依赖

```xml
<!-- 按照配置顺序创建Bean：car -> person -> book -->
<!-- Bean之间的依赖 -->
<!-- depends-on：改变Bean的创建顺序 -->
<!-- book先于person创建 -->
<bean id="car" class="edu.fdzc.beans.Car" depends-on="book, person"/>
<bean id="person" class="edu.fdzc.beans.Person"/>
<bean id="book" class="edu.fdzc.beans.Book"/>
```

##### bean的作用域

> 在`Spring`中，可以在`<bean>`元素的`scope`属性里设置`bean`的作用域，以决定这个`bean`是单实例的还是多实例的

###### 单实例(Singleton)

> 默认为单实例，在`SpringIOC`中仅会存在一个`Bean`实例
>
> 在容器启动之前就已经创建好对象，保存在容器中

``` xml
<bean id="book" class="edu.fdzc.beans.Book" scope="singleton"/>
```

###### 多实例(prototype)

> 容器启动默认不会创建多实例的`bean`
>
> 获取`bean`时，才会被创建
>
> 每次获取`bean`都会创建新的实例对象

###### request

> 在web环境下，同一次请求会创建一个`bean`实例

###### session

> 在web环境下，同一次会话会创建一个bean实例

##### bean的生命周期

> `Spring IOC`容器可以管理`bean`的生命周期，`Spring`允许在`bean`生命周期内特定的时间点执行指定的任务
>
> 生命周期即`bean`的创建到销毁的过程

+ 单实例

  > 容器启动时就会创建好，容器关闭也会销毁创建的`bean`
  >
  > (容器启动)构造器 -> 初始化方法 -> (容器关闭)销毁方法

+ 多实例

  > 获取的时候才创建
  >
  > 获取bean -> 初始化方法 -> 容器关闭不会调用销毁方法

+ 后置处理器

  > (容器启动)构造器 -> 后置处理器before ->初始化方法 -> 后置处理器after -> (容器关闭)销毁方法

###### 管理过程

1. 通过构造器或工厂方法创建bean实例
2. 为bean的属性设置值和对其他bean的引用
3. 调用bean的初始化方法
4. bean可以使用了
5. 当容器关闭时，调用bean的销毁方法

###### 配置

> 可以为`bean`自定义一些生命周期的方法；`spring`在创建或销毁时就是调用指定方法自定义初始化方法和销毁方法且必须是无参的

``` xml
<!--
	创建带有生命周期方法的bean
	生命周期：bean的创建到销毁
-->
<bean id="book" class="fdzc.beans.Book"
          init-method="myInit"
          destroy-method="myDestory" />
```

``` java
/**
 * @author CAI
 * @time 2021/1/3
 */
public class Book {
    private String bookName;
    private String author;

    public void myInit() {
        System.out.println("Book初始化方法");
    }

    public void myDestory() {
        System.out.println("Book销毁方法");
    }

    public Book() {
        super();
        System.out.println("Book被创建");
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
```

###### 后置处理器

> `BeanProcessor`：`Spring`有一个接口：后置处理器：可以在`bean`的初始化前后调用方法
>
> `bean后置处理器`对`IOC容器`里的`所有bean实例`逐一处理，而非单一实例；其典型应用是：检查`bean属性`的正确性或根据特定的标准更改`bean的属性`
>
> 需要实现`org.springframework.beans.factory.config.BeanPostProcessor`接口
>
> 在初始化方法被调用前后，`Spring`将把每个`bean`实例分别传递给上述接口的以下两个方法
>
> **无论bean是否有初始化方法；后置处理器都会默认其有，还会继续工作**

+ `postProcessBeforeInitialization(Object, String)`
+ `postProcessAfterInitialization(Object, String)`

``` xml
<!--
	bean的后置处理器：BeanProcessor
-->
<bean id="beanPostProcessor" class="fdzc.beans.MyBeanProcessor"/>
```

``` java
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器类
 * 配置过程：
 *  1.编写后置处理器的实现类
 *  2.将后置处理器注册在配置文件中
 *
 * @author CAI
 * @time 2021/1/7
 */
public class MyBeanProcessor implements BeanPostProcessor {

    /**
     * 初始化之前调用
     *
     * @param bean 将要初始化的Bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[" + beanName +"] bean将要调用初始化方法，这个bean是这样：[" + bean + "]");

        return bean;
    }

    /**
     * 初始化方法之后调用
     *
     * @param bean
     * @param beanName 在XML配置的bean id
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化方法调用结束");

        // 初始化之后返回的bean；返回的是什么容器中保存的就是什么
        return bean;
    }
}
```

> 具体的生命周期

1. 通过构造器或工厂方法创建`bean实例`
2. 为`bean`的属性设置值和对其他`bean`的引用
3. 将`bean`实例传递给`bean`后置处理器的`postProcessBeforeInitialization()`
4. 调用`bean`的`初始化方法`
5. 将`bean`实例传递给`bean`后置处理器的`postProcessAfterInitialization()`
6. `bean`可以使用了
7. 当容器关闭时调用`bean`的`销毁方法`

#### 引用外部文件

> 多用于在`properties`保存数据库基本的连接信息，并使用`Spring`引用这些配置属性

``` properties
jdbc.username=root
jdbc.password=123123
jdbc.jdbcUrl=jdbc:mysql://localhost:3306/test
jdbc.driverClass=com.mysql.cj.jdbc.Driver
```

``` xml
<!--
	数据库连接池作为单实例是最好的；
	一个项目就一个连接池，连接池里面管理很多连接；
	连接是直接从连接池中拿的；
	可以让Spring帮我们创建连接池（管理连接池）
-->
<!-- 加载外部配置文件：classpath：引用类路径下的一个资源 -->
<context:property-placeholder location="classpath:conf/dbconfig.properties"/>
<!-- username是Spring的key中的关键字；为了防止配置文件中的key和spring自己的关键字冲突：建议加上前缀 -->
<!-- Spring中的username表示主机名 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="user" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
    <property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
    <property name="driverClass" value="${jdbc.driverClass}"/>
</bean>
```

#### 自动装配

> 手动装配：以`value`或`ref`的方式`明确指定属性值`都是手动装配即`手动赋值`
>
> 自动装配：根据指定的装配规则，**不需要明确指定**，Spring**自动**将匹配的属性值**注入**bean中即自动赋值

##### 装配模式

###### 不自动装配

> `autowire = "default"or"no"`：不自动装配：不自动为car属性赋值

###### 根据名称

> `private Car car`：
>
> 以属性名（car）作为id去容器中找到这个组件，为其赋值；找不到则赋值为`null`
>
> 例：`car = ioc.getBean("car")`;

###### 根据类型

> `private Car car`：
>
> 以属性的类型作为查找依据去容器中找到这个组件；如果**同种类型存在多个**，则报错；找不到则赋值为`null`
>
> 例：`car = ioc.getBean(Car.class)`;

###### 根据构造器

> `public Person(Car car)`：
>
> 按照构造器进行赋值先按照有参构造器参数的类型进行装配，匹配成功则赋值；找不到则赋值为`null`
>
> 如果按照类型匹配到了多个；**参数的名称**作为`id`继续匹配；匹配成功则赋值；找不到则赋值为`null`**不会报错**
>
> 当`bean`存在多个构造器会非常复杂，不推荐使用

``` xml
<bean id="person" class="fdzc.beans.Person" autowire="byType"/>

<!-- List<Book> books：容器可以把容器中的所有book封装成list赋值给这个属性，故根据类型自动装配不会报错 -->
<bean id="book01" class="fdzc.beans.Book">
    <property name="bookName" value="book01"/>
</bean>
<bean id="book02" class="fdzc.beans.Book">
    <property name="bookName" value="book02"/>
</bean>
<bean id="book03" class="fdzc.beans.Book">
    <property name="bookName" value="book03"/>
</bean>
```

> 通常不使用xml配置自动装配

#### SpEL

> 即：Spring Expression Language(Spring表达式语言)

##### 基本语法

> 使用`#{…}`作为定界符，所有在大框号中的字符都将被认为是`SpEL表达式`

##### 使用基本数据类型

+ 整数

  ``` xml
  <property name="count" value="#{5}"/>
  ```

+ 小数：

  ``` xml
  <property name="frequency" value="#{89.7}"/>
  ```

+ 科学计数法：

  ``` xml
  <property name="capacity" value="#{1e4}"/>
  ```

+ String类型的字面量可以使用单引号或者双引号作为字符串的定界符号

  ``` xml
  <property name=“name” value="#{'Chuck'}"/>
  <property name='name' value='#{"Chuck"}'/>
  ```

+ Boolean：

  ``` xml
  <property name="enabled" value="#{false}"/>
  ```

##### 使用运算符

1. 算术运算符：+、-、*、/、%、^
2. 字符串连接：+
3. 比较运算符：<、>、==、<=、>=、lt、gt、eq、le、ge
4. 逻辑运算符：and, or, not, |
5. 三目运算符：判断条件?判断结果为true时的取值:判断结果为false时的取值
6. 正则表达式：matches

``` xml
<property name="age" value="#{12 * 5}"/>
```

##### 使用其他bean

``` xml
<property name="car" value="#{car}"/>
```

##### 使用其他bean的属性

``` xml
<property name="lastName" value="#{book01.bookName}"/>
```

##### 调用非静态方法

``` xml
<property name="gender" value="#{book01.getBookName()}"/>
```

##### 调用静态方法

``` xml
<!--
	调用静态方法：
	例：UUID.randomUUID().toString();
	#{T(全类名).静态方法名(1, 2)}
-->
<property name="email" value="#{T(java.util.UUID).randomUUID().toString().substring(0, 5)}"/>
```

#### 通过注解配置自动装配

> 相对于使用`xml`配置，更加简便
>
> 通过给`bean`上添加某些注解，可以快速的将`bean`加入`ioc容器`中
>
> 通过注解反别创建`Dao、Service、Controller`（控制器：控制网页跳转逻辑`Servlet`）
>
> 某个类上添加任何一个注解都能快速的将这个组件加入到ioc容器的管理中

##### 四大注解

+ @Controller

  控制器：推荐给控制器层的组件添加

+ @Service

  业务逻辑：推荐给业务逻辑层的组件添加

+ @Repository

  仓库：推荐给数据库层（数据持久化层：dao层）的组件添加

+ @Component

  组件：推荐不属于上面三层的组件添加

###### 规范

> 注解可随意添加：Spring底层不会去验证这个组件，但是推荐各自层使用各自的注解

##### 配置

1. 给要添加的组件使用以上任意一个注解
2. 配置Spring自动扫描添加注解的组件；依赖context名称空间
3. 需要导入AOP包：需要有AOP支持作为前提

``` xml
<!--
	context:component-scan：自动组件扫描
	base-package：指定扫描的基础包；
	把基础包及其下面所有的包的所有加了注解的类自动扫描进IOC容器
-->
<context:component-scan base-package="edu.fdzc">
</context:component-scan>

<!-- resource-pattern属性过滤特定的类 -->
<!-- 只扫描service下的类 -->
<context:component-scan 
	base-package="edu.fdzc" 
	resource-pattern="service/*.class"/>
```

``` java
/**
  * 通过注解将组件添加到IOC容器
  * 使用注解或XML配置加入到容器中的组件行为都是默认一样的
  * 组件的id：默认就是组件的类名首字母小写
  *      修改注解可以更改id：@Repository("bookdao")：即可通过"bookdao"获取
  *
  * 组件的作用域：默认为单例
  *      使用@Scope(value="prototype")：将组件修改为多实例
  */
@Test
public void test() {
    Object bean = ioc.getBean("bookDao");
    Object bean2 = ioc.getBean("bookDao");

    System.out.println(bean == bean2);
}
```

##### 过滤

> 使用`context:exclude-filter`指定扫描时不包含的类
>
> 扫描的时候可以排除一些不需要的组件

+ `type="annotation"`：指定排除规则
  + 按照注解进行排除：即标注了注解的不扫描
  + `expression=""`：注解的全类名
+ `type="assignable"`：指定排除某个具体类，按照类排除
  + `expression=""`：类的全类名
+ `type="aspectj"`：aspectj表达式
+ `type="custom"`：自定义一个TypeFilter
  + 自定义过滤规则
  + 该类必须实现`org.springframework.core.type.filter.TypeFilter`接口
+ `type="regex"`：正则表达式

``` xml
<!-- 过滤@Controller注解的类 --> 
<!-- 可以拥有若干个include-filter和exclude-filter子节点 -->
<context:component-scan base-package="edu.fdzc">
	<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>-
```

##### 包含

> 使用`context:include-filter`指定扫描时要包含的类
>
> 只扫描需要的组件；默认全部扫描
>
> 一定要禁用默认的过滤规则：`use-default-filters="false"`

``` xml
<context:component-scan base-package="edu.fdzc" use-default-filters="false">-->
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>-->
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Service"/>-->
</context:component-scan>
```

##### 自动注入

> 使用`@Autowired`注解实现根据类型实现自动装配
>
> 为某属性使用`@Autowired`：`Spring`会自动的为该属性赋值
>
> 前提：在容器中存在该组件才能使用自动装配

``` java
@Autowired
private BookService bookService;
```

+ 先按照类型去容器中找到对应的组件：`bookService = ioc.getBean("BookService.class)`

  + 若找到则赋值

  + 未找到抛出异常

  + 存在多个同类型：

    + 按照变量名作为`id`继续匹配

      + 匹配则装配

      + 未匹配则报错
  + `@Qualifier`：指定一个名称作为`id`，让`Spring`不使用变量名做为`id`
      + 匹配则装配
      + 未匹配则报错
+ `@Autowired`标注的自动装配的属性默认是一定装配上的
+ 若修改为：`@autowired(require=false)`
  + 匹配则装配
  + 未匹配则装配为`null`
+ 在方法上使用`@Autowired`:
  + 这个方法会在`bean`创建的时候自动运行
  + 这个方法上的每一个参数都会自动装配

> `@Autowired,@Resource,@Inject`：都是自动装配的意思

+ `@Autowired`：`Spring`的注解，最强大
+ `@Resource`：`j2ee`的注解
+ `@Inject`：EJB

> `@Autowired` 和 `@Resource`区别
>
> `@Resource`：扩展性更强；因为是java标准，如果切换成其他框架还是能够使用
>
> `@Autowired`：依赖于Spring框架

##### 泛型依赖注入

> `Spring 4.x`中可以为子类注入子类对应的泛型类型的成员变量的引用

![image-20210130122003368](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210130122003368.png)

``` java
public class BaseRepository<T> {
	
	public void save() {
		System.out.println("Saved by BaseRepository");
	}
}
```

``` java
BaseService
public class BaseService<T> {
	
	@Autowired
	private BaseRepository<T> repository;
	
	public void add() {
		repository.save();
	}
}
```

``` java
@Repository
public class BookRepository extends BaseRepository<User>{
	
	public void save() {
		System.out.println("Saved by BookRepository");
	}
}
```

``` java
@Service
public class BookService extends BaseService<User>{
}
```

``` java
public class User {
}
```

``` java
import edu.fdzc.service.BookService;
import edu.fdzc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author CAI
 * @time 2021/1/13
 */
@ContextConfiguration(locations = "classpath:conf/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IOCTester {

    @Autowired
    BookService bookService;

    @Test
    public void test() {
        bookService.save();
    }
}

// 输出：Saved by BookRepository
```

## AOP

### 出现背景

> 实现一个数学计算器

![image-20210131102815881](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210131102815881.png)

> 要求：
>
> 日志：在程序执行时追踪正在发生的活动
>
> 验证：只能处理正在计算的数

``` java
/**
 * 运算接口
 *
 * @author CAI
 * @time 2021/1/13
 */
public interface Calculator {
    int add(int i, int j);
    int sub(int i, int j);
    int mul(int i, int j);
    int div(int i, int j);
}
```

``` java
import edu.fdzc.inter.Calculator;

/**
 * 数学计算器
 *
 * @author CAI
 * @time 2021/1/13
 */
public class MathCalculator implements Calculator {

    @Override
    public int add(int i, int j) {
        System.out.println("[add] 方法开始执行，参数列表为：[" + i + "," + j +"]");
        int res = i + j;
        System.out.println("结果：" + res);
        System.out.println("[add] 方法执行结果为：" + res);
        return res;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("[sub] 方法开始执行，参数列表为：[" + i + "," + j +"]");
        int res = i + j;
        System.out.println("结果：" + res);
        System.out.println("[sub] 方法执行结果为：" + res);
        return res;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("[mul] 方法开始执行，参数列表为：[" + i + "," + j +"]");
        int res = i + j;
        System.out.println("结果：" + res);
        System.out.println("[mul] 方法执行结果为：" + res);
        return res;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("[div] 方法开始执行，参数列表为：[" + i + "," + j +"]");
        int res = i + j;
        System.out.println("结果：" + res);
        System.out.println("[div] 方法执行结果为：" + res);
        return res;
    }
}
```

#### 存在问题

+ 代码混乱：

  越来越多的非业务需求(日志和验证等)加入后，原有的业务方法急剧膨胀。每个方法在处理核心逻辑的同时还必须兼顾其他多个关注点

+ 代码分散：

   以日志需求为例，只是为了满足这个单一需求，就不得不在多个模块（方法）里多次重复相同的日志代码；如果日志需求发生变化，必须修改所有模块

### 动态代理

> 代理设计模式的原理：使用一个代理将对象包装起来，然后用该代理对象取代原始对象
>
> 任何对原始对象的调用都要通过代理；代理对象决定是否以及何时将方法调用转到原始对象上

![image-20210131104047441](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210131102815881.png)

#### 改进

> 创建动态代理类

``` java
import edu.fdzc.inter.Calculator;
import edu.fdzc.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 运算动态代理类
 *
 * @author CAI
 * @time 2021/1/13
 */
public class CalculatorProxy {

    /**
     * 为传入的对象创建一个动态代理对象
     *
     * @param calculator 被代理的对象
     * @return 动态代理对象
     */
    public static Calculator getProxy(final Calculator calculator) {
        InvocationHandler handler = new InvocationHandler() {

            /**
             * 方法执行器接口
             *
             * @param proxy 代理对象
             * @param method 将要执行的目标对象的方法
             * @param args 传入的参数值
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null; // 返回结果

                try {
                    LogUtils.logStart(method, args);
                    // 利用反射执行目标方法
                    res = method.invoke(calculator, args);
                    LogUtils.logReturn(method, res);

                } catch (Exception e) {
                    LogUtils.logException(method, e);

                } finally {
                    LogUtils.logEnd(method);
                }

                return res;
            }
        }; // 目标方法执行器

        Class<?>[] interdaces = calculator.getClass().getInterfaces(); // 对象实现的所有接口
        ClassLoader loader = calculator.getClass().getClassLoader(); // 被代理对象的类加载器

        // 为目标对象创建代理对象
        Object proxy = Proxy.newProxyInstance(loader, interdaces, handler);
        return (Calculator) proxy;
    }
}
```

> 日志工具类

``` java
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志工具类
 *
 * @author CAI
 * @time 2021/1/13
 */
public class LogUtils {

    /**
     * 日志开始执行
     *
     * @param method 方法
     * @param args 参数数组
     */
    public static void logStart(Method method, Object... args) {
        System.out.println("[" + method.getName() + "] 方法开始执行，参数列表为：" + Arrays.toString(args));

    }

    /**
     * 日志执行结束
     *
     * @param method 方法
     * @param result 返回对象
     */
    public static void logReturn(Method method, Object result) {
        System.out.println("[" + method.getName() + "] 方法正常执行，结果为：" + result);
    }

    /**
     * 日志执行异常
     *
     * @param method 方法
     * @param e 异常
     */
    public static void logException(Method method, Exception e) {
        System.out.println("[" + method.getName() + "] 方法执行出现异常，异常信息为：" + e.getCause());
    }

    /**
     * 日志终止
     *
     * @param method 方法
     */
    public static void logEnd(Method method) {
        System.out.println("[" + method.getName() + "] 终止");
    }
}
```

> 测试

``` java
import edu.fdzc.impl.MathCalculator;
import edu.fdzc.inter.Calculator;
import edu.fdzc.proxy.CalculatorProxy;
import org.junit.Test;

/**
 * @author CAI
 * @time 2021/1/13
 */
public class Tester {

    @Test
    public void test() {
        Calculator proxy = CalculatorProxy.getProxy(new MathCalculator());
        proxy.add(1, 2);
        proxy.mul(2, 5);
    }
}
```

### AOP 概述

> `AOP(Aspect Oriented Programming)`：面向切面编程
>
> 是一种新的方法论，是对传统`OOP(Object-Oriented Programming，面向对象编程)`的补充
>
> 这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程
>
> `AOP`编程操作的主要对象是`切面(aspect)`，而切面模块化横切关注点
>
> 在应用`AOP`编程时，仍然需要定义公共功能，但可以明确的定义这个功能应用在哪里，以什么方式应用，并且不必修改受影响的类；这样一来横切关注点就被模块化到特殊的类里——这样的类我们通常称之为`切面`

#### 优点

+ 每个事物逻辑位于一个位置，代码不分散，便于维护和升级
+ 业务模块更简洁，只包含核心业务代码

#### 概念图

![image-20210131105219246](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210131105219246.png)

#### 术语

1. 横切关注点：即切入点

   从每个方法中抽取出来的同一类非核心业务：切入到哪些类、哪些方法：即在何处执行

2. 切面(Aspect)

   封装横切关注点信息的类，每个关注点体现为一个通知方法：切入到指定类指定方法的某个方法称为切面

3. 通知(Advice)

   切面必须要完成的各个具体工作：通知定义了切面是什么，以及何时使用

4. 目标(Target)

   被通知的对象

5. 代理(Proxy)

   向目标对象应用通知之后创建的代理对象

6. 连接点(JoinPoint)

   横切关注点在程序代码中的具体体现，对应程序执行的某个特定位置

   例如：某个方法调用前、调用后、方法捕获到异常后等

   > 在应用程序中可以使用横纵两个坐标来定位一个具体的连接点

   ![image-20210131110021956](E:\我的坚果云\images\image-20210131110021956.png)

7. 切入点(PointCut)

   定位连接点的方式

   每个类的方法中都包含多个连接点，所以连接点是类中客观存在的事物

   如果把连接点看作数据库中的记录，那么切入点就是查询条件——AOP可以通过切入点定位到特定的连接点

   切点通过`org.springframework.aop.Pointcut`接口进行描述，它使用类和方法作为连接点的查询条件

### AspectJ

> Java社区里最完整最流行的`AOP框架`，可以使用基于`AspectJ注解`或基于`XML配置`的`AOP`

#### 需要的Jar包

+ 基础包：`spring-aspects.jar`
+ 增强包：
  + `aspectjweaver.jar`
  + `cglib.jar`
  + `aopalliance.jar`

#### 配置

> 在`applicationContext.xml`中配置
>
> 当`Spring IOC容器`侦测到bean配置文件中的`<aop:aspectj-autoproxy>`元素时，会自动为与`AspectJ`切面匹配的`bean`创建代理

``` xml
<!-- 开启基于注解的AOP模式 -->
<aop:aspectj-autoproxy/>
```

#### 使用AspectJ注解声明切面

> 要在`Spring`中声明`AspectJ切面`，只需要在`IOC容器`中将切面声明为`bean实例`：对`bean`使用注解
>
> 当在`Spring IOC`容器中初始化`AspectJ`切面之后，`Spring IOC`容器就会为那些与`AspectJ切面`相匹配的`bean`创建代理
>
> 在`AspectJ`注解中，切面只是一个带有`@Aspect`注解的`Java`类，它往往要包含很多通知
>
> 通知是标注有某种注解的简单的`Java`方法

##### AspectJ 注解

> `AspectJ`支持5种类型的通知注解
>
> 告诉`Spring`每个方法何时执行

1. `@Before(前置通知)`：在目标方法之前执行
2. `@After(后置通知)`：在目标方法结束之后执行
3. `@AfterReturning(返回通知)`：在目标方法返回之后执行
4. `@AfterThrowing(异常通知)`：在目标方法抛出异常之后执行
5. `@Around(环绕通知)`：在目标方法前后执行

#### 切入点表达式

>  执行目标方法之前执行：需要写切入点表达式
>
> 切入点表达式固定写法：`execution(访问权限符 返回值类型 方法全类名(参数表))`

##### 通配符

+ > *：

  1. 匹配一个或多个字符：`execution(public int edu.fdzc.impl.Math*r.*(int, int))`:`Mathxxxr`包下的第一和第二个参数为`int`的方法
  2. 匹配任意一个参数：`execution(public int edu.fdzc.impl.Math*r.*(int, *))`：第一个参数为`int`，第二个参数任意
  3. 匹配一层路径：如：`edu.*`即`edu`下的任意包
  4. 权限位置不能使用`*`，不写为默认`public`

+ > ..：

  1. 匹配任意多个参数，任意类型参数
  2. 匹配任意多层路径

+ > &&：同时满足两个表达式

  `execution(...) && execution(...)`

+ > ||：满足任意一个表达式

  `execution(...) || execution(...)`

+ > !：只要不是该位置都切入

  `!execution(...)`

##### 示例

+ `execution(* edu.fdzc.impl.MathCalculator.*(..))`

  > `MathCalculator`中的所有方法
  >
  > 第一个`*`代表任意返回值
  >
  > 第二个`*`代表任意方法
  >
  > `..`匹配任意数量、任意类型的参数
  >
  > 若目标类、接口与该切面类在同一个包中可以省略包名

+ `execution(public * MathCalculator.*(..))`

  > `MathCalculator`接口的所有公有方法  

+ `  execution(public double MathCalculator.*(..))  `

  > `MathCalculator`接口中返回`double`类型数值的方法  

+   `execution(public double MathCalculator.*(double, ..))  `

  > 第一个参数为`double`类型的方法
  >
  > `..`匹配任意数量、任意类型的参数

+   `execution(public double MathCalculator.*(double, double))`

  > 参数类型为`double，double`类型的方法  

+  `execution (* *.add(int,..)) || execution(* *.sub(int, ..))`

  > 任意类中第一个参数为`int类型`的`add方法`或`sub方法  `

![image-20210131184020413](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210131184020413.png)

##### 总结

+ 最模糊的：`execution(* *.*(..))`：任意包任意类任意方法
+ 最精确的：`execution(public int edu.fdzc.impl.MathCalculator.add(int, int))`

##### 重用切入点表达式

> 在编写`AspectJ`切面时，可以直接在通知注解中书写切入点表达式；但同一个切点表达式可能会在多个通知中重复出现
>
> 在`AspectJ`切面中，可以通过`@Pointcut`注解将一个切入点声明成简单的方法；切入点的方法体通常是空的，因为将切入点定义与应用程序逻辑混在一起是不合理的
>
> 切入点方法的访问控制符同时也控制着这个切入点的可见性；如果切入点要在多个切面中共用，最好将它们集中在一个公共的类中
>
> 在这种情况下，它们必须被声明为`public`；在引入这个切入点时，必须将类名也包括在内；如果类没有与这个切面放在同一个包中，还必须包含包名
>
> 其他通知可以通过方法名称引入该切入点

``` java
/**
 * 抽取可重用的切入点表达式：
 *  1.随意声明一个没有实现的返回void空方法
 *  2.为该方法表注：@Pointcut注解
 */
@Pointcut("execution(public int edu.fdzc.impl.MathCalculator.*(..))")
public void myPoint() { }

// 前置通知
@Before("myPoint()")
public static void logStart(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
    Signature signature = joinPoint.getSignature(); // 获取方法签名
    String methodName = signature.getName(); // 获取方法名
    System.out.println("[LogUtils-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
```

  #### JointPoint 连接点

> 如何在通知方法运行时，得到目标方法的详细信息
>
> 为通知方法的参数列表写一个参数：`JoinPoint`：封装了当前目标方法的详细信息
>
> `Spring`对通知方法的要求不严格：
>
> 唯一要求：参数列表不能乱写：通知方法是利用反射调用的，故每次调用需要确定参数表的值

  ![image-20210131184449546](E:\我的坚果云\images\image-20210131184449546.png)

``` java
@Before("execution(public int edu.fdzc.impl.MathCalculator.*(..))")
public static void logStart(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
    Signature signature = joinPoint.getSignature(); // 获取方法签名
    String methodName = signature.getName(); // 获取方法名
    
    System.out.println("[LogUtils-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
}
```

#### 通知

> 在具体的连接点上要执行的操作
>
> 一个切面可以包括一个或者多个通知
>
> 通知所使用的注解的值往往是切入点表达式

``` java
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志工具类（切面类）
 * AOP：如何将这个类的方法（通知方法）动态的在目标方法运行的各个位置切入
 *
 * @author CAI
 * @time 2021/1/13
 */
@Aspect
@Component
public class LogUtils {
    
    @Pointcut("execution(public int edu.fdzc.impl.MathCalculator.*(..))")
    public void myPoint() { }

    // @Before(前置通知)：在目标方法之前执行
    @Before("myPoint()")
    public static void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 获取目标方法的参数列表
        Signature signature = joinPoint.getSignature(); // 获取方法签名
        String methodName = signature.getName(); // 获取方法名

        System.out.println("[LogUtils-前置][" + methodName + "] 方法开始执行，参数列表为：[" + Arrays.toString(args) + "]");
    }

    /**
     * @AfterReturning(返回通知)：在目标方法返回之后执行
     * 目标运行正常执行完成后执行
     *
     * returning = result：告诉Spring使用result接收返回值
     */
    @AfterReturning(value = "myPoint()", returning = "result")
    public static void logReturn(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-返回][" + methodName +"] 方法正常执行，结果为：[" + result + "]");
    }

    /**
     * @AfterThrowing(异常通知)：在目标方法抛出异常之后执行
     * 目标方法出现异常时执行
     *
     * throwing = "exception"：告诉Spring使用exception接收异常信息
     */
    @AfterThrowing(value = "myPoint()", throwing = "exception")
    public static void logException(JoinPoint joinPoint, Exception exception) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-异常][" + methodName + "] 方法执行出现异常，异常信息为：[" + exception + "]");
    }

    /**
     * @After(后置通知)：在目标方法结束之后执行
     * 目标方法结束时执行
     */
    @After("myPoint()")
    public static void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        System.out.println("[LogUtils-后置][" + methodName +"] 终止");
    }   
}
```

##### 环绕通知

> 环绕通知是所有通知类型中功能最为强大的，能够全面地控制连接点，甚至可以控制是否执行连接点
>
> 对于环绕通知来说，连接点的参数类型必须是`ProceedingJoinPoint`：`JoinPoint`的子接口，允许控制何时执行，是否执行连接点
>
> 在环绕通知中需要明确调用`ProceedingJoinPoint`的`proceed()`方法来执行被代理的方法
>
> 如果忘记这样做就会导致通知被执行了，但目标方法没有被执行
>
> 注意：环绕通知的方法需要返回目标方法执行之后的结果，即调用`joinPoint.proceed()`；的返回值，否则会出现空指针异常

``` java
/**
 * @Around：环绕：是Spring中强大的通知
 * @Around：环绕：动态代理
 * @Around(环绕通知)：在目标方法前后执行
 */
@Around("myPoint()")
public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Object[] args = proceedingJoinPoint.getArgs(); // 获取参数列表
    String methodName = proceedingJoinPoint.getSignature().getName(); // 获取方法名

    // 利用反射调用目标方法：即method.invoke(obj, args)
    Object proceed = null;

    try {
        System.out.println("[LogUtils][环绕前置通知] [" + methodName + "方法开始]");
        proceed = proceedingJoinPoint.proceed(args);
        System.out.println("[LogUtils][环绕返回通知] [" + methodName + "方法返回，返回值" + proceed + "]");

    } catch (Exception e) {
        System.out.println("[LogUtils][环绕异常通知] [" + methodName + "] 方法出现异常，异常信息：" + e);

        // 为了让外界感知到异常，需要抛出
        throw new RuntimeException(e);
    } finally {
        System.out.println("[LogUtils][环绕后置通知] [" + methodName + "] 最终结束");
    }
    // 反射调用后的返回值也一定返回出去
    eturn proceed;
}
```

#### 指定切面的优先级

> 在同一个连接点上应用不止一个切面时，除非明确指定，否则它们的优先级是不确定的
>
> 切面的优先级可以通过实现`Ordered`接口或利用`@Order`注解指定
>
> 实现`Ordered`接口，`getOrder()`方法的返回值越小，优先级越高
>
> 若使用`@Order`注解，序号出现在注解中

``` java
@Aspect
@Component
@Order(0)
public class ValidateAspect { }

@Aspect
@Component
@Order(1)
public class LoggingAspect { }
```

#### 使用

``` java
/**
 * AOP测试
 */
@Test
public void test() {
    // 从IOC容器拿到目标对象；注意：如果要使用类型，使用他的接口类型，不要使用本类
    Calculator bean = ioc.getBean(Calculator.class);
    bean.add(1, 2);
    // 获取到的对象是代理对象
    // AOP底层是动态代理，容器中保存的组件的是他的代理对象
    // 目标对象没有实现接口，会自动由CGLib生成一个代理对象:CGLib为没有接口的组件创建代理对象
}
```

#### 执行顺序

``` 
通知方法执行顺序
try {
	@Before
	method.invoke(obj, args);
	@AfterReturning
	
} catch() {
	@AfterThrowing
	
} finally {
	@After
}
```

> 正常执行：`@Before(前置通知) -> @After(后置通知) -> @AfterReturning(返回通知)`
>
> 异常执行：`@Before(前置通知) -> @After(后置通知) -> @AfterThrowing(异常通知)`

``` 
try {
	// 前置通知
	method.invoke(obj, args);
	// 返回通知
	
} catch(e) {
	// 异常通知
	
} finally {
	// 后置通知
}

四合一为环绕通知
环绕通知优先于普通通知执行：
[普通前置]：不固定存在随机性：又可能是前置先执行或环绕前置先执行
{
	try {
		[环绕前置]
		[环绕执行]：目标方法执行
		[环绕返回]
	} catch() {
		[环绕异常]
		
	} finally {
		[环绕后置]
	}
}
	[普通后置]
	[普通方法返回/异常]
```

> 环绕执行：
>
> `(环绕前置/普通前置[随机]) -> 目标方法执行 -> 环绕返回/异常 -> 环绕后置 -> 普通后置 -> 普通返回/异常`

#### 基于注解的AOP

1. 将目标类和切面类都加入到ioc容器中：`@Component`
2. 告诉Spring哪个是切面类：`@Aspect`
3. 在切面类中使用五个通知注解来配置切面中的这些通知方法都何时何地运行
4. 开启基于注解的AOP功能：`<aop:aspectj-autoproxy/>`

#### 基于XML的AOP

> 通过`aop名称空间`中的`XML元素`配置
>
> 常情况下，基于注解的配置要优先于基于`XML`的配置
>
> 通过`AspectJ注解`，切面可以与`AspectJ`兼容，而基于`XML`的配置则是`Spring`专有的
>
> 由于`AspectJ`得到越来越多的`AOP`框架支持，所以以注解风格编写的切面将会有更多重用的机会

> 在`Spring IOC`配置文件中，所有的`Spring AOP`配置都必须定义在`<aop:config>`元素内部
>
> 对于每个切面而言，都要创建一个`<aop:aspect>`元素来为具体的切面实现引用后端`bean`实例
>
> `切面bean`必须有一个标识符，供`<aop:aspect>`元素引用

##### 配置切入点

> 切入点使用`<aop:pointcut>`元素声明
>
> 切入点必须定义在`<aop:aspect>`元素下，或者直接定义在`<aop:config>`元素下
>
> + 定义在`<aop:aspect>`元素下：只对当前切面有效
> + 定义在`<aop:config>`元素下：对所有切面都有效
>
> 基于`XML`的`AOP配置`不允许在切入点表达式中用名称引用其他切入点

##### 配置通知

> 在`aop`名称空间中，每种通知类型都对应一个特定的`XML元素`
>
> 通知元素需要使用`<pointcut-ref>`来引用切入点，或用`<pointcut>`直接嵌入切入点表达式
>
> `method`属性指定切面类中通知方法的名称

``` xml
<!-- 将目标类和切面类都加入到ioc容器中：@Component -->
<bean id="myMathCalculator" class="edu.fdzc.impl.MathCalculator"/>
<bean id="validateAspect" class="edu.fdzc.utils.ValidateAspect"/>
<bean id="logUtils" class="edu.fdzc.utils.LogUtils"/>

<!-- 告诉Spring哪个是切面类 -->
<aop:config>
    <aop:pointcut id="globalPointcut" expression="execution(* edu.fdzc.impl.*.*(..))"/>
    <!-- 指定切面 -->
    <aop:aspect ref="logUtils" order="2">
        <!-- 前置通知 -->
        <aop:before method="logStart" pointcut="execution(* edu.fdzc.impl.*.*(..))"/>
        <!-- 返回通知 -->
        <aop:after-returning method="logReturn" pointcut-ref="globalPointcut" returning="result"/>
        <!-- 异常通知 -->
        <aop:after-throwing method="logException" pointcut-ref="globalPointcut" throwing="exception"/>
        <!-- 后置通知 -->
        <aop:after method="logEnd" pointcut-ref="globalPointcut"/>
</aop:aspect>

    <aop:aspect ref="validateAspect" order="1">
        <!-- 前置通知 -->
        <aop:before method="logEnd" pointcut-ref="globalPointcut"/>
        <!-- 返回通知 -->
        <aop:after-returning method="logReturn" pointcut-ref="globalPointcut" returning="result"/>
        <!-- 异常通知 -->
        <aop:after-throwing method="logException" pointcut-ref="globalPointcut" throwing="exception"/>
        <!-- 后置通知 -->
        <aop:after method="logEnd" pointcut-ref="globalPointcut"/>
    </aop:aspect>
</aop:config>
```

