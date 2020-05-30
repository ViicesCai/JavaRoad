/**
 * 
 */
package java6601.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 发送端：简单通信
 * 
 * @author CAI
 *
 */
public class UDPTalkClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("发送端启动中......");
		
		// 1. 创建 DatagramSocket 实例：指定端口，作为发送端
		DatagramSocket client = new DatagramSocket(9111);
		
		// 2. 准备数据：转为字节数组（数据报只能封装字节数组）
		// 获取控制台输入
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		while (true) {
			String data = reader.readLine();
			
			byte[] datas = data.getBytes();
			
			// 3. 封装数据：需指定目的地
			DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, 
					new InetSocketAddress("localhost", 9222));
			
			// 4. 发送数据报
			client.send(packet);
			
			if (data.equals("exit")) {
				System.out.println("发送端结束！");
				break;
			}
		}
		
		
		// 5. 释放资源
		client.close();
	}
}
