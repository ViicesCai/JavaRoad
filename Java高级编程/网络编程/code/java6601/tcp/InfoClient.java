/**
 * 
 */
package java6601.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * ��Ϣ�ͻ���
 * 
 * @author CAI
 *
 */
public class InfoClient {

	public static void main(String[] args) throws Exception {		
		System.out.println("Client");
		
		// 1. �������ӣ������ͻ��� ���ӷ������ĵ�ַ��˿�
		Socket client = new Socket("localhost", 18888);
		
		// 2. ������д������
		// ��ȡ����̨����
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps = new PrintStream(client.getOutputStream());

		while (true) {
			String data = reader.readLine();
			
			ps.println(data);
			
			if (data.equals("bye")) {
				System.out.println("Client �ر�");
				break;
			}
		}
		
		// 3. �ͷ���Դ
		ps.close();
		reader.close();
		client.close();
	}
}
