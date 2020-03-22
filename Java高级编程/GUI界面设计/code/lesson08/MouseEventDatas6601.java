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
	private static final long serialVersionUID = 1L; // ��֤�汾һ����
	
	private Box box;
	private JLabel labelName;
	private JTextField textName; // �ı����¼�Դ��
	private JButton btnAdd; // ��ť���¼�Դ��
	private JTextArea jtaMouse;// ��������ǰ׺jta��JTextArea�ļ�д���¼�Դ��
	private JScrollPane scrollP;
	private JPanel p;

	public MouseEventDatas6601() {
	    super("XXXX����¼��Ĳ���");
	    this.setLocation(300, 200);
	    this.setSize(400,340);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Font font = new Font("����", Font.PLAIN, 20);
	    UIManager.put("Button.font", font);
	    UIManager.put("Label.font", font);
	    UIManager.put("TextField.font", font);
	    UIManager.put("TextArea.font", font);
	    initUI6601();

	    this.setVisible(true);
	  }

	private void initUI6601() {
		labelName = new JLabel("ͼ������:");
		textName = new JTextField("Java�߼�����", 20);
		// ����.setMaximumSize�������ߴ�����в��ֵ�Box�У�TextField�Ŀ�ȾͲ����洰�ڴ�С�ı���仯
		textName.setMaximumSize(new Dimension(0, 30));
		btnAdd = new JButton("����");
		box = Box.createHorizontalBox();
		box.add(Box.createHorizontalGlue());
		box.add(labelName);
		box.add(Box.createHorizontalStrut(20));
		box.add(textName);
		box.add(Box.createHorizontalStrut(20));
		box.add(btnAdd);
		box.add(Box.createHorizontalGlue());
		this.add(box, BorderLayout.NORTH);

		jtaMouse = new JTextArea("���״̬", 8, 24);
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
			int n = e.getClickCount(); // �������
			String type = null; // ��������
			
			switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				type = "��";
				break;
				
			case MouseEvent.BUTTON2:
				type = "��";
				break;
				
			case MouseEvent.BUTTON3:
				type = "��";
				break;
			}
			
			jtaMouse.append("\n������" + type + "��" + n + "�Σ���");
			jtaMouse.append("��ǰλ�ã�" + e.getXOnScreen() + "," + e.getYOnScreen());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Object source = e.getSource(); // �¼�Դ����
			String where = null; // ������
			
			if (source instanceof JButton) {
				where = "��ť";
				
			} else if (source instanceof JTextField) {
				where = "�ı���";
				
			} else if (source instanceof JTextArea) {
				where = "�ı���";
			}
			
			if (where != null) {
				jtaMouse.append("\n��" + where + "�ϰ������");
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() instanceof JTextArea) {
				jtaMouse.append("\n�������ı���");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
