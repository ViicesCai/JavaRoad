package java6601.chat;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

//�����ҿͻ���
public class MoreChatClient6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// ���������
	private static final String QUIT = "**QUIT**";
	Socket socket;
	PrintWriter printer;
	Scanner reader;
	JTextArea jtaMessage;
	JTextField txtSay;
	JButton btnSay;
	String name = "";

	public MoreChatClient6601() throws IOException {
		super("6601���촰�� V1.0");
		// ���ֳ�ʼ������
		Font font = new Font("����", Font.PLAIN, 16);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);

		connect6601();
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// �������շ�������Ϣ���߳�
		new Thread(this::receiveMessage6601).start();
	}

	// ���������������
	private void connect6601() {
		boolean stop = false;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("chatClient6601.properties"));
			String ip = prop.getProperty("ip");
			int port = Integer.parseInt(prop.getProperty("port"));
			while (name.trim().isEmpty()) {
				name = JOptionPane.showInputDialog(this, "�������ǳ�");
				if (name == null)
					System.exit(0);
			}

			socket = new Socket(ip, port);
			// ��װSocket��������������������ڽ��պͷ����ַ���
			printer = new PrintWriter(socket.getOutputStream());
			reader = new Scanner(socket.getInputStream());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�Ҳ��������ļ�!");
			stop = true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "���������������!");
			stop = true;
		}
		if (stop) {
			System.exit(0);
		}
	}

	// ���ڽ������
	private void initUI6601() {
		JScrollPane scrollP = new JScrollPane(jtaMessage = new JTextArea());
		jtaMessage.setEditable(false);
		this.add(scrollP);

		JPanel p = new JPanel();
		p.add(new JLabel(name + "��"));
		p.add(txtSay = new JTextField(40));
		p.add(btnSay = new JButton("����"));
		this.add(p, BorderLayout.SOUTH);

		this.setSize(520, 360);
		this.setLocation(200, 300);
	}

	// ���ڵ��¼�����
	private void eventHandler6601() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});
		btnSay.addActionListener(this::say);
		txtSay.addActionListener(this::say);
	}

	// ����Ϣ���͸�������
	private void say(ActionEvent e) {
		String strMsg = txtSay.getText();
		if (!strMsg.trim().isEmpty()) {
			printer.println(name + " ==> " + strMsg);
			printer.flush();

			txtSay.setText("");
		}
	}

	// �˳�����
	private void quit() {
		int result = JOptionPane.showConfirmDialog(this, "�Ƿ��˳���ǰ���죿", "��ȷ��", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			// ���ͽ�����
			printer.println(QUIT + name);
			printer.flush();
			try {
				reader.close();
				printer.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}

	// �ӷ��������������ͻ��˷�����Ϣ���߳���
	public void receiveMessage6601() {
		try {

			while (true) {
				jtaMessage.append(reader.nextLine() + "\n");
			}

		} catch (Exception e) {
			System.out.println(name + "������!");
		}
	}

	public static void main(String args[]) throws IOException {
		new MoreChatClient6601();
	}
}
