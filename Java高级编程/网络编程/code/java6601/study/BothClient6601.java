/**
 * 
 */
package java6601.study;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

/**
 * 客户端：双人聊天
 * 
 * @author CAI
 *
 */
public class BothClient6601 {
	Socket client;
	PrintStream printer;
	Scanner reader;
	int port;
	boolean isStopSend = false;

	public static void main(String[] args) throws IOException {
		new BothClient6601();
	}

	public BothClient6601() throws IOException {
		System.out.println("Client");
		
		// 1. 读取配置文件中的服务器端 ip 地址及端口号
		Properties prop = new Properties();
		prop.load(new FileInputStream("bothClient6601.properties"));
		String ip = prop.getProperty("ip");
		
		// 记下服务端的端口号--->port
		port = Integer.parseInt(prop.getProperty("port"));
		
		// 按给定的IP和端口号向服务器端发送连接请求
		client = new Socket(ip, port);
		
		// 显示当前的端口号：socket.getLocalPort()
		System.out.println("当前端口号：" + client.getLocalPort());
		
		// 将Socket中的输出字节流包装为打印流--->printer
		printer = new PrintStream(client.getOutputStream());
		
		// 用Socket中的输入字节流初始化Scanner--->reader
		reader = new Scanner(client.getInputStream());
				
		// 启动用于接收信息的线程
		new Thread(this::receive6601).start();
		
		// 启动用于发送信息的线程
		new Thread(this::send6601).start();
	}

	// 发送信息的线程体
	public void send6601() {
		String msg = "";
		Scanner input = new Scanner(System.in);
		
		while (!isStopSend) {
			// 键盘输入要发送的信息--->msg
			msg = input.nextLine();
			
			// 发送msg
			printer.println(msg);
			
			// 如果发送end，停止线程：break
			if ("end".equals(msg)) {
				
				try {
					client.close();
					break;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 接收信息的线程体
	public void receive6601() {
		String msg = "";
		
		try {
			while (true) {
				// 接收信息--->msg
				msg = reader.nextLine();
				System.out.println(port + "：" + msg);
				// 显示服务端的端口号、msg
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
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (NoSuchElementException e) {
			System.out.println("Client结束");
		}
	}
}
