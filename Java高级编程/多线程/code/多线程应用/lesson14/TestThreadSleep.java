/**
 * 
 */
package java6601.lesson14;

/**
 * �߳�����
 * 
 * @author CAI
 *
 */
public class TestThreadSleep {
	
	public static void main(String[] args) {
		ClassRoom6601 room = new ClassRoom6601();
		
		room.student.start();
		room.teacher.start();
	}
}

class ClassRoom6601 implements Runnable {
	Thread student, teacher;

	public ClassRoom6601() {
		teacher = new Thread(this);
		student = new Thread(this, "��ά��");
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();

		if (Thread.currentThread() == teacher) {
			for (int i = 0; i < 3; i++) {
				System.out.println("�Ͽ�......");

				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {}
			}

			student.interrupt();

		} else {
			System.out.println(name + "��һ�Ͽξͽ�������......");

			try {
				Thread.sleep(1000 * 60 * 45);

			} catch (InterruptedException e) {
				System.out.println(name + "��������......");
			}

			System.out.println(name + "����ʼ�Ͽ�......");
		}
	}
}
