/**
 * 
 */
package java6601.lesson13;

/**
 * ���߳�����߳�
 * 
 * @author CAI
 *
 */
public class StudyThreadAndRunnable6601 {
	
	public static void main(String[] args) {
		saleByRunnable6601();
	}
	
	/**
	 * ���߳�ʵ��
	 */
	private static void saleByThread6601() {
		TicketThread6601 thread1 = new TicketThread6601("��ά��1");
		TicketThread6601 thread2 = new TicketThread6601("��ά��2");
		TicketThread6601 thread3 = new TicketThread6601("��ά��3");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	/**
	 * ���߳�ʵ��
	 */
	private static void saleByRunnable6601() {
		TicketRunnable6601 runnable = new TicketRunnable6601();
		
		new Thread(runnable, "��ά��1").start();
		new Thread(runnable, "��ά��2").start();
		new Thread(runnable, "��ά��3").start();
	}
}

/**
 * ���߳��ࣺ����ʹ���ڲ���Ϊ�˷���ѧϰ��ʵ�ʿ����в��Ƽ����ַ�ʽ�����߳�
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
			
			System.out.println(name + "����" + tickets++ + "��Ʊ");
		}
	}
}

/**
 * ���߳��ࣺ����ʹ���ڲ���Ϊ�˷���ѧϰ��ʵ�ʿ����в��Ƽ����ַ�ʽ�����߳�
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
			
			System.out.println(name + "����" + tickets++ + "��Ʊ");
		}
	}
}