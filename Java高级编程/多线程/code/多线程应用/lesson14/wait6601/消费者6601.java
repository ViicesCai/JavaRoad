package java6601.lesson14.wait6601;

/**
 * 消费者类：这里用中文作为类名，仅仅是便于学习理解，在实际开发中不推荐用中文
 * 
 * @author CAI
 *
 */
public class 消费者6601 implements Runnable {
	private Product6601 item;
	
	public 消费者6601(Product6601 item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			item.get();
		}
	}
}
