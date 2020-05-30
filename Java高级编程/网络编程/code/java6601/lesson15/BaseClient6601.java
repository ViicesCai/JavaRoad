/**
 * 
 */
package java6601.lesson15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 客户端
 * 
 * @author CAI
 *
 */
public class BaseClient6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Client");

		// 1. 建立连接：创建客户端 连接服务器的地址与端口
		Socket client = new Socket("109.166.36.55", 18888);
		
		// 2. 操作：发送信息
		PrintStream ps = new PrintStream(client.getOutputStream());
		ps.println("你好，我叫蔡维恒");

		// 3. 操作：接收信息
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Server：" + br.readLine());
		
		// 4. 释放资源
		br.close();
		ps.close();
		client.close();
	}
}
