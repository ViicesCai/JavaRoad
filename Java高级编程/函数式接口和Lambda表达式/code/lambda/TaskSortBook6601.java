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
	
	private Book6601[] books; // Book 对象数组
	private JPanel pSort;
	private JList<Book6601> listBooks; // 显示 Books 中的内容

	public TaskSortBook6601() {
		super("6601对象排序");
		setBooks6601();
		this.setSize(540, 480);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initUI6601();
		this.setVisible(true);
	}

	/**
	 * 初始化组件即布局设计
	 */
	private void initUI6601() {
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("RadioButton.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("List.font", new Font("宋体", Font.PLAIN, 30));

		JPanel p0 = new JPanel();
		p0.add(new JLabel("图书清单：ID,图书名称,单价,数量"));
		this.add(p0, BorderLayout.NORTH);

		// 图书清单（books数组）用JList显示
		listBooks = new JList<Book6601>(books);
		JPanel p = new JPanel();
		p.add(listBooks);
		this.add(p, BorderLayout.CENTER);

		// 调用addJRadioButton()方法
		addJRadioButton6601();
		this.add(pSort, BorderLayout.SOUTH);
	}

	/**
	 * 初始化 Books
	 */
	private void setBooks6601() {
		// 创建要显示的图书清单：books数组
		books = new Book6601[] { 
				new Book6601("A1001", "挪威的森林", 20, 5),
				new Book6601("B1003", "且听风吟", 18, 3),
				new Book6601("D1001", "1Q84", 23, 8),
				new Book6601("A4001", "百年孤独", 21, 10),
				new Book6601("B1001", "1984", 17, 7),
				new Book6601("C2001", "围城", 13, 5),
				new Book6601("B1101", "边城", 16, 6),
				new Book6601("A1011", "动物凶猛", 19, 5),
				new Book6601("C1001", "人间词话", 13, 9)
		};
	}
	
	/**
	 * 单选按钮的点击事件
	 * 
	 * @param e 事件源
	 */
	private void actionPerformed(ActionEvent e) {
		String name = e.getActionCommand(); // 获取事件源的文本
		Comparator<Book6601> com = null; // 接口变量
		
		// 按选择的按钮进行排序
		switch (name) {
		case "ID":
			com = (o1, o2) -> o1.getID().compareTo(o2.getID());
			break ;
			
		case "图书名称":
			com = (o1, o2) -> {
				Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
				return cmp.compare(o1.getName(), o2.getName());
			};
			break;
			
		case "单价":
			com = (o1, o2) -> o1.getPrice() - o2.getPrice();
			break;
			
		case "数量":
			com = (o1, o2) -> o1.getNumber() - o2.getNumber();
			break;
		}
		
		Arrays.sort(books, com);
		listBooks.setListData(books);
	}
	
	private void addJRadioButton6601() {
		String[] sorts = new String[] { "ID", "图书名称", "单价", "数量" };
		JRadioButton radioButton = null;
		pSort = new JPanel();
		pSort.add(new JLabel("请选择排序方式："));
		ButtonGroup group = new ButtonGroup();
		for (String text : sorts) {
			radioButton = new JRadioButton(text);
			pSort.add(radioButton);
			// 颜色的RadioButton放在同一组中,否则不会互相排斥
			group.add(radioButton);
			radioButton.addActionListener(this::actionPerformed);
		}
	}

	public static void main(String[] args) {
		new TaskSortBook6601();
	}
}
