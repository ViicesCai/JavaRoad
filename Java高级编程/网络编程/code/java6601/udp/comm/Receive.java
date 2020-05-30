/**
 * 
 */
package java6601.udp.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *接收方
 * 
 * @author CAI
 *
 */
public class Receive implements Runnable {
	private DatagramSocket server;
	private String from;
	
	public Receive(int port, String from) {
		super();
		this.from = from;
		
		try {
			server = new DatagramSocket(port);
			
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		while (true) {
			// 2. 创建容器： 接收数据报，注意接收数据的大小范围
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container, 0, container.length);
			
			// 3. 接收数据报
			try {
				server.receive(packet);
				
				// 4. 转换数据报
				byte[] datas = packet.getData();
				int len = packet.getLength();
				
				String data = new String(datas, 0, len);
				
				if (data.equals("exit")) {
					System.out.println("接收端结束！");
					break;
				}
				
				System.out.println(from + "：" + data);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 5. 释放资源
		server.close();
	}
}
