# 模板方法模式（Template Method）

> 定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤

``` java
/**
 * 模板方法模式
 * 
 * @author CAI
 * @time 2020/11/2
 */
public class AbstractClassPattern {
    public static void main(String[] args) {
        AbstractClass abstractClass;

        abstractClass = new ConcreteClassA();
        abstractClass.templateMethod();

        abstractClass = new ConcreteClassB();
        abstractClass.templateMethod();
    }
}

// 抽象类：即抽象模板，定义并实现了一个模板方法
abstract class AbstractClass {
    public abstract void primitiveOperation1(); // 抽象行为，放到子类中实现
    public abstract void primitiveOperation2();

    // 模板方法，给出逻辑的骨架，逻辑的组成是一些相应的抽象操作，他们都推出到了子类实现
    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        System.out.println();
    }
}

// 具体类：实现父类定义的抽象方法，可以有多个具体类与之对应
class ConcreteClassA extends AbstractClass {
    @Override
    public void primitiveOperation1() {
        System.out.println("具体类A方法1实现");
    }

    @Override
    public void primitiveOperation2() {
        System.out.println("具体类A方法2实现");
    }
}

class ConcreteClassB extends AbstractClass {
    @Override
    public void primitiveOperation1() {
        System.out.println("具体类B方法1实现");
    }

    @Override
    public void primitiveOperation2() {
        System.out.println("具体类B方法2实现");
    }
}
```

## 优点

> 通过把不变的行为搬移到超类，去除子类中的重复代码来体现它的优势

> 模板方法提供了一个很好的代码复用平台

> 当不变的和可变的行为在方法的子类实现中混合在一起的时候，不变的行为就会在子类中重复出现。我们通过模板方法模式把这些行为搬移到单一的地方，这样就帮助子类摆脱重复的不变行为的纠缠

## 使用动机

> 当我们要完成在某一细节层次一致的一个过程或一系列步骤，但其个别步骤在更详细的层次上的实现可能不同时，我们通常考虑用模板方法来处理

## 总结

> 所有重复的代码都应该上升到父类去，而不死让每个子类都去重复