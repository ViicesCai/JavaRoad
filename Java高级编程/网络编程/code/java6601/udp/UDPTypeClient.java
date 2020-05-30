/**
 * 
 */
package java6601.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 发送端：基本类型
 * 
 * @author CAI
 *
 */
public class UDPTypeClient {

	public static void main(String[] args) throws Exception {
		System.out.println("发送端启动中......");

		// 1. 创建 DatagramSocket 实例：指定端口，作为发送端
		DatagramSocket client = new DatagramSocket(9111);

		// 2. 准备数据：转为字节数组（数据报只能封装字节数组）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));

		dos.writeUTF("你好世界");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();

		byte[] datas = baos.toByteArray();

		// 3. 封装数据：需指定目的地
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9222));

		// 4. 发送数据报
		client.send(packet);

		// 5. 释放资源
		client.close();

	}
}
