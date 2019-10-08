import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class MultiplicationTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("请输入任意数字，自动输出其乘法表：");
		Scanner scanner = new Scanner(System.in);
		int inputNumber = scanner.nextInt();
		System.out.println();
		
		for (int i = 1; i <= inputNumber; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.printf("%d X %d = %d  ",i,j,i*j);
			}
			System.out.println();
		}
		
		System.out.println();
		for (int i = inputNumber; i > 0; i--) {
			for (int j = i; j > 0; j--) {
				System.out.printf("%d X %d = %d  ",i,j,i*j);
			}
			System.out.println();
		}
	}
}
