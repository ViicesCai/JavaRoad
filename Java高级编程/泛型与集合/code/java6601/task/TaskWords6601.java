/**
 * 
 */
package java6601.task;

import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CAI
 *
 */
public class TaskWords6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入单词：");
		String str = scanner.nextLine();
		scanner.close();
		
		Collection<String> list = getWordList6601(str); // 用于存放单词的集合
		
		System.out.printf("单词表：有%d个单词\n", list.size());
		
		for (String word : list) {
			System.out.println(word);
		}
	}
	
	/**
	 * 获取字符串中的单词
	 * 
	 * @param str 字符串
	 * @return 存放单词的集合
	 */
	public static Collection<String> getWordList6601(String str) {
		Collection<String> wordlist = new TreeSet<String>(); // 用于存放单词的集合
		
		// 使用正则表达式，提取字符串中的单词
		String regex = "[a-zA-Z]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		// 将匹配到的单词加入集合
		while (m.find()) {
			wordlist.add(m.group().toLowerCase());
		}
		
		return wordlist;
	}
}
