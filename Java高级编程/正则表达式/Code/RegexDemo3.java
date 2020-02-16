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
		String str = "�ɼ�����ѧ83.2�֣�Ӣ�91.7�֣����ģ�88�֣����ģ�80�֡�";
		extractFloat(str);
		countFloatString(str);
	}
	
	/**
	 * ��ȡ�ַ����е�С������ӡ
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
	 * ��ӡ�ַ��������ֵ��ܺ�
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
