/**
 * 
 */
package java6601.udp.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *���շ�
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
			// 2. ���������� �������ݱ���ע��������ݵĴ�С��Χ
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container, 0, container.length);
			
			// 3. �������ݱ�
			try {
				server.receive(packet);
				
				// 4. ת�����ݱ�
				byte[] datas = packet.getData();
				int len = packet.getLength();
				
				String data = new String(datas, 0, len);
				
				if (data.equals("exit")) {
					System.out.println("���ն˽�����");
					break;
				}
				
				System.out.println(from + "��" + data);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 5. �ͷ���Դ
		server.close();
	}
}
