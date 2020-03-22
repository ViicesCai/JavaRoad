# GUI ：基于事件的编程方法

在组件上的某个操作触发事件，执行监听接口中的一个方法

## 1.理解事件处理机制

JVM实现（事件处理流程）

### 监听：不停的监听其关注的对象（一个提交的按钮）

+ 捕获：一旦发现事件发生，将其捕获，记录现场情况（用户点击了此按钮）
+ 通知：调用某个方法进行相应的处理（跳转到相应的函数方法）

## 2.事件处理的编程步骤

+ 确定事件源与要处理的事件

+ 事件监听器：为某个事件，创建事件监听器

  实现监听接口、为事件处理器方法编写代码

+ 注册监听：为某个事件源指定事件监听器

## 3.Action 事件

+ 事件源：按钮、文本框、菜单项

  操作：单击按钮、文本框回车

+ 事件：ActionEvent

+ 监听接口：ActionListener

  actionPerformed() 方法

+ 注册方法：addActionListener() 方法

  接口作为方法的参数

## 4.鼠标事件

### 事件源：几乎所有组件

鼠标操作

### 事件：MouseEvent

+ 封装与鼠标操作有关的数据

+ getSource()、getClickCount()、getButton：事件源对象
+ getXOnScreen()、getYOnScreen：绝对坐标
+ getX、getY：相对坐标

### 监听接口

+ MouseListener(5个方法)

+ MouseMotionListener(2个方法)

  方法的参数：MouseEvent 封装了事件发生时的相关信息

### 事件适配器

+ MouseAdapter(7个方法)

+ MouseMotionAdapter(2个方法)

  注册方法

  + addMouseListener()方法
  + addMouseMotionListener()方法

## 5.与事件相关的类和方法

### 事件：XxxxEvent

java.awt.event包

+ 事件处理器的参数

+ 封装事件发生时的信息

  保留现场

### 监听接口：XxxxListener

方法：事件处理器

+ 只有一个方法：Lambda表达式

+ 多于一个方法：事件适配类XxxxAdapter

  为接口的每个方法提供空实现

### 注册方法：addXxxxListener()

事件源