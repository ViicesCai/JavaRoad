# 如何在Java中操作数据库

## 1. JDBC

### Java提供的专门操作数据库的API

核心是接口

+ 定义了一套标准，可以统一的方法操作不同品牌的数据库
+ Java API 中的包
  + Java.sql : 数据库的基本操作
  + javax.sql : 数据库的高级操作

## 2. JDBC 驱动程序

### 数据库厂商针对 Java 的标准提供具体的实现

一组实现了接口的类

## 以 jar 包的形式提供

*.jar 文件

## 3. JDBC 编程的准备工作

### 必须有 JDBC 驱动程序

+ 下载 JDBC 驱动程序（jar 包）

+ 使用前，由 JVM 加载驱动程序

  Eclipse：右键当前项目 -> Build Patch -> Add External 将 驱动程序 导入到当前项目中

+ 目标数据库允许远程操作

  + 允许远程访问
  + 得到远程登录的账号
  + 数据库服务器已启动

## 4. JDBC 编程的四个步骤

### 加载并注册数据库驱动：Class.forName（String 类名）

不同厂商的数据库，类名不同

### 与目标数据库建立连接：DriverManager.getConnection（连接参数）

有三个重载方法，不同厂商的数据库，连接参数不同

### 执行 SQL 语句：Statement 的 executeUpdate（String）

### 关闭连接

``` java
// 例：方式1：使用反射获取连接
private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
// 数据库服务器的 IP 地址：端口号；使用的数据库名
private static final String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=tempdb";
private static final String USER = "sa";
private static final String PW = "abc123";
	
public void testConnection1() throws SQLException, ClassNotFoundException {
    // 加载并注册数据库驱动
	Class.forName(DRIVER);
    // 连接数据库
	Connection conn = DriverManager.getConnection(URL, USER, PW);
    // 执行 SQL 语句
	System.out.println(conn);
    // 关闭连接
	conn.close();
}
```

## 5. 带参数的 SQL 语句

PreparedStatement 接口

### 为什么 SQL 语句要带参数

用 String 保存 SQL 语句存在的问题	

+ 不检查 SQL 语法
+ 用字符串拼接产生 SQL 语句时存在的问题
  + 字符型需要补一对单引号，极易出错
  + 注入式攻击

### 如何书写带参数的 SQL 语句

使用 ？ 占位符

### 如何执行带参数的 SQL 语句

PreparedStatement 接口

``` java
Connection conn = null;
PreparedStatement ps = null;
		
try {

	conn = JDBCUtil.getConnection(); // 这是将连接写成了一个工具类
			
    // SQL 语句
	String sql = "update Course set Cname = ? where Cno = ?";
    // 创建带参数 SQL 的 PreparedStatement 对象
	ps = conn.prepareStatement(sql);
			
    // 提供具体数据：setXxxx(int 序号，Xxxx类型的数据); 这里用的是一个 Object 型
	ps.setObject(1, "神雕侠侣");
	ps.setObject(2, "C777");
    
    // 执行语句
	ps.execute();
    
} catch (Exception e) {
    e.printStackTrace();
    
} finally { // 也可以将关闭资源建成一个工具类
    // 关闭资源		
    ps.close();
    conn.close();
}
```

## 6. 如何读取 SELECT 返回的结果

ResultSet 接口

``` java
public void testQuery() {
	Connection conn = null;
	PreparedStatement ps = null;
	// 结果集对象(SQL查询语句 返回的结果)
	ResultSet resultSet = null;
		
	try {
		conn = JDBCUtil.getConnection(); // 建立连接
        // 查询语句
		String sql = "select Cno,Cname,Credit,Semester,Pcno from Course";
        // 创建带参数 SQL 的 PreparedStatement 对象
		ps = conn.prepareStatement(sql);
			
        // 执行 SQL 语句并返回 ResultSet 对象
		resultSet = ps.executeQuery();	
			
        // 从 ResultSet 中读取数据
		while (resultSet.next()) { // 如果结果集的下一个有数据，返回 true
			// 从每一行中读取各个字段
            // Xxxx getXxxx（字段名或序号）
			String cno = resultSet.getString(1);
			String cname = resultSet.getString(2);
			int credit = resultSet.getInt(3);
			int semester = resultSet.getInt(4);
			String pcno = resultSet.getString(5);
				
			// 将数据封装为对象：推荐使用这种方式输出数据
			Course course = new Course(cno, cname, credit, semester, pcno);
			System.out.println(course.toString());
		}
			
	} catch (Exception e) {
			e.printStackTrace();
			
	} finally {
			JDBCUtil.closeResource(conn, ps, resultSet);

	}
}
```

## 7. 小结：JDBC 核心的类和接口

### 一个类，四个接口

+ DriverManager 类
+ Connection 接口
+ Statement 接口
+ Statement 接口
+ PreparedStatement 接口
+ ResutlSet 接口

### 面对对象的设计思想

### SRP：单一职责原则

类的职责要单一，不能将太多的职责放在一个类中

### DIP：依赖倒转原则

要针对抽象层编程，而不要针对具体类编程

## 8. 资源关闭的问题

### 打开的顺序

Connection -> PreparedStament -> ResultSet

### 关闭顺序

与打开相反 

### JDK 1.7 自动释放资源

+ java.lang.AutoCloseable 接口

  只有一个 close() 方法

  + 所有 IO 流都实现了 AutoCloseable 接口
  + JDBC 中的接口是 AutoCloseable 的子接口

+ 如何使用

``` java
// 1.必须声明在 try 语句中声明，才能自动关闭
// 2.多个资源之间用分号分隔
// 多个资源的关闭顺序与声明顺序相反
try (InputStream fis = new FileInputStream(source);
     OutputStream fos = new FileOutputStream(target)) {
    byte[] buf = new byte[8192];
    int i;
    
    while ((i = fis.read(buf)) != -1) {
        fos.write(buf, 0, i);
        }
    }

catch (Exception e) {
    e.printStackTrace();
}
```

## 9. 优化程序

### 定义常量

对特定的字段用常量修饰，如：数据库的地址

### 使用配置文件

储存数据库的相关信息

+ 什么是配置文件？

  + 文本文件
  + *.properties
  + 每行固定的格式：key = value

+ 如何读写配置文件？

  + 步骤1：创建实例对象

    HashTable 的子类

  + 步骤2：load() 方法读取所有 key/value

    load(InputStream in)

  + 步骤3：按 key 得到对应的 value

    String getProperty(String key)

    key 大小写敏感

+ 配置文件的编码问题

  遇到中文时，存在编码问题

### 使用静态代码块

### 封装工具类

设计DBUtils工具类封装数据库连接