# Java 编译结构

```mermaid
graph TD
A[*.Java 源代码] --> B[编译器 Javac.exe]
B --> C[*.class 字节码文件 不是任何CPU的机器语言]
C --> D[Java虚拟机 JVM:java.exe]
C --> E[JVM POWER]
C --> F[JVM SPARC]
D --> G[X86 CPU]
E --> H[POWER]
F --> I[SPARC]
```

# 配置Java开发环境

## 1. 下载JDK

[下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html)

![](C:\Users\Vivi_\Downloads\火狐截图_2019-10-04T08-06-43.588Z.png)

按照自己的系统选择相应的版本。

这里以Win10 64为例子，待JavaSE安装完成后，一键安装。

我的电脑-右键-属性-高级系统设置-环境变量-系统变量-新建

新建变量名：JAVA_HOME，变量值：JDK安装目录

![img](https://images2018.cnblogs.com/blog/1313907/201805/1313907-20180505151930953-913534876.png)

新建变量名：CLASSPATH，变量值：.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\tools.jar

![img](https://images2018.cnblogs.com/blog/1313907/201805/1313907-20180505160714794-1912853159.png)

保存完毕后打开CMD，分别输入java -version；javac -version能正常运行则表示配置成功。

## 2. 安装Eclipse

这里用Eclipse作为开发Java的IDE，IntelliJ IDEA也是不错的开发工具（收费）。

[下载地址](https://www.eclipse.org/downloads/packages/)

# HelloWorld

打开安装好的 Eclipse.exe 点击 File -> New -> Project

选择 Java Project

<img src="C:\Users\Vivi_\AppData\Roaming\Typora\typora-user-images\image-20200215113900135.png" alt="image-20200215113900135" style="zoom: 67%;" />



Project name：输入自定义的项目名称

在新建好的项目中继续新建一个类（Class）

Package 表示一个包名 也就是一个文件夹的名称

<img src="C:\Users\Vivi_\AppData\Roaming\Typora\typora-user-images\image-20200215114251863.png" alt="image-20200215114251863" style="zoom:80%;" />

这里类名为 Tester

```java
public class Tester {
	public static void main(String[] args) {
		System.out.println("Hello,World");
	}
}
```

右键 Run As 或者 Ctrl + 11

即可输出

## Main函数

在Java中，main()方法是Java应用程序的入口方法，也就是说，程序在运行的时候，第一个执行的方法就是main()方法，这个方法和其他的方法有很大的不同，比如方法的名字必须是main，方法必须是public static void 类型的，方法必须接收一个字符串数组的参数等等。