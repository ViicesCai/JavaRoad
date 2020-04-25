/**
 * 
 */
package java6601.lesson13;

/**
 * 单线程与多线程
 * 
 * @author CAI
 *
 */
public class StudyThreadAndRunnable6601 {
	
	public static void main(String[] args) {
		saleByRunnable6601();
	}
	
	/**
	 * 单线程实例
	 */
	private static void saleByThread6601() {
		TicketThread6601 thread1 = new TicketThread6601("蔡维恒1");
		TicketThread6601 thread2 = new TicketThread6601("蔡维恒2");
		TicketThread6601 thread3 = new TicketThread6601("蔡维恒3");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	/**
	 * 多线程实例
	 */
	private static void saleByRunnable6601() {
		TicketRunnable6601 runnable = new TicketRunnable6601();
		
		new Thread(runnable, "蔡维恒1").start();
		new Thread(runnable, "蔡维恒2").start();
		new Thread(runnable, "蔡维恒3").start();
	}
}

/**
 * 单线程类：这里使用内部类为了方便学习，实际开发中不推荐这种方式定义线程
 * 
 * 
 * @author CAI
 *
 */
class TicketThread6601 extends Thread {
	private int tickets = 1;
	
	public TicketThread6601(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (tickets <= 8) {
			String name = Thread.currentThread().getName();
			
			System.out.println(name + "：第" + tickets++ + "张票");
		}
	}
}

/**
 * 多线程类：这里使用内部类为了方便学习，实际开发中不推荐这种方式定义线程
 * 
 * 
 * @author CAI
 *
 */
class TicketRunnable6601 implements Runnable {
	private int tickets = 1;
	
	@Override
	public void run() {
		while (tickets <= 8) {
			String name = Thread.currentThread().getName();
			
			System.out.println(name + "：第" + tickets++ + "张票");
		}
	}
}