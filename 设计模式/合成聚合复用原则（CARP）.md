# 合成/聚合复用原则（CARP）

> 尽量使用合成/聚合，尽量不要使用类继承

> 合成和聚合都是关联的特殊种类。聚合表示一种弱的“拥有”关系，体现的是A对象可以包含B对象，但B对象不是A对象的一部分；合成则是一种强的“拥有”关系，体现了严格的部分和整体的关系，部分和整体的生命周期一样。如：手机有多个软件，软件和手机是部分和整体的关系，并且它们的生命周期是相同的，于是手机和软件就是合成关系。而手机有多个品牌，手机可能是某个品牌的，但是某个品牌可以有任意多的手机，所有手机和品牌是聚合关系

``` java
/**
 * 合成/聚合复用原则
 *
 * @author CAI
 * @time 2020/11/19
 */
public class carp {

    public static void main(String[] args) {
        HandsetBrand brand;
        brand = new HandsetBrandN();

        brand.setSoft(new HandsetGame());
        brand.run();

        brand.setSoft(new HandsetAddressList());
        brand.run();

        brand = new HandsetBrandM();

        brand.setSoft(new HandsetGame());
        brand.run();

        brand.setSoft(new HandsetAddressList());
        brand.run();
    }
}

// 手机软件接口
interface HandsetSoft {
    void run();
}

// 手机游戏类
class HandsetGame implements HandsetSoft {

    @Override
    public void run() {
        System.out.println("运行手机游戏");
    }
}

// 手机通讯类类
class HandsetAddressList implements HandsetSoft {

    @Override
    public void run() {
        System.out.println("运行手机通讯录");
    }
}

// 手机品牌类
abstract class HandsetBrand {
    protected HandsetSoft soft;

    public void setSoft(HandsetSoft soft) {
        this.soft = soft;
    }

    public abstract void run();
}

// 手机品牌N类
class HandsetBrandN extends HandsetBrand {

    @Override
    public void run() {
        System.out.println("品牌N");
        soft.run();
    }
}

// 手机品牌M类
class HandsetBrandM extends HandsetBrand {

    @Override
    public void run() {
        System.out.println("品牌M");
        soft.run();
    }
}
```

## 优点

> 优先使用对象的合成/聚合将有助于你保持每个类的被封装，并被集中在单个任务上。这样类和类继承层次会保持较小规模，并且不太可能增长为不可控制的庞然大物

## 总结

> 现在继续为手机增加一个S品牌，并增加一个“网抑云”

``` java
class HandsetBrandS extends HandsetBrand {

    @Override
    public void run() {
        System.out.println("品牌S");
        soft.run();
    }
}

class HandsetNetMusic implements HandsetSoft {

    @Override
    public void run() {
        System.out.println("运行网抑云");
    }
}
```

> 这样的设计不会修改原来的代码，而只是扩展类。优先使用对象和合成和聚合，而不是类继承，可以减少因继承造成的不必要的麻烦，其本质是：继承是一种强耦合的结构，父类变，子类必须要变。



