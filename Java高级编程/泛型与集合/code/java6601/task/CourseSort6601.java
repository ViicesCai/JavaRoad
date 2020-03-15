/**
 * 
 */
package java6601.task;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @author CAI
 *
 */
public class CourseSort6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 自然排序（按成绩降序）
		Collection<Course6601> list1 = new TreeSet<Course6601>();
		// 比较器排序（按学分升序）
		Collection<Course6601> list2 = new TreeSet<Course6601>(new SorterByCredit6601());
		// 比较器排序（按学期升序）
		Collection<Course6601> list3 = new TreeSet<Course6601>(new SorterByTerm6601());
		// 比较器排序（按课程编号升序）=
		Collection<Course6601> list4 = new TreeSet<Course6601>(new SorterById6601());
		
		addSet(list1); // 添加数据到list1
		addSet(list2); // 添加数据到list2
		addSet(list3); // 添加数据到list3
		addSet(list4); // 添加数据到list4
		
		System.out.println("==========按成绩降序==========");
		show(list1); // 打印list1的数据
		
		System.out.println("==========按学分升序==========");
		show(list2); // 打印list2的数据
		
		System.out.println("==========按学期升序==========");
		show(list3); // 打印list3的数据
		
		System.out.println("==========按课程编号升序==========");
		show(list4); // 打印list4的数据
		
	}

	/**
	 * 添加一组Course对象到集合中
	 * 
	 * @param list - 存放Course对象的集合
	 */
	public static void addSet(Collection<Course6601> list) {
		Course6601[] courses = {
				new Course6601("0001", "大学英语", "1上", 3, 80),
				new Course6601("0001", "大学英语", "1上", 3, 80),
				new Course6601("0001", "C程序设计基础", "1上", 4, 80),
				new Course6601("A001", "C程序设计基础", "1上", 4, 80),
				new Course6601("C002", "软件工程与UML", "3下", 3, 75),
				new Course6601("0002", "高等数学", "1下", 2, 85),
				new Course6601("C001", "Java程序高级开发", "3上", 2, 83),
				new Course6601("B002", "Python程序设计", "1下", 2, 85),
				new Course6601("B003", "网络基础", "3上", 3, 75),
				new Course6601("A003", "数据库原理", "2上", 3, 78),
				new Course6601("B001", "操作系统", "2下", 3, 90),
				new Course6601("A002", "面向对象程序设计", "2上", 3, 78)
		};
		
		for (Course6601 course : courses) {
			list.add(course);
		}
	}
	
	/**
	 * 打印集合中的元素
	 * 
	 * @param list - 存放Course对象的集合
	 */
	public static void show(Collection<Course6601> list) {
		// 使用 forEach 遍历输出
		list.forEach(System.out::println);
	}
}
