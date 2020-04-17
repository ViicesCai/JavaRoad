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
		System.out.println("输入字符串：");
		String str = in.nextLine();
		String[] strs = str.split("");
		List<String> list = Arrays.asList(strs);
		
		// 如果映射的 key 包含重复，则根据 Integer::sum 提供的合并函数合并结果
		Map<String, Integer> map = list.stream()
				                       .collect(Collectors
				                       .toConcurrentMap(k -> k, k -> 1, Integer::sum));
		    
		// 遍历输出
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		in.close();
	}
}
