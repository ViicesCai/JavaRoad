/**
 * 
 */
package java6601.lesson12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * 按输入的路径创建文件
 * 
 * @author CAI
 *
 */
public class WriteFile {
	public static void main(String[] args) {
		Scanner in = null;
		PrintWriter writer = null;
		
		try {
			System.out.print("请输入文件路径：");
			in = new Scanner(System.in);
			String fileName = in.next();
			
			File target = new File(fileName);
			
			// 判断该文件是否存在
			if (!target.exists()) {
				File parent = target.getParentFile();
				
				// 判断该文件是否存在路径
				if (parent != null && parent.exists()) {
					parent.mkdirs();
				}
				
				// 创建文件
				target.createNewFile();
				System.out.println("创建成功！");
			}
			
			// 打印输出流
			writer = new PrintWriter(new FileWriter(target));
			writer.println("211906601-蔡维恒");
			writer.println(LocalDateTime.now());
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			writer.close();
			in.close();
		}
	}
}
