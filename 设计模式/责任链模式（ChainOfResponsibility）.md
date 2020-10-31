# 责任链模式（ChainOfResponsibility）

> 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这个对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止

``` java
/**
 * 责任链模式
 *
 * @author CAI
 * @time 2020/10/31
 */
public class ChainofResponsibilityPattern {

    public static void main(String[] args) {
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();

        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        int requests[] = {2, 5, 14, 22, 18, 3, 27, 29};

        for (int i = 0; i < requests.length; i++) {
            h1.handleRequest(requests[i]);
        }
    }
}

// Handler类：定义一个处理请示的接口
abstract class Handler {
    protected Handler successor; // 继任者

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    // 处理请求
    public abstract void handleRequest(int request);
}

class ConcreteHandler1 extends Handler {// 具体处理者：处理它所负责的请求，并传递给下一位

    @Override
    public void handleRequest(int request) {
        if (request >= 0 && request < 10) {
            System.out.println("ConcreteHandler1 处理请求" + request);

        } else if (successor != null){
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("ConcreteHandler2 处理请求" + request);

        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}

class ConcreteHandler3 extends Handler {

    @Override
    public void handleRequest(int request) {
        if (request >= 20 && request < 30) {
            System.out.println("ConcreteHandler3 处理请求" + request);

        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
```

## 优点

> 接收者和发送者都没有对方的明确信息，且链中的对象自己也并不知道链的结构。结构是职责链可简化对象的相互连接，它们仅需保持一个指向其后继者的引用，而不需保持它所有的候选接收者的引用

> 可以随时地增加或修改处理一个请求的结构，增强了给对象指派职责的灵活性

## 使用动机

> 一个请求可能被多个对象处理，但是每个请求在运行时只能有一个接收者，如果显示指定，将必不可少地带来请求发送者与接收者的紧耦合

> 如何使请求的发送者不需要指定具体的接收者?
>
> 让请求的接收者自己在运行时决定来处理请求，从而使两者解耦

## 总结

> 一个请求极有可能到了链的末端都得不到处理，或者因为没有正确配置而得不到处理

> 应用于“一个请求可能有多个接受者，但是最后真正的接受者只有一个”，这时候请求发送者与接受者有可能出现“变化脆弱”的症状，职责链解耦

> 该模式有些过时