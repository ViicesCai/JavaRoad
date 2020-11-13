# 组合模式（Composite）

> 将对象组合成树形结构以表示‘部分-整体’的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性

``` java
import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式：透明方式
 *
 * @author CAI
 * @time 2020/11/13
 */
public class CompositePattern {
    public static void main(String[] args) {
        Composite root = new Composite("root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));

        root.add(comp);

        Composite comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));

        root.add(comp2);

        root.add(new Leaf("Leaf C"));

        Leaf leaf = new Leaf("Leaf D");
        root.add(leaf);
        root.remove(leaf);

        root.display(0);
    }
}

/**
 * 组件接口：用于访问和管理 Component 的子部件
 */
interface Component {
    void add(Component component);
    void remove(Component component);
    void display(int depth);
}

/**
 * 叶节点类
 */
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    // 叶子节点没有在增加分支，故add 和 remove 没有实际意义
    // 但实现组件接口可以消除叶节点和枝节点在抽象层次的区别，它们具备一致的接口
    @Override
    public void add(Component component) {
        System.out.println("Cannot add to leaf");
    }

    @Override
    public void remove(Component component) {
        System.out.println("Cannot remove from a leaf");
    }

    @Override
    public void display(int depth) { // 显示名称与级别
        String ch = "";
        for (int i = 0; i < depth; i++) {
            ch += "-";
        }

        System.out.println(ch + name);
    }
}

/**
 * 枝节点类
 */
class Composite implements Component {
    private List<Component> children = new ArrayList<>(); // 存放子节点
    private String name;

    public Composite(String name) {
        this.name = name;
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void display(int depth) { // 显示自己的节点名称，并遍历子节点
        String ch = "";
        for (int i = 0; i < depth; i++) {
            ch += "-";
        }

        System.out.println(ch + name);

        for (Component component : children) {
            component.display(depth + 2);
        }
    }
}
```

## 实现方式

### 透明方式

> 在 Component 中声明用来管理子对象的方法，其中包括`add`、`remove`
>
> 这样实现 Component 接口的所有子类都具备了 `add`和`remove`

#### 优点

> 叶节点和枝节点对于外界没有区别

#### 缺点

> Leaf 类本身不具备`add`和`remove`的功能，实现是没有意义的

### 安全方式

> Component 接口不声明`add`和`remove`方法，那么 Leaf 就不需要实现它，而是在 Composite 声明所有用来管理子类对象的方法

#### 优点

> 解决了透明方式的缺点

#### 缺点

> 不够透明，所有枝节点和叶节点不具有相同的接口，客户端调用时需要做相应的判断，带来了不便

## 优点

> 组合模式定义抱恨了基本对象和组合对象的类层次结构；基本对象可以被组合对象组合成更复杂的组合对象，而这个组合对象又可以被组合，这样不断地递归下去，客户代码中，任何用到基本对象的地方都可以使用组合对象
>
> 用户不必关系到底是处理一个基本对象或是处理一个组合对象，也就不用为定义组合对象而写一些判断语句了
>
> 组合模式让客户可以一致地使用组合结构和单个对象

## 使用时机

> 当需求中时体现部分与整体层次的结构时，或希望用户可以忽略组合对象与单个对象的不同，统一地使用组合结构中的所有对象时，可以考虑使用

## 总结

> 整体和部分可以被一致对待