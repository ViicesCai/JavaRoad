/**
 * 
 */
package java6601.lesson09;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * �رմ��ڵķ���
 * 
 * @author CAI
 *
 */
public class WindowCloseFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JButton btnExit, btnDispose;
	private JPanel p1, p2;
	private JLabel lblClose;

	public static void main(String[] args) {
		new WindowCloseFrame6601();
	}

	public WindowCloseFrame6601() {
	    super("6601�رմ��ڵķ���");
	    this.setSize(600, 300);
	    this.setLocation(300, 200);
	    
	    //UIManager���ã����������д�����Ч
	    Font font = new Font("����", Font.PLAIN, 20);
	    UIManager.put("Label.font",font);
	    UIManager.put("Button.font", font);
	    initUI6601();
	    this.setVisible(true);
	    
	    // ����eventHandler��������¼����
	    eventHandler6601();
	  }

	private void initUI6601() {
		p1 = new JPanel();
		String str = "<html><body><br><br>" + "1.�Ƚ����ֹرմ��ڵķ�����Dispose��Exit<br><br>" + "2.�رմ���ǰ��ȷ�ϡ�<br><body><html>";
		lblClose = new JLabel(str);
		lblClose.setFont(new Font("����", Font.PLAIN, 24));
		p1.add(lblClose);
		this.add(p1);
		p2 = new JPanel();
		
		btnExit = new JButton("Exit");
		btnDispose = new JButton("Dispose");
		p2.add(btnDispose);
		p2.add(btnExit);
		this.add(p2, BorderLayout.SOUTH);
		
		// ʹ��������ע���������
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// i Ϊ�û���ѡ���һ������ N:1 Y:0
				int i = JOptionPane.showConfirmDialog(e.getWindow(), "�Ƿ�رմ��ڣ�", "6601 ϵͳ��ʾ", JOptionPane.YES_NO_OPTION);
				
				// ����û�ѡ������ ��ر�
				if (i == JOptionPane.OK_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * �����ڵ��¼��������
	 */
	private void eventHandler6601() {
		
		// Dispose ��ť���¼�����
		btnDispose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "<html><body>" + "211906601-��ά�� <br><br>" + "Dispose----�رյ�ǰ���ڣ��� <body><html>";
				JFrame f2 = new JFrame("��Dispose�رմ���");
				f2.setBounds(350, 240, 400, 180);
				f2.setVisible(true);
				f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f2.add(new JLabel(str));
			}
		});
		
		// Exit ��ť���¼�����
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "<html><body>" + "211906601-��ά�� <br><br>" + "Exit----�����������<body><html>";
				JFrame f2 = new JFrame("��Exit�رմ���");
				f2.setBounds(350, 240, 300, 180);
				f2.setVisible(true);
				f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f2.add(new JLabel(str));
			}
		});
	}
}
