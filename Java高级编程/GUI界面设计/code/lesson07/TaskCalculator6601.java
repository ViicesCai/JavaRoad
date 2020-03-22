package java6601.lesson07;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class TaskCalculator6601 extends JFrame {
	// java序列化机制 验证版本一致
	private static final long serialVersionUID = -2483248511457097960L;
	private JTextField txtResult; // 文本框
	private JButton btnClear; // 按钮
	private JPanel p1, p2; // 中间容器

	public TaskCalculator6601() {
		super("6601XXXX计算器");
		this.setSize(230, 230);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮
		initUI6601();
		this.setVisible(true);
		// 设置窗口大小不可改变
		this.setResizable(false);
	}

	private void initUI6601() {
		p1 = new JPanel();
		btnClear = new JButton("清空");
		txtResult = new JTextField(13);

		p1.add(txtResult);
		p1.add(btnClear);
		this.add(p1, BorderLayout.NORTH);

		p2 = new JPanel(new GridLayout(4, 4));

		// 定义一个字符串，存储计算器中按钮上的文字，文字的排列与排列顺序一致
		// 目的：通过循环语句向窗体添加按钮
		String name = "789+456-123*0.=/";
		for (int i = 0; i < name.length(); i++) {
			p2.add(new JButton("" + name.charAt(i)));
		}
		this.add(p2, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new TaskCalculator6601(); // 启动当前窗口
	}
}
