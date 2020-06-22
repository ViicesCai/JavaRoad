# Spring

[下载地址](https://maven.springframework.org/release/org/springframework/spring/4.3.9.RELEASE/spring-framework-4.3.9.RELEASE-dist.zip)

> 开发 Spring学习中用到的Jar：5个 + 1个（第三方）

![image-20200612162954552](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612162954552.png)

[另一个Jar包下载地址](https://repo1.maven.org/maven2/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar)

> Build Path -> add

## 开发包简介

+ `spring-aop.jar`：开发 AOP 特性时需要的 JAR
+ `spring-beans.jar`：处理 Bean 的 JAR
+ `spring-context.jar`：处理 上下文 的 JAR
+ `spring-core.jar`：Spring核心 JAR(必须)
+ `spring-expression.jar`：Spring 表达式
+ `commons-logging.jar`：第三方提供的日志 JAR

## 配置文件

### 下载插件：代码提示与自动生成配置信息

> eclipse：spring tool suite

> 我的Ecipse版本：4.13.0

[下载地址](https://download.springsource.com/release/STS/3.9.12.RELEASE/dist/e4.13/spring-tool-suite-3.9.12.RELEASE-e4.13.0-win32-x86_64.zip)

[其他版本](https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3)

> 添加插件

![image-20200612164341246](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612164341246.png)

![image-20200612164451910](E:\我的坚果云\images\image-20200612164451910.png)

> 注意：比较非人类的是，插件地址并不仅仅只是将下载好的 zip 包导入就行
>
> 需要修改该zip的名字：
>
> springsource-tool-suite-3.9.12.RELEASE-e4.13.0-updatesite.zip
>
> 我的下载地址为：
>
> http://download.springsource.com/release/TOOLS/update/3.9.12.RELEASE/e4.13/springsource-tool-suite-3.9.12.RELEASE-e4.13.0-updatesite.zip
>
> 请自行参照上面的链接修改该路径下载 zip 包 😁

![image-20200612165731404](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612165731404.png)

> 如果觉得内容太大，勾选带 Spring IDE 即可

### 新建 配置文件

> Spring BeanConfiguration file：通常命名为：applicationContext.xml

### 开发Spring程序（IOC）

``` xml
<!-- applicationContext.xml -->

<!-- Spring IOC容器 -->
<bean id="student" class="edu.fdzc.entity.Student">
    <property name="sno" value="211906601"></property>
 	<property name="sname" value="CAI"></property>
 	<property name="sage" value="22"></property>
</bean>
```

``` java
// Student.java
package edu.fdzc.entity;

public class Student {
	private int sno;
	private String sname;
	private int sage;
	
	public int getSno() {
		return sno;
	}
	
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public int getSage() {
		return sage;
	}
	
	public void setSage(int sage) {
		this.sage = sage;
	}

	@Override
	public String toString() {
		return "[sno=" + sno + ", sname=" + sname + ", sage=" + sage + "]";
	}
	
}
```

``` java
// Test.java

package edu.fdzc.test;

public class Test {
	public static void main(String[] args) {
        
		// Spring 上下文对象
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        // 直接从 SpringIOC容器中获取一个 id 为 student 的对象
		Student student = (Student)context.getBean("student");
		System.out.println(student.toString());
		// 1.省略 new
		// 2.省略对象赋值
	}
}
```

## 特性

### SpringIOC 容器

> 创建对象、给对象的属性赋值

#### 字段含义

+ id：唯一标识符
+ class：指定类型
+ property：该 class 所代表的类的属性
  + name：属性名
  + value：属性值

#### 概念引入

![image-20200612201825466](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612201825466.png)

> IOC:控制反转 = DI:依赖注入
>
> IOC：将创建对象、赋值的方式进行了翻转（改变），从 new、setXxx() 翻转为从 springIOC容器中 getBean() 得到
>
> DI：将属性值注入给了属性（value -> name）,将属性注入 bean（class），将 bean 注入给了 ioc 容器（id）
>
> 总结：无论要获取任何对象，都可以直接从 SpringIOC容器中获取，而不需要自己操作
>
> 执行步骤：
>
> 1.先在 SpringIOC 存放对象并赋值 
>
> 2.通过 getBean() 获取对象与属性

#### 赋值

> 简单类型（基本数据类型 + String）: value
>
> 对象类型（引用类型） : ref="需要引用的id值" 

#### 依赖注入的三种方式

> 前提：必须存在无参构造方法

1. set注入：通过 setXxxx() 赋值

   + `<property></property>`
   + 依赖注入的底层是通过反射实现的

2. 通过构造器赋值

   + `<constructor-arg value="" index="">`:index 代表该属性在构造器中的顺序位置，从零开始
     + Student(String name, String age); name => index = 0
   + `<constructor-arg value="" name="">`：按照属性名赋值
   + `<constructor-arg value="" type="">`：按照属性的类型赋值
   + `<constructor-arg value="" type index="">`：可选的：可以灵活使用
   + 顺序不一致时：请指定 index 再赋值 否则无法成功！

3. P 命名空间注入

   > 开启

   ![image-20200613101748270](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200613101748270.png)

   + 简单类型：`<bean id="" class="" p:属性名="">`
   + 引用类型：`<bean id="" class="" p:属性名-ref="">`
   + 这部分：Eclipse 中有代码提示，相对简单
   + 值得注意的是：必须先引入命名空间

   > 注意：无论是 String 、 Int/short/long 类型，在赋值时都是 value = ""，此种情况下需要配合 name\type 进行区分

4. 注入集合类型

   ``` xml
   <bean id="collectionDemo" class="edu.fdzc.entity.AllCollectionType">
       <!-- 通过 set 方式 -->
       <!-- 此处的类名仅仅是恰巧与标签名一致，二者无关联 -->
    	<property name="list">
           <list>
               <value>Java</value>
    			<value>PHP</value>
    			<value>Golang</value>
    		</list>
       </property>
       
       <property name="array">
           <array>
               <value>面线糊</value>
    			<value>牛肉羹</value>
    			<value>烧肉粽</value>
    		</array>
    	</property>
    		
       <property name="set">
           <set>
               <value>衙口</value>
    			<value>黄金海岸</value>
    			<value>红塔湾</value>
    		</set>
    	</property>
    		
    	<property name="map">
           <map>
               <entry>
                   <key>
                       <value>Name</value>
    				</key>
                   <value>Cai</value>
    			</entry>
    				
    			<entry>
    				<key>
    					<value>Age</value>
    				</key>
    				<value>22</value>
    			</entry>
    				
    			<entry>
    				<key>
    					<value>Interest</value>
    				</key>
    				<value>Dance</value>
    			</entry>
    		</map>
       </property>
    		
    	<property name="props">
    		<props>
    			<prop key="省份">福建</prop>
    			<prop key="城市">福州</prop>
    			<prop key="学校">福州大学</prop>
           </props>
       </property>
       
       <!-- 赋空值的方式 -->
       <property name="">
           <null /> <!-- null -->
       </property>
       
       <property name="">
           <value></value> <!-- 空 -->
       </property>
   </bean>
   ```

   > 也可以使用构造方法对其赋值：方式同上

   ![image-20200613114405155](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200613114405155.png)

5. 自动装配

   >  该方式仅适用于 ref 类型

   ``` xml
   <!-- 自动装配 -->
   <!-- 方式1 --> 
   <!-- autowire="byName" : ref属性=teacher（属性名） = bean : id=teacher Name:实际上是某个 Bean 的 Id -->
   <!-- 自动寻找 其 id 等于 Course类属性名的 Bean-->
   
   <!-- 方式2 -->
   <!-- autowire="byType" : ref属性=teacher（属性名） = bean : class=teacher Type:实际上是某个 Bean 的 class -->
   <!-- 自动寻找 其 class 等于 Course类属性类型的 Bean -->
   <!-- 该方式仅适用于1：1的情况（如果存在两个类型一样但是Id 不同的 bean 则报错） -->
   
   <!-- 方式3 -->
   <!-- autowire="constructor" : ref属性=teacher（属性名） = bean : type=teacher-->
   <!-- 自动寻找 其 class 等于 Course类的构造方法参数类型一致的 Bean -->
   
   <bean id="course" class="edu.fdzc.entity.Course" autowire="byName|byType">
   	<!-- 调用 setXxxx()方法 赋值 -->
   	<property name="cname" value="Java"></property>
   	<property name="chour" value="12"></property>
   		
   	<!-- 将 Teacher 对象注入到 Course 对象中 -->
   	<!--
   	<property name="teacher" ref="teacher"></property>
   	-->	
   </bean>
   
   <bean id="teacher" class="edu.fdzc.entity.Teacher" p:tname="Mark" p:tage="43">
   </bean>
   ```

   > 可以在头文件中 一次性将该 IOC容器的所有 Bean 统一设置成自动装配
   >
   > 自动装配虽然减少了代码量，却降低的代码的可读性，使用时需要慎重

   ``` xml
   <beans ........
          default-autowrite="default">
   </beans>
   ```

#### 使用注解定义 Bean

> 通过注解的形式，将 bean 以及相应的属性值放入 IOC 容器

``` java
package edu.fdzc.dao.impl;

//@Component("studentDao") // = <bean id="studentDao" class="edu.fdzc.dao.impl.StudentImpl"> </bean>
@Repository
public class StudentDaoImp {
	
	public void addStudent(Student student) {
		System.out.println("增加学生...");
	}
}
```

``` java
@Service("studentService") // = <bean id="studentService" class="edu.fdzc.service.impl.ServiceImpl"> </bean>
public class StudentServiceImpl implements IStudentService {
    @Autowired // = <property name="studentDao" ref="xxx" /> 自动装配：byType
	// @Qualifier("xxx")：byName
	IStudentDao studentDao;

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
}
```

``` xml
<!-- 配置扫描器 -->
<!-- Spring 在启动时会根据 base-package 扫描该包中的所有类，查找这些类是否有注解，如果存在 @Component 则将该类加入 IOC容器 -->
<context:component-scan base-package="edu.fdzc.dao.impl, edu.fdzc.service.impl"></context:component-scan>
```

> @Component：涵盖范围大：为了明确每一层的实际功能可以用以下注解
>
> @Reposiroty：dao 层注解
>
> @Service：service 层注解
>
> @Controller：控制器 层注解

### 使用注解实现事务

> 即：声明式事务

#### 依赖的 Jar 包

> spring-tx-4.3.9.RELEASE.jar
>
> mysql-connector-java-8.0.18.jar ：根据自己的数据库选择驱动包
>
> spring-jdbc-**4.3.9.RELEASE.jar
>
> aopalliance-1.0.jar
>
> commons-dbcp-1.4.jar ：连接池使用到的数据源
>
> commons-pool-1.6.jar ：连接池

#### 配置

> 增加 TX 的命名空间

``` xml
<!-- 配置数据库相关信息：支持事务 -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
	<property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
	<property name="url" value="jdbc:mysql://localhost:3306/mydatabase"></property>
	<property name="username" value="root"></property>
	<property name="password" value="abc123"></property>	
	<property name="maxActive" value="10"></property>
	<property name="maxIdle" value="6"></property>
</bean>
	
<!-- 配置事务管理器：txManager -->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>
	
<!-- 增加对事务的支持 -->
<!-- txManager：事务管理器 -->
<tx:annotation-driven transaction-manager="txManager"/>
```

#### 使用

> 在需要成为事务的方法前增加：@Transactional() [详细说明](https://blog.csdn.net/nextyu/article/details/78669997)

``` java
@Transactional(readOnly = false, propagation = Propagation.REQUIRED,
			rollbackFor = {SQLException.class, ArithmeticException.class})
public void addStudent(Student student) {
    studentDao.addStudent(student);
}
```

### AOP

> AOP（Aspect Oriented Programming）称为面向切面编程
>
> 面向对象的特点是继承、多态和封装：针对不同的类设计不同的方法，简化了代码，使类的复用性高了
>
> 但是这样做的同时，也带来了重复性即势必存在相同的代码：或许将其重复的部分提取出来写成一个独立的类，调用之；但是 也就产生了耦合，只要我们对这个独立的类做了修改，与它有关联的类就会受到影响
>
> AOP：这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程

#### 名词

+ 切面：切入到指定类指定方法的某个方法称为切面
+ 切入点：切入到哪些类、哪些方法：即在哪里执行，叫作切入点
+ 通知：切面执行时即：通知定义了切面是什么，以及何时使用
  + 前置通知：在切入点执行前，插入的通知
  + 后置通知：在切入点执行完毕后，插入的通知
  + 异常通知：在切入点抛出异常时，插入的通知
  + 最终通知：在切入点执行完毕时无论结果如何，插入的通知
  + 环绕通知：可以贯穿切入点整个执行过程

#### 实现

> 类 -> "通知" ：实现接口

+ 依赖包：

  + aopaliance.jar 

  + [aspectjweaver.jar](https://repo1.maven.org/maven2/aspectj/aspectjweaver/1.5.3/aspectjweaver-1.5.3.jar)：SpringAOP的底层核心

+ 配置

+ 编写

  + aop：每当执行某个方法前，自动执行一个方法

    + 在 add() 前自动执行 log()
      + add()：业务方法
      + log()：自动执行的通知，即：aop前置通知
  
> 前置通知


    ``` java
  package edu.fdzc.aop;
    
  public class LogBefore implements MethodBeforeAdvice {
      
    @Override
    public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
    	System.out.println("前置通知");
    }
}
    ```

    ```xml
  <!-- 前置通知类 -->
  <bean id="logBefore" class="edu.fdzc.aop.LogBefore">

  <!-- 将方法和通知进行关联 -->
  <aop:config>
      <!-- 配置切入点 -->
      <!-- 通过 or 能配置两个方法的通知 -->
	<aop:pointcut expression="execution(public void edu.fdzc.service.impl.StudentServiceImpl.addStudent(edu.fdzc.entity.Student)) or execution(...)" id="poioncut"/>
    	<!-- 链接切入点和切面的线 -->
  	<aop:advisor advice-ref="logBefore" pointcut-ref="poioncut"/>
  </aop:config>
    ```

    > execution 表达式
      
    > execution(public void edu.fdzc.service.impl.StudentServiceImpl.addStudent(edu.fdzc.entity.Student))
    >
  > void：返回值类型
    >
  > edu.fdzc.service.impl.StudentServiceImpl.addStudent：执行方法：StudentServiceImpl 类中的 addStudent()
    >
  > edu.fdzc.entity.Student：参数类型

  > public * ......：代表任意返回类型

  > public void *(......)：代表任意方法名

  > public void addStudent(..)：任意参数列表

    > \* edu.fdzc.service.impl.StudentServiceImpl.\*.\*(..)：该包中包含的所有方法（不包含子包中的方法）
      
    > \* edu.fdzc.service.impl.StudentServiceImpl..\*.\*(..)：该包中包含的所有方法（包含子包中的方法）

  > 后置通知

  ``` java
  package edu.fdzc.aop;
  
  public class LogAfter implements AfterReturningAdvice {
  
  	@Override
  	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
  		System.out.println("后置通知：目标对象：" + target + ",调用的方法名：" + method.getName() + ",方法的参数个数：" + args.length + "方法的返回值：" + returnValue);
  	}
  }
  ```

  ``` xml
  <!-- 后置通知类 -->
  <bean id="logAfter" class="edu.fdzc.aop.LogAfter">
  </bean>
  
  <!-- 将方法和通知进行关联 -->
  <aop:config>
      <!-- 配置切入点 -->
  	<aop:pointcut expression="execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(edu.fdzc.entity.Student))" id="poioncut"/>
  	<!-- 连接线的另一端 -->
  	<aop:advisor advice-ref="logAfter" pointcut-ref="poioncut"/>
  </aop:config>
  ```

  > 异常通知

  ``` java
  package edu.fdzc.aop;
  
  public class LogException implements ThrowsAdvice {
      
  	public void afterThrowing(Method method, Object[] args, Object target, Throwable ex) {
  		System.out.println("异常通知：目标对象：" + target + ",方法名：" + method.getName() + ",方法的参数：" + args.length + ",异常类型：" + ex.getMessage());
  	}
  }
  ```

  ``` xml
  <!-- 异常通知类 -->
  <bean id="logException" class="edu.fdzc.aop.LogException">
  </bean>
  
  <!-- 将方法和通知进行关联 -->
  <aop:config>
  	<!-- 配置切入点 -->
  	<aop:pointcut expression="execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(edu.fdzc.entity.Student))" id="poioncut"/>
  	<!-- 连接线的另一端 -->
  	<aop:advisor advice-ref="logException" pointcut-ref="poioncut"/>
  </aop:config>
  ```

  > 环绕通知：在目标方法前后、异常发生时、最终等各个地方都可进行通知，是最强大的一个通知，可以获取目标方法的全部控制权（目标方法是否执行、执行之前、执行之后、参数、返回值）
  >
  > 它的优先级最高，其他通知都在它之后
  >
  > 实现底层：拦截器

  ``` java
  package edu.fdzc.aop;
  
  public class LogAround implements MethodInterceptor {
  
  	@Override
  	public Object invoke(MethodInvocation invocation) throws Throwable {
  		
  		try {
  			// invocation.proceed() 之前的代码：前置通知
  			System.out.println("用环绕通知实现前置通知");
  			Object result = invocation.proceed(); // 控制目标方法的执行：接受该方法的返回值
  			// invocation.proceed() 之后的代码：后置通知
  			System.out.println("用环绕通知实现后置通知");
              // 目标方法的一切信息都可以通过 invocation 参数获取到
  			System.out.println("目标对象：" + invocation.getThis() + ",调用方法名：" + invocation.getMethod().getName() + ",方法的参数个数：" + invocation.getArguments() + ",方法的返回值：" + result);
  
  		} catch (Exception e) {
  			// 异常通知
  			System.out.println("用环绕通知实现异常通知");
  		}
          
  		return null;
  }
  ```

  ``` xml
  <!-- 环绕通知类 -->
  <bean id="logAround" class="edu.fdzc.aop.LogAround"></bean>
  
  <!-- 将方法和通知进行关联 -->
  <aop:config>
  	<!-- 配置切入点 -->
  	<aop:pointcut expression="execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(..))" id="poioncut"/>
  	<!-- 连接线的另一端 -->
  	<aop:advisor advice-ref="logAround" pointcut-ref="poioncut"/>
  </aop:config>
  ```

#### 通过注解实现

``` java
package edu.fdzc.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 通过注解实现前置通知
 * 
 * @author CAI
 *
 */
@Component("logAnnotation") // 加入到 bean 中
@Aspect // 声明该类是一个通知
public class LogAspectAnnotation {

	/**
	 * 前置通知方法
	 */
	@Before(value = "execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(..))") // 属性：定义切入点
	public void before(JoinPoint jp) { // JoinPoint:获取目标对象
		System.out.println("[注解---前置通知]：目标对象：" + jp.getTarget() + ",方法名：" + jp.getSignature().getName() + 
				",参数列表：" + Arrays.toString(jp.getArgs()));
	}
	
	/**
	 * 后置通知方法（不写包名的话，范围为所有包的此方法）
	 */
	@AfterReturning(value = "execution(public * addStudent(..))", returning = "returningValue")
	public void afterReturning(JoinPoint jp, Object returningValue) { // JoinPoint:获取目标对象; returingValue:返回值
		System.out.println("[注解---后置通知]：目标对象：" + jp.getTarget() + ",方法名：" + jp.getSignature().getName() + 
				",参数列表：" + Arrays.toString(jp.getArgs()) + ",返回值：" + returningValue);
	}
	
	/**
	 * 环绕通知方法
	 */
	@Around("execution(public * addStudent(..))")
	public void around(ProceedingJoinPoint pjp) {
		// 前置通知
		System.out.println("[注解---环绕通知---前置通知]");
		
		try {
			// 执行方法
			Object result = pjp.proceed();
			
			// 后置通知
			System.out.println("[注解---环绕通知---后置通知]");

		} catch (Throwable e) {
			// 异常通知
			System.out.println("[注解---环绕通知---异常通知]");

		} finally {
			// 最终通知
			System.out.println("[注解---环绕通知---最终通知]");
		}
	}
	
	/**
	 * 异常通知方法
	 */
	@AfterThrowing(pointcut = "execution(public * addStudent(..))", throwing = "e")
	public void exception(JoinPoint jp, NullPointerException e) { // 只捕获特定类型的异常（空指针）
		System.out.println("[注解---异常通知]" + e.getMessage());
	}
	
	/**
	 * 最终通知方法
	 */
	@After("execution(public * addStudent(..))")
	public void after() {
		System.out.println("[注解---最终通知]");
	}
}

```

``` xml
<!-- 开启扫描 -->
<context:component-scan base-package="edu.fdzc.aop">
</context:component-scan>
	
<!-- 开启注解对 AOP 的支持：AOP自动代理 -->
<aop:aspectj-autoproxy>
</aop:aspectj-autoproxy>
```

#### 通过配置实现

> 基于 Schema 实现
>
> 类似：实现接口的方式

``` java
package edu.fdzc.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import sun.net.www.content.image.jpeg;

/**
 * 通过 Schema 实现通知
 * 
 * @author CAI
 *
 */
public class LogSchema {
	
	/**
	 * 前置通知
	 */
	public void before() {
		System.out.println("[Schema-前置通知]");
	}
	
	/**
	 * 后置通知
	 * 
	 * @param jp
	 * @param returnValue
	 */
	public void afterReturning(JoinPoint jp, Object returnValue) {
		System.out.println("[Schema-后置通知]：目标对象：" + jp.getThis() + ",调用的方法名：" + jp.getSignature().getName() + ",方法的参数个数：" + jp.getArgs().length + "方法的返回值：" + returnValue);

	}
	
	/**
	 * 异常通知
	 * 
	 * @param jp
	 * @param e 空指针异常
	 */
	public void whenException(JoinPoint jp, NullPointerException e) {
		System.out.println("[Schema-异常通知]：" + e.getMessage());
	}
	
	/**
	 * 环绕通知
	 * 
	 * @param pjp
	 * @return 目标方法的返回值
	 */
	public Object around(ProceedingJoinPoint pjp) {
		System.out.println("[Schema-环绕通知-前置通知]");
		Object result = null;
		
		try {
			result = pjp.proceed(); // 执行方法
			System.out.println("方法名：" + pjp.getSignature().getName() + ",返回值：" + result);
			System.out.println("[Schema-环绕通知-后置通知]");
			
		} catch (Throwable e) {
			System.out.println("[Schema-环绕通知-异常通知]");
		}

		return result;
	}
}
```

``` xml
<!-- 将 LogSchema 加入 AOP容器 -->
<bean id="logSchema" class="edu.fdzc.aop.LogSchema">
</bean>
	
<aop:config>
	<aop:pointcut expression="execution(public * edu.fdzc.service.impl.StudentServiceImpl.addStudent(..))" id="pcSchema"/>
		
    <!-- 链接切面 -->
	<aop:aspect ref="logSchema">
        <!-- 链接方法 -->
		<aop:before method="before" pointcut-ref="pcSchema" />
		<aop:after-returning method="afterReturning" returning="returnValue" pointcut-ref="pcSchema" />
		<aop:after-throwing method="whenException" pointcut-ref="pcSchema" throwing="e"/>
		<aop:around method="around" pointcut-ref="pcSchema"/>
	</aop:aspect>
</aop:config>
```

## Spring 开发 WEB

> SpringIOC容器 初始化
>
> 1.将 IOC 容器中的所有 Bean 实例化为对象 -> new 对象
>
> 2.将各个 Bean 依赖的属性值注入进去 -> 属性赋值

> Java 中：通过 new ClassPathXmlApplicationContext("applicationContext.xml"); 获取对象

### WEB 如何初始化

> 监听器：监听 Tomcat 启动，一旦启动 立即实例化一个 IOC 容器对象
>
> spring-web.jar : 提供监听器

``` xml
<!-- web.xml -->

<!-- 指定 IOC 容器（applicationContext.xml）的位置 -->
<context-param>
  	<!-- 监听器中用于存放IOC容器地址的容器 -->
  	<param-name>contextConfigLocation</param-name>
  	<!-- 指定IOC容器的位置：classpath = 类路径 -->
  	<!-- 如果 IOC容器 在 WebContent/WEB-INF下且命名为 applicationContext.xml 此无需配置 context-param -->
  	<param-value>classpath:applicationContext.xml</param-value>
</context-param>
  
<listener>
    <!-- 加载监听器:自动初始化IOC容器 -->
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>	
</listener>
```

> context-param：告诉监听器该容器的位置
>
> WEB-INF/applicationContext.xml：默认约定的位置

#### 需要的基本 JAR 包

> spring-aop.jar：开发 AOP 特性时需要的 JAR
>
> spring-beans.jar：处理 Bean 的 JAR
>
> spring-context.jar：处理 上下文 的 JAR
>
> spring-core.jar：Spring核心 JAR(必须)
>
> spring-expression.jar：Spring 表达式
>
> commons-logging.jar：第三方提供的日志 JAR
>
> spring-web.jar : 提供监听器

> 注意：这些包需要放在 WEB-INF/lib 中

### 拆分 Spring 配置文件

#### Java 项目

> 无需拆分，需要哪个用哪个

#### WEB 项目

> 根据三层结构拆分；根据功能结构拆分

+ 三层结构
  + UI：html/css/jsp、Servlet：`applicationController.xml`
  + Service：`applicationService.xml`
  + Dao:`applicationDao.xml`
  + 公共数据库：`applicationDB.xml`
+ 功能结构
  + 学生相关配置：`applicationContextStudent.xml`
  + 班级相关配置：`applicationContextClass.xml`

> 如何将多个配置文件合并

``` xml
<!-- web.xml -->
 <context-param>
     <!-- 监听器中用于存放IOC容器地址的容器 -->
     <param-name>contextConfigLocation</param-name>
     
     <!-- 指定IOC容器的位置：classpath = 类路径 -->
     <!-- 如果 IOC容器 在 WebContent/WEB-INF下且命名为 applicationContext.xml 此无需配置 context-param -->
     <param-value>
         classpath:applicationContext.xml
  		 classpath:applicationContext-Controller.xml
         classpath:applicationContext-Service.xml
  		 classpath:applicationContext-Dao.xml
  	</param-value>
</context-param>
```

```xml
<!-- 方式2：推荐-->
<param-value>
    classpath:applicationContext.xml
  	classpath:applicationContext-*.xml
</param-value>
```

> 方式3

``` xml
<!-- web.xml -->
 <context-param>
     <!-- 监听器中用于存放IOC容器地址的容器 -->
     <param-name>contextConfigLocation</param-name>
     
     <!-- 指定IOC容器的位置：classpath = 类路径 -->
     <!-- 如果 IOC容器 在 WebContent/WEB-INF下且命名为 applicationContext.xml 此无需配置 context-param -->
     <param-value>
         <!-- 加载主配置文件 -->
         classpath:applicationContext.xml
  	</param-value>
</context-param>
```

``` xml
<!-- applicationContext.xml -->
<beans ...>
    <import resource="applicationContext-*.xml" />
</beans>
```

## 项目整合

> 任何与 Spring 相关的框架，都交由 Spring 进行整合

### Spring - Mybatis

> Mybatis 是通过 SqlSessionFactory 来操作数据库
>
> Spring 整合 Mybatis 其实就是将 Mybatis 的 SqlSessionFactory 交给 Spring
>
> SqlSessionFactory -> SqlSession -> StudentMapper -> CRUD

#### 需要的 jar 包

> mybatis-spring.jar、spring-tx.jar、spring-jdbc.jar、spring-expression.jar、spring-context-support.jar、spring-core.jar、spring-context.jar、spring-beans.jar、spring-aop.jar、spring-web.jar、commons-logging.jar、commons-jdbc.jar、ojdbc.jar、mybatis.jar、log4.jar、commons-pool.jar

#### 对应类 - 数据表

> 创建一个 entity 包，用于对应数据库中的数据表

#### Mybatis 配置文件：config.xml

> 具体的配置由 Spring 的 applicationContext.xml 完成了，这部分可以省略

#### 映射文件

> mapper.xml：将 entity 类 与 实际的数据表对应起来

#### 主配置文件

> 未整合之前使用 Mybatis:config.xml -> SqlSessionFacotry
>
> 整合之后 需要通过 Spring 管理 SqlSessionFacotry
>
> 即：产生 SqlSessionFacotry 所需要的数据库信息不再放入 config.xml，而需要放入 Spring 的配置文件中

``` xml
<!-- applicationContext.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- 加载 db.proerties 文件 -->
	<bean id="config" class="org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer">
		<!-- 指定需要加载的文件 -->
		<property name="locations">
			<array>
				<!-- 定位到 db.properties -->
				<value>classpath:db.properties</value>
			</array>
		</property>
	</bean>

	<!-- 配置数据库信息：代替 mybatis 的配置文件：config.xml -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="${driver}"></property>
		<property name="url" value="${url}"></property>
		<property name="username" value="${username}"></property>
		<property name="password" value="${password}"></property>
		<property name="maxActive" value="${maxActive}"></property>
		<property name="maxIdle" value="${maxIdle}"></property>
	</bean>
	
	<!-- 在 Spring AOC 容器中创建 Mybatis 的核心类：SqlSessionFactory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 数据库连接的数据源 -->
		<property name="dataSource" ref="dataSource" /> 
		<!-- 加载 mybatis 配置文件 -->
		<property name="configLocation" value="classpath:config.xml" />
	</bean>
</beans>
```

## 使用

> 使用 Spring-Mybatis 整合产物开发程序

> 目标：通过 Spring 产生 Mybatis 最终操作需要的 动态 Mapper对象(如：StudentMapper)

[项目地址](https://github.com/ViicesCai/JavaRoad/tree/master/SSM/Spring/code/SMProject)