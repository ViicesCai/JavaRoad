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
 * ����Ӵ���
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
		super("6601ʵ��������");
		this.setSize(600, 300);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
	}

	private void initUI6601() {
		p = new JPanel();
		lblWindow = new JLabel("�뵥����ť�򿪴���");
		lblWindow.setFont(new Font("����", Font.PLAIN, 24));
		p.add(lblWindow);
		this.add(p);

		// Box ����
		box = Box.createHorizontalBox();
		btnBook = new JButton("ͼ���б�");
		btnEventDatas = new JButton("����¼�");
		btnMove = new JButton("������ƶ����");
		btnCacl = new JButton("������v1.0");
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
	 * Ϊ��ťע������¼�
	 */
	private void eventHandler6601() {
		/**
		 * ͼ���б�ť
		 */
		btnBook.addActionListener((e) -> {
			// ���ⴰ���ظ��������������������ж�
			// �жϸô����Ƿ��Ѿ�����
			if (windowBook == null) {
				// δ����������£����´�����ΪҪ��ʾ���Ӵ���
				windowBook = new ActionEventFrame6601();

			} else {
				windowBook.setVisible(true);
			}

			// �жϴ���״̬
			// ���Ϊ��С��������Ϊ����״̬
			if (windowBook.getState() == ICONIFIED) {
				windowBook.setState(NORMAL);
			}

			// �رհ�ťֻ�رոô���
			windowBook.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
			});

		/**
		 * ����¼���ť
		 */
		btnEventDatas.addActionListener((e) -> {
			// ���ⴰ���ظ��������������������ж�
			// �жϸô����Ƿ��Ѿ�����
			if (windowEvent == null) {
				// δ����������£����´�����ΪҪ��ʾ���Ӵ���
				windowEvent = new MouseEventDatas6601();

			} else {
				windowEvent.setVisible(true);
			}

			// �жϴ���״̬
			// ���Ϊ��С��������Ϊ����״̬
			if (windowEvent.getState() == ICONIFIED) {
				windowEvent.setState(NORMAL);
			}

			// �رհ�ťֻ�رոô���
			windowEvent.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});

		/**
		 * ����ƶ���ť
		 */
		btnMove.addActionListener((e) -> {
			// ���ⴰ���ظ��������������������ж�
			// �жϸô����Ƿ��Ѿ�����
			if (windowMove == null) {
				// δ����������£����´�����ΪҪ��ʾ���Ӵ���
				windowMove = new MoveFrame6601();

			} else {
				windowMove.setVisible(true);
			}

			// �жϴ���״̬
			// ���Ϊ��С��������Ϊ����״̬
			if (windowMove.getState() == ICONIFIED) {
				windowMove.setState(NORMAL);
			}

			// �رհ�ťֻ�رոô���
			windowMove.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});

		/**
		 * ��������ť
		 */
		btnCacl.addActionListener((e) -> {
			// ���ⴰ���ظ��������������������ж�
			// �жϸô����Ƿ��Ѿ�����
			if (windowCacl == null) {
				// δ����������£����´�����ΪҪ��ʾ���Ӵ���
				windowCacl = new TaskCalculator6601();

			} else {
				windowCacl.setVisible(true);
			}
			
			// �жϴ���״̬
			// ���Ϊ��С��������Ϊ����״̬
			if (windowCacl.getState() == ICONIFIED) {
				windowCacl.setState(NORMAL);
			}

			// �رհ�ťֻ�رոô���
			windowCacl.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		});
	}

	public static void main(String[] args) {
		new TaskMain6601();
		
	}
}
