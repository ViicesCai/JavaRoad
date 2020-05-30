/**
 * 
 */
package java6601.lesson15;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * �������ˣ�һ��һ˫�˻Ự
 * 
 * @author CAI
 *
 */
public class OneServer6601 {
	
	public static void main(String[] args) throws Exception {
		System.out.println("18888����ƽ̨");
		
		// 1. ����������
		ServerSocket server = new ServerSocket(18888);
		
		// 2. ����ʽ����
		Socket client = server.accept();
		
		// 3. ����		
		Scanner in = new Scanner(System.in);
		
		Scanner reader = new Scanner(client.getInputStream());
		PrintStream printer = new PrintStream(client.getOutputStream());
		
		printer.println("��ӭ�µ�18888����ƽ̨���ܸ���Ϊ������......");
		
		System.out.println(client.getInetAddress().getHostAddress()
				+ ":" + client.getPort() + "���ѽ���");
		
		String problem = null;
		int count = 1; // ͳ��������
		
		while (true) {
			// ��ȡ�ͻ�������
			problem = reader.nextLine().trim();
			
			if ("bye".equals(problem)) {
				printer.println("Good Bye");
				System.out.println("������" + client.getLocalAddress().getHostName());
				break;
			}
			
			System.out.println("����"+ count++ + "��" + problem);
			
			System.out.print("������𰸣�");
			
			// �����������Ϣ���ͻ���
			printer.println(in.nextLine());
		}
		
		// 4. �ͷ���Դ
		reader.close();
		in.close();
		client.close();
	}
}
