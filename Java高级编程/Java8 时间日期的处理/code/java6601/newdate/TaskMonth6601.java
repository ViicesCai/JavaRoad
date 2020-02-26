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
		System.out.print("请输入年：");
		int year = scanner.nextInt();
		System.out.print("请输入月：");
		int month = scanner.nextInt();
		scanner.close();
		
		// 判断输出的月份是否合法
		if (month >= 1 && month <= 12) {
			// 实例化自定义的日历类
			BuilderCalendar6601 calendar = new BuilderCalendar6601(YearMonth.of(year, month));
			System.out.println(calendar);
			// 打印格式化的日历
			System.out.println(calendar.getLines6601());
			
		} else {
			System.out.println("输入的月不合法!");
		}
	}
}
