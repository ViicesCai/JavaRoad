/**
 * 
 */
package java6601.lesson15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Scanner;

/**
 * �ͻ��ˣ�һ��һ˫�˻Ự
 * 
 * @author CAI
 *
 */
public class OneClient6601 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("�ͻ���");
		
		// 1. ��ȡ�����ļ��еķ������� ip ��ַ���˿ں�
		Properties prop = new Properties();
		prop.load(new FileInputStream("oneClient6601.properties"));
		String ip = prop.getProperty("ip");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		// 2. �����ͻ���
		Socket client = new Socket(ip, port);
		
		// 3. ����
		Scanner reader = new Scanner(client.getInputStream());
		
		Scanner in = new Scanner(System.in);
		
		PrintStream printer = new PrintStream(client.getOutputStream());
		
		System.out.println(reader.nextLine().trim());
		
		while (true) {
			// ����¼������ݵ���������
			System.out.print("���������⣺");
			String data = in.nextLine().trim();
			printer.println(data);
			
			if (data.equals("лл")) {
				break;
			}
			
			// ��ȡ�������˵�����
			System.out.println(client.getPort() + "��" + reader.nextLine());
		}
		
		// 4. �ͷ���Դ
		reader.close();
		in.close();
		client.close();
	}
}
