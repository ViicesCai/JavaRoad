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
		System.out.println("�����뵥�ʣ�");
		String str = scanner.nextLine();
		scanner.close();
		
		Collection<String> list = getWordList6601(str); // ���ڴ�ŵ��ʵļ���
		
		System.out.printf("���ʱ���%d������\n", list.size());
		
		for (String word : list) {
			System.out.println(word);
		}
	}
	
	/**
	 * ��ȡ�ַ����еĵ���
	 * 
	 * @param str �ַ���
	 * @return ��ŵ��ʵļ���
	 */
	public static Collection<String> getWordList6601(String str) {
		Collection<String> wordlist = new TreeSet<String>(); // ���ڴ�ŵ��ʵļ���
		
		// ʹ��������ʽ����ȡ�ַ����еĵ���
		String regex = "[a-zA-Z]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		// ��ƥ�䵽�ĵ��ʼ��뼯��
		while (m.find()) {
			wordlist.add(m.group().toLowerCase());
		}
		
		return wordlist;
	}
}
