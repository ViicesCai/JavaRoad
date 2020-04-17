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
 * �������·�������ļ�
 * 
 * @author CAI
 *
 */
public class WriteFile {
	public static void main(String[] args) {
		Scanner in = null;
		PrintWriter writer = null;
		
		try {
			System.out.print("�������ļ�·����");
			in = new Scanner(System.in);
			String fileName = in.next();
			
			File target = new File(fileName);
			
			// �жϸ��ļ��Ƿ����
			if (!target.exists()) {
				File parent = target.getParentFile();
				
				// �жϸ��ļ��Ƿ����·��
				if (parent != null && parent.exists()) {
					parent.mkdirs();
				}
				
				// �����ļ�
				target.createNewFile();
				System.out.println("�����ɹ���");
			}
			
			// ��ӡ�����
			writer = new PrintWriter(new FileWriter(target));
			writer.println("211906601-��ά��");
			writer.println(LocalDateTime.now());
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			writer.close();
			in.close();
		}
	}
}
