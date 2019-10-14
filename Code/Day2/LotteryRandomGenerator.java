import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class LotteryRandomGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ���ո����ķ�Χ��Ҫ��������ɲ�Ʊ

		System.out.printf("��Ʊ������\n");
		System.out.print("���������ɵ����ַ�Χ��");
		Scanner numberRangeScanner = new Scanner(System.in);
		int numberRange = numberRangeScanner.nextInt();
		
		System.out.print("�����빺������");
		Scanner randomTotalScanner = new Scanner(System.in);
		int randomTotal = randomTotalScanner.nextInt();
		
		System.out.print("�����빺��ע����");
		Scanner randomTimeScanner = new Scanner(System.in);
		int randomTime = randomTimeScanner.nextInt();
		
		if (numberRange <= 0 || randomTotal <= 0 || randomTime <= 0) {
			System.out.println("��Ч���룡");
			numberRangeScanner.close();
			randomTotalScanner.close();
			randomTimeScanner.close();
			
			return;
		}
		
		System.out.print("�Ƿ��������(Y/N)��");
		Scanner sortScanner = new Scanner(System.in);
		String sortCode = new String();
		sortCode = sortScanner.next();
		
		Boolean sortBoolean;
		if (sortCode.equals("Y")) {
			sortBoolean = true;
			
		} else if (sortCode.equals("N")) {
			sortBoolean = false;
			
		} else {
			System.out.println("��Ч���룡");
			numberRangeScanner.close();
			randomTotalScanner.close();
			randomTimeScanner.close();
			sortScanner.close();
			
			return;
		}
		
		int randomArray[] = new int[randomTotal];
		Random random = new Random();
		
		for (int i = 0; i < randomTime; i++) {
			int count = 0;
			
			while (count < randomArray.length) {
				Boolean inexistence = true;
				int randomNumber = 1 + random.nextInt(numberRange);
				
				for (int j = 0; j < randomArray.length; j++) {
					if (randomNumber == randomArray[j]) {
						inexistence = false;
						break;
						
					}
				}
				
				if (inexistence) {
					randomArray[count] = randomNumber;
					count++;
				}
			}
			
			if (sortBoolean) {
				Arrays.sort(randomArray);
			}
			
			System.out.printf("��%d�飺",i+1);
			for (int j = 0; j < randomArray.length; j++) {
				
				System.out.print(randomArray[j] + "  ");
			}
			System.out.println();
		}
		numberRangeScanner.close();
		randomTotalScanner.close();
		randomTimeScanner.close();
		sortScanner.close();
	}
}
