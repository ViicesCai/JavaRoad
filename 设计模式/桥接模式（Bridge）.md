# 桥接模式（Bridge）

> 将抽象部分与它的实现部分分离，使它们都可以独立地变化

``` java
/**
 * 桥接模式
 *
 * @author CAI
 * @time 2020/11/18
 */
public class BridgePattern {

    public static void main(String[] args) {
        Abstraction abstraction = new RefinedAbstraction();

        abstraction.setImplementor(new ConcreteImplementorA());
        abstraction.operation();

        abstraction.setImplementor(new ConcreteImplementorB());
        abstraction.operation();
    }
}

// 实现接口
interface Implementor {
    void operation();
}

// 具体实现类
class ConcreteImplementorA implements Implementor {
    @Override
    public void operation() {
        System.out.println("具体实现A的方法执行");
    }
}

// 具体实现类
class ConcreteImplementorB implements Implementor {
    @Override
    public void operation() {
        System.out.println("具体实现B的方法执行");
    }
}

// 抽象类
class Abstraction {
    protected Implementor implementor;

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation() {
        implementor.operation();
    }
}

// 提炼的抽象类
class RefinedAbstraction extends Abstraction {

    @Override
    public void operation() {
        super.operation();
    }
}
```

> 实现指的是抽象类和它的派生类用来实现自己的对象

> “将抽象部分与它的实现部分分离”即：实现系统可能有多角度分类，每一种分类都有可能变化，那么就把这种多角度分离出来让它们独立变化，减少它们之间的耦合



