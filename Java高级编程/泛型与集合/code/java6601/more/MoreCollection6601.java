/**
 * 
 */
package java6601.more;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAI
 *
 */

class One {
	public boolean equals(Object obj) {
		return true;
	}
}

public class MoreCollection6601 {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("集合");
		list.add("一组对象");
		list.add("动态数组");
		list.add("以Object为参数的方法");
		
		System.out.println(list);
		// contains 只是判断对象不是空之后，依然调用的还是 indexOf
		// 这里调用的是one的equals方法
		System.out.println(list.contains(new One()));
		// indexOf(Object obj)方法的实现机制是从序列(List)的第0个元素开始依次循环，
		// 并且调用每个元素的equals()方法和参数对象进行比较，
		// 如果某一个元素的equals()方法返回值为true，那么就把当前元素的索引位置作为结果返回。
		System.out.println(list.indexOf(new One()));
		// remove(Object o)
		// 在判断传入的对象O 不为空时，遍历list中所有的元素，找到与之相同的元素。
		// 这里判断相同元素用的是对象O的equals方法
		System.out.println(list.remove(new One()));
		System.out.println(list.remove(new One()));
		System.out.println(list);
		
	}

}
