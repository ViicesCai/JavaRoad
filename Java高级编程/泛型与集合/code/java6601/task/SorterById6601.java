/**
 * 
 */
package java6601.task;

import java.util.Comparator;

/**
 * ��Course����Ŀγ̺�����
 * 
 * @author CAI
 *
 */
public class SorterById6601 implements Comparator<Course6601>{
	@Override
	public int compare(Course6601 course1, Course6601 course2) {
		// �������Ƿ��ظ�
		if (course1.equals(course2)) {
			return 0;
		}
		
		int result = course1.getId().compareTo(course2.getId()); // �ַ����ȽϵĽ��
		
		// ����ַ����ıȶԽ�� > 0�򷵻�1�����򷵻�-1
		// ��ǰ���� > ��һ�����󣬷��ؽ��ֵΪ1ʱ��˵������������
		return result > 0 ? 1:-1;
	}
}
