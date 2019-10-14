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
		System.out.println("猴子选猴王");
		
		System.out.print("请输入猴子的总数：");
		Scanner totalScanner = new Scanner(System.in);
		int total = totalScanner.nextInt();
		
		System.out.print("请输入任意关键数作为驱动数：");
		Scanner keyNumberScanner = new Scanner(System.in);
		int keyNumber = keyNumberScanner.nextInt();
		
		if (total < keyNumber) {
			System.out.println("关键字不能超过总数！");
			totalScanner.close();
			keyNumberScanner.close();
			return;
		}
		
		int winner = ChoiceKing(total, keyNumber);
		System.out.printf("第%d只是猴王",winner);
		totalScanner.close();
		keyNumberScanner.close();
	}
	
	public static int ChoiceKing(int total,int keyNumber) {
		Boolean[] identityTag = new Boolean[total];
		
		// 默认每个数都为true
		for (int i = 0; i < total; i++) {
			identityTag[i] = true;
		}
		
		int remainder = total;// 剩余数
		int count = 0; // 计算每轮的猴子个数
		int index = 0; // 数组标识符
		int time = 0;  // 轮数      
		int killer = 0; // 退出的猴子
		int countOff = 0; // 报告的号数
		
		// 确保最终只剩下一只猴子
		while (remainder > 1) {
			
			// choice用来对输出进行控制，当choice为真时，本轮结束
			Boolean choice = false;
			
			// index为0代表一轮
			if (index == 0) {
				time++;
			}
			
			// 为true则代表该猴子未被剔除
			if (identityTag[index]) {
				count += 1;
				countOff += 1;
				
				// 判断该数是否为3的倍数
				if (count % keyNumber == 0 && count != 0) {
					identityTag[index] = false;
					killer = index + 1;
					remainder--;
					choice = true;
					
					// 一轮的计数超出当前数则清零。表示当输出数到最后一个数时继续回到第一个属下去
					if (count > remainder) {
						count = 0;
					}
				}
			}
			// 遍历数组
			index++;
			
			// 当数到最后一个元素时重新开始
			if (index == total) {
				index = 0;
			}
			
			// 输出每一轮的结果
			if (choice) {
				System.out.printf("第%d轮报数   第%d只猴子  因报%d号退出！ 当前退出%d只\n", time,killer,countOff,total-remainder);
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
			 
