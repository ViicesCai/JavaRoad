/**
 * 
 */
package edu.fdzc.training.regex;

import java.util.Scanner;

/**
 * 打印每次输入的字符串 以 # 为结束符号
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
		System.out.print("输入：");
		while (!scanner.hasNext("#")) {
			System.out.println("键盘输入的内容是：" + scanner.next());
		}
		scanner.close();
		System.out.println("结束");
	}

}
