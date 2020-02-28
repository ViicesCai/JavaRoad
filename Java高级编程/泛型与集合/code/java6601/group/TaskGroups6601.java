/**
 * 
 */
package java6601.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
		ArrayList<Student> studentList = getStudentList(); // ѧ������
		ArrayList<Integer> schemeList = getGroupingScheme(studentList.size()); // ���䷽������
		ArrayList<Student[]> groupList = getGroupingList(studentList, schemeList);
		
		Iterator<Student[]> iterator = groupList.iterator();
		
		int count = 1; // ������
		while (iterator.hasNext()) {
			Student[] studentGroup = iterator.next();
			System.out.println("��"+ count++ + "��" + Arrays.toString(studentGroup) + studentGroup.length + "��");
		}
	}

	/**
	 * ��ȡѧ��������
	 * 
	 * @return ���ѧ�����ݵļ���
	 */
	public static ArrayList<Student> getStudentList() {
		try {
			// ʹ��scanner��ɨ��StudentList�е�����
			Scanner scanner = new Scanner(new File("StudentList.txt"));
			
			ArrayList<Student> studentList = new ArrayList<>(); // ���ڴ��ѧ������
			
			// �������
			while (scanner.hasNext()) {
				studentList.add(new Student(scanner.nextLine()));
			}
			scanner.close();
			
			System.out.println("�༶������" + studentList.size());

			return studentList;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		return null;
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
		
		int index = 0; // ��Ϊѭ���ı�ʶ
		int count = 0;// ͳ��ѭ���Ĵ���
		boolean isfinish = false; // �ж�ѭ���Ƿ����
		
		while (!isfinish) {
			// ������ɵ�
			if (index > total) {
				index = 0;
				count++;
				schemeList.clear();
				
			} else if (index == total) {
				isfinish = true;
				System.out.printf("����%d��ѭ�����õ����鷽������Ϊ%d��%s\n", count, schemeList.size(), schemeList);
				
			} else {
				// ��������������ɷ�ΧΪ[3-5],��ӵ�����������
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
	 * @param studentList - ѧ������
	 * @param schemeList - ���䷽������
	 * @return ��Ű��������õ�С�鼯��
	 */
	public static ArrayList<Student[]> getGroupingList(ArrayList<Student> studentList, ArrayList<Integer> schemeList) {
		ArrayList<Student[]> groupList = new ArrayList<Student[]>(); // ��ŷ���ļ���
		ArrayList<Integer> indexList = new ArrayList<Integer>(); // ����һ��������������ȡѧ��
		int total = studentList.size(); // ѧ��������
		
		Random random = new Random();
		
		// ��ѧ������Ŵ���
		while (indexList.size() != total) {
			int num = random.nextInt(total); // ���ɵ��������Χ [0 - total]
			// �жϸ����Ƿ���ڣ������������
			if (!indexList.contains(num)) {
				indexList.add(num);
			}
		}
		
		Iterator<Integer> scheIterator = schemeList.iterator(); // ���鷽���ĵ�����
		
		int index = 0;
		while (scheIterator.hasNext()) {
			int size = scheIterator.next();
			Student[] studentGroup = new Student[size]; // ѧ������
			
			// �����䷽������ÿһ��ĳ�Ա
			for (int i = 0; i < studentGroup.length; i++) {
				// ��ѧ����������ʽ����С��
				studentGroup[i] = studentList.get(indexList.get(index));
				index++;
			}
			// ��ÿһ��С����ӵ����鼯����
			groupList.add(studentGroup);
		}
		
		return groupList;
	}
}
