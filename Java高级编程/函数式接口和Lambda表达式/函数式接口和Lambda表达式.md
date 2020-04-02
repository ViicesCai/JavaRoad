# 函数式接口和Lambda表达式

## 1.什么是函数式接口

+ 有且只有一个抽象方法
+ 可以用@FunctionalInterface标注该接口
  + 作用：编译器将检查语法是否正确

``` java
// 函数式接口，有且仅有一个抽象方法，Object的public方法除外
@FunctionalInterface
public interface Func {
    void foo();
}
```

## 2.函数式接口的含义

把函数作为数据模型

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

  ``` java
  // 使用匿名内部类  
  btn.setOnAction(new EventHandler<ActionEvent>() { 
      @Override  
      public void handle(ActionEvent event) {  
          System.out.println("Hello World!");   
      }  
  });  
  
  // 或者使用 lambda expression
  // 有各种简化版本
  btn.setOnAction(event -> System.out.println("Hello World!")); 
  ```

+ Lambda表达式类型 = 函数的类型(某个函数式接口)

## 4.方法引用

方法名指向某个方法，方法名是一个对象(变量)

``` java
对象名::实例方法名;
类名::静态方法名;
类名::new; // 构造方法
类名::对象方法名; // 对象方法引用
```

eg

```java
/**
 * 函数式接口
 */
public interface StringFunc {
    String func(String n);
}

/**
 * 类，静态方法
 */
public class MyStringOps {  
    //静态方法： 反转字符串
    public static String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

/**
 * 测试类
 */
public class MethodRefDemo {
    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }
    
    public static void main(String[] args) {
        String inStr = "lambda add power to Java";
        //MyStringOps::strReverse 相当于实现了接口方法func() 
        // 并在接口方法func()中作了MyStringOps.strReverse()操作
         String outStr = stringOp(MyStringOps::strReverse, inStr);
        System.out.println("Original string: " + inStr);
        System.out.println("String reserved: " + outStr);
    }
}

// 结果：Original string: lambda add power to Java
//      String reserved: avaJ ot rewop dda adbmal
```

## 5.四大函数式接口

``` java
// 参数T、返回值R
Function<T,R> : R apply(T t);

// 一个参数，没有返回值
Consumer<T> : void accept(T t);

// 没有参数，有返回值
Supplier<R> : T get();

// 参数T,返回boolean
Predicate<T> : boolean test(T t);
```

eg

```java
System.out.println("例：改成方法引用");

// 一个参数没有返回值
Consumer<String> c = System.out::println;
c.accept("例：改成方法引用");
```

