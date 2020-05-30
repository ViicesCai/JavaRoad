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
 * ���Ͷˣ���������
 * 
 * @author CAI
 *
 */
public class UDPTypeClient {

	public static void main(String[] args) throws Exception {
		System.out.println("���Ͷ�������......");

		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���Ͷ�
		DatagramSocket client = new DatagramSocket(9111);

		// 2. ׼�����ݣ�תΪ�ֽ����飨���ݱ�ֻ�ܷ�װ�ֽ����飩
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));

		dos.writeUTF("�������");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();

		byte[] datas = baos.toByteArray();

		// 3. ��װ���ݣ���ָ��Ŀ�ĵ�
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9222));

		// 4. �������ݱ�
		client.send(packet);

		// 5. �ͷ���Դ
		client.close();

	}
}
