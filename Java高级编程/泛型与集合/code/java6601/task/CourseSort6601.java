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
		// ��Ȼ���򣨰��ɼ�����
		Collection<Course6601> list1 = new TreeSet<Course6601>();
		// �Ƚ������򣨰�ѧ������
		Collection<Course6601> list2 = new TreeSet<Course6601>(new SorterByCredit6601());
		// �Ƚ������򣨰�ѧ������
		Collection<Course6601> list3 = new TreeSet<Course6601>(new SorterByTerm6601());
		// �Ƚ������򣨰��γ̱������=
		Collection<Course6601> list4 = new TreeSet<Course6601>(new SorterById6601());
		
		addSet(list1); // ������ݵ�list1
		addSet(list2); // ������ݵ�list2
		addSet(list3); // ������ݵ�list3
		addSet(list4); // ������ݵ�list4
		
		System.out.println("==========���ɼ�����==========");
		show(list1); // ��ӡlist1������
		
		System.out.println("==========��ѧ������==========");
		show(list2); // ��ӡlist2������
		
		System.out.println("==========��ѧ������==========");
		show(list3); // ��ӡlist3������
		
		System.out.println("==========���γ̱������==========");
		show(list4); // ��ӡlist4������
		
	}

	/**
	 * ���һ��Course���󵽼�����
	 * 
	 * @param list - ���Course����ļ���
	 */
	public static void addSet(Collection<Course6601> list) {
		Course6601[] courses = {
				new Course6601("0001", "��ѧӢ��", "1��", 3, 80),
				new Course6601("0001", "��ѧӢ��", "1��", 3, 80),
				new Course6601("0001", "C������ƻ���", "1��", 4, 80),
				new Course6601("A001", "C������ƻ���", "1��", 4, 80),
				new Course6601("C002", "���������UML", "3��", 3, 75),
				new Course6601("0002", "�ߵ���ѧ", "1��", 2, 85),
				new Course6601("C001", "Java����߼�����", "3��", 2, 83),
				new Course6601("B002", "Python�������", "1��", 2, 85),
				new Course6601("B003", "�������", "3��", 3, 75),
				new Course6601("A003", "���ݿ�ԭ��", "2��", 3, 78),
				new Course6601("B001", "����ϵͳ", "2��", 3, 90),
				new Course6601("A002", "�������������", "2��", 3, 78)
		};
		
		for (Course6601 course : courses) {
			list.add(course);
		}
	}
	
	/**
	 * ��ӡ�����е�Ԫ��
	 * 
	 * @param list - ���Course����ļ���
	 */
	public static void show(Collection<Course6601> list) {
		// ʹ�� forEach �������
		list.forEach(System.out::println);
	}
}
