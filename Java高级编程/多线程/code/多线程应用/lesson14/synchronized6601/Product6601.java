/**
 * 
 */
package java6601.lesson14.synchronized6601;

/**
 * ��Ʒ�ࣺͬ��
 * 
 * @author CAI
 *
 */
public class Product6601 {
	private int id;
	private String step1 = "δ֪";
	private String step2 = "δ֪";
	private String step3 = "δ֪";
	
	public synchronized void put(int id, String step1, String step2, String step3) {
		try {
			this.id = id;
			this.step1 = step1;
			Thread.sleep(1);
			
			this.step2 = step2;
			Thread.sleep(10);
			
			this.step3 = step3;
			System.out.println("����������" + id);
			
		} catch (InterruptedException e) {}
	}
	
	public synchronized void get() {
		System.out.println(this);
		
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {}
	}
	
	@Override
	public String toString() {
		return id + ":" + step1 + "," + step2 + "," + step3;
	}
}
