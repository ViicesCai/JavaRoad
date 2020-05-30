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

//聊天室客户端
public class MoreChatClient6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// 聊天结束语
	private static final String QUIT = "**QUIT**";
	Socket socket;
	PrintWriter printer;
	Scanner reader;
	JTextArea jtaMessage;
	JTextField txtSay;
	JButton btnSay;
	String name = "";

	public MoreChatClient6601() throws IOException {
		super("6601聊天窗口 V1.0");
		// 各种初始化操作
		Font font = new Font("宋体", Font.PLAIN, 16);
		UIManager.put("Label.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);

		connect6601();
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 启动接收服务器消息的线程
		new Thread(this::receiveMessage6601).start();
	}

	// 与服务器建立连接
	private void connect6601() {
		boolean stop = false;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("chatClient6601.properties"));
			String ip = prop.getProperty("ip");
			int port = Integer.parseInt(prop.getProperty("port"));
			while (name.trim().isEmpty()) {
				name = JOptionPane.showInputDialog(this, "请输入昵称");
				if (name == null)
					System.exit(0);
			}

			socket = new Socket(ip, port);
			// 包装Socket的输入流与输出流，用于接收和发送字符串
			printer = new PrintWriter(socket.getOutputStream());
			reader = new Scanner(socket.getInputStream());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "找不到配置文件!");
			stop = true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "不能与服务器连接!");
			stop = true;
		}
		if (stop) {
			System.exit(0);
		}
	}

	// 窗口界面设计
	private void initUI6601() {
		JScrollPane scrollP = new JScrollPane(jtaMessage = new JTextArea());
		jtaMessage.setEditable(false);
		this.add(scrollP);

		JPanel p = new JPanel();
		p.add(new JLabel(name + "："));
		p.add(txtSay = new JTextField(40));
		p.add(btnSay = new JButton("发送"));
		this.add(p, BorderLayout.SOUTH);

		this.setSize(520, 360);
		this.setLocation(200, 300);
	}

	// 窗口的事件处理
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

	// 把消息发送给服务器
	private void say(ActionEvent e) {
		String strMsg = txtSay.getText();
		if (!strMsg.trim().isEmpty()) {
			printer.println(name + " ==> " + strMsg);
			printer.flush();

			txtSay.setText("");
		}
	}

	// 退出聊天
	private void quit() {
		int result = JOptionPane.showConfirmDialog(this, "是否退出当前聊天？", "请确认", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			// 发送结束语
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

	// 从服务器接收其他客户端发送消息的线程体
	public void receiveMessage6601() {
		try {

			while (true) {
				jtaMessage.append(reader.nextLine() + "\n");
			}

		} catch (Exception e) {
			System.out.println(name + "已下线!");
		}
	}

	public static void main(String args[]) throws IOException {
		new MoreChatClient6601();
	}
}
