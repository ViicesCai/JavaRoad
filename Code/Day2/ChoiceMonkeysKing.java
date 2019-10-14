import java.util.Scanner;

/**
 * 
 */

/**
 * @author CAI
 *
 */
public class ChoiceMonkeysKing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("����ѡ����");
		
		System.out.print("��������ӵ�������");
		Scanner totalScanner = new Scanner(System.in);
		int total = totalScanner.nextInt();
		
		System.out.print("����������ؼ�����Ϊ��������");
		Scanner keyNumberScanner = new Scanner(System.in);
		int keyNumber = keyNumberScanner.nextInt();
		
		if (total < keyNumber) {
			System.out.println("�ؼ��ֲ��ܳ���������");
			totalScanner.close();
			keyNumberScanner.close();
			return;
		}
		
		int winner = ChoiceKing(total, keyNumber);
		System.out.printf("��%dֻ�Ǻ���",winner);
		totalScanner.close();
		keyNumberScanner.close();
	}
	
	public static int ChoiceKing(int total,int keyNumber) {
		Boolean[] identityTag = new Boolean[total];
		
		// Ĭ��ÿ������Ϊtrue
		for (int i = 0; i < total; i++) {
			identityTag[i] = true;
		}
		
		int remainder = total;// ʣ����
		int count = 0; // ����ÿ�ֵĺ��Ӹ���
		int index = 0; // �����ʶ��
		int time = 0;  // ����      
		int killer = 0; // �˳��ĺ���
		int countOff = 0; // ����ĺ���
		
		// ȷ������ֻʣ��һֻ����
		while (remainder > 1) {
			
			// choice������������п��ƣ���choiceΪ��ʱ�����ֽ���
			Boolean choice = false;
			
			// indexΪ0����һ��
			if (index == 0) {
				time++;
			}
			
			// Ϊtrue�����ú���δ���޳�
			if (identityTag[index]) {
				count += 1;
				countOff += 1;
				
				// �жϸ����Ƿ�Ϊ3�ı���
				if (count % keyNumber == 0 && count != 0) {
					identityTag[index] = false;
					killer = index + 1;
					remainder--;
					choice = true;
					
					// һ�ֵļ���������ǰ�������㡣��ʾ������������һ����ʱ�����ص���һ������ȥ
					if (count > remainder) {
						count = 0;
					}
				}
			}
			// ��������
			index++;
			
			// ���������һ��Ԫ��ʱ���¿�ʼ
			if (index == total) {
				index = 0;
			}
			
			// ���ÿһ�ֵĽ��
			if (choice) {
				System.out.printf("��%d�ֱ���   ��%dֻ����  ��%d���˳��� ��ǰ�˳�%dֻ\n", time,killer,countOff,total-remainder);
				}
			}
		
		for (int i = 0; i < total; i++) {
			if (identityTag[i]) {
				return i + 1;
			}
		}
		return 0;
	}
}
			 
