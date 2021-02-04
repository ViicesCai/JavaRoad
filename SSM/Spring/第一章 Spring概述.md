# 第一章 Spring概述

## 概述

1. Spring是一个开源框架
2. Spring为简化企业级开发而生，使用Spring JavaBean就可以实现以前要靠EJB才能实现的功能且配置要比EJB更加优雅简洁
3. Spring是一个IOC(DI)和AOP容器框架
4. Spring的特性：
   + 非侵入式：基于Spring开发的应用中的对象可以不依赖Spring的API
   + 依赖注入：DI，控制反转（IOC）最经典的实现
   + 面向切面编程：AOP
   + 容器：Spring是一个容器，因为它包含并且管理应用对象的生命周期
   + 组件化：Spring实现了简单的组件配置组合成一个复杂的应用，在Spring中可以使用XML和JAVA注解这些对象

5. 一站式：在IOC和AOP的基础上可以整合各种企业级的开源框架和优秀的第三方类库

## 模块划分

> Spring 模块划分图

![image-20210102230247973](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210102230247973.png)

### Test

> Spring的单元测试模块

### Core Container

> 核心容器（IOC）：由四个jar包组成，代表四个功能

+ Beans：spring-beans.jar：Spring 负责创建类对象并管理对象
+ Core：spring-core.jar：核心类
+ Context：spring-context.jar：上下文参数，获取外部资源或管理注解
+ SpEL（Spring表达式）：spring-expression.jar：

### AOP&Aspects

> 面向切面编程模块

+ AOP：spring-aop.jar：实现aop功能需要依赖
+ Aspects：spring-aspects.jar：切面Aop依赖的包

### Data Access/Integration

> 数据访问/集成

+ JDBC：spring-jdbc.jar：Spring对JDBC封装后的代码
+ ORM（Object Relation Mapping 对象关系映射）：spring-orm.jar：封装了持久层框架的代码
+ OXM：spring-oxm.jar
+ JMS：spring-jms.jar
+ Transactions（事务）：spring-tx.jar：声明事务时使用

### Web

> Spring开发web应用的模块

+ WebSocket：spring-websocket.jar
+ Servlet：spring-web.jar：与原生web相关（Servlet）
+ Web：spring-webmvc.jar：用于开发Web项目
+ Portlet：spring-webmvc-portlet.jar：开发Web应用的组件集成

> 通常使用哪个模块就导入哪个包

