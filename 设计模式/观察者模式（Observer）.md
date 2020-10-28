# 观察者模式（Observer）

> 又称为：发布/订阅（Publish/Subscribe）模式
>
> 定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己

``` java
import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * @author CAI
 * @time 2020/10/25
 */
class ObserverPattern {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        subject.attach(new ConcreteObserver(subject, "X"));
        subject.attach(new ConcreteObserver(subject, "Y"));

        subject.setSubjectState("ABC");
        subject.notifyState();
    }
}

// 抽象通知者：包含：添加、删除、通知
abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    // 增加观察者
    void attach(Observer observer) {
        observers.add(observer);
    }

    // 移除观察者
    void detach(Observer observer) {
        observers.remove(observer);
    }

    // 通知
    void notifyState() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

// 抽象观察者：包含：更新
interface Observer {
    void update();
}

// 具体通知者：将相关状态存入具体观察者对象
class ConcreteSubject extends Subject {
    private String subjectState;

    String getSubjectState() {
        return subjectState;
    }

    void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }
}

// 具体观察者：实现观察者接口：使本身状态与主体状态一致
class ConcreteObserver implements Observer {
    private String name;
    private String observerState;
    private ConcreteSubject subject;

    ConcreteObserver(ConcreteSubject subject, String name) {
        super();
        this.subject = subject;
        this.name = name;
    }

    @Override
    public void update() {
        observerState = subject.getSubjectState();
        System.out.println("观察者" + name + "的新状态是" + observerState);
    }

    ConcreteSubject getSubject() {
        return subject;
    }

    void setSubject(ConcreteSubject subject) {
        this.subject = subject;
    }
}
```

## 优点

> 一个抽象模型有两个方面，其中一方面依赖于另一方面，这时用观察者模式可以将这两者封装在独立的对象中，使他们各自独立地改变和复用

> 观察者模式所做的工作其实就是在解除耦合。让耦合的双方都依赖于抽象，而不是依赖于具体。从而使得各自的变化都不会影响另一边的变化

## 使用动机

> 将一个系统分割成一系列相互协作的类有一个很不好的副作用，那就是需要维护相关对象间的一致性。我们不希望为了维持一致性而使各类紧密耦合，这样会给维护、扩展和重用都带来不便

> 当一个对象的改变需要同时改变其他对象的时候，而且它不知道具体有多少对象有待改变时，应该考虑使用观察者模式

## 总结

> 使用面向对象的抽象，Observer模式使得我们可以独立地改变目标与观察者，从而使二者之间的依赖关系达致松耦合

> 目标发送通知时，无需指定观察者，通知（可以携带通知信息作为参数）会自动传播

> 观察者自己决定是否需要订阅通知，目标对象对此一无所知

> Observer模式是基于事件的UI框架中非常常用的设计模式，也是MVC模式的一个重要组成部分