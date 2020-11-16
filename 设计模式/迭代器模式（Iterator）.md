# 迭代器模式（Iterator）

>  提供一种方法顺序访问一个聚合对象中各个元素，而又不暴露对象的内部表示

``` java
import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 *
 * @author CAI
 * @time 2020/11/16
 */
public class IteratorPattern {

    public static void main(String[] args) {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        aggregate.add("Jay");
        aggregate.add("Mary");
        aggregate.add("Jack");
        aggregate.add("Kay");

        Iterator iterator = new ConcreteIterator(aggregate);

        while (!iterator.isDone()) {
            System.out.println("你好：我是 " + iterator.currentItem());
            iterator.next();
        }
    }
}

interface Iterator {
    Object first();
    Object next();
    boolean isDone();
    Object currentItem();
}

interface Aggregate {
    Iterator createIterator();
}

class ConcreteIterator implements Iterator {
    private ConcreteAggregate aggregate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public Object first() {
        return aggregate.get(0);
    }

    @Override
    public Object next() {
        Object ret = null;
        current++;

        if (current < aggregate.count()) {
            ret = aggregate.get(current);
        }

        return ret;
    }

    @Override
    public boolean isDone() {
        return current >= aggregate.count() ? true : false;
    }

    @Override
    public Object currentItem() {
        return aggregate.get(current);
    }
}

class ConcreteAggregate implements Aggregate {
    private List<Object> items = new ArrayList<>();

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    public int count() {
        return items.size();
    }

    public Object get(int index) {
        return items.get(index);
    }

    public void add(Object obj) {
        items.add(obj);
    }
}
```

## 优点

> 当你需要对聚集有多种方式遍历时，可以考虑使用迭代器模式

## 使用时机

> 当你需要访问一个聚集对象，而且不管这些对象是什么都需要遍历的时候，可与考虑使用迭代器模式

> 需要对聚集有多种方式遍历时，可以考虑使用迭代器模式

> 为遍历不同的聚集结构提供如：开始、下一个、是否结束、当前项等统一接口

## 总结

> Java中：集合的迭代器就是这个模式的实现

> 分离了集合对象的遍历行为，抽象出一个迭代器类来负责，这样既可以做到不暴露集合的内部结构，又可以让外部代码透明的访问集合内部的数据