/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端：输入信息
 * 
 * @author CAI
 *
 */
public class TextClient {

	public static void main(String[] args) throws Exception {
		System.out.println("Client");
		
		// 1. 建立连接：创建客户端 连接服务器的地址与端口
		Socket client = new Socket("localhost", 9000);
		
		// 2. 操作：写入数据
		Scanner in = new Scanner(System.in);
				
		// 封装通道内的数据
		PrintStream printer = new PrintStream(client.getOutputStream());
		String data = null;

		while ((data = in.nextLine()) != null) {
			
			printer.println(data);
			
			if (data.equals("stop")) {
				break;
			}
		}
		
		// 3. 释放资源
		in.close();
		client.close();
	}
}
