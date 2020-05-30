/**
 * 
 */
package java6601.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端：基本类型
 * 
 * @author CAI
 *
 */
public class UDPTypeServer {

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

		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas, 0, len)));
		
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		
		System.out.println(msg + " " + age + " " + flag + " " + ch);

		// 5. 释放资源
		server.close();

	}
}
