/**
 * 
 */
package java6601.lesson14;

/**
 * �߳�����
 * 
 * @author CAI
 *
 */
public class StudyThreadDaemon6601 implements Runnable {
	private int a;

	private Thread daemonThread = new Thread() {
		public void run() {
			String name = isDaemon() ? "��̨�߳�" : "ǰ̨�߳�";
			
			while (true) {
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {}
				
				a = 0;
				System.out.println("211906601:" + name + "������ a ���㣡");
			}
		}
	};
	
	@Override
	public void run() {
		String name = Thread.currentThread().isDaemon() ? "��̨�߳�" : "ǰ̨�߳�";
		
		for (int i = 0; i < 300; i++) {
			System.out.println("211906601:" + name + ": " + a++);
			Thread.yield();
		}
		
		System.out.println("211906601:" + name + "������");
	}
	
	public StudyThreadDaemon6601() {
		daemonThread.setDaemon(true);
		daemonThread.start();
	}
	
	public static void main(String[] args) {
		new Thread(new StudyThreadDaemon6601()).start();
		System.out.println("���߳̽�����");
	}
}
