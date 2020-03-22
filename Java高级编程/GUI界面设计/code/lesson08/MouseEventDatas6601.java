/**
 * 
 */
package java6601.lesson08;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
public class MouseEventDatas6601 extends JFrame {
	private static final long serialVersionUID = 1L; // 验证版本一致性
	
	private Box box;
	private JLabel labelName;
	private JTextField textName; // 文本框（事件源）
	private JButton btnAdd; // 按钮（事件源）
	private JTextArea jtaMouse;// 变量名的前缀jta是JTextArea的简写（事件源）
	private JScrollPane scrollP;
	private JPanel p;

	public MouseEventDatas6601() {
	    super("XXXX鼠标事件的参数");
	    this.setLocation(300, 200);
	    this.setSize(400,340);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Font font = new Font("宋体", Font.PLAIN, 20);
	    UIManager.put("Button.font", font);
	    UIManager.put("Label.font", font);
	    UIManager.put("TextField.font", font);
	    UIManager.put("TextArea.font", font);
	    initUI6601();

	    this.setVisible(true);
	  }

	private void initUI6601() {
		labelName = new JLabel("图书名称:");
		textName = new JTextField("Java高级开发", 20);
		// 调用.setMaximumSize设置最大尺寸后，在行布局的Box中，TextField的宽度就不会随窗口大小改变而变化
		textName.setMaximumSize(new Dimension(0, 30));
		btnAdd = new JButton("增加");
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(labelName);
		box.add(Box.createHorizontalStrut(20));
		box.add(textName);
		box.add(Box.createHorizontalStrut(20));
		box.add(btnAdd);
		box.add(Box.createHorizontalGlue());
		this.add(box, BorderLayout.NORTH);

		jtaMouse = new JTextArea("鼠标状态", 8, 24);
		scrollP = new JScrollPane(jtaMouse);
		
		MyListener listener = new MyListener();
		textName.addMouseListener(listener);
		btnAdd.addMouseListener(listener);
		jtaMouse.addMouseListener(listener);
		
		this.add(scrollP);

	}

	public static void main(String[] args) {

		new MouseEventDatas6601();
	}
	
	class MyListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int n = e.getClickCount(); // 点击次数
			String type = null; // 鼠标键类型
			
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				type = "左";
				break;
				
			case MouseEvent.BUTTON2:
				type = "中";
				break;
				
			case MouseEvent.BUTTON3:
				type = "右";
				break;
			}
			
			jtaMouse.append("\n点击鼠标" + type + "键" + n + "次！！");
			jtaMouse.append("当前位置：" + e.getXOnScreen() + "," + e.getYOnScreen());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Object source = e.getSource(); // 事件源对象
			String where = null; // 对象名
			
			if (source instanceof JButton) {
				where = "按钮";
				
			} else if (source instanceof JTextField) {
				where = "文本框";
				
			} else if (source instanceof JTextArea) {
				where = "文本域";
			}
			
			if (where != null) {
				jtaMouse.append("\n在" + where + "上按下鼠标");
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() instanceof JTextArea) {
				jtaMouse.append("\n鼠标进入文本域");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
