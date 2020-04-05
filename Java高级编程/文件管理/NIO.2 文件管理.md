# NIO.2 文件管理

JDK 7 增强了对文件处理和文件系统特性的支持，以至于我们程他为NIO.2

## 1.NIO.2 

### 功能

+ 高效地处理文件系统

  以一致的方式处理所有的系统文件

+ java.nio.file包以及子包

## 2.如何进行文件操作

### 步骤1：找到文件

java.nio.file.Path

+ 将文件名封装为Path对象

### 步骤2：调用方法完成文件操作

工具类：java.nio.file.Files

+ 封装了处理文件的方法(以Path为参数)

## 3.Path是什么

### java.nio.file.Path 是接口

### 表示文件中的路径

+ 作用：在文件系统中定位与查找

  逻辑地址

+ 路径书写的规范

  如：e:\javacode\lesson01\src\demo.java

+ 组成：由根、目录、分隔符、文件名组成

+ 特征：具有文件系统层次结构的特点

### 路径的多种表现形式

+ 绝对路径

  从根开始：D：\\Java\\demo

+ 相对路径

  没有从根开始：Java\demo

+ .和.. 

  .\Java

  ..\Java

### 路径指向哪些对象

+ 文件

+ 目录：directory

+ 符号链接：symbolic link

  Windows：快捷方式

  ``` java
  // 符号链接的绝对路径
  Path link = Paths.get("D:\\A\\B\\C\\D\\E\\F\\H\\I\\a.mp4"); // symbolic link's absolute path
  // 从符号链接到原始文件的相对路径
  Path relativeSrc = link.getParent().relativize(source); 
  ```

### 一个路径并不意味着其所指的对象并不一定就是物理存在的

## 4.Path的使用

### 如何得到 Path 的实例对象

``` java
// 将文件名封装为 path 对象
// path 可以看成是 file 类的升级版，实际引用的资源也可以不存在
// 方式一
static Path get(URI uri); // 返回指定 uri 对应的 Path 路径
Path path = Paths.get("demo.java");
// 方式二
static Path get(String first, String... more); // 用于将多个字符串串成路径
Path path = Path.get("d:\\", "nio", "nio2");

// 方式三
FileSystem fileSystem = FileSystems.getDefault(); // 得到 FileSystem 对象：FileSystems.getDefault()
Path path = fileSystem.getPath("D:\\", "JavaRoad");

// 方式四
File file = new File("D:\\JavaRoad");
Path path = file.toPath();
```

### 对 Path 的处理

仅仅针对得到 Path 时传入的参数与 Path 的实际路径无关（与 File 一致)

``` java
Path path = Paths.get("D:\\", "JavaRoad\\Java");

Path getParent(); // 返回 Path 对象包含的整个路径，不含 Path 对象指定的文件路径
// 结果：D:\JavaRoad

Path getRoot(); // 返回 Path 对象的根路径
// 结果：D:\

Path getFileName(); // 返回 Path 对象的文件名
// 结果：Java

int getNameCount(); // 返回 Path 对象根路径之后的路径数量（即子路径）
// 结果：2

Path getName(int index); // 返回指定 index 处的路径名（从0开始）
file.getName(0); // 结果：JavaRoad

Iterator<Path> iterator(); // 返回此路径的名称元素的迭代器
// 例
Iterator<Path> iterator =  path.iterator();

while (iterator.hasNext()) {
	System.out.println(iterator.next());
}

for (Path path2 : path) {
	System.out.println(path2);
}

// 结果：JavaRoad Java

Path subpath(int beginIndex, int endIndex); // 返回一个相对的 Path ，它是该路径的名称元素的子序列。(包左不包右)
// 例
Path path = Paths.get("D:\\", "JavaRoad\\Java\\Demo1\\Demo2\\Demo3");
System.out.println(path.subpath(1, 3));
// 结果：Java\Demo1

Path resolve(Path other); // 合并两个路径 ，返回合并路径后对应的 Path 对象
// 例
Path path = Paths.get("D:\\", "JavaRoad\\Java\\Demo1\\Demo2");
Path path2 = Paths.get("JavaRoad\\Test");
Path path3 = path.resolveSibling(path2);
// 结果：D:\JavaRoad\Java\Demo1\JavaRoad\Test

int compareTo(Path other); // 比较两个抽象的路径词典。 由此方法定义的排序是提供者特定的(Comparable<Path> 实现该接口，自定义排序)，在默认提供程序的情况下，具体为平台
// 结果：如果参数为此路径的参数为 equal ，则为零，如果此路径在字典上小于参数，则该值小于零，或者如果此路径在字典上大于参数，则该值大于零 

Path toAbsolutePath(); // 返回表示此路径的绝对路径的 Path 对象
// 例
Path path = Paths.get("JavaRoad");
path.toAbsolutePath();
// 结果：某盘:\工作区\项目文件夹\JavaRoad(从存放该文件的根目录开始)

Path toRealPath() throws IOException; // 返回该路径的真实路径，如果对应的文件不存在则抛出异常
Path path = Paths.get("..\\DesignMode"); // 电脑里存在该路径，例如D:\Java\DesionMode
path.toRealPath();
// 结果：D:\Java\DesionMode(返回的路径中不再有 . 或 ..)
```

## 5.Files 工具类

提供一组静态方法，对文件(Path 对象)进行操作

``` java
// 以下大部分操作的特点 都可以参考 file类的操作

Files.createFile(); // 创建文件，文件路径不存在则抛异常
// 与 mkdir，mkdirs 一致
Files.createDirectory(); // 创建目录
Files.createDirectories(); // 创建多个目录
Files.delete(); // 路径不存在则抛异常
Files.deleteIfExists(); // Path 对应的文件/目录存在，则删除
Files.copy(); // 把一个文件从一个地址复制到另一个位置
// 例：Files.copy(sourcePath, destinationPath,StandardCopyOption.REPLACE_EXISTING); 会覆盖已存在的目标文件
Files.move(); // 把一个文件从一个地址移动到另一个位置，同样的 也可以通过上面的方法 覆盖存在的文件
Files.exits(); // 判断文件是否存在
Files.notExists(); // 判断文件是否不存在
Files.isDirectory(); // 判断是否为目录
Files.isSymbolicLink(); // 判断是否是符号链接
Files.size(); // 返回指定文件的大小
Files.isHidden; // 判断文件是否隐藏
Files.getLastModifiedTime(); // 返回文件的最后修改时间

// 默认使用UTF-8,可以通过指定参数：java.nio.charest.Charest
Files.readAllBytes(); // 读取文件中的所有字节
Files.readAllLines(); // 从文件中读取所有行
Files.write(); // 将文本写入文件

// 遍历整个目录文件
walkFileTree(Path, FileVisior<? super Path>); // Path对象是需要遍历的目录，FileVistor则会在每次遍历中被调用

FileVisitor<T>; // 接口：遍历控制器
// 包含四个方法：指定遍历过程中应执行的操作
```

## 6.与文件系统有关的类

### FileSystem 与 FileSystems

+ FileSystem 是抽象类

+ FileSystem 工具类

  文件系统的工厂方法

### FileStore 抽象类

文件系统的存储设备