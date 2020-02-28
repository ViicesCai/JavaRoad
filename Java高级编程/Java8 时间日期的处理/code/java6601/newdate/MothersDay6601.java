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
		// 母亲节为每年五月份的第二个周日
		Scanner scanner = new Scanner(System.in);
		System.out.print("请按yyyy的格式输入年份：");
		int year = scanner.nextInt();
		scanner.close();
		
		// 对输入的年份进行判断
		if (year < 1000 && year > 9999) {
			System.out.println("输入的年份错误！");
			return;
		}
		
		// 初始化时间为五月份
		String date1 = String.format("%d-05-01", year);
		LocalDate date2 = LocalDate.parse(date1);
		
		// 获取五月的第一个周日
		TemporalAdjuster adjuster = TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY);
		LocalDate date3 = date2.with(adjuster);
		
		// 定义日期的输出格式
		DateTimeFormatter formatter = 
				DateTimeFormatter.ofPattern("yyyy年的MM月dd日 EEE是母亲节！");
		System.out.println(date3.plusWeeks(1).format(formatter));
	}

}
