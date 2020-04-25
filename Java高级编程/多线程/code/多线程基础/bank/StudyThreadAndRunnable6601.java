package java6601.bank;

/**
 * 多线程：同步
 * 
 * @author CAI
 *
 */
public class StudyThreadAndRunnable6601 {
	private int tickets = 1;
	
	/**
	 * 线程的运行方法
	 */
	private void run() {
		while (true) {
			if (tickets <= 8) {
				
				// 延迟加载：方便观察线程的运行
				try {
					Thread.sleep(1000);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				sell();
				
			} else {
				break;
			}
		}
	}
	
	/**
	 * 售票方法
	 */
	private void sell() {
		// 给如下的代码加锁：这里的 this 是这个类的对象
		// 分离两个方法的目的是，如果在单方法中使用 this 的话，因为是方法引用建立的线程，这里的 this 实际上是的该线程对象
		synchronized (this) {
			if (tickets <= 8) {
				String name = Thread.currentThread().getName();
				System.out.println(name + "：第" + tickets++ + "张票");
			}
		}
	}
	
	public static void main(String[] args) {
		StudyThreadAndRunnable6601 runnable = new StudyThreadAndRunnable6601();
		
		// 匿名对象直接调用 start 方法：这里的 Thread 的身份是一个代理类
		new Thread(runnable::run, "蔡维恒1").start();
		new Thread(runnable::run, "蔡维恒2").start();
		new Thread(runnable::run, "蔡维恒3").start();
	}
}
