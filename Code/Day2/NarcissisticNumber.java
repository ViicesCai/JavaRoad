import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class NarcissisticNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ˮ�ɻ���
		long startTime = System.currentTimeMillis();   //��ȡ��ʼʱ��
		
		long startNumber = 100;
		long numberRange = 1;
		System.out.print("������Ҫ����ˮ�ɻ����ĳ��ȷ�Χ��3-10��:");
		Scanner r = new Scanner(System.in);
		long range = r.nextInt();
		
		if (range < 3 || range > 10) {
			System.out.println("����������Χ��");
			r.close();
			
			return;
		}
		
		
		for (long i = 0; i < range; i++) {
			numberRange *= 10;
		}
		
		System.out.println("Loading.......");
		
		for (long j = startNumber; j <= numberRange-1; j++) {
			long temporaryNumber = j;
			long disposeNumber = 0;
			long total = 0;
			
			while (temporaryNumber != 0) {
				
				disposeNumber = temporaryNumber;
				
				//System.out.println("Dispose:" + disposeNumber);
				temporaryNumber /= 10;
				temporaryNumber *= 10;
				long digitalNumber = disposeNumber - temporaryNumber;
				
				//System.out.println("Digital:" + digitalNumber);
				long digitalNumberPower = 1;
				
				for (long i = 0; i < range; i++) {
					digitalNumberPower *= digitalNumber;
				}
				
				total += digitalNumberPower;
				//System.out.println("Total:"+total);
				
				temporaryNumber /= 10;
			}
			
			if (total == j) {
				System.out.println("����������:" + j);	
			}
		}
		
		long endTime=System.currentTimeMillis(); //��ȡ����ʱ��
		
		System.out.println("��������"+"  ��������ʱ�䣺 " + (endTime - startTime) + "MS");
		r.close();
	}
}
