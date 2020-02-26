package java6601.newdate;

import java.time.YearMonth;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class TaskMonth6601 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("�������꣺");
		int year = scanner.nextInt();
		System.out.print("�������£�");
		int month = scanner.nextInt();
		scanner.close();
		
		// �ж�������·��Ƿ�Ϸ�
		if (month >= 1 && month <= 12) {
			// ʵ�����Զ����������
			BuilderCalendar6601 calendar = new BuilderCalendar6601(YearMonth.of(year, month));
			System.out.println(calendar);
			// ��ӡ��ʽ��������
			System.out.println(calendar.getLines6601());
			
		} else {
			System.out.println("������²��Ϸ�!");
		}
	}
}
