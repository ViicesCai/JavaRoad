/**
 * 
 */
package java6601.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP协议没有所谓的客户端与服务端概念，这里只是用作举例，请勿混淆
 * 发送端
 * 
 * @author CAI
 *
 */
public class UDPClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("发送端启动中......");
		
		// 1. 创建 DatagramSocket 实例：指定端口，作为发送端
		DatagramSocket client = new DatagramSocket(9111);
		
		// 2. 准备数据：转为字节数组（数据报只能封装字节数组）
		String data = "Hello,Honey";
		byte[] datas = data.getBytes();
		
		// 3. 封装数据：需指定目的地
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, 
				new InetSocketAddress("localhost", 9222));
		
		// 4. 发送数据报
		client.send(packet);
		
		// 5. 释放资源
		client.close();
		
	}
}
