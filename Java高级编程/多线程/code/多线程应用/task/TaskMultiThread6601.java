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
 * ��������
 * 
 * @author CAI
 *
 */
public class TaskMultiThread6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblTitle, lblMove;
	private String titles[] = { "C�������", "Java�������������", "Java�߼�����" };
	private int moveX = 5;

	public TaskMultiThread6601() {
		super("���̶߳�����6601");
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
		lblTitle.setFont(new Font("����", Font.BOLD, 40));
		lblTitle.setBounds(80, 60, 460, 50);

		lblMove = new JLabel("��ӭ������ũ��԰����ϵ�ˣ�211906601-��ά��");
		lblMove.setFont(new Font("����", Font.PLAIN, 14));
		lblMove.setForeground(Color.RED);
		lblMove.setBounds(0, 200, 350, 25);

		p.add(lblTitle);
		p.add(lblMove);
		this.add(p);
	}

	// �޸�������ɫ�����ݵ��߳�������
	public void setLabel6601() {
		while (true) {
			for (int i = 0; i < titles.length; i++) {
				// 1.�޸ı�ǩ��ʾ������
				lblTitle.setText(titles[i]);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}

				// 2.�޸ı�ǩ����ʾ��ɫ�����ѡ��һ����ɫ
				int r = (int) (Math.random() * 256);
				int g = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);

				lblTitle.setForeground(new Color(r, g, b));
			}
		}
	}

	// Timer��ʱ������ǩ�����ƶ�
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
