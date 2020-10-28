# 简单工厂模式（SimpleFactory）

> 又称为静态工厂方法（Static Factory），属于类创建型模式

+ 在简单工厂模式中，可以根据参数的不同返回不同类型的实例
+ 简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类

## 优缺点

+ 优点：实现对象创建和使用的分离，创建交由专门的工厂类负责，客户端不需要关心如何创建，只需考虑怎么使用
+ 缺点：不够灵活，如果新增一个新的工厂类则需要修改相关的判断逻辑

``` java
/**
 * 简单工厂模式
 *
 * @author CAI
 * @time 2020/9/1
 */
public class SimpleFactory {

    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ProductA();

        } else {
            return new ProductB();
        }
    }

    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct("A");
        product.print();
    }
}

/**
 * 抽象产品类
 */
abstract class Product {
    public abstract void print();
}

class ProductA extends Product {

    @Override
    public void print() {
        System.out.println("产品A");
    }
}

class ProductB extends Product {
    
    @Override
    public void print() {
        System.out.println("产品B");
    }
}
```



