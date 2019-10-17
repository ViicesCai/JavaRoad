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
		// 水仙花数
		long startTime = System.currentTimeMillis();   //获取开始时间
		
		long startNumber = 100;
		long numberRange = 1;
		System.out.print("请输入要搜索水仙花数的长度范围（3-10）:");
		Scanner r = new Scanner(System.in);
		long range = r.nextInt();
		
		if (range < 3 || range > 10) {
			System.out.println("超出搜索范围！");
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
				System.out.println("搜索到的数:" + j);	
			}
		}
		
		long endTime=System.currentTimeMillis(); //获取结束时间
		
		System.out.println("搜索结束"+"  程序运行时间： " + (endTime - startTime) + "MS");
		r.close();
	}
}
