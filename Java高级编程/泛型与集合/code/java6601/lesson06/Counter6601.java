/**
 * 
 */
package java6601.lesson06;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *ͳ���ַ������ַ����ֵĴ���
 * 
 * @author CAI
 *
 */
public class Counter6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("�����ַ�����");
		String str = in.nextLine();

		// ����map���ϴ洢�ַ�����ÿ���ַ����ֵĴ���
		Map<Character, Integer> map = new TreeMap<Character, Integer>();

		// ͳ��ÿ���ַ����ֵĴ���
		count6601(map, str);

		// ��ӡ����
		show(map);
		in.close();
	}

	/**
	 * ͳ��ÿ���ַ����ֵĴ���
	 * 
	 * @param map - ���ͳ�ƽ���ļ���
	 * @param str - �û�������ַ���
	 */
	public static void count6601(Map<Character, Integer> map, String str) {
		// �� str ת���� char ����
		char[] chars = str.toCharArray();

		// �����ַ����е�ÿ���ַ�
		for (char c : chars) {
			// ʹ�� map �� compute����
			// ��key������ʱ��ִ��value���㷽��������value
			// ������ value Ϊ�� ���ᱻ remove
			map.compute(c, (k, v) -> {
				// ������ַ�����ĸ
				if (Character.isLetter(c)) {
					// ���ֵ�ǿյĻ� +1
					// ��ʾ���ַ�����һ��
					if (v == null) {
						v = 1;
					} else {
						// ���ֵĴ��� +1
						v += 1;
					}
				}
				// �������µ�value
				return v;
			});
		}

//		char[] chars = str.toCharArray();
//		for (char c : chars) {
//			if (Character.isLetter(c)) {
//				map.compute(c, Counter6601::add);
//			}
//			continue;
//		}
	}

//	/**
//	 * ���ֵ�� key �� value ��
//	 * @param key
//	 * @param value
//	 * @return value
//	 */
//	private static Integer add(Character key, Integer value) {
//		// ���ֵ�ǿյĻ� +1��������ֵĴ��� +1;
//		// ͬ��
//		return value == null ? 1 : value + 1;
//	}

	public static void show(Map<Character, Integer> map) {
		// JDK1.8 �����ĵ�����ʽ
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}
}
