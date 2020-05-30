/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务器端：输出文本文件
 * 
 * @author CAI
 *
 */
public class TextServer {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		
		// 1. 创建服务器
		ServerSocket server = new ServerSocket(9000);

		// 2. 阻塞式等待连接
		Socket client = server.accept();
		System.out.println("建立连接中......");

		// 4. 操作：读取数据
		Scanner in = new Scanner(client.getInputStream());
		
		
		// 封装成文本文件
		PrintStream printer = new PrintStream("text.txt");
		
		String data = null;
		
		while ((data = in.nextLine()) != null) {
			System.out.println("data" + data);
			if (data.equals("stop")) {
				break;
			}
			printer.println(data);
			printer.flush();
		}

		// 5. 释放资源
		printer.close();
		in.close();
		client.close();
	}
}
