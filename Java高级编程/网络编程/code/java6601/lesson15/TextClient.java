/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * �ͻ��ˣ�������Ϣ
 * 
 * @author CAI
 *
 */
public class TextClient {

	public static void main(String[] args) throws Exception {
		System.out.println("Client");
		
		// 1. �������ӣ������ͻ��� ���ӷ������ĵ�ַ��˿�
		Socket client = new Socket("localhost", 9000);
		
		// 2. ������д������
		Scanner in = new Scanner(System.in);
				
		// ��װͨ���ڵ�����
		PrintStream printer = new PrintStream(client.getOutputStream());
		String data = null;

		while ((data = in.nextLine()) != null) {
			
			printer.println(data);
			
			if (data.equals("stop")) {
				break;
			}
		}
		
		// 3. �ͷ���Դ
		in.close();
		client.close();
	}
}
