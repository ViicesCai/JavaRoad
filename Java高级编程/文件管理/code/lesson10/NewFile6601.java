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
		System.out.print("������Ҫ�������ļ�����");
		String fileName = input.next();
		File file = new File(fileName);
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
				System.out.println("����Ŀ¼��" + parent);
			}
			file.createNewFile();
			System.out.println("�������ļ���" + file.getAbsolutePath());
			
		} else {
			System.out.println("�ļ��Ѵ���");
		}
		input.close();
	}
}
