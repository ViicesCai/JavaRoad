/**
 * 
 */
package java6601.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * ���նˣ���ͨ��
 * 
 * @author CAI
 *
 */
public class UDPTalkServer {

	public static void main(String[] args) throws Exception {
		System.out.println("���ն�������......");
		
		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���ն�
		DatagramSocket server = new DatagramSocket(9222); // �˿��뷢�Ͷ˵�ָ���ӿ�һ�£�ע��˿��ظ�
		
		while (true) {
			// 2. ���������� �������ݱ���ע��������ݵĴ�С��Χ
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet = new DatagramPacket(container, 0, container.length);
			
			// 3. �������ݱ�
			server.receive(packet);
			
			// 4. ת�����ݱ�
			byte[] datas = packet.getData();
			int len = packet.getLength();
			
			String data = new String(datas, 0, len);
			
			if (data.equals("exit")) {
				System.out.println("���ն˽�����");
				break;
			}
			
			System.out.println("ByClient:" + data);
		}
		
		// 5. �ͷ���Դ
		server.close();
		
	}
}
