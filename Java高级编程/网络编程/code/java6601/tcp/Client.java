/**
 * 
 */
package java6601.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���
 * 
 * @author CAI
 *
 */
public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Client");
		
		// 1. �������ӣ������ͻ��� ���ӷ������ĵ�ַ��˿�
		Socket client = new Socket("localhost", 9000);
		
		// 2. �������������
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "hello";
		dos.writeUTF(data);
		dos.flush();
		
		// 3. �ͷ���Դ
		dos.close();
		client.close();
	}
}
