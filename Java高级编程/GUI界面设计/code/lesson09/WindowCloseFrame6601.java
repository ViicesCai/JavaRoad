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
 * 关闭窗口的方法
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
	    super("6601关闭窗口的方法");
	    this.setSize(600, 300);
	    this.setLocation(300, 200);
	    
	    //UIManager设置，对其后的所有窗口有效
	    Font font = new Font("宋体", Font.PLAIN, 20);
	    UIManager.put("Label.font",font);
	    UIManager.put("Button.font", font);
	    initUI6601();
	    this.setVisible(true);
	    
	    // 调用eventHandler方法完成事件编程
	    eventHandler6601();
	  }

	private void initUI6601() {
		p1 = new JPanel();
		String str = "<html><body><br><br>" + "1.比较两种关闭窗口的方法：Dispose和Exit<br><br>" + "2.关闭窗口前的确认。<br><body><html>";
		lblClose = new JLabel(str);
		lblClose.setFont(new Font("黑体", Font.PLAIN, 24));
		p1.add(lblClose);
		this.add(p1);
		p2 = new JPanel();
		
		btnExit = new JButton("Exit");
		btnDispose = new JButton("Dispose");
		p2.add(btnDispose);
		p2.add(btnExit);
		this.add(p2, BorderLayout.SOUTH);
		
		// 使用匿名类注册监听对象
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// i 为用户的选项，是一个整型 N:1 Y:0
				int i = JOptionPane.showConfirmDialog(e.getWindow(), "是否关闭窗口？", "6601 系统提示", JOptionPane.YES_NO_OPTION);
				
				// 如果用户选择了是 则关闭
				if (i == JOptionPane.OK_OPTION) {
					e.getWindow().dispose();
				}
			}
		});
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * 本窗口的事件处理程序
	 */
	private void eventHandler6601() {
		
		// Dispose 按钮的事件处理
		btnDispose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "<html><body>" + "211906601-蔡维恒 <br><br>" + "Dispose----关闭当前窗口！！ <body><html>";
				JFrame f2 = new JFrame("用Dispose关闭窗口");
				f2.setBounds(350, 240, 400, 180);
				f2.setVisible(true);
				f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f2.add(new JLabel(str));
			}
		});
		
		// Exit 按钮的事件处理
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "<html><body>" + "211906601-蔡维恒 <br><br>" + "Exit----程序结束！！<body><html>";
				JFrame f2 = new JFrame("用Exit关闭窗口");
				f2.setBounds(350, 240, 300, 180);
				f2.setVisible(true);
				f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f2.add(new JLabel(str));
			}
		});
	}
}
