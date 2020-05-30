/**
 * 
 */
package java6601.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP协议没有所谓的客户端与服务端概念，这里只是用作举例，请勿混淆
 * 接收端
 * 
 * @author CAI
 *
 */
public class UDPServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("接收端启动中......");
		
		// 1. 创建 DatagramSocket 实例：指定端口，作为接收端
		DatagramSocket server = new DatagramSocket(9222); // 端口与发送端的指定接口一致，注意端口重复
		
		// 2. 创建容器： 接收数据报，注意接收数据的大小范围
		byte[] container = new byte[1024 * 60];
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);
		
		// 3. 接收数据报
		server.receive(packet);
		
		// 4. 转换数据报
		byte[] datas = packet.getData();
		int len = packet.getLength();
		
		System.out.println(new String(datas, 0, len));
		
		// 5. 释放资源
		server.close();
		
	}
}
