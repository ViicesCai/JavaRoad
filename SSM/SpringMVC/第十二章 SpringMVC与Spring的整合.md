# 第十二章 SpringMVC与Spring的整合

> 目的：明确分工
>
> SpringMVC的配置文件：配置和网站转发逻辑及网站功能相关的（视图解析器，文件上传解析器，支持`ajax`，`Controller`）
>
> Spring的配置文件：与业务相关（事务控制，数据源,`Service`,`Dao`）

## 配置

> 若`Spring`的`IOC容器`和`SpringMVC`的`IOC容器`扫描的包有重合的部分, 就会导致有的`bean`会被创建 2 次

> 使用`exclude-filter`和`include-filter`子节点来规定只能扫描的注解：确保`Spring`和`SpringMVC`没有重合

> `Spring.xml`

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="edu.fdzc">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
</beans>
```

> `SpringMVC.xml`

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="edu.fdzc" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!-- 视图解析器 -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>
</beans>
```

## Spring IOC 和 SpringMVC IOC的关系

![image-20210222234131436](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20210222234131436.png)

+ `SpringMVC`的`IOC 容器`中的`bean`可以来引用`Spring IOC`容器中的`bean `
+ `Spring IOC`容器中的`bean`却不能来引用`SpringMVC IOC`容器中的`bean`
+ `Spring`可以看成是`SpringMVC`的父容器