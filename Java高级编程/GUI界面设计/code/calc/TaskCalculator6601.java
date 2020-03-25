package java6601.calc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 计算器
 * 
 * @author CAI
 *
 */
public class TaskCalculator6601 extends JFrame {
	private static final long serialVersionUID = -2483248511457097960L; // 验证版本一致
	private JTextField resultTextField; // 计算结果文本框
	private JButton clearButton; // 清空按钮
	private JPanel p1, p2; // 中间容器

	/**
	 * 设置窗体参数
	 */
	public TaskCalculator6601() {
		super("计算器");
		this.setSize(230, 230);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮
		initUI6601();
		this.setVisible(true); // 窗体可见

		// 设置窗口大小不可改变
		this.setResizable(false);
	}

	/**
	 * 初始化组件
	 */
	private void initUI6601() {
		p1 = new JPanel();
		clearButton = new JButton("清空");
		resultTextField = new JTextField(13);

		resultTextField.setEditable(false); // 不能直接在文本框输入

		p1.add(resultTextField);
		p1.add(clearButton);

		this.add(p1, BorderLayout.NORTH); // 设置 P1 布局方式

		p2 = new JPanel(new GridLayout(4, 4)); // 设置按钮的布局方式

		// 存储计算器中按钮上的文字，文字的排列与排列顺序一致
		String name = "789+456-123*0.=/";
		ButtonClick6601 click = new ButtonClick6601();

		// 通过循环语句在窗体上添加按钮
		for (int i = 0; i < name.length(); i++) {
			JButton button = new JButton("" + name.charAt(i));
			button.addActionListener(click); // 注册按钮监听
			p2.add(button);
		}

		clearButton.addActionListener(click);

		this.add(p2, BorderLayout.CENTER); // 设置 P2 布局方式
	}

	public static void main(String[] args) {
		new TaskCalculator6601(); // 启动窗口
	}

	class ButtonClick6601 implements ActionListener {
		private OperationFactory operationFactory; // 运算器工厂
		private double number1; // 数字1
		private double number2; // 数字2
		private boolean flag; // 判断是否输入第二个数
		private boolean numValidity; // 判断数字的合法性
		private boolean isOperation; // 执行的是否执行运算器
		private boolean temporary; // 判断是否使用临时区域

		// 初始化参数
		public ButtonClick6601() {
			super();
			operationFactory = null;
			number1 = 0;
			number2 = 0;
			flag = true; // true 代表未输入第二个数
			numValidity = true;
			isOperation = false;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand(); // 通过获取按钮上的文本，判断执行的功能

			// 清空功能
			if (buttonName == "清空") {
				resultTextField.setText(""); // 清空文本框

				// 将所有的成员变量重置
				operationFactory = null;
				number1 = 0;
				number2 = 0;
				flag = false;
				numValidity = true;
				isOperation = false;

				return;

			} else if (Character.isDigit(buttonName.charAt(0)) || buttonName.equals(".")) {
				// 生成显示数据
				genNum(buttonName.charAt(0));

			} else {
				// 执行运算符
				operator(buttonName.charAt(0));
			}
		}

		/**
		 * 运算功能
		 * 
		 * @param ch - 运算符
		 */
		public void operator(char ch) {
			// 检测数据的合法性
			// 如果运算的数不合法，则提前结束（数字包含两个及以上.不执行运算）
			if (!numValidity) {
				return;
			}

			// 计算结果
			if (ch == '=') {
				// 未执行过运算操作，则 运算器为空
				// 即用户刚启动计算器未作任何操作运算 直接按等号的情况
				if (operationFactory == null) {
					// 如果未指定运算符，则原样输出
					resultTextField.setText(String.valueOf(number1));

				} else {
					// 创建运算器工厂
					Operation operation = operationFactory.createOperation();

					// 指定运算的数
					operation.number1 = number1;
					operation.number2 = number2;

					// 显示运算的结果
					double result = operation.getResult();
					resultTextField.setText(String.valueOf(result));

					// 将运算的结果赋给num1，用作连续计算
					number1 = result;
				}

				// 执行的是等号，所以判断是否为运算器 否
				isOperation = false;

			} else {
				// 用户没有输入，将第二个数清零
				// 这里的作用是将 switch 中修改的 number2 恢复过来
				if (flag) {
					if (temporary) {
						number2 = 0;
						// 重置了 number2，则 temporary 为 false
						temporary = false;
					}
				}

				// 先判断是否执行过运算操作
				if (isOperation) {
					// flag 用于判断是否输入了第二个数
					// 这里的主要作用是当用户进行多次输入 却未执行等号的情况
					// 即 1 + 2 + 3 这种情况
					if (!flag) {
						// 将第二个数 赋给 第一个
						number1 = number2;
						
						// 这时候将 第二数置零 重新等待用户输入
						// 如果连续运算的运算符号是 * 或者 / 则将第二个数暂时置 1
						if (ch == '*' || ch == '/') {
							number2 = 1;
							temporary = true;
							
						} else {
							number2 = 0;
						}
						
					} else {
						// 这里的作用是如果用户执行了多次的运算符操作 却没有输入第二个值的情况下
						// 即 +-*/= 这系列的操作 需要关闭这个通道
						// 否则会与多次输入的情况下 冲突
						isOperation = false;
					}
				}

				// 选择执行的运算符
				switch (ch) {
				case '+':
					operationFactory = new AddFactory();
					break;

				case '-':
					operationFactory = new SubFactory();
					break;

				case '*':
					operationFactory = new MulFactory();
					// 为了保证第二个数没输入的情况下 输出的是原来的数
					// 需要将 第二个数 暂时设置成 1
					if (!isOperation) {
						number2 = 1;
						// temporary 用于鉴别是否使用了临时值，这里的 1 就是一个临时的值
						temporary = true;
					}
					break;

				case '/':
					operationFactory = new DivFactory();
					if (!isOperation) {
						number2 = 1;
						temporary = true;
					}
					break;
				}

				// 执行完运算器 则为 true
				isOperation = true;
			}
			
			// 执行过运算器功能，可以继续输入数字则为 true
			flag = true;
		}

		/**
		 * 生成用作计算的数
		 * 
		 * @param ch - [1-9 .]的按钮
		 */
		public void genNum(char ch) {
			// 如果是 .
			if (ch == '.') {
				// 先判断是否存在 .
				// 在判断输入的数据中是否存在 . 表示是否添加过
				if (!resultTextField.getText().contains(".")) {
					numValidity = true;

				} else {
					// 当数据中已经存在 . 并且继续 添加 . 时，则 数据不合法
					numValidity = false;
				}

				// 在原有的数据上添加 .
				resultTextField.setText(resultTextField.getText() + ch);

			} else {
				// flag 作为判断是否输数字
				// 即当用户点击操作符时 如果继续输入数字 则覆盖以后的数字
				if (flag) {
					resultTextField.setText("" + ch);
					// 这里的flag 用于判断显示数据的覆盖操作，也作为鉴别输入是否完成的标志
					flag = false;

				} else {
					resultTextField.setText(resultTextField.getText() + ch);
				}

				// 如果数据不合法，则不进行转换 否则会报错
				if (!numValidity) {
					return;
				}

				// 获取当前的数值
				double number = Double.parseDouble(resultTextField.getText());

				// 通过判断用户是否执行 运算符来判断 输入的数
				// 执行了运算符 则输入的是第二个数
				if (isOperation) {
					number2 = number;

				} else {
					number1 = number;
				}
			}
		}
	}
}
