/**
 * 
 */
package java6601.udp.comm;

/**
 * ˫��ͨ�ŵĽ��ն�
 * 
 * @author CAI
 *
 */
public class CommByReceive {
	
	public static void main(String[] args) {
		System.out.println("���� JF");
		int port = 8888; // ��ǰ���̵� �˿ں�
		String toAddr = "localhost"; // ���ն˵ĵ�ַ��������
		int toPort = 9033; // ���ն˵Ķ˿ڣ�������
		
		int targetPort = 9011; // ���Ͷ˵Ķ˿ںţ�������
		 
		new Thread(new Send(port, toAddr, toPort)).start();
		new Thread(new Receive(targetPort, "CAI")).start();
	}
}
