# Maven

> Maven 是一个 项目管理工具，主要用于项目构建，依赖管理，项目信息管理

## 作用

+ 管理 Jar 包
  + 增加第三方 Jar 包
  + 管理 Jar 包之间的依赖关系：自动关联下载所有依赖的 Jar 包，并且不冲突
+ 将项目拆分成若干个模块

## 概念

> 基于 Java 平台的自动化构建工具：make-ant-maven-gradle
>
> maven：将原材料（java、js、css、html、图片）-> 产品（可发布项目）

+ 清理：清空编译结果：为重新编译做准备
+ 编译：java -> class
+ 测试：针对于项目中的关键点进行测试；使用项目中的测试代码去测试开发代码
+ 报告：将测试的结果进行显示
+ 打包：将项目的多个文件压缩成一个文件用于安装或部署
+ 安装：将打包好的包放到本地仓库，供其他项目使用
  + 本地仓库：当前主机下的一个目录
+ 部署：将打包好的包放到服务器上准备运行
  + Eclipse中的项目，在部署时（通过Servers add 时）会自动生成一个对应的项目文件夹（部署项目）在 Tomcat 的 `wtpwebapps`
  + 部署项目中没有 src（java 源码）只有编译后的 `.class文件`和`jsp文件`
  + 二者目录结构不同：tomcat 中无法直接运行项目源码
  + 将 eclipse 中的项目 可以 通过 `Export`打包成`.war包`即自动按照 tomcat 目录机构生成该项目，将该包放入`wtpwebapps`即可运行该项目

## 安装与配置

[下载地址](https://mirrors.bfsu.edu.cn/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)

## 配置

> 我的 maven地址：D:\apache-maven-3.6.3

+ 配置系统变量

  ![image-20200608131053621](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608131053621.png)

+ 引入到环境变量 PATH 中

  ![image-20200608131203874](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608131203874.png)

+ 验证：cmd

  ![image-20200608131317276](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608131317276.png)

## 本地仓库

> 打开 maven/conf/setting.xml

+ 默认位置：` ${user.home}/.m2/repository`

### 修改本地仓库

``` xml
<localRepository>D:/maven_local</localRepository> <!-- 修改本地仓库的位置 -->
```

## 使用

> 本地开发原则：约定 优于 配置

### 约定

`默认值`

> 目录结构：
>
> ​	项目
>
> ​	--src
>
> ​		--main:程序功能代码
>
> ​			--java：java 代码
>
> ​			--resource：资源代码、配置代码
>
> ​		--test：测试代码
>
> ​			--java
>
> ​			--resource
>
> ​		pom.xml：项目对象模型

### 配置

``` xml
<groupId>域名反转.项目名</groupId> <!-- 项目在本地仓库的唯一标识 -->
<artifactId>HelloWrold</artifactId> <!-- 子模块 -->
<version>当前版本号</version>

<!-- 通过这三个 能在本地仓库中确定唯一的模块  -->
```

### 依赖

> A.jar 中的某些类需要使用 B.jar 中的某些类，则称为 A 依赖 B
>
> 在 Maven 项目中，如果需要使用一个当时存在的 Jar 或模块，则可以通过依赖实现（去本地仓库、中央仓库中寻找）

```xml
// 依赖的配置 : 例
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.0</version>
    <scope>test</scope> <!-- 依赖的范围、有效性：compile、test、provided -->
</dependency>
```

+ 依赖的范围、有效性

  + Maven在编译、测试、运行项目时，各自使用一套 classpath
  + compile：默认，在编译、测试、运行时可以获取到该 jar
  + test：只有测试时使用得到
  + provided：仅在编译、测试时可以获取得到该 jar

+ 依赖排除

  + A.jar -> B.jar
    + 通过 Maven 引入 A.jar 时，会自动引入 B.jar
    + 若确认未使用到 B.jar 中的包 不想引用，则可以进行依赖排除

  ```xml
  <!-- 排除依赖 -->
  <exclusions>
      <exclusion>
          <groupId></groupId>
          <artifactId></artifactId>
      </exclusion>
  </exclusions>
  ```

+ 依赖的传递性

  + A.jar -> B.jar -> C.jar
  + 则：A.jar -> C.jar
    + 在 Maven 中要满足此关系的前提条件：B.jar -> C.jar：依赖范围为：Compile

## 常见命令

> 执行 mvn：必须在 pom.xml 所在目录中执行

`mvn compile`：编译

+ 本地的仓库中会自动生成（Maven 基础组件，基础 jar）,仅在第一次时（目录中不存在编译需要的基础组件，则会到远程仓库中下载）
+ 只编译 main 目录中的代码

`mvn test`：测试

+ 本地的仓库中会自动生成（Maven 基础组件，基础 jar）
+ 只编译 test 目录中的代码

`mvn package`：打包

+ 将项目打包为 jar/war

`mvn install`：安装

+ 将开发模块放入本地仓库中，供其他模块使用

## 新建 Maven 项目

> 以 eclipse 为例

![image-20200608162040606](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608162040606.png)

![image-20200608162511392](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608162511392.png)

### eclipse 中配置本地仓库

`Preferences - Maven - Installations`

![image-20200608163110459](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608163110459.png) 

![image-20200608163251225](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608163251225.png)

![image-20200608163403892](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608163403892.png)

![image-20200608163638020](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608163638020.png)

### 此时项目下的文件

![image-20200608163751729](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608163751729.png)

### 配置依赖 pom.xml

``` xml
<dependencies>
    <dependency>
  		<groupId>junit</groupId>
  		<artifactId>junit</artifactId>
  		<version>4.0</version>
  		<scope>test</scope>
    </dependency>
</dependencies>
```

![image-20200608170502173](E:\我的坚果云\images\image-20200608170502173.png)

![image-20200608170524779](E:\我的坚果云\images\image-20200608170524779.png)

+ 自动生成

### 执行编译命令

右键点击 `pom.xml`

![image-20200608170731199](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608170731199.png)

## Maven 生命周期

### 生命周期和构建关系

`清理 -> 编译 -> 测试 -> 打包 -> 部署`

+ 执行 测试命令时
  + 实际的执行的顺序为 ： `清理 -> 编译 -> 测试`

#### 生命周期

+ `clean lifecycle`：清理
  + `pre-clean`
  + `clean` 
  + `post-clean`
+ `default lifecycle`：默认
  + `compile`
  + `test-compile`
  + `test`
  + `package`
  + `install`
+ `site lifecycle`：站点
  + `pre-site`
  + `site`
  + `post-site`
  + `site-deploy`

## 整合项目

1. 将 `HelloWorld`放入仓库

   + `run` `HelloWorld`下的`pom.xml`文件

     ![image-20200608175649979](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608175649979.png)

   ![image-20200608175746748](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200608175746748.png)

   ![image-20200612102240563](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612102240563.png)

   + 检查本地仓库，发现该项目已在本地仓库中：成功

2. 配置要使用`HelloWrold`项目的 `pom.xml`

   ``` xml
   <dependencies>
       <!-- 本项目依赖于 HelloWorld -->
     	<dependency>
           <groupId>edu.fdzc.maven</groupId>
     		<artifactId>HelloWrold</artifactId>
     		<version>0.0.1-SNAPSHOT</version>
       </dependency>
   </dependencies>
   ```

   + 更新

     ![image-20200612103312245](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612103312245.png)

   + 在项目中已经可以正常使用：两个项目整合完毕

### 流程总结

> a项目 依赖于 b项目

1. a项目 install 到本地仓库
2. b项目 配置依赖

## 依赖原则

> 防止冲突

1. 路径最短有限原则

   ![image-20200612111211950](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612111211950.png)
   
   + 项目A 传递依赖于 -> `junit4.0`，又直接依赖于 -> `junit3.8`：则根据该原则 项目只会依赖 `junit3.8`
   
2. 路径长度相同

   + 在同一个 `pom.xml`文件中存在两个相同的依赖

     + 越往后声明的依赖的优先级越高，后面的依赖覆盖前面的依赖（禁止在同一个 pom 中声明两个相同的依赖）

   + 不同的`pom.xml`中有两个相同的依赖

     + 先声明的依赖会优先后声明的依赖

     ![image-20200612114422222](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612114422222.png)

     + 项目1中只存在 `junit4.0`

## 统一项目的版本

### 同一版本

> 右键项目 -> Build Path -> Configure -> (需要替换的版本 Remove) -> Add Library -> JRE System Library

![image-20200612115749900](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612115749900.png)

> 也可以通过配置 pom.xml 来同一版本

``` xml
<profile>
    <id>jdk-18</id>
    <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
    </activation>
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
    </properties>
</profile>
```

``` xml
<!-- 同一版本编号 -->
<properties>
    <edu.fdzx.junit.version>4.0</edu.fdzx.junit.version>
</properties>

<!-- 使用 -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>${edu.fdzc.junit.version}</version>
</dependency>
```

## 继承

> 只要项目A 继承 项目B : 则 A 可以使用 B的所有依赖

### 实现

#### 建立父工程

> Packaging：pom：标识这是个父工程

![image-20200612122156419](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612122156419.png)

#### 编写父工程的依赖

``` xml
<!-- 父工程依赖 -->
<dependencyManagement>
  	<dependencies>
        <dependency>
            ...依赖
		</dependency>
  	</dependencies>
</dependencyManagement>
```

#### 继承父工程

> 在子工程的 pom.xml 配置

``` xml
<!-- 继承父工程 -->
<parent>
  	<!-- 加入父工程坐标：gav -->
  	<groupId>edu.fdzc.maven</groupId> <!-- 如果与子工程相同：子工程可以不写：groupId -->
  	<artifactId>FatherMaven</artifactId> 
  	<version>0.0.1-SNAPSHOT</version> <!-- 如果与子工程相同：子工程可以不写：version -->
  	
  	<!-- 子工程：pom.xml 到 父工程：pom.xml 之间的相对路径  -->
  	<relativePath>../FatherMaven/pom.xml</relativePath> <!-- 两个项目都在同一工作区：参考 -->
</parent>
```

> |---WorkSpace
>
> ​	|---FatherMaven
>
> ​	|---SonMaven	

#### 使用父类的依赖

``` xml
<dependencies>
  	<dependency>
  	<!-- 声明：需要使用到父类的 junit -->
  	<groupId>junit</groupId>
  	<artifactId>junit</artifactId>
  	</dependency>
</dependencies>
```

## 聚合

> 执行项目依赖时，必须先将项目安装到本地仓库中，才能够生效，这部分需要手工一个个执行，当项目体量大时，就显得过于繁琐了

> 聚合：能帮助我们批量的进行这些操作:即 一键打包

### 使用

> 配置 modules：只能在总工程（packaging：pom的项目）的 pom.xml 中

``` xml
<!-- 配置聚合 -->
<modules>
  	<!-- 项目的根路径 ：顺序任意-->
  	<module>../项目1</module>
  	<module>../项目2</module>
</modules>
```

## 特性总结

> Maven：将一个大工程拆分为若干个子工程（子模块）
>
> 聚合：可以将拆分的多个子工程合起来
>
> 继承：统一管理版本依赖的版本号

## 应用

> 部署一个 WEB 项目

![image-20200612141409914](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612141409914.png)

> 创建完之后如果出现：pom.xml 报错（我这里灰色的原因是我配置过了）

![image-20200612141522397](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612141522397.png)

> 配置 Tomcat：Build Path -> Add Library -> Server Runtime -> TomcatXXX

![image-20200612141827137](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612141827137.png)

> 添加 Servlet 依赖：pom.xml 

``` xml
<dependencies>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>javax.servlet-api</artifactId>
  		<version>3.0.1</version>
  		<scope>provided</scope>
    </dependency>
</dependencies>
```

> 打包成 war 包

![image-20200612142859960](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612142859960.png)

> 自己编写 jsp 文件测试：略

![image-20200612143640574](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20200612143640574.png)

> 使用 Cargo 实现一键部署：略

