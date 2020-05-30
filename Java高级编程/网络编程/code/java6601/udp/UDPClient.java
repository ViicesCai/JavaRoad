/**
 * 
 */
package java6601.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDPЭ��û����ν�Ŀͻ��������˸������ֻ�������������������
 * ���Ͷ�
 * 
 * @author CAI
 *
 */
public class UDPClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("���Ͷ�������......");
		
		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���Ͷ�
		DatagramSocket client = new DatagramSocket(9111);
		
		// 2. ׼�����ݣ�תΪ�ֽ����飨���ݱ�ֻ�ܷ�װ�ֽ����飩
		String data = "Hello,Honey";
		byte[] datas = data.getBytes();
		
		// 3. ��װ���ݣ���ָ��Ŀ�ĵ�
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, 
				new InetSocketAddress("localhost", 9222));
		
		// 4. �������ݱ�
		client.send(packet);
		
		// 5. �ͷ���Դ
		client.close();
		
	}
}
