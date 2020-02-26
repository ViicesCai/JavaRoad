/**
 * 
 */
package java6601.more;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class MoreBirthDay6601 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d");
		Scanner scanner = new Scanner(System.in);
		System.out.println("请按月-日的格式输入生日：");
		String str = scanner.next();
		scanner.close();
		
		// 将用户输入的月日解析成一个MonthDay对象
		MonthDay day = MonthDay.parse(str, formatter);
		LocalDate today = LocalDate.now();
		
		// day.atYear 将用户输入的生日解析成一个LocalDate对象
		// LocalDate.until 根据指定的单位计算直到另一个日期的时间量，返回Long型
		/*
		 *  改语句现将用户输入的生日解析成LocalDate对象，并调用until方法，
		 *  以天数的方法计算今天到用户输入的日期间的差量
		 */
		// ChronoUnit是个日期枚举类，可通过调用当中的成员计算不同的格式，一周、一月等
		long result = day.atYear(today.getYear()).until(today, ChronoUnit.DAYS);
		
		if (result == 0) {
			System.out.println("生日快乐");
			
		} else if (result < 0) {
			System.out.println("还有" + (-result) + "天，敬请期待......");
			
		} else {
			System.out.println("已过" + result + "天，等待来年！");
		}

	}

}
