/**
 * 
 */
package java6601.lesson14.wait6601;

/**
 * ��Ʒ�ࣺ�ȴ���֪ͨ
 * 
 * @author CAI
 *
 */
public class Product6601 {
	private int id;
	private String step1 = "δ֪";
	private String step2 = "δ֪";
	private String step3 = "δ֪";
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
			System.out.println("����������" + id);
			
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
