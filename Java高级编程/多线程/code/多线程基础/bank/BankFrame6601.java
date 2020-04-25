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
 * ʹ�ö��߳�ģ�������˻��Ĵ�ȡ��
 * 
 * @author CAI
 *
 */
public class BankFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel lblBalance;
	private JTextField textBankID, textBalance;
	private JTextArea jtaMoney, jtaResult;
	private int[] moneys;// �洢��ߵ��ı���������Ĵ桢ȡ����
	private BankAccount6601 account;
	private int threadCount; // ��ȡ���̵߳�����

	public BankFrame6601() {
		super("���߳�ͬ��(���÷���)6601");
		this.setSize(760, 460);
		this.setLocation(300, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		initUI6601();
		this.setVisible(true);
		threadCount = 0;
	}

	private void initUI6601() {
		JLabel lblBankID = new JLabel("�����ʺ�ID");
		textBankID = new JTextField("1002103", 16);
		JLabel lblBalance0 = new JLabel("��ʼ���");
		textBalance = new JTextField("100", 16);
		JPanel p1 = new JPanel();
		p1.add(lblBankID);
		p1.add(textBankID);
		p1.add(lblBalance0);
		p1.add(textBalance);
		p1.setBorder(new TitledBorder("�����˻�������Ϣ"));

		JLabel lblMoney = new JLabel("<html>����������<br>��ȡ����</html>");
		jtaMoney = new JTextArea("100\n-120\n-100\n300\n500\n-700", 10, 14);
		JScrollPane scrollP1 = new JScrollPane(jtaMoney);

		JLabel lblResult = new JLabel("���н��");
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
		// ��ֹ�߳�δִ����ϣ��ظ�����
		if (threadCount == 0) {
			jtaResult.setText("");
			lblBalance.setText("");

			// ���������˺����ʼ��� ���������˻�����
			account = new BankAccount6601(textBankID.getText(), Integer.parseInt(textBalance.getText()));

			String[] text = jtaMoney.getText().split("\n");
			moneys = new int[text.length];
						
			for (int i = 0; i < moneys.length; i++) {
				moneys[i] = Integer.parseInt(text[i]);
				threadCount++; // ͳ�������ÿһ�ʽ�� 
				new Thread(this::run6601, i + 1 +"").start();		
			}
		}
	}

	public void run6601() {
			String name = Thread.currentThread().getName(); // ��ȡ��ǰ�̵߳�����
			int index = Integer.parseInt(name); // Ԫ���±꼴�߳���
			int money = moneys[index - 1]; // ��ȡ��Ӧ�Ľ��
			boolean isDone = false; // �жϴ�ȡ������Ƿ�ɹ�
			
			// ͬ���飺ͬ�� ��֤ account���޸��������
			synchronized (account) {
				
			// ͨ�� money �жϣ����� ȡ��
			if (money > 0) {
				jtaResult.append(String.format("�߳�%s:���%d\n", name, money));
				isDone = true;
				
			} else if (money < 0) {
				// ȡ��ʱ���㣺����ʧ��
				if (Math.abs(money) >= account.getBalance()) {
					jtaResult.append(String.format("�߳�%s:ȡ��%dʧ�ܣ�����(%d)\n", name, Math.abs(money), account.getBalance()));
					isDone = false;
					
				} else {
					jtaResult.append(String.format("�߳�%s:ȡ��%d\n", name, Math.abs(money)));
					isDone = true;
				}
			}
			
			// �ӳ�1�룺����۲��̵߳�����
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			
			// �����ɹ������µ�ǰ�����
			if (isDone) {
				account.setBalance(account.getBalance() + money);
			}
		}
		
		threadCount--; // ������ɺ󣬴���Ľ������ - 1
		
		// �����н�������ɣ���ʾ��ǰ�����
		if (threadCount == 0) {
			lblBalance.setText("�ܽ�" + account.getBalance());
		}
	}

	public static void main(String[] args) {
		new BankFrame6601();
	}
}
