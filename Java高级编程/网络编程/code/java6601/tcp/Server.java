/**
 * 
 */
package java6601.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * 
 * @author CAI
 *
 */
public class Server {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Server");
		
		// 1. 创建服务器
		ServerSocket server = new ServerSocket(9000);
		
		// 2. 阻塞式等待连接
		Socket client = server.accept();
		System.out.println("建立连接中......");
		
		// 3. 操作：输出输入
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		
		System.out.println(data);
		
		// 4. 释放资源
		dis.close();
		client.close();
		server.close();
	}
}
