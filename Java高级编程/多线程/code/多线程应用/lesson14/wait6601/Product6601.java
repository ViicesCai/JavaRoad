/**
 * 
 */
package java6601.lesson14.wait6601;

/**
 * 产品类：等待与通知
 * 
 * @author CAI
 *
 */
public class Product6601 {
	private int id;
	private String step1 = "未知";
	private String step2 = "未知";
	private String step3 = "未知";
	private boolean isPut = false;
	
	public synchronized void put(int id, String step1, String step2, String step3) {
		if (isPut == true) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		try {
			this.id = id;
			this.step1 = step1;
			Thread.sleep(10);
			
			this.step2 = step2;
			Thread.sleep(10);
			
			this.step3 = step3;
			System.out.println("生产结束：" + id);
			
		} catch (InterruptedException e) {}
		
		isPut = true;
		notify();
	}
	
	public synchronized void get() {
		if (isPut == false) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		System.out.println(this);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {}
		
		isPut = false;
		notify();
	}
	
	@Override
	public String toString() {
		return id + ":" + step1 + "," + step2 + "," + step3;
	}
}
