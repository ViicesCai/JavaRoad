# GUI界面设计

## 1. 什么是GUI

### Graphical User Interface

+ 图形用户界面

## 2.Java的GUI编程

重点在事件处理机制

+ java.awt 包 

  JDK 1.0

  + 依赖于本地系统

+ javax.swing 包

  JDK 1.2

  + 完全由Java实现

    与平台无关

+ 可视化工具

  + Eclipse 中 WindowBuilder 插件
  + NetBean 等其他开发工具

## 3.GUI编程：窗口

### 第一个窗口（JFrame）

```java
// 创建窗体对象
JFrame frame = new JFrame();
// 显示
frame.setVisible(true);
```

###  窗体中的元素

``` java
// 创建按钮
Button btnNewButton = new JButton("New button");
```

### 如何使用Java中的组件构成窗口

+ 容器：各种窗口

  + 一个特殊的组件，用来包含其他的组件，是一个大集合

+ 基本组件：窗口中的元素

  + 图形界面的最小单位，里面不再包含其他的成分

  如：按钮、文本框

  必须添加到容器中

### 使用J开头的swing组件

+ 最顶层的类统称为组件

+ 层次结构

  + Component

    + Container

      + 组件

      + JComponent

        + 基本组件

        + Jlabel
        + JTextField
        + JTextArea
        + JPanel

      + 容器

      + Panel

      + ScrollPanel

      + Window

        + Frame
          + JFrame
        + Dialog
          + JDialog
        + JWindow

### GUI界面

+ 以窗口为使用单位

+ 窗口的结构

  + 由各种基本组件组成
    + Jlabel
    + JTextField
    + JTextArea
    + JPanel

+ 组成

  + 顶级容器
    + Window
    + JFrame
    + JDialog
  + 中间容器
    + JPanel
  + 基本组件

  顶级容器在最外面

  其次是中间容器放在顶级容器上

  最后是基本组件放在中间容器上

## 4.布局管理器

### 容器中组件如果排列与显示？

+ 使用布局管理器（java.awt）
+ setLayout()方法

``` java
JFrame f = new JFrame();
f.setTitle("窗口是容器");
f.setSize(260, 200);
f.setLocation(300, 300);
f.add(new JButton("确定") ,BorderLayout.NORTH);
f.add(new JButton("取消"));
f.setVisible(true);
```

### 四种AWT布局

+ BorderLayout 边界布局

  ``` java
  JFrame f = new JFrame();
  f.setTitle("窗口是容器");
  f.setSize(260, 200);
  f.setLocation(300, 300);
  // 同一区域添加多个组件，则覆盖
  f.add(new Button("居中"));
  f.add(new Button("中间填满"));
  f.add(new JButton("上-北") ,BorderLayout.NORTH);
  f.add(new JButton("下-南") ,BorderLayout.SOUTH);
  f.add(new JButton("左-西") ,BorderLayout.WEST);
  f.add(new JButton("右-东") ,BorderLayout.EAST);
  f.setVisible(true);
  ```

+ FlowLayout 流式布局

  ``` java
  JFrame f = new JFrame("Flowlayout");
  f.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 50));
  f.setSize(600, 240);
  f.setLocation(300, 200);
  f.add(new JButton("第一个按钮"));
  f.add(new JButton("第二个按钮"));
  f.add(new JButton("第三个按钮"));
  f.add(new JButton("第四个按钮"));
  f.add(new JButton("第五个按钮"));
  f.setVisible(true);
  ```

+ GridLayout 网格布局

  ```java
  JFrame f = new JFrame("GridLayout");
  f.setLayout(new GridLayout(3, 4));
  f.setSize(400, 200);
  f.setLocation(400, 300);
  f.add(new JButton("第一个按钮"));
  f.add(new JButton("第二个按钮"));
  f.add(new JButton("第三个按钮"));
  f.add(new JButton("第二个按钮后面"), 2);
  f.add(new JButton("第四个按钮"));
  f.add(new JButton("第五个按钮"));
  f.add(new JButton("第四个按钮后面"), 4);
  f.setVisible(true);
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  ```

  

+ null 布局

  ``` java
  JPanel p = new JPanel();
  p.setLayout(null);
  JButton okButton = new JButton("确定");
  okButton.setBounds(20, 40, 80, 30);
  JButton cannelButton = new JButton("取消");
  cannelButton.setSize(80, 30);
  cannelButton.setLocation(140, 40);
  p.add(okButton);
  p.add(cannelButton);
  		
  JFrame f = new JFrame("nullLayout");
  f.setSize(260, 260);
  f.setLocation(400, 300);
  f.add(p);
  f.setVisible(true);
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  ```

### Box 布局

+ BoxLayout(Container target, int axis)
  + 为第一个参数指定的容器创建BoxLayout布局管理器对象
  + 第二个参数指定排列方向
    + BoxLayout.X_AXIS：表示水平方向排列（行布局）
    + BoxLayout.Y_AXIS：表示垂直方向排列（列布局）

+ Box 容器

  + BoxLayout布局与Box 容器配套使用更方便

    ``` java
    JFrame frame = new JFrame("BOX");
    frame.setSize(260, 200);
    frame.setLocation(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    // 列布局的Box容器
    Box box = Box.createVerticalBox();
    box.add(new JButton("按钮1"));
    // 两个按钮之间垂直间距
    box.add(Box.createVerticalStrut(30));
    box.add(new JButton("按钮2"));
    // 可在两个按钮间伸缩
    box.add(Box.createVerticalGlue());
    box.add(new JButton("按钮3"));
    		
    frame.add(box);
    frame.setVisible(true);
    ```

## 5.简单的界面设计

### 窗口类的基本结构

使用 new 创建

``` java
// Demo.java
// 1.从Frame继承
public class Demo extends JFrame {
    // 2.主要的组件定义为成员变量
	private JButton okButton;
	
    // 3.构造方法
    // 窗口初始化，调用init()方法
    // 最后 setVisible、默认窗口关闭方法
	public Demo() {
		super("窗口标题");
		this.setSize(600, 500);
		this.setLocation(200, 100);
		init();
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    // 4.窗口中组件的初始化方法
    // new 组件
    // 设置外观，add到容器
	private void init() {
		okButton = new JButton("确定");
		this.add(okButton);
	}
	
	public static void main(String[] args) {
		new Demo(); // 创建窗口
	}
}
```

### 中间容器JPanel

使用 new 创建中间容器Jpanel

``` java
JFrame frame = new JFrame();
JPanel panel = new JPanel();
JButton button = new JButton();

// 输入组件
panel.add(button);
// 输出组件
frame.add(panel);

// 如何显示图片
JLabel label = new JLabel(new ImageIcon("..."));
panel.add(label);
```


