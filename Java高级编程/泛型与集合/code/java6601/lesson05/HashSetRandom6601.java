/**
 * 
 */
package java6601.lesson05;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * @author CAI
 *
 */
public class HashSetRandom6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		// 创建用于存放随机数的集合
		Collection<Integer> list = new HashSet<Integer>();
		
		// 判断容量是否达到10
		while (list.size() < 10) {
			// 随机数生成范围[50-80]
			int num = random.nextInt(31) + 50;
			list.add(num);
		}
		
		for (Integer num : list) {
			System.out.println(num);
		}
	}
}
