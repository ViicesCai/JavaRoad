/**
 * 
 */
package java6601.lesson06;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *统计字符串中字符出现的次数
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
		System.out.println("输入字符串：");
		String str = in.nextLine();

		// 创建map集合存储字符串中每个字符出现的次数
		Map<Character, Integer> map = new TreeMap<Character, Integer>();

		// 统计每个字符出现的次数
		count6601(map, str);

		// 打印集合
		show(map);
		in.close();
	}

	/**
	 * 统计每个字符出现的次数
	 * 
	 * @param map - 存放统计结果的集合
	 * @param str - 用户输入的字符串
	 */
	public static void count6601(Map<Character, Integer> map, String str) {
		// 将 str 转换成 char 数组
		char[] chars = str.toCharArray();

		// 遍历字符串中的每个字符
		for (char c : chars) {
			// 使用 map 的 compute方法
			// 当key不存在时，执行value计算方法，计算value
			// 如果这个 value 为空 将会被 remove
			map.compute(c, (k, v) -> {
				// 如果该字符是字母
				if (Character.isLetter(c)) {
					// 如果值是空的话 +1
					// 表示该字符出现一次
					if (v == null) {
						v = 1;
					} else {
						// 出现的次数 +1
						v += 1;
					}
				}
				// 返回最新的value
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
//	 * 添加值到 key 的 value 中
//	 * @param key
//	 * @param value
//	 * @return value
//	 */
//	private static Integer add(Character key, Integer value) {
//		// 如果值是空的话 +1，否则出现的次数 +1;
//		// 同上
//		return value == null ? 1 : value + 1;
//	}

	public static void show(Map<Character, Integer> map) {
		// JDK1.8 新增的迭代方式
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}
}
