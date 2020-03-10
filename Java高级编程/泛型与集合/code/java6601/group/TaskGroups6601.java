/**
 * 
 */
package java6601.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class TaskGroups6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>(); // ѧ������
		initList(studentList); // ��ʼ��ѧ������
		show(studentList); // ��ʾ
	}

	/**
	 * ��ʾѧ���ķ�����Ϣ
	 * 
	 * @param list
	 */
	public static void show(ArrayList<Student> list) {
		ArrayList<Integer> schemeList; // ���鷽������
		ArrayList<Student[]> groupList; // ������Ϣ����

		schemeList = getGroupingScheme(list.size()); // ��÷��鷽��
		groupList = getGroupingList(list, schemeList); // ��÷�����Ϣ

		Iterator<Student[]> iterator = groupList.iterator();

		System.out.println("�༶������" + list.size());
		
		int count = 1; // ������������С������
		while (iterator.hasNext()) {
			Student[] studentGroup = iterator.next();
			System.out.printf("��%d��%s%d��\n", count++
					, Arrays.toString(studentGroup)
					, studentGroup.length);
		}
	}

	/**
	 * ��ʼ��ѧ����Ϣ
	 * 
	 * @param list - ѧ������
	 */
	public static void initList(ArrayList<Student> list) {
		try {
			// ʹ��scanner��ɨ��StudentList�е�����
			Scanner scanner = new Scanner(new File("StudentList.txt"));

			// �������
			while (scanner.hasNext()) {
				list.add(new Student(scanner.nextLine()));
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���鷽��
	 * 
	 * @param total - ѧ����������
	 * 
	 * @return ��ŷ��鷽���ļ���
	 */
	public static ArrayList<Integer> getGroupingScheme(int total) {
		ArrayList<Integer> schemeList = new ArrayList<Integer>(); // ��ŷ��䷽��

		Random random = new Random();

		int index = 0; // �ۼ�����ʶ��
		int count = 1;// ͳ��ѭ���Ĵ���
		boolean isfinish = false; // �ж�ѭ���Ƿ����

		while (!isfinish) {

			// ����������������ѧ�������������·���
			if (index > total) {
				index = 0;
				count++;
				schemeList.clear();

				// ����������������ѧ�������������ѭ��
			} else if (index == total) {
				isfinish = true;
				System.out.printf("����%d��ѭ�����õ����鷽������Ϊ%d��%s\n", count
						, schemeList.size()
						, schemeList);

			} else {
				// ��������������ɷ�ΧΪ[5-7],��ӵ�����������
				int num = random.nextInt(3) + 5;
				schemeList.add(num);
				index += num; // ͳ�Ƶ�ǰ���ɵ�������Ϊѭ�������ı�ʶ
			}
		}

		return schemeList;
	}

	/**
	 * ��ȡ�����䷽�������С�鼯��
	 * 
	 * @param list - ѧ������
	 * @param schemeList - ���䷽������
	 * @return ��Ű��������õ�С�鼯��
	 */
	public static ArrayList<Student[]> getGroupingList(ArrayList<Student> list, Collection<Integer> schemeList) {
		ArrayList<Student[]> groupList = new ArrayList<Student[]>(); // ��ŷ���ļ���
		Collections.shuffle(list); // shuffle ϴ���㷨������ѧ�����ϵ�����˳��

		Iterator<Integer> scheIterator = schemeList.iterator(); // ���鷽���ĵ�����

		int index=0;
		while (scheIterator.hasNext()) {
			int size = scheIterator.next();
			Student[] studentGroup = new Student[size]; // ѧ������

			// �����䷽������ÿһ��ĳ�Ա
			for (int i = 0; i < studentGroup.length; i++) {
				// ��ѧ����������ʽ����С��
				studentGroup[i] = list.get(index);
				index++;
			}
			// ��ÿһ��С����ӵ����鼯����
			groupList.add(studentGroup);
		}

		return groupList;
	}
}
