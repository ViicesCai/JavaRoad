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
	private String[] word1 = new String[] {"211906601", "106609112"};
	private String[] word2 = new String[] {"��ά��", "�Ⱥ��"};
	private String[] word3 = new String[] {"����������", "�������ߺ�"};
	private Product6601 item;
	
	public ������6601(Product6601 item) {
		this.item = item;
	}
	
	@Override
	public void run() {
		int k;
		for (int i = 0; i < 10; i++) {
			k = i % 2;
			
			try {
				item.setId(i);
				item.setStep1(word1[k]);
				Thread.sleep(10);
				
				item.setStep2(word2[k]);
				Thread.sleep(10);
				
				item.setStep3(word3[k]);
				System.out.println("����������" + i);
				
			} catch (InterruptedException e) {}
		}
	}
}
