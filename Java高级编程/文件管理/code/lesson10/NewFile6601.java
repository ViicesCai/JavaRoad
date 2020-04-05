/**
 * 
 */
package java6601.lesson10;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class NewFile6601 {
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.print("请输入要创建的文件名：");
		String fileName = input.next();
		File file = new File(fileName);
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
				System.out.println("创建目录：" + parent);
			}
			file.createNewFile();
			System.out.println("创建新文件：" + file.getAbsolutePath());
			
		} else {
			System.out.println("文件已存在");
		}
		input.close();
	}
}
