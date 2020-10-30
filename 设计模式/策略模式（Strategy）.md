# 策略模式（Strategy）

> 定义一系列算法，分别封装起来，让它们之间可以相互替换（变化）。此模式使得算法可独立于使用它的客户程序而变化，不会影响到使用算法的客户。

``` java
/**
 * 策略模式
 * 
 * @author CAI
 * @time 2020/10/28
 */
public class StrategyPattern {

    public static void main(String[] args) {
        Context context;

        context = new Context(new ConcreteStrategyA());
        context.contextInterface();

        context = new Context(new ConcreteStrategyB());
        context.contextInterface();

        context = new Context(new ConcreteStrategyC());
        context.contextInterface();
    }
}

// 抽象算法类
abstract class Strategy {

    abstract void algorithmInterface(); // 算法方法
}

// 具体算法
class ConcreteStrategyA extends Strategy {

    @Override
    void algorithmInterface() {
        System.out.println("算法A实现");
    } // 具体算法方法
}

class ConcreteStrategyB extends Strategy {

    @Override
    void algorithmInterface() {
        System.out.println("算法B实现");
    }
}

class ConcreteStrategyC extends Strategy {

    @Override
    void algorithmInterface() {
        System.out.println("算法C实现");
    }
}

// 上下文类：使用ConcreteStrategy来配置，维护一个对Strategy对象的引用
class Context {
    Strategy strategy;

    Context(Strategy strategy) {
        this.strategy = strategy;
    }

    // 根据具体的策略对象，调用其算法的方法
    void contextInterface() {
        strategy.algorithmInterface();
    }
}
```

## 优点

> 策略模式是一种定义一系列算法的方法，从概念上来看，所有这些算法完成的都是相同的工作，只是实现不同，它可以以相同的方法调用所有的算法，减少了各种算法类与使用算法类之间的耦合

> 策略模式的 Strategy 类层次为 Context 定义了一系列的可供重用的算法或行为。继承有助于析取出这些算法中的公共功能

> 简化了单元测试，因为每个算法都有自己的类，可以通过自己的接口单独测试

> 当不同的行为堆砌在一个类中时，就很难避免使用条件语句来选择合适的行为。将这些行为封装在一个个独立的Strategy类中，可以在使用这些行为的类中消除条件语句

## 使用动机

> 策略模式主要用来封装算法，在实践中，它可以用来封装几乎任何类型的规则，只要在分析过程中听到需要在不同时间应用不同的业务规则，就可以考虑使用策略模式处理这种变化的可能性

## 总结

> Strategy及其子类为组件提供了一系列可重用的算法，从而可以使得类型在运行时方便地根据需要在各个算法之间进行切换

> Strategy模式提供了用条件判断语句以外的另一种选择，消除条件判断语句，就是在解耦合。含有许多条件判断语句的代码通常都需要Strategy模式

> 如果Strategy对象没有实例变量，那么各个上下文可以共享同一个Strategy对象，从而节省对象开销

> 策略模式与简单工厂模式结合后，选择具体实现的职责也可以由 Context 来承担，这就最大化地减轻了客户端的职责

