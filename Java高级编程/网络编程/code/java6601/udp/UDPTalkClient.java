/**
 * 
 */
package java6601.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * ���Ͷˣ���ͨ��
 * 
 * @author CAI
 *
 */
public class UDPTalkClient {
	
	public static void main(String[] args) throws Exception {
		System.out.println("���Ͷ�������......");
		
		// 1. ���� DatagramSocket ʵ����ָ���˿ڣ���Ϊ���Ͷ�
		DatagramSocket client = new DatagramSocket(9111);
		
		// 2. ׼�����ݣ�תΪ�ֽ����飨���ݱ�ֻ�ܷ�װ�ֽ����飩
		// ��ȡ����̨����
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		
		while (true) {
			String data = reader.readLine();
			
			byte[] datas = data.getBytes();
			
			// 3. ��װ���ݣ���ָ��Ŀ�ĵ�
			DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, 
					new InetSocketAddress("localhost", 9222));
			
			// 4. �������ݱ�
			client.send(packet);
			
			if (data.equals("exit")) {
				System.out.println("���Ͷ˽�����");
				break;
			}
		}
		
		
		// 5. �ͷ���Դ
		client.close();
	}
}
