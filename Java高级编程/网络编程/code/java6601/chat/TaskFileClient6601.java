/**
 * 
 */
package java6601.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Properties;

/**
 * �ͻ��ˣ��ϴ��ļ���������
 * 
 * @author CAI
 *
 */
public class TaskFileClient6601 {
	private Socket client;

	public static void main(String[] args) throws Exception {
		System.out.println("Client");
		new TaskFileClient6601();
	}

	public TaskFileClient6601() throws Exception {
		// 1. ��ȡ�����ļ��еķ������� ip ��ַ���˿ںţ�Ҫ�ϴ����ļ�·��
		Properties prop = new Properties();
		
		// ��ȡ�����ļ�
		FileInputStream fis = (new FileInputStream("fileClient6601.properties"));
		
		// ʹ�� GBK �ַ�������ֹ���������ļ�ʱ��������
		prop.load(new InputStreamReader(fis, "GBK"));
		
		String ip = prop.getProperty("ip");
		int port = Integer.parseInt(prop.getProperty("port"));	
		String fileName = prop.getProperty("filename");
		
		// �����ļ��Ƿ���ڣ���������������
		File file = new File(fileName);
		
		if (!file.exists()) {
			System.out.println("���ϴ����ļ������ڣ����н�����");
			return;
		}
		
		// 2. �����ͻ���
		client = new Socket(ip, port);
		
		// 3. ���� ����upload6601()�����ϴ��ļ�
		upload6601(file);
	  }

	// �ϴ��ļ�
	private void upload6601(File file) throws IOException {
		// �� client ���������װΪ DataOutputStream
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		
		// �ȴ��ļ���
		dos.writeUTF(file.getName());
		
		// ����FileInputStream����
		FileInputStream fis = new FileInputStream(file);
		
		// ���建�������߶��ߴ�
		byte buffer[] = new byte[1024]; // 1kb
		int length = -1; // �ļ���ȡ��ʶ
		
		// ��������е����ݶ�����������length > 0 ���������δ��ȡ���
		while((length = fis.read(buffer)) != -1) { 
			// ���ʹ�����
			dos.write(buffer, 0, length);
		}
		
		// �رտͻ�������������ͽ���
		client.shutdownOutput();
		fis.close();
		
		// ���շ���˷��صĽ���������������̨		
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println(reader.readLine());
		
	}
}
