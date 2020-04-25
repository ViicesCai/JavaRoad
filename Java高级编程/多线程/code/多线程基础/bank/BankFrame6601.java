/**
 * 
 */
package java6601.bank;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * 使用多线程模拟银行账户的存取款
 * 
 * @author CAI
 *
 */
public class BankFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblBalance;
	private JTextField textBankID, textBalance;
	private JTextArea jtaMoney, jtaResult;
	private int[] moneys;// 存储左边的文本域中输入的存、取款金额
	private BankAccount6601 account;
	private int threadCount; // 存取款线程的总数

	public BankFrame6601() {
		super("多线程同步(引用方法)6601");
		this.setSize(760, 460);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		initUI6601();
		this.setVisible(true);
		threadCount = 0;
	}

	private void initUI6601() {
		JLabel lblBankID = new JLabel("银行帐号ID");
		textBankID = new JTextField("1002103", 16);
		JLabel lblBalance0 = new JLabel("初始余额");
		textBalance = new JTextField("100", 16);
		JPanel p1 = new JPanel();
		p1.add(lblBankID);
		p1.add(textBankID);
		p1.add(lblBalance0);
		p1.add(textBalance);
		p1.setBorder(new TitledBorder("银行账户基本信息"));

		JLabel lblMoney = new JLabel("<html>请逐行输入<br>存取款金额</html>");
		jtaMoney = new JTextArea("100\n-120\n-100\n300\n500\n-700", 10, 14);
		JScrollPane scrollP1 = new JScrollPane(jtaMoney);

		JLabel lblResult = new JLabel("运行结果");
		jtaResult = new JTextArea(10, 34);
		JScrollPane scrollP2 = new JScrollPane(jtaResult);

		JPanel p2 = new JPanel();
		p2.add(lblMoney);
		p2.add(scrollP1);
		p2.add(lblResult);
		p2.add(scrollP2);

		lblBalance = new JLabel("");

		Box box1 = Box.createVerticalBox();
		box1.add(p1);
		box1.add(p2);
		box1.add(lblBalance);

		JPanel p3 = new JPanel();
		p3.add(box1);
		this.add(p3);

		JButton btnStart = new JButton("Start");
		JPanel p4 = new JPanel();
		p4.add(btnStart);
		this.add(p4, BorderLayout.SOUTH);

		btnStart.addActionListener(this::start6601);
	}

	public void start6601(ActionEvent e) {
		// 防止线程未执行完毕，重复操作
		if (threadCount == 0) {
			jtaResult.setText("");
			lblBalance.setText("");

			// 根据银行账号与初始余额 创建银行账户对象
			account = new BankAccount6601(textBankID.getText(), Integer.parseInt(textBalance.getText()));

			String[] text = jtaMoney.getText().split("\n");
			moneys = new int[text.length];
						
			for (int i = 0; i < moneys.length; i++) {
				moneys[i] = Integer.parseInt(text[i]);
				threadCount++; // 统计输入的每一笔金额 
				new Thread(this::run6601, i + 1 +"").start();		
			}
		}
	}

	public void run6601() {
			String name = Thread.currentThread().getName(); // 获取当前线程的名字
			int index = Integer.parseInt(name); // 元素下标即线程名
			int money = moneys[index - 1]; // 获取对应的金额
			boolean isDone = false; // 判断存取款操作是否成功
			
			// 同步块：同步 保证 account的修改是有序的
			synchronized (account) {
				
			// 通过 money 判断：存入 取出
			if (money > 0) {
				jtaResult.append(String.format("线程%s:存款%d\n", name, money));
				isDone = true;
				
			} else if (money < 0) {
				// 取款时余额不足：操作失败
				if (Math.abs(money) >= account.getBalance()) {
					jtaResult.append(String.format("线程%s:取款%d失败，余额不足(%d)\n", name, Math.abs(money), account.getBalance()));
					isDone = false;
					
				} else {
					jtaResult.append(String.format("线程%s:取款%d\n", name, Math.abs(money)));
					isDone = true;
				}
			}
			
			// 延迟1秒：方便观察线程的运行
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			// 操作成功：更新当前的余额
			if (isDone) {
				account.setBalance(account.getBalance() + money);
			}
		}
		
		threadCount--; // 操作完成后，处理的金额总数 - 1
		
		// 当所有金额都处理完成，显示当前的余额
		if (threadCount == 0) {
			lblBalance.setText("总金额：" + account.getBalance());
		}
	}

	public static void main(String[] args) {
		new BankFrame6601();
	}
}
