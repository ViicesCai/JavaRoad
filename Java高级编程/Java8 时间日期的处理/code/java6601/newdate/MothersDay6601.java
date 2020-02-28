/**
 * 
 */
package java6601.newdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class MothersDay6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ĸ�׽�Ϊÿ�����·ݵĵڶ�������
		Scanner scanner = new Scanner(System.in);
		System.out.print("�밴yyyy�ĸ�ʽ������ݣ�");
		int year = scanner.nextInt();
		scanner.close();
		
		// ���������ݽ����ж�
		if (year < 1000 && year > 9999) {
			System.out.println("�������ݴ���");
			return;
		}
		
		// ��ʼ��ʱ��Ϊ���·�
		String date1 = String.format("%d-05-01", year);
		LocalDate date2 = LocalDate.parse(date1);
		
		// ��ȡ���µĵ�һ������
		TemporalAdjuster adjuster = TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY);
		LocalDate date3 = date2.with(adjuster);
		
		// �������ڵ������ʽ
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("yyyy���MM��dd�� EEE��ĸ�׽ڣ�");
		System.out.println(date3.plusWeeks(1).format(formatter));
	}

}
