# 工厂方法模式（Factory Method）

> 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使用一个类的实例化延迟到其子类

``` java
package edu.fdzc.patterns.factorymethod;

/**
 * 工厂方法模式
 *
 * @author CAI
 * @time 2020/9/2
 */
public class FactoryMethod {

    /**
     * 抽象产品：提供了产品的接口
     */
    abstract interface Product {
        void show();
    }

    /**
     * 具体产品A：实现产品接口
     */
    class ConcreteProduct implements Product {

        @Override
        public void show() {
            System.out.println("具体产品");
        }
    }

    /**
     * 抽象工厂：提供产品的生产方法
     */
    abstract interface AbstractFactory {
        Product createProduct();
    }

    class ConcreteFactory implements AbstractFactory {

        @Override
        public Product createProduct() {
            return new ConcreteProduct();
        }
    }
}
```

## 与简单工厂模式的区别

+ 克服了简单工厂违背开放-封闭原则的缺点，又保持了封装对象创建过程的优点

## 优缺点

+ 优点
  + 仅需要具体的工厂名就可以得到产品，无需知道产品具体的创建过程
  + 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则
+ 缺点：每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，提高了代码量