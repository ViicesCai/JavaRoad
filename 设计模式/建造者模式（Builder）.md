# 建造者模式（Builder）

> 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示

``` java
import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 *
 * @author CAI
 * @time 2020/11/5
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder1 = new ConcreteBuilder1();
        Builder builder2 = new ConcreteBuilder2();

        director.construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}

// 产品类：由多个部件组成
class Product {
    List<String> parts = new ArrayList<>();

    public void add(String part) { // 添加部件
        parts.add(part);
    }

    public void show() { // 列举所有的部件
        System.out.println("产品 创建------");

        for (String part: parts) {
            System.out.println(part);
        }
    }
}

// 建造者接口：产品有两部组成，并有一个返回方法
interface Builder {
    void buildPartA();
    void buildPartB();
    Product getResult();
}

// 具体建造者
class ConcreteBuilder1 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("部件A");
    }

    @Override
    public void buildPartB() {
        product.add("部件B");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

class ConcreteBuilder2 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("部件X");
    }

    @Override
    public void buildPartB() {
        product.add("部件Y");
    }

    @Override
    public Product getResult() {
        return product;
    }
}

// 指挥者类：指挥建造的过程
class Director {

    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
```

## 优点

> 使得建造代码和表示代码分离，由于建造者隐藏了该产品是如何组装的，所以若需要改变一个产品的内部表示，只需要在定义一个具体的建造者即可

> 用户只需指定需要建造的类型就可以得到它们，而具体建造的过程和细节就不需知道了

## 使用动机

> 当需要将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示，可以考虑使用

> 主要用于创建一些复杂的对象，这些对象内部构建间的建造顺序通常是稳定的，但对象内部的构建通常面临着复杂的变化

## 总结

> 建造者模式是在当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时适用的模式



