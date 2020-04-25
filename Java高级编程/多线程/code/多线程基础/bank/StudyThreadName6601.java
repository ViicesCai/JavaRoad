/**
 * 
 */
package java6601.bank;

/**
 * 猫狗喝水：线程同步
 * 
 * @author CAI
 *
 */
public class StudyThreadName6601 {
	private int waters; // 水量
	private boolean isDone = false; // 水是否足够喝

	public StudyThreadName6601(int waters) {
		super();
		this.waters = waters;
	}

	private void run() {

		// 如果水够喝，执行 drink 方法
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
	 * 猫狗喝水的方法
	 */
	private void drink() {
		String name = Thread.currentThread().getName();
		int num = 0; // 喝水的水量

		// 猫狗每次喝水的水量不同
		if (name.equals("狗")) {
			num = 5;

		} else if (name.equals("猫")) {
			num = 2;
		}

		// 同步块，一次只允许一个对象调用该方法
		synchronized (this) {
			int total = waters - num;

			if (total >= 0) { // 如果水量足够，执行
				waters = total;

				System.out.println(name + "已喝水，剩" + waters);

			} else { // 否则，提示添水
				System.out.println(name + "需要添水");
				isDone = true;
			}
		}
	}

	public static void main(String[] args) {
		StudyThreadName6601 thread = new StudyThreadName6601(20);

		new Thread(thread::run, "狗").start();
		new Thread(thread::run, "猫").start();

	}
}
