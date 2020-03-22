/**
 * 
 */
package java6601.lesson07;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author CAI
 *
 */
public class BookFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	static final int SCROLL_YES = 1;
	static final int SCROLL_NO = 0;
	private Box labelBox, textBox, upBox, box; // Box����
	private JLabel lblName, lblBook;
	private JTextField txtName;
	private JButton btnOk;
	private JTextArea txtBook; // �ı���
	private JScrollPane scrollP; // �������
	private int scroll;

	public BookFrame6601(int scroll) {
		super("XXXX����Ǽ�");
		this.scroll = scroll;
		this.setSize(400, 380);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI();
		this.setVisible(true);
	}

	private void initUI() {
		lblName = new JLabel("ͼ������:");
		lblBook = new JLabel("ͼ����:");
		labelBox = Box.createVerticalBox();
		labelBox.add(lblName);
		labelBox.add(Box.createVerticalStrut(60));
		labelBox.add(lblBook);
		labelBox.add(Box.createVerticalGlue());

		txtName = new JTextField("Java�߼�����", 20);
		// ���ı�����\r\n��\n�����Ի���
		txtBook = new JTextArea("\r\n\r\n��1��\r\n��2��\r\n��3��\r\n��4��\r\n��5��", 10, 20);
		txtBook.setLineWrap(true);// �Զ����У�������ˮƽ������
		textBox = Box.createVerticalBox();
		textBox.add(txtName);
		
		textBox.add(Box.createVerticalStrut(6));
		if (this.scroll == SCROLL_NO) {
			testWithout();// ��ʹ�ù������
		} else {
			testScroll();// ʹ�ù������
		}

		upBox = Box.createHorizontalBox();
		upBox.add(labelBox);
		upBox.add(Box.createHorizontalStrut(10));
		upBox.add(textBox);

		btnOk = new JButton("�ύ");

		box = Box.createVerticalBox();
		box.add(upBox);
		box.add(Box.createVerticalStrut(10));
		box.add(btnOk);
		
		// box.setBorder(BorderFactory.createTitledBorder("������"));
		this.setLayout(new FlowLayout());
		this.add(box);
	}

	private void testScroll() {
		// TODO Auto-generated method stub
		scrollP = new JScrollPane(txtBook);
		textBox.add(scrollP);

	}

	private void testWithout() {
		// TODO Auto-generated method stub
		textBox.add(txtBook);
	}

	public static void main(String[] args) {
		new BookFrame6601(BookFrame6601.SCROLL_YES);
	}
}
