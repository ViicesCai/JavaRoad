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
	private static final long serialVersionUID = 1L; // ��֤�汾һ����

	// �������
	private Box box; // Box ����
	private JLabel labelName, labelBook; // ��ǩ
	private JTextField textName; // �ı���
	private JButton btnAdd; // ��ť
	private JTextArea textBook; // �ı���
	private JScrollPane scrollP; // �������
	private JPanel p; // �м�����

	//
	public ActionEventFrame6601() {
		super("6601ͼ���б�"); // ������
		this.setBounds(300, 200, 460, 300); // ���ڲ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ʵ�ֹرհ�ť
		// ����Ĭ������
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		
		initUI6601(); // ��ʼ�����
		this.setVisible(true); // ��ʾ����
	}

	/**
	 * ��ʼ����������
	 */
	private void initUI6601() {
		labelName = new JLabel("ͼ������:");
		textName = new JTextField("Java�߼�����", 20);
		// ����.setMaximumSize�������ߴ�����в��ֵ�Box�У�
		// TextField�Ŀ�ȾͲ����洰�ڴ�С�ı���仯
		textName.setMaximumSize(new Dimension(0, 30));
		btnAdd = new JButton("����");

		box = Box.createHorizontalBox(); // �����в��ֵ� Box ����
		box.add(Box.createHorizontalGlue()); // �ڶ�������
		box.add(labelName);
		box.add(Box.createHorizontalStrut(20)); // ���ˮƽ���
		box.add(textName);
		box.add(Box.createHorizontalStrut(20));
		box.add(btnAdd);
		box.add(Box.createHorizontalGlue());
		this.add(box, BorderLayout.NORTH);

		labelBook = new JLabel("ͼ���б�:");
		textBook = new JTextArea("�������������\n���ݿ�ԭ��\n���������", 8, 24);
		scrollP = new JScrollPane(textBook); // ����ı��򵽹�����壨�������й�������
		
		// �����м�����
		p = new JPanel();
		
		// �������ӵ��м�������
		p.add(labelBook);
		p.add(scrollP);

		// ���м�������ӵ�Frame
		this.add(p);

		// Ϊ���Ӱ�ť��ͼ�������ı���ָ������Action�¼��ļ�����
		MyListener6601 listener = new MyListener6601();
		btnAdd.addActionListener(listener); 
		textName.addActionListener(listener);
	}

	public static void main(String[] args) {

		new ActionEventFrame6601();
	}

	/**
	 * �ڲ���������
	 * �������¼����������ͼ������ӵ�ͼ���б�
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
