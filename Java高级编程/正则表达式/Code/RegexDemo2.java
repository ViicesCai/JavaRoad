/**
 * 
 */
package edu.fdzc.training.regex;

import java.util.Arrays;

/**
 * @author CAI
 *
 */
public class RegexDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(replaceLetters("��һ��String�ڶ���Object������Collection"));
		System.out.println(extractNum("2018��ɼ�����ѧ83�֣�Ӣ��91�֣�����88�֣�����80��"));
	}
	
	/**
	 * ���ַ����е�Ӣ���滻Ϊ***
	 * @param str
	 * @return
	 */
	public static String replaceLetters(String str) {
		String regex = "[a-zA-Z]+";
		return str.replaceAll(regex, "***");
	}
	
	/**
	 * ��ȡ�ַ����е����ֲ���
	 * @param str
	 * @return
	 */
	public static String extractNum(String str) {
		String regex = "\\D+";
		return Arrays.toString(str.split(regex));
	}
}
