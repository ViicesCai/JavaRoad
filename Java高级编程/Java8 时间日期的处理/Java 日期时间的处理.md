# Java 日期时间的处理

## 1.Date

+ 例

  ```java
  // 将date对象 转为long型
  Date date = new Date();
  
  // 将long型时间转为date对象
  long time = 123L;
  Date date = new Date(time);
  
  // 将date对象转为String型
  // 输出的格式 不符合中国的阅读习惯
  date.toString();
  
  // long 与 date 的转换
  // 返回long型的时间
  date.getTime() ;
      
  // 将long型转为Date对象
  date.setTime(time);
  ```

  

### 日期的输入和输出

+ DateFormat 是日期/时间格式化子类的抽象类

  SimpleDateFormat 是 DateFormat 的子类

  + 是一个具体的类，他能格式化文本和解析日期

  + Y : Week Year

    W : Week in month w : Weak in year

    D : Day in month (小写同上)

    E : Day name in week

    H : Hour in day (24) h : (12)

    m : Minute in hour

    s : Second in minute

+ 输出：日期格式化，即 Date -> String

  ```java
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  Date date = new Date();
  String str = sdf.format(date);
  System.out.println(date);
  ```

  

+ 输入：日期解析，即 String -> Date

  ``` java
  public static void main(String[] args) throws ParseException {
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");/
  		String dstr="2008-4-24";
  		Date date = sdf.parse(dstr);
  		System.out.println(date);
  	}
  ```



## 2.Calendar

+ 可以方便的操作日期中的各种字段

###  创建实例

``` java
// java.util.Calendar 类是一个抽象类，可以通过调用 getInstance() 静态方法获取一个 Calendar 对象
// 此对象已由当前日期时间初始化，即默认代表当前时间
Calendar calender = Calendar.getInstance();
```

### 读取日期的各个部分

``` java
// 使用Calendar获取时间
Calendar a = Calendar.getInstance(); // 创建Calendar对象
int year = a.get(Calendar.YEAR); // 获取年份
int month = a.get(Calendar.MONTH) + 1; // 获取月份，0表示1月
int day = a.get(Calendar.DAY_OF_MONTH); // 获取日期
int hour = a.get(Calendar.HOUR_OF_DAY); // 获取小时
int minute = a.get(Calendar.MINUTE); // 获取分钟
int second = a.get(Calendar.SECOND); // 获取秒数
```

### 修改日期的各个部分

``` java
calendar = Calendar.getInstance(); // 创建Calender 对象
calendar.set(2013, 1, 2, 17, 35, 44); // 分别修改 year, month, date, hourOfDay, minute, second  
str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(calendar.getTime());
System.out.println(str);  
```

###  Calender 与 Date 互相转换

``` java
// 创建 Calendar 对象  
Calendar calendar = Calendar.getInstance();
// 对 calendar 设置时间的方法  
// 设置传入的时间格式  
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
// 指定一个日期  
Date date = dateFormat.parse("2013-6-1 13:24:16");
// 对 calendar 设置为 date 所定的日期  
calendar.setTime(date); // 将Date 转为i Clender
System.out.println(calendar.getTime()); // Clender 转为 Date
```

### Calender 与 long 互相转化

``` java
Calendar calendar = Calendar.getInstance();
long time = calendar.getTimeInMillis(); // calender 转换为 long型

long time = System.currentTimeMillis() + 100000000;
calendar.setTimeInMillis(time); // lonng型 转换为 calendar
```

### Calender 与 String 互相转化

``` java
// 获取当前时间的具体情况,如年,月,日,week,date,分,秒等
Calendar calendat = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String dateStr = sdf.format(calendar.getTime()); // calender 转换为 String型

// String 转换为 Calender
String str = "2010-5-27";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date date = sdf.parse(str);
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
```

# Java8 日期时间的处理

## 1.在程序中时间怎么用？

### 在计算机时间的表示（内部表示：面向机器）

+ 表示自1970年1月1日0时0分0秒以来的毫秒数
+ 以毫秒为单位
+ long类型

### 日常生活中时间的表示（外部表示：面向用户）

+ 中文表示：X年X月X日X时X分X秒
+ 国际标准（ISO）：XXXX-XX-XXTXX:XX:XX 年必须4位，其他都必须时2位

### 程序中时间怎么用

+ 如何输入输出时间（字符串）
+ 如何提取时间的某个部分
+ 如何对时间进行简单的计算

## 2.JDK与日期有关的类

+ JDK 1.0 ：面向机器，对程序员不友好
+ JDK 1.8：面向用户，使用方便（基于接口的设计）

## 3.LocalDate

+ 只有日期没有时间

### 如何创建实例

``` java
// 从默认时区的系统时钟中获取当前日期
LocalDate date = LocalDate.now();
System.out.println(date);

// 从年，月，日获取LocalDate实例
LocalDate date2 = LocalDate.of(2020, 2, 12);
System.out.println(date2);
```

### 如何读取日期中的字段

``` java
LocalDate date = LocalDate.of(2020, 2, 12);
System.out.println(date.getDayOfYear()); // 获取当前日期为今年的第几天
System.out.println(date.getDayOfMonth()); // 获取当前日期为本月的第几天
```

### 如何修改日期中的字段

```java
// 当前日期对象不变，返回修改后的新日期
LocalDate date = LocalDate.now();
// 支持链式调用
// 如果修改的日期参数超出范围，将会抛出异常 withDayofMonth(32) 一月当中没有32天
LocalDate date2 = date.withDayOfMonth(1).withYear(2000); // 修改日期为本月一日，年份为2000年
System.out.println(date2);
```

### 日期运算

```java
// 当前日期对象不变，返回运算后的新日期
LocalDate date = LocalDate.now();

// 支持链式调用
// 参数超出范围，同样会抛出异常。
System.out.println(date.plusDays(2)); // 天数 + 2
System.out.println(date.plusMonths(1)); // 月份 + 1
	    
System.out.println(date.minusMonths(2)); // 月份 - 2
System.out.println(date.minusDays(7)); // 天数 - 7
```

### 其他方法

```java
LocalDate date = LocalDate.now();
	    
System.out.println(date.isLeapYear()); // 判断本年是否为闰年
System.out.println(date.lengthOfMonth()); // 获取本月总天数
System.out.println(date.lengthOfYear()); // 获取本年总天数
```

## 4.日期的格式化与解析

+ 日期与字符串的转换，转换的格式

### DateTimeFormatter

+ 没有public的构造方法
+ 和SimpleDateFormat不同的是，DateTimeFormatter不但是不变对象，它还是线程安全的
+ DateTimeFormatter可以只创建一个实例，到处引用



### 日期的格式符

+ 与SimpleDateFormat 略有不同

### 如何得到DateTimeFormatter实例？

``` java
// 实例化
// 输入：日期解析，即 String -> 日期
String str = "yyyy年MM月dd日";
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(str); // 将时间日期设置为年-月-日 时：分：秒
LocalDate date = LocalDate.now();

// 输出：日期解析，即 String -> 日期
// 将当前的日期按格式格式化
String nowDate = date.format(formatter);
System.out.println(nowDate);
```

### 其他使用方法

```java
// parse 只能按ISO的格式输出时间，否则报错！
// 从文本中获得ISO格式的LocalDate实例，文本只能是ISO格式的
LocalDate date2 = LocalDate.parse("2012-12-11");

// 从给定的文本格式在获取ISO格式的LocalDate实例，文本只能是xxxx年xx月xx日的
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
LocalDate date2 = LocalDate.parse("2012年12月21日", formatter);
```

## 5.根据规则计算日期

### 计算规则（函数）

+ TemporalAdjuster 接口

  函数式接口：一个方法adjustInto

+ TemporalAdjusters

  工具类，提供常见的计算方法（函数）

  ```java
  dayOfWeekInMonth // 创建一个新的日期，它的值为同一个月中每一周的第几天
  firstDayOfMonth // 创建一个新的日期，它的值为当月的第一天
  firstDayOfNextMonth // 创建一个新的日期，它的值为下月的第一天
  firstDayOfNextYear // 创建一个新的日期，它的值为明年的第一天
  firstDayOfYear // 创建一个新的日期，它的值为当年的第一天
  firstInMonth // 创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
  lastDayOfMonth // 创建一个新的日期，它的值为当月的最后一天
  lastDayOfNextMonth // 创建一个新的日期，它的值为下月的最后一天
  lastDayOfNextYear // 创建一个新的日期，它的值为明年的最后一天
  lastDayOfYear // 创建一个新的日期，它的值为今年的最后一天
  lastInMonth // 创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
  // 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星 期几要求的日期
  next / previous
      
  // 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象    
  nextOrSame / previousOrSame
  ```

### LocalDate的with()方法

```java
// LocalDate with(TemporalAdjuster adjuster)
// with() 方法的参数是接口，根据某个函数来计算特定的日期

// 获取本月的第一天
LocalDate date = LocalDate.now();
TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfMonth();
System.out.println(date.with(adjuster));
```

## 6.Java 8 日期类的体系结构

### 基于接口

+ java.time包中的是类是不可变且线程安全的，新的时间及日期API位于java.time中
+ Instant，它代表的是时间戳
+ LocalDate，不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等
+ LocalTime，它代表的是不含日期的时间
+ LocalDateTime，它包含了日期及时间，不过还是没有偏移信息或者说时区
+ ZonedDateTime，这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。

### from()

``` java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年D日");	    
TemporalAccessor temp = formatter.parse("2018年322日");
LocalDate date = LocalDate.from(temp);
System.out.println(date); // 返回ISO格式的日期 2018年322日 即 2018-11-18

```

### LocalDateTime

``` java
// 获取当前时间
LocalDateTime today = LocalDateTime.now();
System.out.println(today);

// 使用LocalDate and LocalTime 创建LocalDateTime
today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
System.out.println(today);

// 通过of方法参数创建
LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
System.out.println(specificDate);
```

### LocalTime

```java
// 获取当前时间
LocalTime time = LocalTime.now();
System.out.println(time);
```

### YearMonth

```java
// YearMonth是不可变的日期时间对象，表示年、月的组合。其实现了Comparable接口。
// 格式化为字符串
YearMonth ym = YearMonth.now();  
String s = ym.format(DateTimeFormatter.ofPattern("MM yyyy"));  
System.out.println(s); 
```

### MonthDay

+ 表示`ISO-8601`日历系统中的月和日，具体用法同上

### 总结

+ 本段例举的所有类 均可参照LocalDate，只是具体的格式不同。

### equals、compareTo

+ 比较两个时间日期是否相等
+ 重写



