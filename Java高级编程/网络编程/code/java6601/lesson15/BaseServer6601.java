/**
 * 
 */
package java6601.lesson15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��������
 * 
 * @author CAI
 *
 */
public class BaseServer6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");

		// 1. ����������
		ServerSocket server = new ServerSocket(18888);

		// 2. ����ʽ�ȴ�����
		Socket client = server.accept();
		System.out.println("����������......");

		// 3. ������������Ϣ
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Client��" + br.readLine());
		
		// 4. ������������Ϣ
		PrintStream printer = new PrintStream(client.getOutputStream());
		printer.println("Hello");

		// 5. �ͷ���Դ
		printer.close();
		br.close();
		client.close();
		server.close();
	}
}
