/**
 * 
 */
package java6601.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 信息客户端
 * 
 * @author CAI
 *
 */
public class InfoClient {

	public static void main(String[] args) throws Exception {		
		System.out.println("Client");
		
		// 1. 建立连接：创建客户端 连接服务器的地址与端口
		Socket client = new Socket("localhost", 18888);
		
		// 2. 操作：写入数据
		// 获取控制台输入
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps = new PrintStream(client.getOutputStream());

		while (true) {
			String data = reader.readLine();
			
			ps.println(data);
			
			if (data.equals("bye")) {
				System.out.println("Client 关闭");
				break;
			}
		}
		
		// 3. 释放资源
		ps.close();
		reader.close();
		client.close();
	}
}
