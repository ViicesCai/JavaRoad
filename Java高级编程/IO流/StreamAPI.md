# Stream API

## 1. 什么是 Stream API

java.util.stream

流（Stream）是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列

集合讲的是数据，流讲的是计算

### Stream

+ 对象序列

  一组对象

  + 有限序列：数组、集合
  + 无限序列：无限流

+ 注意：

  + Stream 自己不会存储元素
  + Stream 不会改变源对象，相反，他们会返回一个持有结果的新 Stream
  + Stream 操作是延迟执行的，这意味着他们会等到需要结果的时候才执行

### Stream API

+ 功能：以对象序列为基础进行各种操作

  与 SQL 的 SELECT 语句类似

### Stream 流的一般用法

+ 基本步骤
  + 创建流（Stream）：一个数据源（如：集合、数组），获取一个流
  + 中间操作：一个中间操作链，对数据源的数据进行处理
  + 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果
+ 特点：函数式编程

## 2. 如何创建 Stream

### Stream< T > 是接口

### 创建 Stream 的方法

``` java
// 常用方法
// 1. 集合 -> Stream<T>
List<String> list = new ArrayList<String>();
Stream<String> stream = list.stream(); // 串行
Stream<String> stream = list.parallelStream(); // 并行

// 2. 数组 -> Stream<T>
Employee[] emps = new Employee[10];
Stream<Employee> stream = Arryas.stream(emps);

// 3. 值 -> Stream<T>
Stream<String> stream = Stream.of("aa", "bb", "cc");

// 4. 函数 -> Stream<T> 创建无限流
// 迭代
// static <T> Stream<T> iterate(T seed,UnaryOperator<T> f); // seed:起始值，UnaryOperator:函数接口;
Stream<Integer> stream = Stream.iterate(0, (x) -> x + 2);
stream.limit(10).forEach(System.out::println); // limit:截取方法（中间操作）;forEach:打印（终止操作）

// 生成
Stream.generate(() -> Math.random() * 10)
	  .limit(10)
	  .forEach(System.out::println);

// 5. 文本文件 -> Stream<String>
Stream<String> stream = Files.lines(Paths.get("text.txt"), Charset.defaultCharset());
```

## 3. 如何对 Stream 流进行操作

### 基本操作

中间操作：调用方法

函数式编程：方法的参数大部分是函数式接口

#### 筛选与切片

+ filter：接收 Lambda，从流中排除某些元素、
+ limit：截断流，使其元素不超过给定的数量
+ skip(n)：跳过元素，返回一个扔掉了前 n 个元素的流，若流中的元素不足 n 个，则返回一个空流，与 limit 互补
+ distinct：筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

``` java
// 例：
// 内部迭代：迭代操作由 Stream API 完成
List<Integer> list = Arrays.asList(
				11,23,24,25,26,27,33,21,22,22,41);
// 中间操作不会执行任何操作
Stream<Integer> stream = list.stream()
							.filter(e -> e > 20) // 过滤出 > 20 的数
							.limit(3); // 只获取3个
// 满足这些条件后，就不再继续迭代了
// 终止操作：一次性执行全部操作
stream.forEach(System.out::println); 
// 输出：23，24，25（3个 = limit）

list.stream()
	.filter(e -> e > 20)
	.skip(2) // 跳过满足条件的前两个
	.distinct() // 去除重复的元素
	.forEach(System.out::println); 
// 结果：25，26，27，33，21，22，41
```

#### 映射

+ map：接收 Lambda，将元素转换成其他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
+ mapTo(Double/Int/Long)：和 map 类似，但是产生的是一个数据类型的 Stream
+ flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连成一个流

``` java
// 例：
public static void main(String[] args) {
    List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    list.stream()
    .map((str) -> str.toUpperCase()) // 所有值变成大写形式
    .forEach(System.out::println);
    // 结果：AAA,BBB,CCC,DDD,EEE
    
    list.stream()
		.map(Demo::filterCharacter) // {{a,a,a}, {b,b,b}, ....}
		.forEach((sm) -> {
            sm.forEach(System.out::println); 
	});
    // 结果：a a a b b b .....
    
    list.stream()
	    .flatMap(Demo::filterCharacter) // {a,a,a,b,b,......}
		.forEach(System.out::println);
    // 和上一个结果一样
}


public static Stream<Character> filterCharacter(String str) {
    List<Character> list = new ArrayList<Character>();
	for (Character ch : str.toCharArray()) {
        list.add(ch);
    }
	return list.stream();
}
```

#### 排序

+ sorted()：自然排序
+ sorted(Comparator com)：定制排序

``` java
// 例：
List<String> list = Arrays.asList("ccc", "bbb", "eee", "aaa", "ddd");
list.stream()
	.sorted() // 自然排序
	.forEach(System.out::println);
// 结果：aaa,bbb,ccc,ddd,eee

List<Book6601>books = Arrays.asList(
	new Book("A1001", "挪威的森林", 20, 5),
	new Book("B1003", "且听风吟", 18, 3),
	new Book("D1001", "1Q84", 23, 8),
	new Book("A4001", "百年孤独", 21, 10),
	new Book("B1001", "1984", 17, 7),
	new Book("C2001", "围城", 13, 5),
	new Book("B1101", "边城", 16, 6),
	new Book("A1011", "动物凶猛", 19, 5),
	new Book("C1001", "人间词话", 13, 9)
	);
		
books.stream()
	.sorted((e1, e2) -> {
		return e1.getID().compareTo(e2.getID());
	})
	.forEach(System.out::println);
// 结果：按照 ID 升序排序
```

#### 执行操作

+ peek：对流中的每个元素执行特定的操作

  和 map 非常相似，但是唯一的不同之处在于 map 方法的入参为 Function，peek 方法的入参为 Consumer

  前者有返回值，后者没有

``` java
 Person a = new Person("a", 18);
 Person b = new Person("b", 23);
 Person c = new Person("c", 34);
 Stream<Person> persons = Stream.of(a, b, c);
 persons.filter(person -> person.getAge() < 30)
        .peek(person -> System.out.println("filter " + person))
        .map(person -> new Person(person.getName() + " map", person.getAge()))
        .peek(person -> System.out.println("map " + person))
        .collect(Collectors.toList());

// peek：可以在不替代流中数据的同时，执行一些特殊的操作

// 结果： filter Person{name='a', age=18}
//       map Person{name='a map', age=18}
//       filter Person{name='b', age=23}
//       map Person{name='b map', age=23}
```

#### 总结

多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！而在终止操作时一次性全部处理，称为"惰性加载"(懒加载)

### 终止操作

#### 查找与匹配

+ allMatch：检查是否匹配所有元素
+ anyMatch：检查是否至少匹配一个元素

+ noneMatch：检查是否没有匹配所有元素
+ findFirst：返回第一个元素
+ findAny：返回当前流中的任意元素

``` java
List<Book>books = Arrays.asList(
	new Book("A1001", "挪威的森林", 20, 10),
	new Book("B1003", "且听风吟", 18, 11),
	new Book("D1001", "1Q84", 23, 10),
	new Book("A4001", "百年孤独", 21, 10)
);
		
boolean result = books.stream()
					.allMatch(e -> e.getNumber() == 10);
// 结果：false，所有的对象的 number 没有都等于10 

boolean result = books.stream()
                      .anyMatch(e -> e.getNumber() == 10);
// 结果：true，至少有一个匹配了

boolean result = books.stream()
                      .noneMatch(e -> e.getNumber() == 10);
// 结果：false

// Optional：只是一个存储的容器：防止结果为空，所以需要有一个容器
Optional<Book6601> op = books.stream()
				            .findFirst();
System.out.println(op);
// 结果：Optional[A1001,挪威的森林,20,10]

Optional<Book6601> op = books.stream()
				            .findAny();
// 结果：Optional[A1001,挪威的森林,20,10]

Optional<Book6601> op = books.parallelStream()
				            .findAny();
// 结果：谁都有可能，相同的越多，出现的概率越大注意此二者的区别：并行与串行
```

## 4. Stream 流的迭代计算

归约：reduce(T identity, BinaryOperator) / reduce(BinaryOperator)

###  功能

对整个序列中的元素进行连续的计算，得到一个结果

### 参数

identity：起始值，BinaryOperator< T >：两个参数的函数，并且函数的返回值类型与参数的类型相同

### 运算过程

``` java
List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
Integer sum = list.stream()
				 .reduce(0, (x, y) -> x + y); // x=x+y；y=(1,2,3,4 .....)：类似递归调用
// 结果：sum = 55

List<Book>books = Arrays.asList(
	new Book("A1001", "挪威的森林", 20, 10),
	new Book("B1003", "且听风吟", 18, 11),
	new Book("D1001", "1Q84", 23, 10),
	new Book("A4001", "百年孤独", 21, 10)
);

// 获取书籍的单价总和
Optional<Integer> op = books.stream()
						  .map(Book6601::getPrice)
						  .reduce(Integer::sum);
System.out.println(op.get());
// 结果：82
```

### 计算结果

+ T : 提供计算的初始值
+ Optional< T >：没有计算的初值

## 5. Stream流的收集处理

处理：collect

### collect 方法

+ 原型

  < R, A > R collect (Collector < ? super T,A,R > collector)

+ 功能

  按照指定的收集器（Collector(收集器) 接口的实现），对整个序列进行计算，得到一个结果，用于给 Stream 中元素做汇总的方法

  Stream< T > --> R

+ 参数

  Collector：收集器，是一个接口

  ``` java
  public interface Collector<T , A, R>;
  Collectors 类提供常用的收集器：工具类，静态方法，工厂方法
  ```

+ 返回结果

  R

### 对流的统计

+ 聚合计算
  + 计数
  + 最大值、最小值
  + 求和、平均值

``` java
// collectors 工具类提供了很多静态方法方便创建常见的收集器实例

// 方法1：逐个统计
List<Book>books = Arrays.asList(
	new Book("A1001", "挪威的森林", 20, 10),
	new Book("B1003", "且听风吟", 18, 11),
	new Book("D1001", "1Q84", 23, 10),
	new Book("A4001", "百年孤独", 21, 10)
);

List<String> list = books.stream()
				        .map(Book::getName)
				        .collect(Collectors.toList()); // 将 book 的 name 收集到 List 中

list.forEach(System.out::println);
// 输出：挪威的森林 且听风吟 1Q84 百年孤独

// Counting:总数
Long count = books.stream()
				 .collect(Collectors.counting());

// maxBy:最大值
Optional<Book> max = books.stream()
	    .collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getPrice(), e2.getPrice())));

// minBy:最小值
Optinal<Book> min = books.stream()
     .map(Book6601::getPrice)
     .collect(Collects.minBy(Dounle::compare));

// averaging(int,Long,Double):平均值
Double avg = books.stream()
				 .collect(Collectors.averagingLong(Book::getPrice));

// summing(int,Long,Double):总和
Double sum = books.stream()
				 .collect(Collectors.summingDouble(Book::getNumber));
System.out.println(sum);

// 方法2：一次性统计(可以是Int、Long、Double)
IntSummaryStatistics summary = books.stream()
								  .collect(Collectors.summarizingInt(Book::getPrice));
System.out.println("单价最高：" + summary.getMax());
// 包含五种统计结果
```

``` java
// 方法3：直接对流操作
// Stream 的方法

// max
Optional<Book6601> op = books.stream()
				            .max((e1, e2) -> Integer.compare(e1.getPrice(), e2.getPrice()));

// min
Optional<Integer> op = books.stream()
				           .map(Book::getPrice)
				           .min(Integer::compareTo);

// count
long op = books.stream()
		       .map(Book6601::getPrice)
		       .count();

// avg、sum：需要使用数值流
```

### 分组统计

``` java
Map Collectors.groupingBy(Function<? super T, ? extends K>, Collector);
// 第一个参数：分组函数，返回值 K 就是分组的关键字
// 什么样的数据适合作为关键字：一组有限的数据（建议：使用枚举作为分组的关键字）
// 第二个参数：Collector 收集器：对流进行统计的收集器
// 返回结果：Map 集合，key = 分组关键字

// 分组列表：value 是 list
// 只有一个参数

// 分组统计：value 是 统计结果
// 两个参数
```

``` java
// 例子：按照 Status 分组
List<Book6601>books = Arrays.asList(
	new Book("A1001", "挪威的森林", 20, 10, Status.HOT),
	new Book("B1003", "且听风吟", 18, 5, Status.NORMAL),
	new Book("D1001", "1Q84", 23, 5, Status.LESS),
	new Book("A4001", "百年孤独", 21, 1, Status.LESS)
);

Map<Status, List<Book6601>> map = books.stream()
				                      .collect(Collectors.groupingBy(Book::getStatus));
```

``` java
// 例子：多级分组
Map<Status, Map<String, List<Book6601>>> map = books.stream()
				                               .collect(
    Collectors.groupingBy(Book6601::getStatus, 
                          Collectors.groupingBy((e) -> {
                               if (e.getNumber() > 5) {
                                   return "货源充足";
                                   
                               } else if (e.getNumber() > 3) {
								return "需要补货";
                                   
                               } else {
                                   return "即将缺货";
							}
                          })));
```

## 6. 数值流

### 什么是数值流

+ 基本数据类型的流
+ 避免装箱和拆箱，提高效率

### 有哪些数值流

+ IntStream
+ LongStream
+ DoubleStream

### 特点

+ 创建流：range，rangeClosed
+ 统计
  + sum、average
  + summaryStatistics

## 其他

+ Option< T > 容器类
+ 静态导入
+ 对象方法引用