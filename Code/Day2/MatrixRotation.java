import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class MatrixRotation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ���������Ľ׳���ת90������
		
		System.out.print("�������������֣�");
		Scanner inputNumberScanner = new Scanner(System.in);
		int inputNumber = inputNumberScanner.nextInt();
		
		int [][] a = new int[inputNumber][inputNumber];
		
		for (int i = 0; i < inputNumber; i++) {
			for (int j = 0; j < inputNumber; j++) {
				a[i][j] = i * inputNumber + j + 1; 
			}
		}
		
		
		for (int j = 0; j < inputNumber; j++) {
			for (int i = 0; i < inputNumber; i++) {
				System.out.printf("%-5d",a[j][i]);
			}
			System.out.println();
		}
		
		System.out.println("��ת90��");
		
		for (int i = 0; i < inputNumber; i++) {
			for (int j = inputNumber - 1; j >= 0; j--) {
				System.out.printf("%-5d",a[j][i]);
			}
			System.out.println();
		}
		inputNumberScanner.close();
	}
}
