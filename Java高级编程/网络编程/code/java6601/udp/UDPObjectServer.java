/**
 * 
 */
package java6601.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;

/**
 * ���նˣ���������
 * 
 * @author CAI
 *
 */
public class UDPObjectServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("���ն�������......");

		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���ն�
		DatagramSocket server = new DatagramSocket(9222); // �˿��뷢�Ͷ˵�ָ���ӿ�һ�£�ע��˿��ظ�

		// 2. ���������� �������ݱ���ע��������ݵĴ�С��Χ
		byte[] container = new byte[1024 * 60];
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);

		// 3. �������ݱ�
		server.receive(packet);

		// 4. ת�����ݱ�
		byte[] datas = packet.getData();
		int len = packet.getLength();

		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas, 0, len)));
		
		Object data = ois.readObject();
		
		if (data instanceof LocalDateTime) {
			LocalDateTime dateTime = (LocalDateTime) data;
			System.out.println("Time��" + dateTime);
		}
		
		// 5. �ͷ���Դ
		server.close();

	}
}
