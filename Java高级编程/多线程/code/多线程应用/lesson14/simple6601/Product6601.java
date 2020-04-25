/**
 * 
 */
package java6601.lesson14.simple6601;

/**
 * ²úÆ·Àà
 * 
 * @author CAI
 *
 */
public class Product6601 {
	private int id;
	private String step1;
	private String step2;
	private String step3;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStep1() {
		return step1;
	}
	
	public void setStep1(String step1) {
		this.step1 = step1;
	}
	
	public String getStep2() {
		return step2;
	}
	
	public void setStep2(String step2) {
		this.step2 = step2;
	}
	
	public String getStep3() {
		return step3;
	}
	
	public void setStep3(String step3) {
		this.step3 = step3;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + "]";
	}
	
}
