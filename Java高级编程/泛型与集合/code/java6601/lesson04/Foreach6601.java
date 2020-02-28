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
		
		collection.add(new Student6601("��С��", 19));
		collection.add(new Student6601("������", 20));
		collection.add(new Student6601("������", 19));
		
		list.add(new Student6601("��С��", 19));
		list.add(new Student6601("������", 20));
		list.add(new Student6601("������", 19));
		
		useIterator6601(collection);
		useForeach6601(collection);
		useList6601(list);
		useArray6601(collection);
	}
	
	/**
	 * ʹ�õ�������������е�����
	 * @param collection
	 */
	public static void useIterator6601(Collection<Student6601> collection) {
		Iterator<Student6601> iterator = collection.iterator();
		
		System.out.println("����������");
		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString() + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * ͨ��foreach��������е�����
	 * @param collection
	 */
	public static void useForeach6601(Collection<Student6601> collection) {
		System.out.println("��ǿforѭ������");
		
		for (Student6601 student : collection) {
			System.out.print(student + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * ͨ�������������е�����
	 * @param list
	 */
	public static void useList6601(List<Student6601> list) {
		System.out.println("����ű���");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		
		System.out.println();
	}
	
	/**
	 * ������תΪ���飬���������������
	 * @param collection
	 */
	public static void useArray6601(Collection<Student6601> collection) {
		// �ȴ�����������
		Student6601[] students = new Student6601[collection.size()];
		
		// ���� toArray���� �� ����תΪ����
		collection.toArray(students);
		
		System.out.println("ͨ���������");
		for (int i = 0; i < students.length; i++) {
			System.out.print(students[i] + " ");
		}
		
		System.out.println();
	}
}
