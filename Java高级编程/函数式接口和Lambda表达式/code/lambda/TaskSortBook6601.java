package java6601.lambda;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class TaskSortBook6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Book6601[] books; // Book ��������
	private JPanel pSort;
	private JList<Book6601> listBooks; // ��ʾ Books �е�����

	public TaskSortBook6601() {
		super("6601��������");
		setBooks6601();
		this.setSize(540, 480);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initUI6601();
		this.setVisible(true);
	}

	/**
	 * ��ʼ��������������
	 */
	private void initUI6601() {
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("RadioButton.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("List.font", new Font("����", Font.PLAIN, 30));

		JPanel p0 = new JPanel();
		p0.add(new JLabel("ͼ���嵥��ID,ͼ������,����,����"));
		this.add(p0, BorderLayout.NORTH);

		// ͼ���嵥��books���飩��JList��ʾ
		listBooks = new JList<Book6601>(books);
		JPanel p = new JPanel();
		p.add(listBooks);
		this.add(p, BorderLayout.CENTER);

		// ����addJRadioButton()����
		addJRadioButton6601();
		this.add(pSort, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ�� Books
	 */
	private void setBooks6601() {
		// ����Ҫ��ʾ��ͼ���嵥��books����
		books = new Book6601[] { 
				new Book6601("A1001", "Ų����ɭ��", 20, 5),
				new Book6601("B1003", "��������", 18, 3),
				new Book6601("D1001", "1Q84", 23, 8),
				new Book6601("A4001", "����¶�", 21, 10),
				new Book6601("B1001", "1984", 17, 7),
				new Book6601("C2001", "Χ��", 13, 5),
				new Book6601("B1101", "�߳�", 16, 6),
				new Book6601("A1011", "��������", 19, 5),
				new Book6601("C1001", "�˼�ʻ�", 13, 9)
		};
	}
	
	/**
	 * ��ѡ��ť�ĵ���¼�
	 * 
	 * @param e �¼�Դ
	 */
	private void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand(); // ��ȡ�¼�Դ���ı�
		Comparator<Book6601> com = null; // �ӿڱ���
		
		// ��ѡ��İ�ť��������
		switch (name) {
		case "ID":
			com = (o1, o2) -> o1.getID().compareTo(o2.getID());
			break ;
			
		case "ͼ������":
			com = (o1, o2) -> {
				Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
				return cmp.compare(o1.getName(), o2.getName());
			};
			break;
			
		case "����":
			com = (o1, o2) -> o1.getPrice() - o2.getPrice();
			break;
			
		case "����":
			com = (o1, o2) -> o1.getNumber() - o2.getNumber();
			break;
		}
		
		Arrays.sort(books, com);
		listBooks.setListData(books);
	}
	
	private void addJRadioButton6601() {
		String[] sorts = new String[] { "ID", "ͼ������", "����", "����" };
		JRadioButton radioButton = null;
		pSort = new JPanel();
		pSort.add(new JLabel("��ѡ������ʽ��"));
		ButtonGroup group = new ButtonGroup();
		for (String text : sorts) {
			radioButton = new JRadioButton(text);
			pSort.add(radioButton);
			// ��ɫ��RadioButton����ͬһ����,���򲻻ụ���ų�
			group.add(radioButton);
			radioButton.addActionListener(this::actionPerformed);
		}
	}

	public static void main(String[] args) {
		new TaskSortBook6601();
	}
}
