/**
 * 
 */
package java6601.chat;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * �������ˣ���ȡ�ͻ��˷��͵��ļ�
 * 
 * @author CAI
 *
 */
public class TaskFileServer6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		// 1. ����������
		ServerSocket server = new ServerSocket(18888);

		while (true) {
			// 2. �������ӣ�ÿ��ȡһ���ͻ��˾Ϳ���һ���߳�
			Socket client = server.accept();
			System.out.println("�ͻ���������");
			new Thread(new TaskFileThread6601(client)).start();
		}
	}
}

class TaskFileThread6601 implements Runnable {
	private Socket client;
	private static Properties prop;
	
	static {
		// �������ļ��ж�ȡdirSave����ʼ��DIR_SAVE
		prop = new Properties();
		try {
			prop.load(new InputStreamReader(new FileInputStream("fileServer6601.properties"), "GBK"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public final String DIR_SAVE = prop.getProperty("dirSave");


	public TaskFileThread6601(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		FileOutputStream fos = null;
		try {
			// �� client ����������װΪDataInputStream�����ڽ��տͻ��˷��͵��ļ������ļ�
			DataInputStream dis = new DataInputStream(client.getInputStream());
			PrintStream printer = new PrintStream(client.getOutputStream());
			
			// ��ȡ�ļ���
			String filename = dis.readUTF();
			
			// ����getSaveFile6601()�����õ��ļ�����
			File file = getSaveFile6601(filename);
			
			// �����ڷ�������д�ļ���FileOutputStream����
			fos = new FileOutputStream(file);

			// ���建����,�߶���д
			byte[] buffer = new byte[1024]; // 1kb
			int count = 0; // ͳ���ļ���С
			int length = -1; // �ļ���ȡ��ʶ

			// ��ȡ�������е�����
			while ((length = dis.read(buffer)) != -1) {
				// д���ļ���
				fos.write(buffer, 0, length);
				count += length; // ͳ�����ݳ���
			}
			
			fos.close();
			
			// ��ӡ��������ͻ��˷�����Ϣ
			int fileSize = count/1024; // �ļ���С����λ KB			
			printer.println(filename + "===�ϴ��ɹ���" + fileSize + "KB");
			
			// ��ʾ���յ����ļ��������Ϣ
			System.out.println(client.getLocalAddress().getHostAddress()+"�ϴ��ļ�==>" + filename + "--" + fileSize + "KB");
			
			// �ر�
			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ͻ����ϴ����ļ�
	 * 
	 * @param filename �ļ���
	 * @return �ļ�·��
	 */
	private File getSaveFile6601(String filename) {
		String name, suffix;// suffix��������׺��.
		// 1. �����ļ��������һ��.��λ�ã����Դ˲���ļ������׺
		int index = filename.indexOf(".");
		
		name = filename.substring(0, index);
		suffix = filename.substring(index);
		
		// 2. �����ļ���Ŀ¼
		// ���������ϴ��ļ���Ŀ¼����
		File dirSave = new File(DIR_SAVE);
		
		// ȷ��Ŀ¼�Ѵ���
		if (!dirSave.exists()) {
			dirSave.mkdirs();
		}
		
		// 3. �õ����õ��ļ���
		// �ڷ��������ÿͻ����ϴ����ļ��������ļ�
		File file = new File(dirSave, filename);
		
		// ѭ���жϣ�����Ѿ���ͬ���ļ��������(1)��(2)������������(n)������׺�����ֲ���
		int count = 1;
		
		while (file.exists()) {
			filename = String.format("%s(%d)%s", name, count++, suffix);
			file = new File(dirSave,filename);
		}
		
		return file;
	}
}
