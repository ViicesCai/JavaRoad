/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * �������ˣ�����ı��ļ�
 * 
 * @author CAI
 *
 */
public class TextServer {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		
		// 1. ����������
		ServerSocket server = new ServerSocket(9000);

		// 2. ����ʽ�ȴ�����
		Socket client = server.accept();
		System.out.println("����������......");

		// 4. ��������ȡ����
		Scanner in = new Scanner(client.getInputStream());
		
		
		// ��װ���ı��ļ�
		PrintStream printer = new PrintStream("text.txt");
		
		String data = null;
		
		while ((data = in.nextLine()) != null) {
			System.out.println("data" + data);
			if (data.equals("stop")) {
				break;
			}
			printer.println(data);
			printer.flush();
		}

		// 5. �ͷ���Դ
		printer.close();
		in.close();
		client.close();
	}
}
