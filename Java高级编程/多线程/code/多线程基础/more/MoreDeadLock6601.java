/**
 * 
 */
package java6601.more;

/**
 * �߳�����
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
		
		if (name.equals("���")) {
			while (true) {
				synchronized (blueHostage) {
					System.out.println("����������������֣����ȷ�����");
					
					synchronized (redHostage) {
						System.out.println("������췽���ʹ�ӣ��ŷ���������");
					}
				}
				
				System.out.println("�������ʤ��");
			}
		} else {
			while (true) {
				synchronized (redHostage) {
					System.out.println("�������췽�������֣����ȷ�����");
					
					synchronized (blueHostage) {
						System.out.println("�������������ʹ�ӣ��ŷź췽����");
					}
				}
				
				System.out.println("��������ʤ��");
			}
		}
	}
	
	public static void main(String[] args) {
		MoreDeadLock6601 deadLock = new MoreDeadLock6601();
		
		new Thread(deadLock, "����").start();
		new Thread(deadLock, "���").start();
	}
}
