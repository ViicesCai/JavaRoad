# 中介者模式（Mediator）

> 用一个 中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互

``` java
/**
 * 中介者模式
 *
 * @author CAI
 * @time 2020/11/26
 */
public class MediatorPattern {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);

        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.send("Hello");
        colleague2.send("What's up");
    }
}

/**
 * 中介者接口
 */
interface Mediator {
    // 发送方法：得到同事对象和发送消息
    void send(String message, Colleague colleague);
}

/**
 * 抽象同事类
 */
abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}

/**
 * 具体中介者类
 */
class ConcreteMediator implements Mediator {
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    @Override
    public void send(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.notify(message);

        } else {
            colleague1.notify(message);
        }
    }
}

class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void notify(String message) {
        System.out.println("同事 1 得到消息：" + message);
    }
}

class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void notify(String message) {
        System.out.println("同事 2 得到消息：" + message);
    }
}
```

## 优点

> `Mediator`的出现减少了各个`Colleague`的耦合，使得可以独立地改变和复用各个`Colleague`类和`Mediator`。由于把对象如何协作进行了抽象，将中介者作为一个独立的概念并将其封装在一个对象中，这样关注的对象就从对象各自本身的行为转移到它们之间的交互上来，也就是站在一个更宏观的角度去看待系统。

> 缺点：由于`ConcreteMediator`控制了集中化，于是就把交互复杂性变为了中介者的复杂性，这就使得中介者会变得比任何一个`ConcreteColleague`都复杂。

## 使用动机

> 中介者模式一般应用于一组对象以定义良好但是复杂的方式进行通信的场合

> 中介者模式很容易在系统中应用，也很容易在系统中误用。当系统出现了“多对多”交互复杂的对象群时，不要急于使用中介者模式，而要先反思你的系统在设计上是不是合理的



