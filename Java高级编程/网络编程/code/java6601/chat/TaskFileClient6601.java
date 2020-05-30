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
 * 客户端：上传文件到服务器
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
		// 1. 读取配置文件中的服务器端 ip 地址，端口号，要上传的文件路径
		Properties prop = new Properties();
		
		// 读取配置文件
		FileInputStream fis = (new FileInputStream("fileClient6601.properties"));
		
		// 使用 GBK 字符集，防止加载配置文件时中文乱码
		prop.load(new InputStreamReader(fis, "GBK"));
		
		String ip = prop.getProperty("ip");
		int port = Integer.parseInt(prop.getProperty("port"));	
		String fileName = prop.getProperty("filename");
		
		// 检查该文件是否存在，不存在则程序结束
		File file = new File(fileName);
		
		if (!file.exists()) {
			System.out.println("待上传的文件不存在，运行结束！");
			return;
		}
		
		// 2. 创建客户端
		client = new Socket(ip, port);
		
		// 3. 操作 调用upload6601()方法上传文件
		upload6601(file);
	  }

	// 上传文件
	private void upload6601(File file) throws IOException {
		// 将 client 的输出流包装为 DataOutputStream
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		
		// 先传文件名
		dos.writeUTF(file.getName());
		
		// 创建FileInputStream对象
		FileInputStream fis = new FileInputStream(file);
		
		// 定义缓冲区，边读边传
		byte buffer[] = new byte[1024]; // 1kb
		int length = -1; // 文件读取标识
		
		// 将输出流中的数据读到缓冲区，length > 0 则代表数据未读取完毕
		while((length = fis.read(buffer)) != -1) { 
			// 发送此数据
			dos.write(buffer, 0, length);
		}
		
		// 关闭客户端输出流，发送结束
		client.shutdownOutput();
		fis.close();
		
		// 接收服务端返回的结果，并输出到控制台		
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		System.out.println(reader.readLine());
		
	}
}
