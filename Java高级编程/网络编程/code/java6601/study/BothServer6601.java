/**
 * 
 */
package java6601.study;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * �������ˣ�˫������
 * 
 * @author CAI
 *
 */
public class BothServer6601 {

	ServerSocket server; // ��������
	Socket client; // �ͻ���
	Scanner reader;
	PrintStream printer;
	int clientPort;
	boolean isStopSend = false;

	public static void main(String[] args) throws IOException {
		new BothServer6601();
	}

	public BothServer6601() throws IOException {
		System.out.println("Server");
		
		// 1. �����������ˣ���ָ���˿�
		server = new ServerSocket(18888);
		
		// 2. �ȴ��ͻ��˷�����������
		client = server.accept();
		
		// 3. �����յ��ͻ��˵����������ڿ���̨����ʾ�ͻ���IP��ַ�Ͷ˿ں�
		// ���¿ͻ��˵Ķ˿ں�
		clientPort = client.getPort();
		
		// �� Client �е�����ֽ�����װΪ��ӡ��
		printer = new PrintStream(client.getOutputStream());
		
		// �� Client �е������ֽ�����ʼ��Scanner
		reader = new Scanner(client.getInputStream());
		
		// ���ȷ���һ����к�������
		printer.println("���ã����ѳɹ����ӷ�����");
		
		// �ڿ���̨����ʾ�ͻ��˵�IP�Ͷ˿ں�
		System.out.println(client.getLocalAddress().getHostAddress() + ":" + clientPort + "��������");
		
		// �������ڽ�����Ϣ���̣߳��÷�������this::receiveMsgXXX
		new Thread(this::receiveMsg6601).start();
		
		// �������ڷ�����Ϣ���̣߳��÷�������this::sendMsgXXX
		new Thread(this::sendMsg6601).start();
	}

	// ������Ϣ���߳���
	public void sendMsg6601() {
		// ��ͻ��˷�����Ϣ
		String msg = "";
		Scanner input = new Scanner(System.in);
		
		while (!isStopSend) {
			// ��������Ҫ���͵���Ϣ
			msg = input.nextLine();
			
			// ����msg
			printer.println(msg);
			
			// �������end��ֹͣ�̣߳�break
			if ("end".equals(msg)) {
				isStopSend = true;
				
				try {
					client.close();
					break;

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		input.close();
	}

	// ������Ϣ���߳���
	public void receiveMsg6601() {
		String msg = "";
		
		try {
			while (true) {
				// ������Ϣ--->msg
				msg = reader.nextLine();
				
				// ��ʾ�ͻ��˵Ķ˿ںš�msg
				System.out.println(clientPort + "��" + msg);
				
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
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (NoSuchElementException e) {
			System.out.println("Server����");
		}
	}

	public BothServer6601(ServerSocket server, Socket client, Scanner reader,
			PrintStream printer, int clientPort,boolean isStopSend) {
		super();
		this.server = server;
		this.client = client;
		this.reader = reader;
		this.printer = printer;
		this.clientPort = clientPort;
		this.isStopSend = isStopSend;
	}
}
