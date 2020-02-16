/**
 * 
 */
package edu.fdzc.training.regex;

import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;
/**
 * @author CAI
 *
 */
public class RegexDemo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "成绩：数学83.2分，英语：91.7分，语文：88分，作文：80分。";
		extractFloat(str);
		countFloatString(str);
	}
	
	/**
	 * 提取字符串中的小数并打印
	 * @param str
	 */
	public static void extractFloat(String str) {
		String regex = "\\d+\\.\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(str.substring(m.start(),m.end()));
		}
	}
	
	/**
	 * 打印字符串中数字的总和
	 * @param str
	 */
	public static void countFloatString(String str) {
		double sum = 0;
		
		Scanner scanner = new Scanner(str);
		scanner.useDelimiter("[^0-9.]+");
		
		while (scanner.hasNext()) {
			sum =sum + scanner.nextDouble();
		}
		scanner.close();
		System.out.println("sum = " + sum);
	}
}
