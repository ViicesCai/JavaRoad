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
 * 对话框特点
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
		super("6601对话框的特点");
		this.setSize(500, 400);
		this.setLocation(200, 100);
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		initUI6601();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void initUI6601() {
		p1 = new JPanel();
		String str = "<html><body><br><br>" + "1.了解对话框的父窗口与子窗口的关系<br><br>" + "2.比较模态和非模态对话框。<br><br>"
				+ "3.蔡维恒-211906601<br><body><html>";

		lblMessage = new JLabel(str);
		lblMessage.setFont(new Font("黑体", Font.PLAIN, 24));
		p1.add(lblMessage);
		this.add(p1);

		p2 = new JPanel();
		btnMod = new JButton("模态对话框");
		btnNon = new JButton("非模态对话框");
		btnDialog = new JButton("简单对话框");

		p2.add(btnMod);
		p2.add(btnNon);
		p2.add(btnDialog);
		this.add(p2, BorderLayout.SOUTH);

		// 创建模态对话框
		String text1 = "<html><body>" + "以\"对话框的特点\"为父窗口<br><br>" + "蔡维恒-211906601<body><hmtl>";

		modDialog = new JDialog(this, "模态对话框", true);
		modDialog.add(new JLabel(text1));
		modDialog.setBounds(260, 140, 300, 260);

		// 创建非模态对话框
		nonDialog = new JDialog(this, "非模态对话框", false);
		nonDialog.add(new JLabel(text1));
		nonDialog.setBounds(300, 200, 300, 200);

		// 创建非模态，无父窗口的对话框
		String text2 = "<html><body>" + "非模态，无父窗口<br><br>" + "蔡维恒-211906601<body><html>";
		dialog = new JDialog();
		dialog.setTitle("简单对话框");
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
