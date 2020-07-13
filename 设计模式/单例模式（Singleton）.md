# 单例模式（Singleton）

> 保证一个类仅有一个实例，并提供一个访问它的全局访问点

+ 确保一个类仅有一个实例，则表示其构造方法一定不能为 public：不能被外界实例化（private）
+ 仅有一个实例即表示，即该实例为当前类的类变量（静态变量）
+ 提供一个访问它的全局访问点：一个静态方法，向外界提供当前类的实例

> 统计在线人数：计数器
>
> IO访问、数据库访问时，可以使用单例模式来减少资源的消耗

## 懒汉式

> 在第一次引用时，才会将自己实例化

``` java
/**
 * 单例模式-懒汉式：线程不安全
 *
 * @author CAI
 * @time 2020/7/5
 */
public class Singleton {
    private static  Singleton instance;

    private Singleton(){};

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

### 优缺点

+ 优点
  + 第一次调用时才会创建，避免资源浪费
  + 加载类时速度快
+ 缺点
  + 线程不安全
  + 运行时获得对象的速度比较慢

### 适用场景

+ 内存占用大，对启动速度有要求
+ 不适合多线程同时使用

## 饿汉式

> 在自己被加载时就将自己实例化

``` java
/**
 * 单例模式-饿汉式
 *
 * @author CAI
 * @time 2020/7/5
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton(){};

    public static Singleton getInstance() {
        return instance;
    }
}
```

### 优缺点

+ 优点
  + 线程安全
  + 运行时获得对象的速度比较快
+ 缺点
  + 占用资源
  + 加载类的时候比较慢

### 适用场景

+ 内存占用小，使用频繁
+ 不适合使用频率低的场景使用

## 带锁

> 在懒汉式的基础上解决了其线程不安全的问题，但是加锁也带来了性能上的损耗

``` java
/**
 * 单例模式-带锁懒汉式：解决线程不安全的问题
 *
 * @author CAI
 * @time 2020/7/5
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {};

    public synchronized static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

### 双重锁定

``` java
/**
 * 双重锁定
 *
 * @author CAI
 * @time 2020/7/5
 */
public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {};

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}

```

> 双重检查模式，进行了两次的判断，第一次是为了避免不要的实例，第二次是为了进行同步，避免多线程问题，使得其线程安全，且在多线程下能保持高性能
>
> new Singleton() 可以分为三部分
>
> 1.分配内存
>
> 2.初始化对象：调用构造器
>
> 3.指向刚分配的地址
>
> 若发生了指令重排序
>
> 即由于 CPU 调度问题执行顺序可能无法按照我们理想的情况进行，如：线程A执行了1、3此时未执行2，但是线程B进入了发现存在内存地址，但实际上对象并不存在
>
> 必须使用 volatile 避免重排序

