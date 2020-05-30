/**
 * 
 */
package java6601.udp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

/**
 * ���Ͷˣ���������
 * 
 * @author CAI
 *
 */
public class UDPObjectClient {

	public static void main(String[] args) throws Exception {
		System.out.println("���Ͷ�������......");

		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���Ͷ�
		DatagramSocket client = new DatagramSocket(9111);

		// 2. ׼�����ݣ�תΪ�ֽ����飨���ݱ�ֻ�ܷ�װ�ֽ����飩
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));

		// ǰ�᣺������Ҫʵ�����л��ӿ�
		oos.writeObject(LocalDateTime.now());
		oos.flush();
		
		byte[] datas = baos.toByteArray();

		// 3. ��װ���ݣ���ָ��Ŀ�ĵ�
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9222));

		// 4. �������ݱ�
		client.send(packet);

		// 5. �ͷ���Դ
		client.close();

	}
}
