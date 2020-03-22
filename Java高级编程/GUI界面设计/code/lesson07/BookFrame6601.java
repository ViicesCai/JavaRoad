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
	private Box labelBox, textBox, upBox, box; // Box容器
	private JLabel lblName, lblBook;
	private JTextField txtName;
	private JButton btnOk;
	private JTextArea txtBook; // 文本域
	private JScrollPane scrollP; // 滚动面板
	private int scroll;

	public BookFrame6601(int scroll) {
		super("XXXX新书登记");
		this.scroll = scroll;
		this.setSize(400, 380);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI();
		this.setVisible(true);
	}

	private void initUI() {
		lblName = new JLabel("图书名称:");
		lblBook = new JLabel("图书简介:");
		labelBox = Box.createVerticalBox();
		labelBox.add(lblName);
		labelBox.add(Box.createVerticalStrut(60));
		labelBox.add(lblBook);
		labelBox.add(Box.createVerticalGlue());

		txtName = new JTextField("Java高级开发", 20);
		// 在文本域中\r\n或\n都可以换行
		txtBook = new JTextArea("\r\n\r\n第1章\r\n第2章\r\n第3章\r\n第4章\r\n第5章", 10, 20);
		txtBook.setLineWrap(true);// 自动折行，不再有水平滚动条
		textBox = Box.createVerticalBox();
		textBox.add(txtName);
		
		textBox.add(Box.createVerticalStrut(6));
		if (this.scroll == SCROLL_NO) {
			testWithout();// 不使用滚动面板
		} else {
			testScroll();// 使用滚动面板
		}

		upBox = Box.createHorizontalBox();
		upBox.add(labelBox);
		upBox.add(Box.createHorizontalStrut(10));
		upBox.add(textBox);

		btnOk = new JButton("提交");

		box = Box.createVerticalBox();
		box.add(upBox);
		box.add(Box.createVerticalStrut(10));
		box.add(btnOk);
		
		// box.setBorder(BorderFactory.createTitledBorder("面板标题"));
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
