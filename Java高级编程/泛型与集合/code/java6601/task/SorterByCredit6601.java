/**
 * 
 */
package java6601.task;

import java.util.Comparator;

/**
 * ��Course�����ѧ������
 * 
 * @author CAI
 *
 */
public class SorterByCredit6601 implements Comparator<Course6601> {
	@Override
	public int compare(Course6601 course1, Course6601 course2) {
		// �������Ƿ��ظ�
		if (course1.equals(course2)) {
			return 0;
		}
		// �����ǰ��ѧ�� > course�����ѧ�� �򷵻�1�����򷵻�-1
		// ��ǰ���� > ��һ�����󣬷��ؽ��ֵΪ1ʱ��˵������������
		return course1.getCredit() > course2.getCredit() ? 1:-1;
	}
}
