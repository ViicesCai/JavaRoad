package java6601.bank;

/**
 * ���̣߳�ͬ��
 * 
 * @author CAI
 *
 */
public class StudyThreadAndRunnable6601 {
	private int tickets = 1;
	
	/**
	 * �̵߳����з���
	 */
	private void run() {
		while (true) {
			if (tickets <= 8) {
				
				// �ӳټ��أ�����۲��̵߳�����
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
	 * ��Ʊ����
	 */
	private void sell() {
		// �����µĴ������������� this �������Ķ���
		// ��������������Ŀ���ǣ�����ڵ�������ʹ�� this �Ļ�����Ϊ�Ƿ������ý������̣߳������ this ʵ�����ǵĸ��̶߳���
		synchronized (this) {
			if (tickets <= 8) {
				String name = Thread.currentThread().getName();
				System.out.println(name + "����" + tickets++ + "��Ʊ");
			}
		}
	}
	
	public static void main(String[] args) {
		StudyThreadAndRunnable6601 runnable = new StudyThreadAndRunnable6601();
		
		// ��������ֱ�ӵ��� start ����������� Thread �������һ��������
		new Thread(runnable::run, "��ά��1").start();
		new Thread(runnable::run, "��ά��2").start();
		new Thread(runnable::run, "��ά��3").start();
	}
}
