# 访问者模式（Visitor）

> 表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。

``` java
import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 *
 * @author CAI
 * @time 2020/12/9
 */
public class VisitorPattern {

    public static void main(String[] args) {
        ObjectStructure o = new ObjectStructure();
        o.attach(new ConcreteElementA());
        o.attach(new ConcreteElementB());

        ConcreteVisitor1 v1 = new ConcreteVisitor1();
        ConcreteVisitor2 v2 = new ConcreteVisitor2();

        o.accept(v1);
        o.accept(v2);
    }
}

/**
 * 访问者接口
 */
interface Visitor {
    void visitConcreteElementA(ConcreteElementA concreteElementA);
    void visitConcreteElementB(ConcreteElementB concreteElementB);
}

/**
 * 具体访问者1：实现访问者的每个功能
 */
class ConcreteVisitor1 implements Visitor {

    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println(concreteElementA + " 被 " + this + " 访问");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println(concreteElementB + " 被 " + this + " 访问");
    }
}

/**
 * 具体访问者2：实现访问者的每个功能
 */
class ConcreteVisitor2 implements Visitor {

    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.println(concreteElementA + " 被 " + this + " 访问");
    }

    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.println(concreteElementB + " 被 " + this + " 访问");
    }
}

/**
 * 元素接口：以访问者为接口
 */
interface Element {
    void accept(Visitor visitor);
}

/**
 * 具体的元素
 */
class ConcreteElementA implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }

    public void operationA() {
        System.out.println("一些相关操作");
    }
}

/**
 * 具体的元素
 */
class ConcreteElementB implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }

    public void operationB() {
        System.out.println("一些相关操作");
    }
}

/**
 * 提供高层的接口：允许访问者访问它的元素
 */
class ObjectStructure {
    private List<Element> elements = new ArrayList<>();

    public void attach(Element element) {
        elements.add(element);
    }

    public void detach(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        for (Element e : elements) {
            e.accept(visitor);
        }
    }
}
```

## 优点

> 增加新操作容易，因为增加新的操作就意味着增加一个新的访问者

## 使用时机

> 如果系统有比较稳定的数据结构，又有易于变化的算法的话，使用访问者模式就是比较合适的，因为访问者模式使得算法操作的增加变得容易

## 总结

> 适用于数据结构相对稳定的系统

> 访问者模式把数据结构和作用于结构上的操作之间的耦合解脱开，使得操作集合可以相对自由地演化

> 访问者模式增加新的数据结构比较困难

