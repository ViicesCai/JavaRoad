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
public class SorterByTerm6601 implements Comparator<Course6601>{
	@Override
	public int compare(Course6601 course1, Course6601 course2) {
		// �������Ƿ��ظ�
		if (course1.equals(course2)) {
			return 0;
		}
		
		int result = course1.getTerm().compareTo(course2.getTerm()); // �ַ����ȽϵĽ��
		
		// ����ַ����ıȶԽ�� > 0�򷵻�1�����򷵻�-1
		// ��ǰ���� > ��һ�����󣬷��ؽ��ֵΪ1ʱ��˵������������
		return result > 0 ? 1: -1;
	}
}
