/**
 * 
 */
package java6601.task;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 多任务窗体
 * 
 * @author CAI
 *
 */
public class TaskMultiThread6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblTitle, lblMove;
	private String titles[] = { "C程序设计", "Java面向对象程序设计", "Java高级开发" };
	private int moveX = 5;

	public TaskMultiThread6601() {
		super("多线程多任务6601");
		initUI6601();
		this.setBounds(300, 300, 600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		new Thread(this::setLabel6601).start();

		Timer timer = new Timer(100, this::moveLabel6601);
		timer.start();
	}

	private void initUI6601() {
		JPanel p = new JPanel(null);
		lblTitle = new JLabel(titles[0]);
		lblTitle.setFont(new Font("黑体", Font.BOLD, 40));
		lblTitle.setBounds(80, 60, 460, 50);

		lblMove = new JLabel("欢迎加入码农乐园，联系人：211906601-蔡维恒");
		lblMove.setFont(new Font("宋体", Font.PLAIN, 14));
		lblMove.setForeground(Color.RED);
		lblMove.setBounds(0, 200, 350, 25);

		p.add(lblTitle);
		p.add(lblMove);
		this.add(p);
	}

	// 修改字体颜色和内容的线程任务体
	public void setLabel6601() {
		while (true) {
			for (int i = 0; i < titles.length; i++) {
				// 1.修改标签显示的内容
				lblTitle.setText(titles[i]);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

				// 2.修改标签的显示颜色：随机选择一种颜色
				int r = (int) (Math.random() * 256);
				int g = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);

				lblTitle.setForeground(new Color(r, g, b));
			}
		}
	}

	// Timer定时器：标签左右移动
	public void moveLabel6601(ActionEvent e) {
		int x = lblMove.getX() + moveX;
		
		if (x <= 0) {
			moveX = -moveX;

		} else if (x >= this.getWidth() - lblMove.getWidth()) {
			x = this.getWidth() - lblMove.getWidth();
			moveX = -moveX;
		}

		lblMove.setLocation(x, lblMove.getY());

		try {
			Thread.sleep(100);

		} catch (InterruptedException e2) {
		}

	}

	public static void main(String[] args) {
		new TaskMultiThread6601();
	}
}
