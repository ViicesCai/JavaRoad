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
 * 服务器端：读取客户端发送的文件
 * 
 * @author CAI
 *
 */
public class TaskFileServer6601 {

	public static void main(String[] args) throws Exception {
		System.out.println("Server");
		// 1. 创建服务器
		ServerSocket server = new ServerSocket(18888);

		while (true) {
			// 2. 建立连接，每获取一个客户端就开启一个线程
			Socket client = server.accept();
			System.out.println("客户端已连接");
			new Thread(new TaskFileThread6601(client)).start();
		}
	}
}

class TaskFileThread6601 implements Runnable {
	private Socket client;
	private static Properties prop;
	
	static {
		// 从配置文件中读取dirSave，初始化DIR_SAVE
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
			// 将 client 的输入流包装为DataInputStream，用于接收客户端发送的文件名与文件
			DataInputStream dis = new DataInputStream(client.getInputStream());
			PrintStream printer = new PrintStream(client.getOutputStream());
			
			// 读取文件名
			String filename = dis.readUTF();
			
			// 调用getSaveFile6601()方法得到文件对象
			File file = getSaveFile6601(filename);
			
			// 创建在服务器上写文件的FileOutputStream对象
			fos = new FileOutputStream(file);

			// 定义缓冲区,边读边写
			byte[] buffer = new byte[1024]; // 1kb
			int count = 0; // 统计文件大小
			int length = -1; // 文件读取标识

			// 读取输入流中的数据
			while ((length = dis.read(buffer)) != -1) {
				// 写入文件中
				fos.write(buffer, 0, length);
				count += length; // 统计数据长度
			}
			
			fos.close();
			
			// 打印流用于向客户端发送信息
			int fileSize = count/1024; // 文件大小，单位 KB			
			printer.println(filename + "===上传成功：" + fileSize + "KB");
			
			// 显示接收到的文件的相关信息
			System.out.println(client.getLocalAddress().getHostAddress()+"上传文件==>" + filename + "--" + fileSize + "KB");
			
			// 关闭
			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存客户端上传的文件
	 * 
	 * @param filename 文件名
	 * @return 文件路径
	 */
	private File getSaveFile6601(String filename) {
		String name, suffix;// suffix：包含后缀的.
		// 1. 查找文件名中最后一个.的位置，并以此拆分文件名与后缀
		int index = filename.indexOf(".");
		
		name = filename.substring(0, index);
		suffix = filename.substring(index);
		
		// 2. 保存文件的目录
		// 创建保存上传文件的目录对象
		File dirSave = new File(DIR_SAVE);
		
		// 确保目录已存在
		if (!dirSave.exists()) {
			dirSave.mkdirs();
		}
		
		// 3. 得到可用的文件名
		// 在服务器上用客户端上传的文件名保存文件
		File file = new File(dirSave, filename);
		
		// 循环判断，如果已经有同名文件，则加上(1)、(2)……，即加上(n)，但后缀名保持不变
		int count = 1;
		
		while (file.exists()) {
			filename = String.format("%s(%d)%s", name, count++, suffix);
			file = new File(dirSave,filename);
		}
		
		return file;
	}
}
