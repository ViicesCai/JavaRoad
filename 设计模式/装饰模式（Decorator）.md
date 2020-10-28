# 装饰模式（Decorator）

> 动态（组合）地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类（继承）更为灵活（消除重复代码和减少子类）

``` java
/**
 * 装饰模式
 *
 * @author CAI
 * @time 2020/9/4
 */
public class Decorator {
    public static void main(String[] args) {
        new DecoratorObj(new ConcreteComponent()).otherOperation();
    }
}

/**
 * 对象接口：动态的给对象添加职责
 */
interface Component {
    void operation();
}

/**
 * 具体对象：可以给这些对象添加一些职责
 */
class ConcreteComponent implements Component {

    @Override
    public void operation() {
        System.out.println("具体对象的方法");
    }
}

/**
 * 装饰对象：给 Component 添加职责的功能
 */
class DecoratorObj implements Component {
    private Component component;

    public DecoratorObj(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }

    public void otherOperation() {
        component.operation();
        System.out.println("其他方法");
    }
}
```

## 应用

> java中的 i/o流大量使用了装饰模式

![image-20201020221653193](https://typora-image-1301733210.cos.ap-guangzhou.myqcloud.com/img/image-20201020221653193.png)

> 装饰模式是为已有的功能动态地添加更多功能的一种方式，当系统需要新功能的时候，是向旧的类中添加新的代码。这些新加的代码通常装饰了原有类的核心职责或主要行为

## 优点

> 有效的把类的核心职责和装饰功能区分开了，而且可以去除相关类中重复的装饰逻辑

## 使用动机

> 在某些情况下，我们可能会“过度地使用继承来扩展对象的功能”，由于继承为类型引入的静态特质，使得这种拓展方式缺乏灵活性；并且随着子类的增多（扩展功能的增多），各种子类的组合（扩展功能的组合）会导致更多子类的膨胀。

## 总结

> 通过采用组合而继承的手法，Decorator模式实现了在运行时动态扩展对象功能的功能，而且可以根据需要扩展多个功能。避免了使用继承带来的“灵活性差”和“多子类衍生的问题”

> Decorator 类在接口上表现为：is-a Component的继承关系，即：Decorator类继承了Component类所具有的接口。但在实现上又表现为has-a Component的组合关系，即：Decorator类又使用了另外一个Component类

> Decorator 模式的目的并非解决“多子类衍生的多继承”问题，Decorator模式应用的要点在于解决“主体类在多个方向上的扩展功能”--是为“装饰”的含义