/**
 * 
 */
package java6601.lesson11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 读写文本文件
 * 
 * @author CAI
 *
 */
public class TextFile6601 {
	public static void main(String[] args) throws IOException {
		// 确定源，指定流
		Scanner in = new Scanner(new File("src\\java6601\\lesson11\\StudyDataFile6601.java"));
		File targ = new File("write\\Text6601.txt");
		
		// 确保路径一定存在
		if (!targ.getParentFile().exists()) {
			targ.getParentFile().mkdirs();
		}
		
		PrintWriter writer = new PrintWriter(new FileWriter(targ, Charset.forName("GBK")));

		String data = null;
		
		// 操作
		while (in.hasNextLine()) {
			data = in.nextLine();
			System.out.println(data);
			writer.println(data);

		}
		
		// 释放资源
		writer.close();
		in.close();
	}
}
