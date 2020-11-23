# 命令模式（Command）

> 将一个请求封装为一个对象，从而使你可用不同的操作请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤销的操作

```java
/**
 * 命令模式
 *
 * @author CAI
 * @time 2020/11/23
 */
public class CommandPattern {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();

        invoker.setCommand(command);
        invoker.executeCommand();
    }
}

/**
 * 命令接口：定义操作方法
 */
interface Command {
    void execute();
}

/**
 * 具体命令类：绑定一个接收者，调用接收者执行相应的命令
 */
class ConcreteCommand implements Command {
    Receiver receiver;

    ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

/**
 * 调用者：执行命令
 */
class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

/**
 * 接收者
 */
class Receiver {

    public void action() {
        System.out.println("执行请求！");
    }
}
```

## 优点

> 能较容易地设计一个命令队列；在需要的情况下，可以较容易地将命令记入日志；允许接受请求的一方决定是否要否决请求；可以容易地实现对请求的撤销和重做；由于加进新的具体命令类不影响其他的类，因此增加新的具体命令类很容易。

> 命令模式把请求一个操作的对象与知道怎么执行一个操作的对象分割开。

## 总结

> 敏捷开发原则告诉我们，不要为代码添加基于猜测的、实际不需要的功能。如果不清楚一个系统是否需要命令模式，一般就不要着急去实现它，事实上，在需要的时候通过重构实现这个模式并不困难，只有仔真正需要如撤销/恢复操作等功能时，把原来的代码重构为命令模式才有意义。