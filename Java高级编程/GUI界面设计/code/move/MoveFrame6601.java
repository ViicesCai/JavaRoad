package java6601.move;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 组件随鼠标移动
 * 
 * @author CAI
 *
 */
public class MoveFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel p;
	private JButton btnMove;
	private JLabel lblName;
	private JTextField textName;

	public MoveFrame6601() {
		super("6601用鼠标移动组件");
		this.setBounds(300, 200, 500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initUI6601();
		this.setVisible(true);
	}

	private void initUI6601() {
		lblName = new JLabel("姓名：");
		textName = new JTextField(20);
		btnMove = new JButton("Move");
		p = new JPanel();
		p.add(lblName);
		p.add(textName);
		p.add(btnMove);
		this.add(p);
		
		// 创建监听对象 
		MyMouseAdapter6601 myMouseAdapter = new MyMouseAdapter6601();

		// 为事件源注册监听对象
		btnMove.addMouseListener(myMouseAdapter);
		btnMove.addMouseMotionListener(myMouseAdapter);
		lblName.addMouseListener(myMouseAdapter);
		lblName.addMouseMotionListener(myMouseAdapter);
		textName.addMouseListener(myMouseAdapter);
		textName.addMouseMotionListener(myMouseAdapter);
	}

	public static void main(String[] args) {
		new MoveFrame6601();
	}

	class MyMouseAdapter6601 extends MouseAdapter {
		private int currentX,currentY; // 组件的当前位置
		private int oldX,oldY; // 鼠标被点击时的位置
		
		@Override
		public void mousePressed(MouseEvent e) {
			// 获取事件源组件
			JComponent componet = (JComponent) e.getSource();
			// 获取该组件的当前坐标
			this.currentX = componet.getX();
			this.currentY = componet.getY();
			
			// 获取鼠标被点击时的坐标
			this.oldX = e.getXOnScreen();
			this.oldY = e.getYOnScreen();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// 鼠标的移动距离，即移动时的坐标 - 点击时的坐标
			int moveX = e.getXOnScreen() - this.oldX;
			int moveY = e.getYOnScreen() - this.oldY;

			// 当前组件的新坐标 = 组件的旧作标 + 移动距离
			int newX = this.currentX + moveX;
			int newY = this.currentY + moveY;
			
			// 获取事件源组件
			JComponent componet = (JComponent) e.getSource();
			// 修改坐标
			componet.setLocation(newX, newY);
		}
	}
}

