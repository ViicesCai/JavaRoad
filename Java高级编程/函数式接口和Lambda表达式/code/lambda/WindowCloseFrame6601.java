package java6601.lesson10;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 
 * 
 * @author CAI
 *
 */
public class WindowCloseFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnExit, btnDispose;

	public static void main(String[] args) {
		new WindowCloseFrame6601();
	}

	public WindowCloseFrame6601() {
		super("�رմ��ڵķ���");
		this.setSize(600, 300);
		this.setLocation(300, 200);
		// UIManager���ã����������д�����Ч
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		initUI6601();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		eventHandler6601();
	}

	private void initUI6601() {
		JPanel p1 = new JPanel();
		String str = "<html><body><br><br>" + "1.�Ƚ����ֹرմ��ڵķ�����Dispose��Exit<br><br>" + "2.�رմ���ǰ��ȷ�ϡ�<br><body><html>";
		JLabel lblClose = new JLabel(str);
		lblClose.setFont(new Font("����", Font.PLAIN, 24));
		p1.add(lblClose);
		this.add(p1);
		JPanel p2 = new JPanel();
		btnExit = new JButton("Exit");
		btnDispose = new JButton("Dispose");
		p2.add(btnDispose);
		p2.add(btnExit);
		this.add(p2, BorderLayout.SOUTH);
	}

	/**
	 * dispose ��ťʵ�ֵĹ���
	 * 
	 * @param e �¼�Դ
	 */
	private static void dispose6601(ActionEvent e) {
		JFrame f2 = new JFrame("��Dispose�رմ���");
		f2.setBounds(350, 240, 400, 180);
		f2.setVisible(true);
		f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f2.add(new JLabel("Dispose----�رյ�ǰ���ڣ���"));
	}
	
	/**
	 * exit ��ťʵ�ֵĹ���
	 * 
	 * @param e �¼�Դ
	 */
	private static void exit6601(ActionEvent e) {
		JFrame f1 = new JFrame("��Exit�رմ���");
		f1.setBounds(400, 240, 300, 200);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.add(new JLabel("Exit----�����������"));
	}
	
	private void eventHandler6601() {
		btnDispose.addActionListener(WindowCloseFrame6601::dispose6601);
		btnExit.addActionListener(WindowCloseFrame6601::exit6601);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(e.getWindow(), "�Ƿ�رմ��ڣ�", "ϵͳ��ʾ", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.OK_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
	}
}
