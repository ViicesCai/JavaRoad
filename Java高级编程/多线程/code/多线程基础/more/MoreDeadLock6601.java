/**
 * 
 */
package java6601.more;

/**
 * 线程死锁
 * 
 * @author CAI
 *
 */
public class MoreDeadLock6601 implements Runnable {
	private Object blueHostage = new Object();
	private Object redHostage = new Object();
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		
		if (name.equals("红军")) {
			while (true) {
				synchronized (blueHostage) {
					System.out.println("红军：蓝方人质在手，你先放人质");
					
					synchronized (redHostage) {
						System.out.println("红军：红方人质归队，才放兰方人质");
					}
				}
				
				System.out.println("红军：完胜！");
			}
		} else {
			while (true) {
				synchronized (redHostage) {
					System.out.println("兰军：红方人质在手，你先放人质");
					
					synchronized (blueHostage) {
						System.out.println("兰军：兰方人质归队，才放红方人质");
					}
				}
				
				System.out.println("兰军：完胜！");
			}
		}
	}
	
	public static void main(String[] args) {
		MoreDeadLock6601 deadLock = new MoreDeadLock6601();
		
		new Thread(deadLock, "兰军").start();
		new Thread(deadLock, "红军").start();
	}
}
