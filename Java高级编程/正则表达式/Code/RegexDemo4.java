/**
 * 
 */
package edu.fdzc.training.regex;

import java.util.Scanner;

/**
 * ��ӡÿ��������ַ��� �� # Ϊ��������
 * 
 * @author CAI
 *
 */
public class RegexDemo4 {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("���룺");
		while (!scanner.hasNext("#")) {
			System.out.println("��������������ǣ�" + scanner.next());
		}
		scanner.close();
		System.out.println("����");
	}

}
