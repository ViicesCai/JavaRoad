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
		ArrayList<Student> studentList = getStudentList(); // 学生集合
		ArrayList<Integer> schemeList = getGroupingScheme(studentList.size()); // 分配方案集合
		ArrayList<Student[]> groupList = getGroupingList(studentList, schemeList);
		
		Iterator<Student[]> iterator = groupList.iterator();
		
		int count = 1; // 计数器
		while (iterator.hasNext()) {
			Student[] studentGroup = iterator.next();
			System.out.println("第"+ count++ + "组" + Arrays.toString(studentGroup) + studentGroup.length + "人");
		}
	}

	/**
	 * 获取学生的数据
	 * 
	 * @return 存放学生数据的集合
	 */
	public static ArrayList<Student> getStudentList() {
		try {
			// 使用scanner类扫描StudentList中的数据
			Scanner scanner = new Scanner(new File("StudentList.txt"));
			
			ArrayList<Student> studentList = new ArrayList<>(); // 用于存放学生数据
			
			// 添加数据
			while (scanner.hasNext()) {
				studentList.add(new Student(scanner.nextLine()));
			}
			scanner.close();
			
			System.out.println("班级人数：" + studentList.size());

			return studentList;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
		return null;
	}

	/**
	 * 获取分组方案
	 * 
	 * @param total - 学生的总人数
	 * 
	 * @return 存放分组方案的集合
	 */
	public static ArrayList<Integer> getGroupingScheme(int total) {
		ArrayList<Integer> schemeList = new ArrayList<Integer>(); // 存放分配方案
		
		Random random = new Random();
		
		int index = 0; // 作为循环的标识
		int count = 0;// 统计循环的次数
		boolean isfinish = false; // 判断循环是否结束
		
		while (!isfinish) {
			// 如果生成的
			if (index > total) {
				index = 0;
				count++;
				schemeList.clear();
				
			} else if (index == total) {
				isfinish = true;
				System.out.printf("经过%d次循环，得到分组方案：分为%d组%s\n", count, schemeList.size(), schemeList);
				
			} else {
				// 设置随机数的生成范围为[3-5],添加到方案集合中
				int num = random.nextInt(3) + 5;
				schemeList.add(num);
				index += num; // 统计当前生成的人数作为循环结束的标识
			}
		}
		
		return schemeList;
	}
	
	/**
	 * 获取按分配方案分配的小组集合
	 * 
	 * @param studentList - 学生集合
	 * @param schemeList - 分配方案集合
	 * @return 存放按乱序分配好的小组集合
	 */
	public static ArrayList<Student[]> getGroupingList(ArrayList<Student> studentList, ArrayList<Integer> schemeList) {
		ArrayList<Student[]> groupList = new ArrayList<Student[]>(); // 存放分组的集合
		ArrayList<Integer> indexList = new ArrayList<Integer>(); // 生成一组随机序号用于提取学生
		int total = studentList.size(); // 学生的总数
		
		Random random = new Random();
		
		// 将学生的序号打乱
		while (indexList.size() != total) {
			int num = random.nextInt(total); // 生成的随机数范围 [0 - total]
			// 判断该数是否存在，不存在则添加
			if (!indexList.contains(num)) {
				indexList.add(num);
			}
		}
		
		Iterator<Integer> scheIterator = schemeList.iterator(); // 分组方案的迭代器
		
		int index = 0;
		while (scheIterator.hasNext()) {
			int size = scheIterator.next();
			Student[] studentGroup = new Student[size]; // 学生分组
			
			// 按分配方案分配每一组的成员
			for (int i = 0; i < studentGroup.length; i++) {
				// 将学生以乱序形式加入小组
				studentGroup[i] = studentList.get(indexList.get(index));
				index++;
			}
			// 将每一个小组添加到分组集合内
			groupList.add(studentGroup);
		}
		
		return groupList;
	}
}
