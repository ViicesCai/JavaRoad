# 享元模式（Flyweight）

> 运用共享技术有效地支持大量细粒度的对象

``` java
import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 *
 * @author CAI
 * @time 2020/11/2
 */
public class FlyweightPattern {

    public static void main(String[] args) {
        int extrinsicstate = 22;

        FlyweightFactory factory = new FlyweightFactory();

        Flyweight flyweightX = factory.getFlyweight("X");
        flyweightX.operation(--extrinsicstate);

        Flyweight flyweightY = factory.getFlyweight("Y");
        flyweightY.operation(--extrinsicstate);

        Flyweight flyweightZ = factory.getFlyweight("Z");
        flyweightZ.operation(--extrinsicstate);

        Flyweight unsharedFlyweigh = new UnsharedConcreteFlyweight();
        unsharedFlyweigh.operation(--extrinsicstate);
    }
}

// 享元接口：使 flyweight 可以接受并作用于外部状态
interface Flyweight {
    void operation(int extrinsicstate);
}

// 实现享元接口：为内部增加存储空间
class ConcreteFlyweight implements Flyweight {

    @Override
    public void operation(int extrinsicstate) {
        System.out.println("具体Flyweight：" + extrinsicstate);
    }
}

// 不需要共享的 flyweight 类
class UnsharedConcreteFlyweight implements Flyweight {

    @Override
    public void operation(int extrinsicstate) {
        System.out.println("不共享的具体Flyweight：" + extrinsicstate);
    }
}

/*
 享元工厂：创建并管理 flyweight 对象，主要用来确保合理地共享 flyweight，
 当用户请求一个 Flyweight 时，享元工厂提供一个已创建的实例或创建一个
 */
class FlyweightFactory {
    private Map<String, Flyweight> flyweights = new HashMap<>();

    public FlyweightFactory() { // 初始化：生成三个实例
        flyweights.put("X", new ConcreteFlyweight());
        flyweights.put("Y", new ConcreteFlyweight());
        flyweights.put("Z", new ConcreteFlyweight());
    }

    public Flyweight getFlyweight(String key) {
        return flyweights.get(key);
    }
}
```

## 优点

> 享元模式可以避免大量非常相似类的开销

## 使用时机

> 如果一个应用程序使用了大量的对象，而大量的这些对象造成了很大的存储开销时就应该考虑使用；还有就是对象的大多数状态可以外部状态（随着环境的改变而改变、不可以共享），如果删除对象的外部状态，那么可以用相对较少的共享对象取代很多组对象，此时可以考虑使用享元模式

## 总结

> 在程序设计中，有时需要大量细粒度的类实例来表示数据。如果能发现这些实例除了几个参数外基本上都是相同的，有时就能够受到大幅度地减少需要实例化的数量。如果能把那些参数移到类实例的外面，在方法调用时将它们传递进来，就可以通过共享大幅度地减少单个实例的数目

