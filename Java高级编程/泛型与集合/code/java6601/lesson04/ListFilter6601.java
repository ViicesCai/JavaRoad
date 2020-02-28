/**
 * 
 */
package java6601.lesson04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author CAI
 *
 */
public class ListFilter6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建list
		ArrayList<Integer> list = new ArrayList<Integer>();

		// 添加20个数到list中
		addNumbers(list);
		System.out.println(list);

		// 筛选集合
		filterFiveTimesNumber(list);
		System.out.println("共" + list.size() + "个是5的倍数：");
		System.out.println(list);
	}

	/**
	 * 将[60-100]的二十个数添加进集合中
	 * 
	 * @param list - 存放数据的集合
	 */
	public static void addNumbers(ArrayList<Integer> list) {
		// 创建随机数对象
		Random random = new Random();

		// 添加20个数到list中,添加的范围为[60-100]之间的数
		for (int i = 0; i < 20; i++) {
			// 初始值[0-40] 在后面 + 60使的其范围变为 [60 - 100]
			list.add(random.nextInt(41) + 60);
		}
	}

	/**
	 * 对集合中的数进行筛选，保留5的倍数
	 * 
	 * @param list - 存放数据的集合
	 */
	public static void filterFiveTimesNumber(ArrayList<Integer> list) {
		// 创建迭代器
		Iterator<Integer> iterator = list.iterator();

		// 先遍历list中的所有数据
		while (iterator.hasNext()) {
			// 找出5的倍数并跳过
			if (iterator.next() % 5 == 0) {
				continue;
			}
			
			// 删除list中不是5的倍数的数
			iterator.remove();
		}
	}
}
