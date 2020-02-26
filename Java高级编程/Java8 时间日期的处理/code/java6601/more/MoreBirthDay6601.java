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
		System.out.println("�밴��-�յĸ�ʽ�������գ�");
		String str = scanner.next();
		scanner.close();
		
		// ���û���������ս�����һ��MonthDay����
		MonthDay day = MonthDay.parse(str, formatter);
		LocalDate today = LocalDate.now();
		
		// day.atYear ���û���������ս�����һ��LocalDate����
		// LocalDate.until ����ָ���ĵ�λ����ֱ����һ�����ڵ�ʱ����������Long��
		/*
		 *  ������ֽ��û���������ս�����LocalDate���󣬲�����until������
		 *  �������ķ���������쵽�û���������ڼ�Ĳ���
		 */
		// ChronoUnit�Ǹ�����ö���࣬��ͨ�����õ��еĳ�Ա���㲻ͬ�ĸ�ʽ��һ�ܡ�һ�µ�
		long result = day.atYear(today.getYear()).until(today, ChronoUnit.DAYS);
		
		if (result == 0) {
			System.out.println("���տ���");
			
		} else if (result < 0) {
			System.out.println("����" + (-result) + "�죬�����ڴ�......");
			
		} else {
			System.out.println("�ѹ�" + result + "�죬�ȴ����꣡");
		}

	}

}
