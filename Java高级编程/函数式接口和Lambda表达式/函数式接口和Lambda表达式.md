# 函数式接口和Lambda表达式

## 1.什么是函数式接口

+ 有且只有一个抽象方法
+ 可以用@FunctionalInterface标注该接口
  + 作用：编译器将检查语法是否正确

``` java
// 函数式接口，有且仅有一个抽象方法，Object的public方法除外
@FunctionalInterface
修饰符 interface 接口名称 {
    public abstract 返回值类型 方法名称(可选参数信息);
    // 其他非抽象方法内容
 }

// 实例
@FunctionalInterface
public interface MyFun {
    public abstract Integer getValue(Integer num);
}

public Integer operation(Integer num, MyFun mf) {
    return mf.getValue(num);
}

public void demo() {
    Integer num = operation(100, (x) -> x * x);
    System.out.println(num);
}
```

## 2.函数式接口的含义

把函数作为数据模型

你够使用Lambda的依据是必须有相应的函数接口

### 对函数进行分类，代表某一类型的函数

### 函数如何分类？

+ 参数类型、个数一样
+ 返回值类型相同

### 怎样把某个方法赋值给函数式接口的变量

+ Lambda表达式
+ 方法引用

## 3.Lambda表达式

Lambda表达式是一个对象

+ 用一个特殊的表达式来表示一个函数

  + 语法：(参数列表) -> {方法体}

  参数列表中的数据类型可以省略，JVM可根据上下文作出推断，即"类型推断"

  ``` java
  // 语法格式1：无参数，无返回值
  Runnable r = () -> System.out.println("Hello");
  r.run();
  
  // 语法格式2：有参数，无返回值
  // 如果只有一个参数，小括号可省略 x -> System.out.println(x);
  Consumer<String> con = (x) -> System.out.println(x);
  x.accept("Hello");
  
  // 语法格式3：有两个以上参数，且方法体中有多条语句
  Comparator<Integer> com = (x, y) -> {
      System.out.println("函数式接口");
      return Integer.compaer(x, y);
  }
  
  // 语法格式5：方法体中仅有一条语句，可省略 return 与 {}
  Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
  
  
  ```

+ Lambda表达式类型 = 函数的类型(某个函数式接口)

当我们需要使用匿名内部类去实现一个接口参数时，就可以考虑是否使用Lambda表达式来简化代码

``` java
/**
 * Employee 类
 *
 * @author CAI
 *
 */
public class Employee {
	private String name;
	private int age;
	private double salary;
	
	public Employee() {
		super();
	}

	public Employee(String name, int age, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
	
}

// 策略接口
public interface Mystrategy<T> {
	public boolean test(T t);
}

/**
 * Demo
 *
 * @author CAI
 *
 */
public class Demo3 {
    // 初始化对象数据
	List<Employee> employees = Arrays.asList(
			new Employee("Cai", 19, 2300),
			new Employee("Wang", 23, 4200),
			new Employee("Jiang", 19, 2500),
			new Employee("Wei", 24, 4400),
			new Employee("Li", 20, 3000),
			new Employee("Ju", 22, 3400));
	
    // 过滤方法
	public List<Employee> fileterEmployee(List<Employee> list, MyPridecate<Employee> mp) {
		List<Employee> emps = new ArrayList<Employee>();
		
		for (Employee employee : list) {
			if (mp.test(employee)) {
				emps.add(employee);
			}
		}
		
		return emps;
	}
	
	/**
	 * 匿名内部类
	 */
	public void test1() {
		List<Employee> list = fileterEmployee(employees, new MyPridecate<Employee>() {
			
			@Override
			public boolean test(Employee t) {
				return t.getSalary() > 3000;
			}
		});
		
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	/**
	 * Lambda表达式
	 */
	public void test2() {
		List<Employee> list = fileterEmployee(employees, (e) -> e.getSalary() <= 3000);
		
		list.forEach(System.out::println);
	}
	
    /**
	 * Stream API
	 */
	public void test3() {
		employees.stream()
				 .filter((e) -> e.getSalary() >= 4000)
				 .forEach(System.out::println);
	}
	
	public void test4() {
		employees.stream()
				 .map(Employee::getName)
				 .forEach(System.out::println);
	}
}

```

## 4.方法引用

方法名指向某个方法，方法名是一个对象(变量)

若 Lambda 体中的内容已经有方法实现了，我们可以使用"方法引用"

可以理解为方法引用时 Lambda 表达式的另一种表现形式

``` java
// 四种语法格式
// Lambda 体中调用方法的参数列表与返回类型，要与函数式接口中的抽象方法的函数列表和返回类型保持一致！

对象名::实例方法名; 
// 例1
// System.out 是一个 PrintStream 对象
Consumer<String> con = System.out::println;
con.accept("Hello");

// 例2
Employee emp = new Employee("CAI"); // 任意创建一个对象
Supplier<String> sup = emp::getName; 
System.out.println(sup.get());

类名::静态方法名;
// 例
Comparator<Integer> com = Integer::compare;

类名::new; // 构造方法
// 例1
Supplier<Employee> sup = Employee::new;
sup.get();
// 例2
Function<String, Employee> fun = Employee::new;
fun.apply("CAI");
// 例3
Function<Integer, String[]> fun2 = String[]::new;
fun.apply(10);

// 注意：Lambda 参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数。
类名::对象方法名; // 对象方法引用
// 例
String[] strArray = {"x", "c", "b"};
Arrays.sort(strArray, String::compareTo);
```

``` java
public class Employee {
	private String name;

	
	public Employee() {
		super();
	}
	
	public Employee(String name) {
		super();
		this.name = name;
	}
    
	..................................
}
```

## 5.四大函数式接口

``` java
// 参数T、返回值R
Function<T,R> : R apply(T t); // 函数型接口

// 例子：处理字符串
void test() {
	String newStr = strHandler("\t\t\t你好中国 ", (str) -> str.trim());
	System.out.println(newStr);
}
	
String strHandler(String str, Function<String, String> fun) {
	return fun.apply(str);
}

// 一个参数，没有返回值
Consumer<T> : void accept(T t); // 消费型接口

// 例子：输出传入的参数
void test() {
	shopping(1000, (m) -> System.out.println("淘宝购物总额" + m));
}

void shopping(double money, Consumer<Double> con) {
	con.accept(money);
}

// 没有参数，有返回值
Supplier<R> : T get(); // 供给型接口

// 例子：获取任意个随机数
void test() {
	List<Integer> nums = getNumList(10, () -> (int)(Math.random() * 100));
	nums.forEach(System.out::println);
}

List<Integer> getNumList(int num, Supplier<Integer> sup) {
	List<Integer> list = new ArrayList<Integer>();
	for (int i = 0; i < num; i++) {
		Integer n = sup.get();
		list.add(n);
	}
		
	return list;
}

// 参数T，返回boolean
Predicate<T> : boolean test(T t); // 断定型接口

// 例子：按格式过滤字符数组
void test4() {
	List<String> strs = Arrays.asList(
				"hello",
				"beautiful",
				"world",
				"tonight",
				"wisper");
		
	List<String> newStrs = filterStr(strs, (s) -> s.length() > 6);
	newStrs.forEach(System.out::println);
}
	
List<String> filterStr(List<String> list, Predicate<String> pre) {
	List<String> strs = new ArrayList<String>();
		
	for (String str : list) {
		if (pre.test(str)) {
			strs.add(str);
		}
    }
		
	return strs;
}
```
