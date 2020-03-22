/**
 * 
 */
package java6601.lesson08;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * @author CAI
 *
 */
public class ActionEventFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L; // 验证版本一致性

	// 定义组件
	private Box box; // Box 容器
	private JLabel labelName, labelBook; // 标签
	private JTextField textName; // 文本框
	private JButton btnAdd; // 按钮
	private JTextArea textBook; // 文本域
	private JScrollPane scrollP; // 滚动面板
	private JPanel p; // 中间容器

	//
	public ActionEventFrame6601() {
		super("6601图书列表"); // 窗口名
		this.setBounds(300, 200, 460, 300); // 窗口参数
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 实现关闭按钮
		// 设置默认字体
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		
		initUI6601(); // 初始化组件
		this.setVisible(true); // 显示窗体
	}

	/**
	 * 初始化窗体的组件
	 */
	private void initUI6601() {
		labelName = new JLabel("图书名称:");
		textName = new JTextField("Java高级开发", 20);
		// 调用.setMaximumSize设置最大尺寸后，在行布局的Box中，
		// TextField的宽度就不会随窗口大小改变而变化
		textName.setMaximumSize(new Dimension(0, 30));
		btnAdd = new JButton("增加");

		box = Box.createHorizontalBox(); // 创建行布局的 Box 容器
		box.add(Box.createHorizontalGlue()); // 在顶部伸缩
		box.add(labelName);
		box.add(Box.createHorizontalStrut(20)); // 添加水平间距
		box.add(textName);
		box.add(Box.createHorizontalStrut(20));
		box.add(btnAdd);
		box.add(Box.createHorizontalGlue());
		this.add(box, BorderLayout.NORTH);

		labelBook = new JLabel("图书列表:");
		textBook = new JTextArea("面向对象程序设计\n数据库原理\n计算机网络", 8, 24);
		scrollP = new JScrollPane(textBook); // 添加文本域到滚动面板（让他具有滚动条）
		
		// 创建中间容器
		p = new JPanel();
		
		// 将组件添加到中间容器中
		p.add(labelBook);
		p.add(scrollP);

		// 将中间容器添加到Frame
		this.add(p);

		// 为增加按钮、图书名称文本框指定监听Action事件的监听器
		MyListener6601 listener = new MyListener6601();
		btnAdd.addActionListener(listener); 
		textName.addActionListener(listener);
	}

	public static void main(String[] args) {

		new ActionEventFrame6601();
	}

	/**
	 * 内部监听器类
	 * 负责处理事件，将输入的图书名添加到图书列表
	 * 
	 * @author CAI
	 *
	 */
	class MyListener6601 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			textBook.append("\n" + textName.getText());
		}
	}
}
