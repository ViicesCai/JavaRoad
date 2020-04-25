/**
 * 
 */
package java6601.bank;

/**
 * è����ˮ���߳�ͬ��
 * 
 * @author CAI
 *
 */
public class StudyThreadName6601 {
	private int waters; // ˮ��
	private boolean isDone = false; // ˮ�Ƿ��㹻��

	public StudyThreadName6601(int waters) {
		super();
		this.waters = waters;
	}

	private void run() {

		// ���ˮ���ȣ�ִ�� drink ����
		while (!isDone) {
			if (waters >= 0) {
				try {
					Thread.sleep(1000);

				} catch (Exception e) {
					e.printStackTrace();
				}

				drink();

			} else {
				break;
			}
		}
	}

	/**
	 * è����ˮ�ķ���
	 */
	private void drink() {
		String name = Thread.currentThread().getName();
		int num = 0; // ��ˮ��ˮ��

		// è��ÿ�κ�ˮ��ˮ����ͬ
		if (name.equals("��")) {
			num = 5;

		} else if (name.equals("è")) {
			num = 2;
		}

		// ͬ���飬һ��ֻ����һ��������ø÷���
		synchronized (this) {
			int total = waters - num;

			if (total >= 0) { // ���ˮ���㹻��ִ��
				waters = total;

				System.out.println(name + "�Ѻ�ˮ��ʣ" + waters);

			} else { // ������ʾ��ˮ
				System.out.println(name + "��Ҫ��ˮ");
				isDone = true;
			}
		}
	}

	public static void main(String[] args) {
		StudyThreadName6601 thread = new StudyThreadName6601(20);

		new Thread(thread::run, "��").start();
		new Thread(thread::run, "è").start();

	}
}
