/**
 * 
 */
package java6601.study;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

/**
 * �ͻ��ˣ�˫������
 * 
 * @author CAI
 *
 */
public class BothClient6601 {
	Socket client;
	PrintStream printer;
	Scanner reader;
	int port;
	boolean isStopSend = false;

	public static void main(String[] args) throws IOException {
		new BothClient6601();
	}

	public BothClient6601() throws IOException {
		System.out.println("Client");
		
		// 1. ��ȡ�����ļ��еķ������� ip ��ַ���˿ں�
		Properties prop = new Properties();
		prop.load(new FileInputStream("bothClient6601.properties"));
		String ip = prop.getProperty("ip");
		
		// ���·���˵Ķ˿ں�--->port
		port = Integer.parseInt(prop.getProperty("port"));
		
		// ��������IP�Ͷ˿ں���������˷�����������
		client = new Socket(ip, port);
		
		// ��ʾ��ǰ�Ķ˿ںţ�socket.getLocalPort()
		System.out.println("��ǰ�˿ںţ�" + client.getLocalPort());
		
		// ��Socket�е�����ֽ�����װΪ��ӡ��--->printer
		printer = new PrintStream(client.getOutputStream());
		
		// ��Socket�е������ֽ�����ʼ��Scanner--->reader
		reader = new Scanner(client.getInputStream());
				
		// �������ڽ�����Ϣ���߳�
		new Thread(this::receive6601).start();
		
		// �������ڷ�����Ϣ���߳�
		new Thread(this::send6601).start();
	}

	// ������Ϣ���߳���
	public void send6601() {
		String msg = "";
		Scanner input = new Scanner(System.in);
		
		while (!isStopSend) {
			// ��������Ҫ���͵���Ϣ--->msg
			msg = input.nextLine();
			
			// ����msg
			printer.println(msg);
			
			// �������end��ֹͣ�̣߳�break
			if ("end".equals(msg)) {
				
				try {
					client.close();
					break;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ������Ϣ���߳���
	public void receive6601() {
		String msg = "";
		
		try {
			while (true) {
				// ������Ϣ--->msg
				msg = reader.nextLine();
				System.out.println(port + "��" + msg);
				// ��ʾ����˵Ķ˿ںš�msg
				if ("end".equals(msg)) {
					// �ñ��˵ķ����߳�ֹͣ��isStopSend��Ϊtrue
					isStopSend = true;
					
					// ��ʾ���س�����
					System.out.println("���س�������");
					break;
				}
			}
			
			reader.close();
			printer.close();
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (NoSuchElementException e) {
			System.out.println("Client����");
		}
	}
}
