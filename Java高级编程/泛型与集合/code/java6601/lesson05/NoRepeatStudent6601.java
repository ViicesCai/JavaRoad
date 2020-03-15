/**
 * 
 */
package java6601.lesson05;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author CAI
 *
 */
public class NoRepeatStudent6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<Student6601> list = new ArrayList<Student6601>();
		list.add(new Student6601("Jack", 21));
		list.add(new Student6601("Tom", 19));
		list.add(new Student6601("Cai", 22));
		list.add(new Student6601("Jack", 21));
		list.add(new Student6601("Tom", 23));
		
		System.out.println("ԭʼ����");
		for (Student6601 student : list) {
			System.out.println(student);
		}
		System.out.println("ȥ�غ�");
		getList6601(list);
	}
	
	/**
	 * ʹ��containsȥ�ص�ǰ���Ǳ�����д����� equals����
	 * 
	 * @param list
	 * @return
	 */
	public static void getList6601(Collection<Student6601> list) {
		Collection<Student6601> noRepeatList = new ArrayList<Student6601>();
		
		for (Student6601 student : list) {
			if (!noRepeatList.contains(student)) {
				noRepeatList.add(student);
			}
		}
		
		for (Student6601 student : noRepeatList) {
			System.out.println(student);
		}
		
	}
}
