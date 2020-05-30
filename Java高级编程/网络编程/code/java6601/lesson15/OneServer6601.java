/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器端：一对一双人会话
 * 
 * @author CAI
 *
 */
public class OneServer6601 {
	
	public static void main(String[] args) throws Exception {
		System.out.println("18888服务平台");
		
		// 1. 创建服务器
		ServerSocket server = new ServerSocket(18888);
		
		// 2. 阻塞式连接
		Socket client = server.accept();
		
		// 3. 操作		
		Scanner in = new Scanner(System.in);
		
		Scanner reader = new Scanner(client.getInputStream());
		PrintStream printer = new PrintStream(client.getOutputStream());
		
		printer.println("欢迎致电18888服务平台，很高兴为您服务......");
		
		System.out.println(client.getInetAddress().getHostAddress()
				+ ":" + client.getPort() + "，已接入");
		
		String problem = null;
		int count = 1; // 统计问题数
		
		while (true) {
			// 读取客户端数据
			problem = reader.nextLine().trim();
			
			if ("bye".equals(problem)) {
				printer.println("Good Bye");
				System.out.println("结束：" + client.getLocalAddress().getHostName());
				break;
			}
			
			System.out.println("问题"+ count++ + "：" + problem);
			
			System.out.print("请输入答案：");
			
			// 发送输入的信息到客户端
			printer.println(in.nextLine());
		}
		
		// 4. 释放资源
		reader.close();
		in.close();
		client.close();
	}
}
