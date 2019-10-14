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
		// 按照给定的范围及要求随机生成彩票

		System.out.printf("彩票生成器\n");
		System.out.print("请输入生成的数字范围：");
		Scanner numberRangeScanner = new Scanner(System.in);
		int numberRange = numberRangeScanner.nextInt();
		
		System.out.print("请输入购买串数：");
		Scanner randomTotalScanner = new Scanner(System.in);
		int randomTotal = randomTotalScanner.nextInt();
		
		System.out.print("请输入购买注数：");
		Scanner randomTimeScanner = new Scanner(System.in);
		int randomTime = randomTimeScanner.nextInt();
		
		if (numberRange <= 0 || randomTotal <= 0 || randomTime <= 0) {
			System.out.println("无效输入！");
			numberRangeScanner.close();
			randomTotalScanner.close();
			randomTimeScanner.close();
			
			return;
		}
		
		System.out.print("是否进行排序(Y/N)：");
		Scanner sortScanner = new Scanner(System.in);
		String sortCode = new String();
		sortCode = sortScanner.next();
		
		Boolean sortBoolean;
		if (sortCode.equals("Y")) {
			sortBoolean = true;
			
		} else if (sortCode.equals("N")) {
			sortBoolean = false;
			
		} else {
			System.out.println("无效输入！");
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
			
			System.out.printf("第%d组：",i+1);
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
