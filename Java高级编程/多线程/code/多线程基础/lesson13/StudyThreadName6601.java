/**
 * 
 */
package java6601.lesson13;

/**
 * 线程冲突例子
 * 
 * @author CAI
 *
 */
public class StudyThreadName6601 {
	private int waters; // 水量

	public StudyThreadName6601(int waters) {
		super();
		this.waters = waters;
	}

	public void drinkWater6601() {

		String name = Thread.currentThread().getName();

		while (waters > 0) {
			if (name.equals("狗")) {
				waters = waters - 5;

			} else if (name.equals("猫")) {
				waters = waters - 2;
			}

			System.out.println(name + "已喝水，剩" + waters);
		}

		System.out.println(name + "请加水！");

	}

	public static void main(String[] args) {
		StudyThreadName6601 thread = new StudyThreadName6601(20);

		new Thread(thread::drinkWater6601, "狗").start();
		new Thread(thread::drinkWater6601, "猫").start();
	}
}
