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
	// java���л����� ��֤�汾һ��
	private static final long serialVersionUID = -2483248511457097960L;
	private JTextField txtResult; // �ı���
	private JButton btnClear; // ��ť
	private JPanel p1, p2; // �м�����

	public TaskCalculator6601() {
		super("6601XXXX������");
		this.setSize(230, 230);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رհ�ť
		initUI6601();
		this.setVisible(true);
		// ���ô��ڴ�С���ɸı�
		this.setResizable(false);
	}

	private void initUI6601() {
		p1 = new JPanel();
		btnClear = new JButton("���");
		txtResult = new JTextField(13);

		p1.add(txtResult);
		p1.add(btnClear);
		this.add(p1, BorderLayout.NORTH);

		p2 = new JPanel(new GridLayout(4, 4));

		// ����һ���ַ������洢�������а�ť�ϵ����֣����ֵ�����������˳��һ��
		// Ŀ�ģ�ͨ��ѭ�����������Ӱ�ť
		String name = "789+456-123*0.=/";
		for (int i = 0; i < name.length(); i++) {
			p2.add(new JButton("" + name.charAt(i)));
		}
		this.add(p2, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new TaskCalculator6601(); // ������ǰ����
	}
}
