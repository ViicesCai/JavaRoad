# 原型模式（Prototype）

> 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象

``` java
/**
 * 原型模式
 *
 * @author CAI
 * @time 2020/11/7
 */
public class PrototypePattern {

    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype();
        System.out.println(prototype.getId());

        ConcretePrototype clone = (ConcretePrototype)prototype.clone();
        System.out.println(clone.getId());
    }
}

/**
 * 原型接口
 */
interface Prototype {
    Object clone();
}

/**
 * 具体原型类
 */
class ConcretePrototype implements Prototype {
    private String id;

    public ConcretePrototype() {
        id = "ID：" + Math.random();
    }

    public ConcretePrototype(ConcretePrototype prototype) {
        this.id = prototype.id;
    }

    @Override
    public Object clone() { // 浅拷贝
        return new ConcretePrototype(this);
    }

    public String getId() {
        return id;
    }
}
```

## 优点

> 每NEW一次，都需要执行一次构造函数，多次执行该操作过于低效。一般在初始化的信息不发生变化的情况下，克隆是最好的办法。这既隐藏了对象创建的细节，有提高了性能

> 不用重新初始化对象，而是动态地获得对象运行时的状态

## 深拷贝和浅拷贝

### 浅拷贝

> 被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用都仍然指向原来的对象

### 深拷贝

> 把引用对象的变量指向复制过的新对象，而不是原有的被引用的对象

## 使用时机

> 经常面临某些结构复杂的对象的创建工作，这些对象经常面临着剧烈的变化，但是拥有比较稳定一致的接口

## 总结

> 原型模式即：从一个对象再创建另外一个可定制的对象，而且不需知道任何创建的细节

