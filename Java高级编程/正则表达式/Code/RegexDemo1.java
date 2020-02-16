/**
 * 
 */
package edu.fdzc.training.regex;

/**
 * @author CAI
 *
 */
public class RegexDemo1 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isNumber("222"));
		System.out.println(isNumber2("Hello"));
		System.out.println(isYearMonth("2018-08"));
		System.out.println(isYearMonth("2018-8"));
		System.out.println(isYearMonth("11"));
		System.out.println(isFloat(".3"));
		System.out.println(isFloat("3."));
		System.out.println(isFloat("."));
		System.out.println(isFloat("+."));
		System.out.println(isFloat("+.6"));
	}
	
	/**
	 * �ж��ַ����Ƿ��Ǵ�����
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * ��������ʽ�ж��ַ����Ƿ�Ϊ������
	 * @param str
	 * @return
	 */
	public static boolean isNumber2(String str) {
		String regex = "\\d*";
		return str.matches(regex);
	}
	
	/**
	 * ��֤�ַ����ĸ�ʽ�Ƿ�Ϊ ���-�·�
	 * @param str
	 * @return
	 */
	public static boolean isYearMonth(String str) {
		String regex = "\\d{4}-(0?[1-9]|1[0-2])";
		return str.matches(regex);
	}
	
	/**
	 * ��֤�ַ����Ƿ�Ϊ��С��������
	 * @param str
	 * @return
	 */
	public static boolean isFloat(String str) {
		String regex = "([+-]?\\d+)|(\\d+\\.\\d*)|(\\d*\\.\\d+)";
		return str.matches(regex);
	}
}
