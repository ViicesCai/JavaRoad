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
		System.out.print("������Ҫ���������ķ�Χ���ÿո�ָ����س�������");
		Scanner scanner = new Scanner(System.in);
		int beginRange = scanner.nextInt();
		int overRange = scanner.nextInt();
		
		if (beginRange >= overRange) {
			System.out.println("�����ķ�Χ����ȷ����ʼ��Χ���ܴ��ڽ�����Χ��");
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
		System.out.println("������������������"+ count);
		System.out.println("�÷�Χ�������ܺ�Ϊ��"+ total);
	}
}
