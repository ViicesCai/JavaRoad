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
 * 客户端：一对一双人会话
 * 
 * @author CAI
 *
 */
public class OneClient6601 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("客户端");
		
		// 1. 读取配置文件中的服务器端 ip 地址及端口号
		Properties prop = new Properties();
		prop.load(new FileInputStream("oneClient6601.properties"));
		String ip = prop.getProperty("ip");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		// 2. 创建客户端
		Socket client = new Socket(ip, port);
		
		// 3. 操作
		Scanner reader = new Scanner(client.getInputStream());
		
		Scanner in = new Scanner(System.in);
		
		PrintStream printer = new PrintStream(client.getOutputStream());
		
		System.out.println(reader.nextLine().trim());
		
		while (true) {
			// 发送录入的数据到服务器端
			System.out.print("请输入问题：");
			String data = in.nextLine().trim();
			printer.println(data);
			
			if (data.equals("谢谢")) {
				break;
			}
			
			// 获取服务器端的数据
			System.out.println(client.getPort() + "：" + reader.nextLine());
		}
		
		// 4. 释放资源
		reader.close();
		in.close();
		client.close();
	}
}
