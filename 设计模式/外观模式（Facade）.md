# 外观模式（Facade）

> 为子系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使得这一子系统更加容易使用

``` java
/**
 * 外观模式
 *
 * @author CAI
 * @time 2020/10/26
 */
public class FacadePattern {

    public static void main(String[] args) {
        Facade facade = new ConcreteFacade();

        facade.methodA();
        facade.methodB();
    }
}

// 子系统
class SubSystem1 {
    public void method1() {
        System.out.println("子系统方法1");
    }
}

class SubSystem2 {
    public void method2() {
        System.out.println("子系统方法2");
    }
}

class SubSystem3 {
    public void method3() {
        System.out.println("子系统方法3");
    }
}

class SubSystem4 {
    public void method4() {
        System.out.println("子系统方法4");
    }
}

// 外观模式高层接口：定义调用子系统的方法
interface Facade {
    void methodA();
    void methodB();
}

// 外观模式实现类：具体实现
class ConcreteFacade implements Facade {
    SubSystem1 one;
    SubSystem2 two;
    SubSystem3 three;
    SubSystem4 four;

    public ConcreteFacade() {
        one = new SubSystem1();
        two = new SubSystem2();
        three = new SubSystem3();
        four = new SubSystem4();
    }

    @Override
    public void methodA() {
        System.out.println("\n方法组A------");
        one.method1();
        two.method2();
        three.method3();
        four.method4();
    }

    @Override
    public void methodB() {
        System.out.println("\n方法组B------");
        two.method2();
        three.method3();
    }
}
```

## 优点

> 对客户端屏蔽了子系统组件，减少了客户端处理的对象数量，也减少了客户端的代码量

> 实现了客户端和子系统的松散耦合，使得子系统个变化不会影响到调用它的客户端，只需要改变外观类即可

> 一个子系统的变化不会影响到另一个子系统，子系统内部变化也不会影响到外观对象

## 使用动机

> 首先，在设计初期阶段，应该要有意识的将不同的两个层分离，层与层之间建立外观Facade；其次，在开发阶段，子系统往往因为不断的重构演化而边得越来越复杂，增加外观Facade可以提供一个简单的接口，减少它们之间的依赖；第三，在维护一个遗留的大型系统时，可能这个系统已经非常难以维护和扩展了，可以为新系统开发一个外观Facade类，来提供设计粗糙或高度复杂的遗留代码的比较清晰简单的接口，让新系统与Facade对象交互，Facade与遗留代码交互所有复杂的工作

## 总结

> 三层结构可以理解为一个外观模式的实例，如果没有 Service 层，Controller 层就可能需要直接去关联多个 dao 才能实现功能，这样会使得系统越来越复杂。外观模式定义了一个外观类 Facade，这个类了解所有子系统的方法和属性，在其内部进行组合后封装成方法，让客户端进行调用。