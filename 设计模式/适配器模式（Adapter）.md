# 适配器模式（Adapter）

> Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作

``` java
/**
 * 适配器模式
 *
 * @author CAI
 * @time 2020/10/20
 */
public class AdapterPattern {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}

/**
 * 目标接口：客户所期待的接口
 */
interface Target {
    public void request();
}

/**
 * 需要适配的类
 */
class Adaptee {
    public void specificRequest() {
        System.out.println("特殊请求");
    }
}

/**
 * 适配器类：把源接口转换成目标接口
 */
class Adapter implements Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
```

> 系统的数据和行为都正确，但接口不符时，我们应该考虑用适配器，目的是使控制范围之外的一个原有对象与某个接口匹配。适配器模式主要应用于希望复用一些现存的类，但是接口又与复用环境要求不一致的情况。

## 使用时机

> 使用一个已存在的类，但如果他的接口，也就是他的方法和你的要求不相同时就应该考虑使用适配器模式

