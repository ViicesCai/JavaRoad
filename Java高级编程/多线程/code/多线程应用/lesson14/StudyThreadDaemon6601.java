/**
 * 
 */
package java6601.lesson14;

/**
 * 线程休眠
 * 
 * @author CAI
 *
 */
public class StudyThreadDaemon6601 implements Runnable {
	private int a;

	private Thread daemonThread = new Thread() {
		public void run() {
			String name = isDaemon() ? "后台线程" : "前台线程";
			
			while (true) {
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {}
				
				a = 0;
				System.out.println("211906601:" + name + "将变量 a 清零！");
			}
		}
	};
	
	@Override
	public void run() {
		String name = Thread.currentThread().isDaemon() ? "后台线程" : "前台线程";
		
		for (int i = 0; i < 300; i++) {
			System.out.println("211906601:" + name + ": " + a++);
			Thread.yield();
		}
		
		System.out.println("211906601:" + name + "结束！");
	}
	
	public StudyThreadDaemon6601() {
		daemonThread.setDaemon(true);
		daemonThread.start();
	}
	
	public static void main(String[] args) {
		new Thread(new StudyThreadDaemon6601()).start();
		System.out.println("主线程结束！");
	}
}
