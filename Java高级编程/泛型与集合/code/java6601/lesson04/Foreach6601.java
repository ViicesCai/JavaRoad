/**
 * 
 */
package java6601.lesson04;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author CAI
 *
 */
public class Foreach6601 {
	public static void main(String[] args) {
		Collection<Student6601> collection = new ArrayList<Student6601>();
		List<Student6601> list = new ArrayList<Student6601>();
		
		collection.add(new Student6601("王小明", 19));
		collection.add(new Student6601("吕阿香", 20));
		collection.add(new Student6601("张优玛", 19));
		
		list.add(new Student6601("王小明", 19));
		list.add(new Student6601("吕阿香", 20));
		list.add(new Student6601("张优玛", 19));
		
		useIterator6601(collection);
		useForeach6601(collection);
		useList6601(list);
		useArray6601(collection);
	}
	
	/**
	 * 使用迭代器输出集合中的数据
	 * @param collection
	 */
	public static void useIterator6601(Collection<Student6601> collection) {
		Iterator<Student6601> iterator = collection.iterator();
		
		System.out.println("迭代器遍历");
		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString() + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * 通过foreach输出集合中的数据
	 * @param collection
	 */
	public static void useForeach6601(Collection<Student6601> collection) {
		System.out.println("增强for循环遍历");
		
		for (Student6601 student : collection) {
			System.out.print(student + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * 通过序号输出集合中的数据
	 * @param list
	 */
	public static void useList6601(List<Student6601> list) {
		System.out.println("按序号遍历");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * 将集合转为数组，遍历数组输出数据
	 * @param collection
	 */
	public static void useArray6601(Collection<Student6601> collection) {
		// 先创建对象数组
		Student6601[] students = new Student6601[collection.size()];
		
		// 调用 toArray方法 将 集合转为数组
		collection.toArray(students);
		
		System.out.println("通过数组遍历");
		for (int i = 0; i < students.length; i++) {
			System.out.print(students[i] + " ");
		}
		
		System.out.println();
	}
}
