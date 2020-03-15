/**
 * 
 */
package java6601.task;

import java.util.Comparator;

/**
 * 按Course对象的学分升序
 * 
 * @author CAI
 *
 */
public class SorterByCredit6601 implements Comparator<Course6601> {
	@Override
	public int compare(Course6601 course1, Course6601 course2) {
		// 检查对象是否重复
		if (course1.equals(course2)) {
			return 0;
		}
		// 如果当前的学分 > course对象的学分 则返回1，否则返回-1
		// 当前对象 > 后一个对象，返回结果值为1时，说明是升序排列
		return course1.getCredit() > course2.getCredit() ? 1:-1;
	}
}
