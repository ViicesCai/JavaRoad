# 备忘录模式（Memento）

> 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可以将该对象恢复到原先保存的状态

``` java
/**
 * 备忘录模式
 *
 * @author CAI
 * @time 2020/11/12
 */
public class MementoPattern {

    public static void main(String[] args) {
        // 创建信息
        Originator originator = new Originator();
        originator.setState("On");
        originator.show();

        // 保存该信息
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());

        // 改变当前信息
        originator.setState("Off");
        originator.show();

        // 恢复信息
        originator.setMemento(caretaker.getMemento());
        originator.show();
    }
}

/**
 * 发起人类：
 * 负责创建一个备忘录：Memento，用以记录当前时刻它的内部状态，并可以使用备忘录恢复内部状态
 * Originator：可根据需要决定 Memento 存储 Originator 的哪些内部状态
 */
class Originator {
    private String state; // 需要保存的属性：可能有多个

    public Memento createMemento() { // 创建备忘录，将需要保存的信息存入 Memento 对象内
        return new Memento(state);
    }

    public void setMemento(Memento memento) { // 恢复备忘录，将 Memento 的信息导入，用于恢复
        state = memento.getState();
    }

    public void show() { // 显示信息
        System.out.println("State = " + state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 备忘录类：
 * 负责存储 Originator 对象的内部状态，并可防止 Originator 以外的其他对象访问备忘录 Memento
 * 备忘录有两个接口，Caretaker 只能看到备忘录的窄接口，它只能将备忘录传递给其他对象
 * Originator 能够看到一个宽接口，允许它访问返回先前状态所需的所有数据
 */
class Memento {
    private String state;

    public Memento(String state) { // 将信息导入
        this.state = state;
    }

    public String getState() { // 获取保存的信息
        return state;
    }
}

/**
 * 管理者类：
 * 负责保存好备忘录 Memento，不能对备忘录的内容进行操作或检查
 */
class Caretaker {
    private Memento memento;

    // 获取或设置备忘录
    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
```

## 使用时机

> 适用于功能比较复杂，但需要维护或记录属性历史的类

> 如果在某个系统中使用命令模式时，需要实现命令的撤销功能，那么命令模式可以使用备忘录模式来存储可撤销操作的状态

> 当角色的状态改变的时候，有可能这个状态无效，这时候就可以使用暂时存储起来的备忘录将状态复原

## 总结

> 该模式有些过时

> 将保存的细节封装在 Memento 中，哪一天需要更改保存的细节也不用影响客户端