/**
 * 
 */
package java6601.task;

import java.util.Comparator;

/**
 * 按Course对象的学期升序
 * 
 * @author CAI
 *
 */
public class SorterByTerm6601 implements Comparator<Course6601>{
	@Override
	public int compare(Course6601 course1, Course6601 course2) {
		// 检查对象是否重复
		if (course1.equals(course2)) {
			return 0;
		}
		
		int result = course1.getTerm().compareTo(course2.getTerm()); // 字符串比较的结果
		
		// 如果字符串的比对结果 > 0则返回1，否则返回-1
		// 当前对象 > 后一个对象，返回结果值为1时，说明是升序排列
		return result > 0 ? 1: -1;
	}
}
