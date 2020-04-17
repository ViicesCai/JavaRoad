# IO流基础

使用

## 1. IO流的四大抽象基类

java.io 包

都属于节点流

### 字节流

适合处理非文本文件

以字节为单位读写，每次一个字节或一组字节

+ 输入流：InputStream 读，是一个抽象类，是所有字节输入流的父类
+ 输出流：OutputStream 写，是一个抽象类，是所有字节输出流的父类

### 字符流

适合处理文本文件

以字符为读写单位，一次一个字符或一组字符

+ 输入流：Reader 读，是一个抽象类，是所有字符输入流的父类
+ 输出流：Writer 写，是一个抽象类，是所有字符输出流的父类

## 2. IO流的基本用法

以字节流为例

### 直接操作文件的字节流

File前缀的节点流

``` java
// 构造方法
// File 只能是文件，不能是目录
// 文本字节输出流（FileInputStream)
// 通过打开与 File 类对象代表的实际文件的链接来创建 FileInputStream 流对象
public FileInputStream(File file) throws FileNotFoundException;
// 通过指定的 name 来创建 File 类对象，而后再与 File 对象所代表的实际路径建立链接创建 FileInputStream 流对象
public FileInputStream(String name) throws FileNotFoundException;

// 文本字节输入流(FileOutputStream)
// 创建 FileOutputStream 流以写入数据到 File 所代表的文件，同时创建一个新的 FileDescriptor 对象来表示与该文件的关联(源码中会new一个该对象)
public FileOutputStream(File file) throws FileNotFoundException;
// 如果第二个参数为true，则字节将写入文件的末尾而不是开头
public FileOutputStream(File file, boolean append) throws FileNotFoundException;
// 创建 FileOutputStream 流以写入数据到 name 所代表的路径的文件，同时创建一个新的FileDescriptor对象来表示与该文件的关联(源码中会new一个该对象)
public FileOutputStream(String name) throws FileNotFoundException;
// 如果第二个参数为true，则字节将写入文件的末尾而不是开头
public FileOutputStream(String name, boolean append) throws FileNotFoundException;
```

### IO 流的使用步骤

#### 1.创建源：确定要读写的文件

+ 读文件：文件必须存在，否则抛异常

+ 写文件：

  + 文件不存在，创建

  + 文件已存在

    + 覆盖：默认
    + 追加：append = true

  + 如果路径不存在，抛异常

    构造方法传入的文件名包含路径

#### 2.选择流：创建指向文件的 IO 流

#### 3.操作：read或write

#### 4.释放资源：关闭流

``` java
// 例：从文件中读取数据
public static void main(String[] args) throws IOException{
	// 创建源
	File file = new File("abc.txt");
	// 指定流：文件字节输入流
	InputStream is = new FileInputStream(file);
		
	// 操作：读数据
	byte[] data = new byte[1024];
	int len = is.read(data);
		
	// 释放资源
	is.close();
	System.out.println(new String(data)); // 如果数组没有被充满，剩下的会用空格替换：输出有冗余
	System.out.println(new String(data, 0, len)); // 限定范围，从第一位到获取的数据位：避免了空格输出
}

// 例：每次读取若干个字节
public static void main(String[] args) throws IOException{
	// 创建源
	File file = new File("abc.txt");
	// 指定流：文件字节输入流
	InputStream is = new FileInputStream(file);
		
	// 操作：读数据(一次读 len 个字节)
	int len = 10;
	byte[] data = new byte[len];
	int result = is.read(data, 0, len); // 保存数据时从下标 0 开始，最多读到 len
		
	while (result != -1) { // 当 read 读不到数据时会返回 -1 (操作结束)
		System.out.print(new String(data, 0, result));
		result = is.read(data, 0, len);
	}
		
	// 释放资源
	is.close();
}
```

### 异常处理

``` java
// 标准写法
方法 throws IOException
```

### 读写方法

``` java
// 一次一个字节的读取，到达文件末尾，返回 -1
// 返回 0 到 255 范围内的 int 字节值
public int read() throws IOException;
// 一次一个字节数组的读取，到达文件末尾，返回 -1
// 如果定义的数组长度为10则一次读取10个字节
// 如果读取的长度超过源的长度，在后面补上字节0（不够严谨，会造成冗余）
public int read(byte[] b) throws IOException;
// int off = 目标数组 b 的起始偏移量 int len = 读取的最大字节数
// off，len 不能为负数；len 不能大于 b.length - off 
// 返回值：是实际读取到的数
// 例:b 的长度为10 read(b, 0, 9) 则每次只会读取九个，读取两次，最后一次读取1个
// 将off len 看成是界定的范围，更容易理解
public int read(byte[] b,int off, int len) throws IOException;
// 总结：使用数组读取效率非常高 可以将设置成 byte[1024*n]:就可以按照不同的单位读取数据

// 一次一个字节的写入
// int 为写入的字节值
public void write(int b) throws IOException;
// 往流里边写入缓冲字节数组中的所有内容,如果该数组未被占满，则剩余的部分会全部替换成空格，造成写入大量冗余的结果
// 数组中的内容并不会每次都清空，只会在原来的基础上进行覆盖
public write(byte[] bytes);
// 在外部定义len,然后每次len(为的是最后一次的细节长度)都等于流往数组中存放的长度（可以保证每次都输出读取的内容）
public write(byte[] bytes, int off, int len);

// 类似的使用 read(byte[] b,int off, int len) 和 write(byte[] bytes, int off, int len)，就可以保证每次写入的数据都是读取到的内容，绝杜绝了写入空白的问题（参考下面的写法）
```

``` java
// 参考写法
byte[] bytes = new byte[1024*5];
int len = -1;
while ((len = fis.read(bytes,0,1024)) != -1) {
    fos.write(bytes,0,len);
}
```

### 带缓冲的字节流

+ 自带数组，效率高

+ Buffered前缀：表示是一个带缓冲的流，提升了性能

+ 这是一个包装流（装饰类）：用于修饰其他的字节流

  ``` java
  // 默认的缓冲区大小为8K,他会创建一个缓冲区提前缓存 InputStream 的数据
  public BufferedInputStream(InputStream in); // 作用：提升性能
  // 创建具有 size 大小的缓冲区
  public BufferedInputStream(InputStream in, int size);
  // 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
  public BufferedOutputStream(OutputStream out);
  // 创建一个 size 大小的缓冲流
  public BufferedOutputStream(OutputStream out, int size)
  ```

### 字节流的应用

#### 局限：只能处理字节

``` java
// 字符串与字节互换
// 字符串->字节数组
byte[] = String.getBytes();
// 字符数组->字符串
new String(byte[]);

其他数据与字节的互换操作不方便
```

#### 经典应用：文件复制

``` java
public static void main(String[] args) throws IOException{
    // 创建源 指定流
	InputStream is = new FileInputStream("abc.txt");
	OutputStream os = new FileOutputStream("target.txt");
		
    // 操作
	byte[] buff = new byte[1024]; // 缓存区
	int len;
	while ((len = is.read(buff)) != -1) {
		os.write(buff, 0, len);
	}
	
    // 释放资源
	is.close();
	os.close();
	System.out.println("OK");
}
```

``` java
// 使用带缓冲的字节缓：提高效率
public static void main(String[] args) throws IOException{
    // 创建源 指定流
	InputStream is = new BufferedInputStream(new FileInputStream("abc.txt"));
	OutputStream os = new BufferedOutputStream(new FileOutputStream("target.txt"));
		
    // 操作
	byte[] buff = new byte[1024]; // 缓存区
	int len;
	while ((len = is.read(buff)) != -1) {
		os.write(buff, 0, len);
	}
		
    // 释放资源
	is.close();
	os.close();
	System.out.println("OK");
}
```

#### 问题：操作中文不方便，容易出现乱码

## 3. 字符类与字符编码

### 字符流与字符编码有关

字符流 = 字节流 + 编码表

+ 默认编码：GBK

  ``` java
  // 构造方法 同 字节流
  // 这是InputStreamReader的子类
  FileReader();
  // 这是OutputStreamWriter的子类
  FileWriter();
  ```

+ 其他编码的字符流

  包装流

  ``` java
  // 字节流 -> 指定编码的字符流，如 UTF-8
  InputStreamReader(InputStream in, String charsetName);
  OutputStreamWriter(OutputStream out, String charsetName);
  ```

  ### 字符流的特点

  + 以字符为单位读写(中文根据编码的不同占的字符数也不同)

    单个字符或字符数组

  + 输出流的特点

    ``` java
    // 直接写字符串
    write(String);
    // 刷新缓冲区
    // 将缓冲区的内容写入文件
    flush();
    
    注意：执行 flush() 在执行 write() 如果没有 close() 数据写不进去
         close() 关闭流的同时将清空缓冲区中的数据
         正常的清空下应该是读取完一轮，未读完，刷新；读取结束 关闭
    ```

### 带缓冲的字符流

以行为单位读写字符串

``` java
// 直接读字符串
BufferedReader(Reader in);
readLine(); // 返回字符串(一次读一行，即读取到\n)
// readLine 返回 null 代表文件结束

// 直接写字符串
BufferedWriter(Writer out);
write(String); // 写入字符串
newLine(); // 换行：与平台无关
// Windows：\r\n
// Linux：\n
// Mac：\r
```

## 4. 在不同的设备之间传输数据

### 使用字节流

## 5. 包装流

### 节点流

可以直接从数据源或目的地读写数据

### 处理流(包装流)

不直接连接到数据源或目的地，是其他流进行封装

目的：简化操作提高性能

### 关系

+ 节点流处于 IO 操作的第一线，所有操作必须通过他进行
+ 处理流可以对其他流进行处理(提高效率或操作灵活性)