/**
 * 
 */
package java6601.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������
 * 
 * @author CAI
 *
 */
public class Server {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Server");
		
		// 1. ����������
		ServerSocket server = new ServerSocket(9000);
		
		// 2. ����ʽ�ȴ�����
		Socket client = server.accept();
		System.out.println("����������......");
		
		// 3. �������������
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		
		System.out.println(data);
		
		// 4. �ͷ���Դ
		dis.close();
		client.close();
		server.close();
	}
}
