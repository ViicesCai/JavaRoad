/**
 * 
 */
package java6601.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 信息服务器
 * 
 * @author CAI
 *
 */
public class InfoServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		
		// 1. 创建服务器
		ServerSocket server = new ServerSocket(18888);
		
		// 2. 阻塞式等待连接
		Socket client = server.accept();
		System.out.println("建立连接中......");
		
		// 3. 操作：读取数据
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		while (true) {
			String 	data = br.readLine();
			
			if (data.equals("bye")) {
				System.out.println("连接已关闭");
				break;
			}
			
			System.out.println("Client：" + data);
		}
		
		// 4. 释放资源
		br.close();
		client.close();
		server.close();
	}

}
