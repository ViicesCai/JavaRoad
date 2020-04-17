/**
 * 
 */
package java6601.task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author CAI
 *
 */
public class Counter6601 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("�����ַ�����");
		String str = in.nextLine();
		String[] strs = str.split("");
		List<String> list = Arrays.asList(strs);
		
		// ���ӳ��� key �����ظ�������� Integer::sum �ṩ�ĺϲ������ϲ����
		Map<String, Integer> map = list.stream()
				                       .collect(Collectors
				                       .toConcurrentMap(k -> k, k -> 1, Integer::sum));
		    
		// �������
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		in.close();
	}
}
