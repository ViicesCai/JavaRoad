/**
 * 
 */
package java6601.lesson13;

/**
 * �̳߳�ͻ����
 * 
 * @author CAI
 *
 */
public class StudyThreadName6601 {
	private int waters; // ˮ��

	public StudyThreadName6601(int waters) {
		super();
		this.waters = waters;
	}

	public void drinkWater6601() {

		String name = Thread.currentThread().getName();

		while (waters > 0) {
			if (name.equals("��")) {
				waters = waters - 5;

			} else if (name.equals("è")) {
				waters = waters - 2;
			}

			System.out.println(name + "�Ѻ�ˮ��ʣ" + waters);
		}

		System.out.println(name + "���ˮ��");

	}

	public static void main(String[] args) {
		StudyThreadName6601 thread = new StudyThreadName6601(20);

		new Thread(thread::drinkWater6601, "��").start();
		new Thread(thread::drinkWater6601, "è").start();
	}
}
