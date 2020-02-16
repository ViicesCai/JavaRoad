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
		System.out.println(replaceLetters("第一节String第二节Object第三节Collection"));
		System.out.println(extractNum("2018年成绩：数学83分，英语91分，语文88分，作文80分"));
	}
	
	/**
	 * 将字符串中的英文替换为***
	 * @param str
	 * @return
	 */
	public static String replaceLetters(String str) {
		String regex = "[a-zA-Z]+";
		return str.replaceAll(regex, "***");
	}
	
	/**
	 * 提取字符串中的数字部分
	 * @param str
	 * @return
	 */
	public static String extractNum(String str) {
		String regex = "\\D+";
		return Arrays.toString(str.split(regex));
	}
}
