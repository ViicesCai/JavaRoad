/**
 * 
 */
package java6601.lesson15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * 
 * @author CAI
 *
 */
public class BaseServer6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");

		// 1. 创建服务器
		ServerSocket server = new ServerSocket(18888);

		// 2. 阻塞式等待连接
		Socket client = server.accept();
		System.out.println("建立连接中......");

		// 3. 操作：接收信息
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Client：" + br.readLine());
		
		// 4. 操作：发送信息
		PrintStream printer = new PrintStream(client.getOutputStream());
		printer.println("Hello");

		// 5. 释放资源
		printer.close();
		br.close();
		client.close();
		server.close();
	}
}
