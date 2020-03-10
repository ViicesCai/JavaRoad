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
		ArrayList<Student> studentList = new ArrayList<Student>(); // 学生集合
		initList(studentList); // 初始化学生数据
		show(studentList); // 显示
	}

	/**
	 * 显示学生的分组信息
	 * 
	 * @param list
	 */
	public static void show(ArrayList<Student> list) {
		ArrayList<Integer> schemeList; // 分组方案集合
		ArrayList<Student[]> groupList; // 分组信息集合

		schemeList = getGroupingScheme(list.size()); // 获得分组方案
		groupList = getGroupingList(list, schemeList); // 获得分组信息

		Iterator<Student[]> iterator = groupList.iterator();

		System.out.println("班级人数：" + list.size());
		
		int count = 1; // 计数器，用做小组的序号
		while (iterator.hasNext()) {
			Student[] studentGroup = iterator.next();
			System.out.printf("第%d组%s%d人\n", count++
					, Arrays.toString(studentGroup)
					, studentGroup.length);
		}
	}

	/**
	 * 初始化学生信息
	 * 
	 * @param list - 学生集合
	 */
	public static void initList(ArrayList<Student> list) {
		try {
			// 使用scanner类扫描StudentList中的数据
			Scanner scanner = new Scanner(new File("StudentList.txt"));

			// 添加数据
			while (scanner.hasNext()) {
				list.add(new Student(scanner.nextLine()));
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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

		int index = 0; // 累加器标识符
		int count = 1;// 统计循环的次数
		boolean isfinish = false; // 判断循环是否结束

		while (!isfinish) {

			// 如果分组的总数超过学生总数，则重新分组
			if (index > total) {
				index = 0;
				count++;
				schemeList.clear();

				// 如果分组的总数等于学生总数，则结束循环
			} else if (index == total) {
				isfinish = true;
				System.out.printf("经过%d次循环，得到分组方案：分为%d组%s\n", count
						, schemeList.size()
						, schemeList);

			} else {
				// 设置随机数的生成范围为[5-7],添加到方案集合中
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
	 * @param list - 学生集合
	 * @param schemeList - 分配方案集合
	 * @return 存放按乱序分配好的小组集合
	 */
	public static ArrayList<Student[]> getGroupingList(ArrayList<Student> list, Collection<Integer> schemeList) {
		ArrayList<Student[]> groupList = new ArrayList<Student[]>(); // 存放分组的集合
		Collections.shuffle(list); // shuffle 洗牌算法，打乱学生集合的排列顺序

		Iterator<Integer> scheIterator = schemeList.iterator(); // 分组方案的迭代器

		int index=0;
		while (scheIterator.hasNext()) {
			int size = scheIterator.next();
			Student[] studentGroup = new Student[size]; // 学生分组

			// 按分配方案分配每一组的成员
			for (int i = 0; i < studentGroup.length; i++) {
				// 将学生以乱序形式加入小组
				studentGroup[i] = list.get(index);
				index++;
			}
			// 将每一个小组添加到分组集合内
			groupList.add(studentGroup);
		}

		return groupList;
	}
}
