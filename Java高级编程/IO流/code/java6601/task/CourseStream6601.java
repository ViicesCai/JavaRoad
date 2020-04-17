package java6601.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Stream�ķ������
 * 
 * @author CAI
 *
 */
public class CourseStream6601 {

	public static void main(String[] args) {
		Map<String, List<Course6601>> map = getCourses().stream().collect(Collectors.groupingBy(Course6601::getTerm));
		TreeMap<String, List<Course6601>> treeMap = new TreeMap<String, List<Course6601>>(map);
		
		for (Map.Entry<String, List<Course6601>> entry : treeMap.entrySet()) {
			System.out.println("=====ѧ�ڣ�" + entry.getKey() + "=====");
			Double avg = entry.getValue().stream().collect(Collectors.averagingDouble(Course6601::getScore));
			System.out.println(String.format("��%d�ſγ̣�ƽ���֣�%.1f", entry.getValue().size(), avg));
			entry.getValue()
			     .stream().sorted((o1, o2) -> Integer.compare(o1.getScore(), o2.getScore()))
			     .forEach(System.out::println);
		}
		
	}

	private static List<Course6601> getCourses() {
		List<Course6601> courses = new ArrayList<Course6601>();
		courses.add(new Course6601("A001", "C������ƻ���", "1��", 4, 80));
		courses.add(new Course6601("A004", "��ɢ��ѧ", "1��", 3, 79));
		courses.add(new Course6601("B002", "Pthon�������", "1��", 2, 85));
		courses.add(new Course6601("A002", "�������������", "2��", 3, 78));
		courses.add(new Course6601("C012", "�������", "3��", 2, 72));
		courses.add(new Course6601("C001", "Java����߼�����", "3��", 2, 83));
		courses.add(new Course6601("0003", "��ѧ����", "2��", 2, 77));
		courses.add(new Course6601("C002", "�������", "3��", 3, 75));
		courses.add(new Course6601("C011", "�ƶ���Ŀ����", "3��", 2, 87));
		courses.add(new Course6601("B001", "����ϵͳ", "2��", 3, 90));
		courses.add(new Course6601("A005", "�㷨�����ݽṹ", "2��", 3, 82));
		courses.add(new Course6601("A003", "���ݿ�ԭ��", "2��", 3, 78));
		courses.add(new Course6601("C003", "ǰ�˿�������", "3��", 3, 69));
		courses.add(new Course6601("B003", "�������", "2��", 3, 75));
		courses.add(new Course6601("0001", "��ѧӢ��", "1��", 3, 80));
		courses.add(new Course6601("0002", "�ߵ���ѧ", "1��", 2, 85));
		courses.add(new Course6601("B004", "���������", "1��", 4, 68));
		courses.add(new Course6601("B005", "���������ԭ��", "2��", 4, 78));
		return courses;
	}

	private static class Course6601 {
		private String no;// �γ̱��
		private String name;// �γ�����
		private String term;// ����ѧ��
		private int credit;// ѧ��
		private int score;// �ɼ�

		public Course6601() {
			super();
		}

		public Course6601(String no, String name, String term, int credit, int score) {
			super();
			this.no = no;
			this.name = name;
			this.term = term;
			this.credit = credit;
			this.score = score;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTerm() {
			return term;
		}

		public void setTerm(String term) {
			this.term = term;
		}

		public int getCredit() {
			return credit;
		}

		public void setCredit(int credit) {
			this.credit = credit;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return String.format("%s %-20s\t\t%2s,%d,%d", no, name, term, credit, score);
		}
	}

}
