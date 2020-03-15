package java6601.more;

import java.text.Collator;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 
 * @author CAI
 *
 */
class Student {
	private String name;
	private int age;

	public Student() {
		this.name = "新学生";
		this.age = 20;
	}

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[" + name + ", " + age + "]";
	}
}

class StudentByName implements Comparator<Student> {
	@Override
	public int compare(Student stu1, Student stu2) {
		int i = stu1.getName().compareTo(stu2.getName());
		if (i == 0) {
			return stu1.getAge() - stu2.getAge();
		} else {
			return i;
		}
	}
}

class StudentByNamePY implements Comparator<Student> {
	@Override
	public int compare(Student stu1, Student stu2) {
		Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		int i = cmp.compare(stu1.getName(), stu2.getName());
		if (i == 0) {
			return stu1.getAge() - stu2.getAge();
		} else {
			return i;
		}
	}
}

public class ChineseSort6601 {
	public static void main(String[] args) {
		TreeSet<Student> ts;
		
		System.out.println("----------TreeSet按姓名排序----------");
		ts = new TreeSet<Student>(new StudentByName());
		show(ts);
		
		System.out.println("----------TreeSet按姓名排序PY----------");
		ts = new TreeSet<Student>(new StudentByNamePY());
		show(ts);
	}

	public static void show(TreeSet<Student> ts) {
		ts.add(new Student("刘明刚", 10));
		ts.add(new Student("陈通山", 20));
		ts.add(new Student("陈通山", 15));
		ts.add(new Student("林甘福", 15));
		ts.add(new Student("林甘福", 15));

		for (Student student : ts) {
			System.out.println(student);
		}

		System.out.println(ts.contains(new Student("刘明刚", 10)));
	}
}
