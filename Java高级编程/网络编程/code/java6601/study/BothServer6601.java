/**
 * 
 */
package java6601.study;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 服务器端：双人聊天
 * 
 * @author CAI
 *
 */
public class BothServer6601 {

	ServerSocket server; // 服务器端
	Socket client; // 客户端
	Scanner reader;
	PrintStream printer;
	int clientPort;
	boolean isStopSend = false;

	public static void main(String[] args) throws IOException {
		new BothServer6601();
	}

	public BothServer6601() throws IOException {
		System.out.println("Server");
		
		// 1. 创建服务器端，并指定端口
		server = new ServerSocket(18888);
		
		// 2. 等待客户端发送连接请求
		client = server.accept();
		
		// 3. 当接收到客户端的连接请求，在控制台上显示客户端IP地址和端口号
		// 记下客户端的端口号
		clientPort = client.getPort();
		
		// 将 Client 中的输出字节流包装为打印流
		printer = new PrintStream(client.getOutputStream());
		
		// 用 Client 中的输入字节流初始化Scanner
		reader = new Scanner(client.getInputStream());
		
		// 首先发送一句打招呼的用语
		printer.println("您好，你已成功连接服务器");
		
		// 在控制台上显示客户端的IP和端口号
		System.out.println(client.getLocalAddress().getHostAddress() + ":" + clientPort + "，已连接");
		
		// 启动用于接收信息的线程：用方法引用this::receiveMsgXXX
		new Thread(this::receiveMsg6601).start();
		
		// 启动用于发送信息的线程：用方法引用this::sendMsgXXX
		new Thread(this::sendMsg6601).start();
	}

	// 发送信息的线程体
	public void sendMsg6601() {
		// 向客户端发送信息
		String msg = "";
		Scanner input = new Scanner(System.in);
		
		while (!isStopSend) {
			// 键盘输入要发送的信息
			msg = input.nextLine();
			
			// 发送msg
			printer.println(msg);
			
			// 如果发送end，停止线程：break
			if ("end".equals(msg)) {
				isStopSend = true;
				
				try {
					client.close();
					break;

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		input.close();
	}

	// 接收信息的线程体
	public void receiveMsg6601() {
		String msg = "";
		
		try {
			while (true) {
				// 接收信息--->msg
				msg = reader.nextLine();
				
				// 显示客户端的端口号、msg
				System.out.println(clientPort + "：" + msg);
				
				if ("end".equals(msg)) {
					// 让本端的发送线程停止：isStopSend设为true
					isStopSend = true;
					
					// 提示按回车结束
					System.out.println("按回车键结束");
					break;
				}
			}
			
			reader.close();
			printer.close();
			client.close();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (NoSuchElementException e) {
			System.out.println("Server结束");
		}
	}

	public BothServer6601(ServerSocket server, Socket client, Scanner reader,
			PrintStream printer, int clientPort,boolean isStopSend) {
		super();
		this.server = server;
		this.client = client;
		this.reader = reader;
		this.printer = printer;
		this.clientPort = clientPort;
		this.isStopSend = isStopSend;
	}
}
