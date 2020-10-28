# 状态模式（State）

> 当一个对象的内在状态改变时，允许改变其行为，这个对象看起来像是改变了其类

``` java
/**
 * 状态模式
 *
 * @author CAI
 * @time 2020/10/27
 */
public class StatePattern {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteStateA());

        // 不断的请求，同时更改状态
        context.request();
        context.request();
        context.request();
        context.request();
    }
}

// 抽象状态类：封装与 Context 的一个特定状态相关的行为
abstract class State {
    abstract void Handle(Context context);
}

// 具体状态类：每一个子类实现一个与 Context 的一个状态相关的行为
class ConcreteStateA extends State {

    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateB()); // ConcreteStateA 的下一状态是 ConcreteStateB
    }

    @Override
    public String toString() {
        return "StateA";
    }
}

class ConcreteStateB extends State {

    @Override
    public void Handle(Context context) {
        context.setState(new ConcreteStateA());
    }

    @Override
    public String toString() {
        return "StateB";
    }
}

// 维护一个 ConcreteState 子类的实例，这个实例定义当前的状态
class Context {
    private State state;

    public Context(State state) { // 定义 Context 的初始状态
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        System.out.println("当前状态：" + state.toString());
    }

    public void request() { // 处理请求，设置下一状态
        state.Handle(this);
    }
}
```

## 优点

> 将与特定状态相关的行为局部化，并且将不同状态的行为分割开来

> 将特定的状态相关的行为都放入一个对象中，由于所有与状态相关的代码都存在于某个 ConcreteState 中，所以通过定义新的子类可以很容易地增加新的状态和转移

> 为了消除庞大的条件分支语句，状态模式通过把各自状态转移逻辑分布到 State 的子类之间，来减少相互间的依赖

## 使用动机

> 当一个对象的行为取决于它的状态，并且它必须在运行时刻根据状态改变它的行为时，就可以考虑使用状态模式了

## 总结

> State模式将所有与一个特定状态相关的行为都放入一个State的子对象中，在对象状态切换时，切换相应的对象； 但同时维持State的接口，这样实现了具体操作与状态转换之间的解耦