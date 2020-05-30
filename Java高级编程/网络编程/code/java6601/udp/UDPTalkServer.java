/**
 * 
 */
package java6601.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端：简单通信
 * 
 * @author CAI
 *
 */
public class UDPTalkServer {

	public static void main(String[] args) throws Exception {
		System.out.println("接收端启动中......");
		
		// 1. 创建 DatagramSocket 实例：指定端口，作为接收端
		DatagramSocket server = new DatagramSocket(9222); // 端口与发送端的指定接口一致，注意端口重复
		
		while (true) {
			// 2. 创建容器： 接收数据报，注意接收数据的大小范围
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container, 0, container.length);
			
			// 3. 接收数据报
			server.receive(packet);
			
			// 4. 转换数据报
			byte[] datas = packet.getData();
			int len = packet.getLength();
			
			String data = new String(datas, 0, len);
			
			if (data.equals("exit")) {
				System.out.println("接收端结束！");
				break;
			}
			
			System.out.println("ByClient:" + data);
		}
		
		// 5. 释放资源
		server.close();
		
	}
}
