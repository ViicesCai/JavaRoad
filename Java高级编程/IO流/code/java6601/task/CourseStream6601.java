package java6601.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Stream的分组计算
 * 
 * @author CAI
 *
 */
public class CourseStream6601 {

	public static void main(String[] args) {
		Map<String, List<Course6601>> map = getCourses().stream().collect(Collectors.groupingBy(Course6601::getTerm));
		TreeMap<String, List<Course6601>> treeMap = new TreeMap<String, List<Course6601>>(map);
		
		for (Map.Entry<String, List<Course6601>> entry : treeMap.entrySet()) {
			System.out.println("=====学期：" + entry.getKey() + "=====");
			Double avg = entry.getValue().stream().collect(Collectors.averagingDouble(Course6601::getScore));
			System.out.println(String.format("共%d门课程，平均分：%.1f", entry.getValue().size(), avg));
			entry.getValue()
			     .stream().sorted((o1, o2) -> Integer.compare(o1.getScore(), o2.getScore()))
			     .forEach(System.out::println);
		}
		
	}

	private static List<Course6601> getCourses() {
		List<Course6601> courses = new ArrayList<Course6601>();
		courses.add(new Course6601("A001", "C程序设计基础", "1上", 4, 80));
		courses.add(new Course6601("A004", "离散数学", "1下", 3, 79));
		courses.add(new Course6601("B002", "Pthon程序基础", "1下", 2, 85));
		courses.add(new Course6601("A002", "面向对象程序设计", "2上", 3, 78));
		courses.add(new Course6601("C012", "软件测试", "3下", 2, 72));
		courses.add(new Course6601("C001", "Java程序高级开发", "3上", 2, 83));
		courses.add(new Course6601("0003", "大学物理", "2上", 2, 77));
		courses.add(new Course6601("C002", "软件工程", "3下", 3, 75));
		courses.add(new Course6601("C011", "移动项目开发", "3下", 2, 87));
		courses.add(new Course6601("B001", "操作系统", "2下", 3, 90));
		courses.add(new Course6601("A005", "算法与数据结构", "2上", 3, 82));
		courses.add(new Course6601("A003", "数据库原理", "2上", 3, 78));
		courses.add(new Course6601("C003", "前端开发技术", "3上", 3, 69));
		courses.add(new Course6601("B003", "网络基础", "2下", 3, 75));
		courses.add(new Course6601("0001", "大学英语", "1上", 3, 80));
		courses.add(new Course6601("0002", "高等数学", "1下", 2, 85));
		courses.add(new Course6601("B004", "计算机网络", "1下", 4, 68));
		courses.add(new Course6601("B005", "计算机数组原理", "2上", 4, 78));
		return courses;
	}

	private static class Course6601 {
		private String no;// 课程编号
		private String name;// 课程名称
		private String term;// 开课学期
		private int credit;// 学分
		private int score;// 成绩

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
