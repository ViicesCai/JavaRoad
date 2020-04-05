/**
 * 
 */
package java6601.lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * NIO.2 �����ļ�
 * 
 * @author CAI
 *
 */
public class NIO2NewFile6601 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("������Ҫ�������ļ���");
		String uri = in.next();
		Path path = Paths.get(uri); // ��������ļ�·���ַ���ת��Ϊ Path ����
		
		// ���жϸ��ļ��Ƿ����
		if (Files.notExists(path)) {
			Path parent = path.getParent(); // ��ȡ�ļ��� ��·��
			// �����·�����ڸ�·�� �� ��·�������� 
			if (parent != null && Files.notExists(parent)) {
				Files.createDirectories(parent); // ���� ��·�� ����Ŀ¼
				System.out.println("����Ŀ¼��" + parent);
			}
			Files.createFile(path); // ���� �ļ�·�� �����ļ�
			System.out.println("�������ļ���" + path.toAbsolutePath());
		} else {
			System.out.println("���ļ��Ѵ���");
		}
		
		in.close();
	}
}
