/**
 * 
 */
package java6601.udp.comm;

/**
 * ˫��ͨ�ŵķ��Ͷ�
 * 
 * @author CAI
 *
 */
public class CommBySend {
	
	public static void main(String[] args) {
		System.out.println("���� CAI");
		
		int port = 9999; // ��ǰ���̵� �˿ںţ�����Ϊ�ý��̵ı�ʶ
		String toAddr = "localhost"; // ���ն˵ĵ�ַ��������
		int toPort = 9011; // ���ն˵Ķ˿ڣ�������
		
		int targetPort = 9033; // ���Ͷ˵Ķ˿ںţ�������
		
		new Thread(new Receive(targetPort, "JF")).start();
		new Thread(new Send(port, toAddr, toPort)).start();
	}
}
