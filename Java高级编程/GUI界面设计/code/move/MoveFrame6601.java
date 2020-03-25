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
 * ���������ƶ�
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
		super("6601������ƶ����");
		this.setBounds(300, 200, 500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initUI6601();
		this.setVisible(true);
	}

	private void initUI6601() {
		lblName = new JLabel("������");
		textName = new JTextField(20);
		btnMove = new JButton("Move");
		p = new JPanel();
		p.add(lblName);
		p.add(textName);
		p.add(btnMove);
		this.add(p);
		
		// ������������ 
		MyMouseAdapter6601 myMouseAdapter = new MyMouseAdapter6601();

		// Ϊ�¼�Դע���������
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
		private int currentX,currentY; // ����ĵ�ǰλ��
		private int oldX,oldY; // ��걻���ʱ��λ��
		
		@Override
		public void mousePressed(MouseEvent e) {
			// ��ȡ�¼�Դ���
			JComponent componet = (JComponent) e.getSource();
			// ��ȡ������ĵ�ǰ����
			this.currentX = componet.getX();
			this.currentY = componet.getY();
			
			// ��ȡ��걻���ʱ������
			this.oldX = e.getXOnScreen();
			this.oldY = e.getYOnScreen();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// �����ƶ����룬���ƶ�ʱ������ - ���ʱ������
			int moveX = e.getXOnScreen() - this.oldX;
			int moveY = e.getYOnScreen() - this.oldY;

			// ��ǰ����������� = ����ľ����� + �ƶ�����
			int newX = this.currentX + moveX;
			int newY = this.currentY + moveY;
			
			// ��ȡ�¼�Դ���
			JComponent componet = (JComponent) e.getSource();
			// �޸�����
			componet.setLocation(newX, newY);
		}
	}
}

