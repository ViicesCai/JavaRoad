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
		// �������ڴ��������ļ���
		Collection<Integer> list = new HashSet<Integer>();
		
		// �ж������Ƿ�ﵽ10
		while (list.size() < 10) {
			// ��������ɷ�Χ[50-80]
			int num = random.nextInt(31) + 50;
			list.add(num);
		}
		
		for (Integer num : list) {
			System.out.println(num);
		}
	}
}
