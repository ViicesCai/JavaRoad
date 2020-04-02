package java6601.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * �Ի����ص�
 * 
 * 
 * @author CAI
 *
 */
public class JDialogDemo6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JPanel p1, p2;
	JButton btnMod, btnNon, btnDialog;
	JDialog modDialog, nonDialog, dialog;
	JLabel lblMessage;

	public JDialogDemo6601() {
		super("6601�Ի�����ص�");
		this.setSize(500, 400);
		this.setLocation(200, 100);
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		initUI6601();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initUI6601() {
		p1 = new JPanel();
		String str = "<html><body><br><br>" + "1.�˽�Ի���ĸ��������Ӵ��ڵĹ�ϵ<br><br>" + "2.�Ƚ�ģ̬�ͷ�ģ̬�Ի���<br><br>"
				+ "3.��ά��-211906601<br><body><html>";

		lblMessage = new JLabel(str);
		lblMessage.setFont(new Font("����", Font.PLAIN, 24));
		p1.add(lblMessage);
		this.add(p1);

		p2 = new JPanel();
		btnMod = new JButton("ģ̬�Ի���");
		btnNon = new JButton("��ģ̬�Ի���");
		btnDialog = new JButton("�򵥶Ի���");

		p2.add(btnMod);
		p2.add(btnNon);
		p2.add(btnDialog);
		this.add(p2, BorderLayout.SOUTH);

		// ����ģ̬�Ի���
		String text1 = "<html><body>" + "��\"�Ի�����ص�\"Ϊ������<br><br>" + "��ά��-211906601<body><hmtl>";

		modDialog = new JDialog(this, "ģ̬�Ի���", true);
		modDialog.add(new JLabel(text1));
		modDialog.setBounds(260, 140, 300, 260);

		// ������ģ̬�Ի���
		nonDialog = new JDialog(this, "��ģ̬�Ի���", false);
		nonDialog.add(new JLabel(text1));
		nonDialog.setBounds(300, 200, 300, 200);

		// ������ģ̬���޸����ڵĶԻ���
		String text2 = "<html><body>" + "��ģ̬���޸�����<br><br>" + "��ά��-211906601<body><html>";
		dialog = new JDialog();
		dialog.setTitle("�򵥶Ի���");
		dialog.add(new JLabel(text2));
		dialog.setBounds(250, 200, 260, 180);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		btnMod.addActionListener((e) -> {
			modDialog.setVisible(true);
		});
		
		btnNon.addActionListener((e) -> {
			nonDialog.setVisible(true);
		});
		
		btnDialog.addActionListener((e) -> {
			dialog.setVisible(true);
		});

	}
	
	public static void main(String[] args) {
		new JDialogDemo6601();
	}
}
