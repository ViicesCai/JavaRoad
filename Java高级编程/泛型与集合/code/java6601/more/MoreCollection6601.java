/**
 * 
 */
package java6601.more;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAI
 *
 */

class One {
	public boolean equals(Object obj) {
		return true;
	}
}

public class MoreCollection6601 {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("����");
		list.add("һ�����");
		list.add("��̬����");
		list.add("��ObjectΪ�����ķ���");
		
		System.out.println(list);
		// contains ֻ���ж϶����ǿ�֮����Ȼ���õĻ��� indexOf
		// ������õ���one��equals����
		System.out.println(list.contains(new One()));
		// indexOf(Object obj)������ʵ�ֻ����Ǵ�����(List)�ĵ�0��Ԫ�ؿ�ʼ����ѭ����
		// ���ҵ���ÿ��Ԫ�ص�equals()�����Ͳ���������бȽϣ�
		// ���ĳһ��Ԫ�ص�equals()��������ֵΪtrue����ô�Ͱѵ�ǰԪ�ص�����λ����Ϊ������ء�
		System.out.println(list.indexOf(new One()));
		// remove(Object o)
		// ���жϴ���Ķ���O ��Ϊ��ʱ������list�����е�Ԫ�أ��ҵ���֮��ͬ��Ԫ�ء�
		// �����ж���ͬԪ���õ��Ƕ���O��equals����
		System.out.println(list.remove(new One()));
		System.out.println(list.remove(new One()));
		System.out.println(list);
		
	}

}
