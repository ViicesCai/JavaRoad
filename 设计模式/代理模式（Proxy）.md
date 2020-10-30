# 代理模式（Proxy）

> 为其他对象提供一种代理以控制（隔离，使用接口）对这个对象的访问

``` java
/**
 * 代理模式
 *
 * @author CAI
 * @time 2020/10/30
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

// Subject接口：定义 RealSubject 和 Proxy 共有的方法
interface Subject {
    void request();
}

// Proxy类所代表的真实实体
class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}

// 代理类：保存引用使得代理可以访问这个实体，并提供一个相同的方法，使得代理可以替换实体
class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }

        realSubject.request();
    }
}
```

## 优点

> 代理模式能将代理对象与真实被调用的目标对象分离

> 一定程度上降低了系统的耦合度，扩展性好

> 可以起到保护目标对象的作用

> 可以对目标对象的功能增强

## 使用动机

> 远程代理：为一个对象在不同的地址空间提供局部代表。这样可以隐藏一个对象存在于不同地址空间的事实

> 虚拟代理：根据需要创建开销很大的对象。通过它来存放实例化需要很长时间的真实对象

> 安全代理：用来控制真实对象访问时的权限

> 智能指引：当调用真实的对象时，代理处理另外一些事

## 总结

> 代理模式就是在访问对象时引入一定程度的间接性，因为这种间接性，可以附加多种用途，即：真实对象的代表

> Proxy 并不一定要求保持接口完整的一致性，只要能够实现间接控制，有时候牺牲一些透明性也可以