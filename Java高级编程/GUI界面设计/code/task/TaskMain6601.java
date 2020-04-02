package java6601.task;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java6601.calc.TaskCalculator6601;
import java6601.lesson08.ActionEventFrame6601;
import java6601.lesson08.MouseEventDatas6601;
import java6601.move.MoveFrame6601;

/**
 * 多个子窗口
 * 
 * @author CAI
 *
 */
public class TaskMain6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel p;
	private JLabel lblWindow;
	private Box box;
	private JButton btnBook, btnEventDatas, btnMove, btnCacl;
	private JFrame windowBook, windowEvent, windowMove, windowCacl;

	public TaskMain6601() {
		super("6601实验主窗口");
		this.setSize(600, 300);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
	}

	private void initUI6601() {
		p = new JPanel();
		lblWindow = new JLabel("请单击按钮打开窗口");
		lblWindow.setFont(new Font("黑体", Font.PLAIN, 24));
		p.add(lblWindow);
		this.add(p);

		// Box 布局
		box = Box.createHorizontalBox();
		btnBook = new JButton("图书列表");
		btnEventDatas = new JButton("鼠标事件");
		btnMove = new JButton("用鼠标移动组件");
		btnCacl = new JButton("计算器v1.0");
		box.add(Box.createHorizontalGlue());
		box.add(btnBook);
		box.add(Box.createHorizontalGlue());
		box.add(btnEventDatas);
		box.add(Box.createHorizontalGlue());
		box.add(btnMove);
		box.add(Box.createHorizontalGlue());
		box.add(btnCacl);
		box.add(Box.createHorizontalGlue());

		this.add(box, BorderLayout.SOUTH);
	}

	/**
	 * 为按钮注册监听事件
	 */
	private void eventHandler6601() {
		/**
		 * 图书列表按钮
		 */
		btnBook.addActionListener((e) -> {
			// 避免窗口重复创建，做出以下两种判断
			// 判断该窗口是否已经创建
			if (windowBook == null) {
				// 未创建的情况下，将新窗口作为要显示的子窗口
				windowBook = new ActionEventFrame6601();

			} else {
				windowBook.setVisible(true);
			}

			// 判断窗口状态
			// 如果为最小化，设置为正常状态
			if (windowBook.getState() == ICONIFIED) {
				windowBook.setState(NORMAL);
			}

			// 关闭按钮只关闭该窗口
			windowBook.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
			});

		/**
		 * 鼠标事件按钮
		 */
		btnEventDatas.addActionListener((e) -> {
			// 避免窗口重复创建，做出以下两种判断
			// 判断该窗口是否已经创建
			if (windowEvent == null) {
				// 未创建的情况下，将新窗口作为要显示的子窗口
				windowEvent = new MouseEventDatas6601();

			} else {
				windowEvent.setVisible(true);
			}

			// 判断窗口状态
			// 如果为最小化，设置为正常状态
			if (windowEvent.getState() == ICONIFIED) {
				windowEvent.setState(NORMAL);
			}

			// 关闭按钮只关闭该窗口
			windowEvent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});

		/**
		 * 鼠标移动按钮
		 */
		btnMove.addActionListener((e) -> {
			// 避免窗口重复创建，做出以下两种判断
			// 判断该窗口是否已经创建
			if (windowMove == null) {
				// 未创建的情况下，将新窗口作为要显示的子窗口
				windowMove = new MoveFrame6601();

			} else {
				windowMove.setVisible(true);
			}

			// 判断窗口状态
			// 如果为最小化，设置为正常状态
			if (windowMove.getState() == ICONIFIED) {
				windowMove.setState(NORMAL);
			}

			// 关闭按钮只关闭该窗口
			windowMove.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});

		/**
		 * 计算器按钮
		 */
		btnCacl.addActionListener((e) -> {
			// 避免窗口重复创建，做出以下两种判断
			// 判断该窗口是否已经创建
			if (windowCacl == null) {
				// 未创建的情况下，将新窗口作为要显示的子窗口
				windowCacl = new TaskCalculator6601();

			} else {
				windowCacl.setVisible(true);
			}
			
			// 判断窗口状态
			// 如果为最小化，设置为正常状态
			if (windowCacl.getState() == ICONIFIED) {
				windowCacl.setState(NORMAL);
			}

			// 关闭按钮只关闭该窗口
			windowCacl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});
	}

	public static void main(String[] args) {
		new TaskMain6601();
		
	}
}
