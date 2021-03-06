/**
 * 
 */
package java6601.lesson14.simple6601;

/**
 * 生产者类：这里用中文作为类名，仅仅是便于学习理解，在实际开发中不推荐用中文
 * 
 * @author CAI
 *
 */
public class 生产者6601 implements Runnable {
	private String[] word1 = new String[] {"211906601", "106609112"};
	private String[] word2 = new String[] {"蔡维恒", "奕恒蔡"};
	private String[] word3 = new String[] {"闽南烧肉粽", "醋肉面线糊"};
	private Product6601 item;
	
	public 生产者6601(Product6601 item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		int k;
		for (int i = 0; i < 10; i++) {
			k = i % 2;
			
			try {
				item.setId(i);
				item.setStep1(word1[k]);
				Thread.sleep(10);
				
				item.setStep2(word2[k]);
				Thread.sleep(10);
				
				item.setStep3(word3[k]);
				System.out.println("生产结束：" + i);
				
			} catch (InterruptedException e) {}
		}
	}
}
