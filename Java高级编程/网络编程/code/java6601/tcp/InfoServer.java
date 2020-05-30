/**
 * 
 */
package java6601.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��Ϣ������
 * 
 * @author CAI
 *
 */
public class InfoServer {
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		
		// 1. ����������
		ServerSocket server = new ServerSocket(18888);
		
		// 2. ����ʽ�ȴ�����
		Socket client = server.accept();
		System.out.println("����������......");
		
		// 3. ��������ȡ����
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		while (true) {
			String 	data = br.readLine();
			
			if (data.equals("bye")) {
				System.out.println("�����ѹر�");
				break;
			}
			
			System.out.println("Client��" + data);
		}
		
		// 4. �ͷ���Դ
		br.close();
		client.close();
		server.close();
	}

}
