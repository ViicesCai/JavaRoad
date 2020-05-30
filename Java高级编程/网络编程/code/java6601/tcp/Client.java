/**
 * 
 */
package java6601.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author CAI
 *
 */
public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Client");
		
		// 1. 建立连接：创建客户端 连接服务器的地址与端口
		Socket client = new Socket("localhost", 9000);
		
		// 2. 操作：输入输出
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "hello";
		dos.writeUTF(data);
		dos.flush();
		
		// 3. 释放资源
		dos.close();
		client.close();
	}
}
