/**
 * 
 */
package java6601.lesson15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * �ͻ���
 * 
 * @author CAI
 *
 */
public class BaseClient6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Client");

		// 1. �������ӣ������ͻ��� ���ӷ������ĵ�ַ��˿�
		Socket client = new Socket("109.166.36.55", 18888);
		
		// 2. ������������Ϣ
		PrintStream ps = new PrintStream(client.getOutputStream());
		ps.println("��ã��ҽв�ά��");

		// 3. ������������Ϣ
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println("Server��" + br.readLine());
		
		// 4. �ͷ���Դ
		br.close();
		ps.close();
		client.close();
	}
}
