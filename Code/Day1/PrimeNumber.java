import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class PrimeNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("请输入要搜索质数的范围，用空格分隔，回车结束：");
		Scanner scanner = new Scanner(System.in);
		int beginRange = scanner.nextInt();
		int overRange = scanner.nextInt();
		
		if (beginRange >= overRange) {
			System.out.println("搜索的范围不正确，开始范围不能大于结束范围！");
			return;
		}
		
		int count = 0;
		int total = 0;
		for (int i = beginRange; i <= overRange; i++) {
			boolean isPNum = true;
			for (int j = 2; j <= i-1; j++) {
				if (i % j == 0) {
					isPNum = false;
					break;
				}
			}
			if (isPNum) {
				System.out.println(i);
				count++;
				total += i;
			}
		}
		System.out.println("搜索到的质数个数："+ count);
		System.out.println("该范围内质数总和为："+ total);
	}
}
