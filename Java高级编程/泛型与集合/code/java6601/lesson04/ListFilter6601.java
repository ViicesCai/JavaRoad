/**
 * 
 */
package java6601.lesson04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author CAI
 *
 */
public class ListFilter6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ����list
		ArrayList<Integer> list = new ArrayList<Integer>();

		// ���20������list��
		addNumbers(list);
		System.out.println(list);

		// ɸѡ����
		filterFiveTimesNumber(list);
		System.out.println("��" + list.size() + "����5�ı�����");
		System.out.println(list);
	}

	/**
	 * ��[60-100]�Ķ�ʮ������ӽ�������
	 * 
	 * @param list - ������ݵļ���
	 */
	public static void addNumbers(ArrayList<Integer> list) {
		// �������������
		Random random = new Random();

		// ���20������list��,��ӵķ�ΧΪ[60-100]֮�����
		for (int i = 0; i < 20; i++) {
			// ��ʼֵ[0-40] �ں��� + 60ʹ���䷶Χ��Ϊ [60 - 100]
			list.add(random.nextInt(41) + 60);
		}
	}

	/**
	 * �Լ����е�������ɸѡ������5�ı���
	 * 
	 * @param list - ������ݵļ���
	 */
	public static void filterFiveTimesNumber(ArrayList<Integer> list) {
		// ����������
		Iterator<Integer> iterator = list.iterator();

		// �ȱ���list�е���������
		while (iterator.hasNext()) {
			// �ҳ�5�ı���������
			if (iterator.next() % 5 == 0) {
				continue;
			}
			
			// ɾ��list�в���5�ı�������
			iterator.remove();
		}
	}
}
