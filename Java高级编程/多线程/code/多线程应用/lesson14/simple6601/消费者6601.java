/**
 * 
 */
package java6601.lesson14.simple6601;

/**
 * �������ࣺ������������Ϊ�����������Ǳ���ѧϰ��⣬��ʵ�ʿ����в��Ƽ�������
 * 
 * @author CAI
 *
 */
public class ������6601 implements Runnable {
	private Product6601 item;
	
	public ������6601(Product6601 item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(item);
			
			try {
				Thread.sleep(30);
				
			} catch (InterruptedException e) {}
		}
	}
}
