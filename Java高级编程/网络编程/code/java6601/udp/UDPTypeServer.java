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
 * ���նˣ���������
 * 
 * @author CAI
 *
 */
public class UDPTypeServer {

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

		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas, 0, len)));
		
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		
		System.out.println(msg + " " + age + " " + flag + " " + ch);

		// 5. �ͷ���Դ
		server.close();

	}
}
