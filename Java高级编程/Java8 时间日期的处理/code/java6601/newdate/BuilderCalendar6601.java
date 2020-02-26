/**
 * 
 */
package java6601.newdate;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author CAI
 *
 */
public class BuilderCalendar6601 {
	private YearMonth yearMonth; // ������Ӧ���ꡢ��

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * �õ�ǰ���ڳ�ʼ��yearMonth��ʹ��YearMonth��now()
	 */
	public BuilderCalendar6601() {
		super();
		this.yearMonth = YearMonth.now();
	}
	
	/**
	 * �ô���Ĳ�����ʼ��yearMonth
	 * 
	 * @param yearMonth ����ʱ�����
	 */
	public BuilderCalendar6601(YearMonth yearMonth) {
		super();
		this.yearMonth = yearMonth;
	}
	
	@Override
	public String toString() {
		// �����·������ʽ���滻
		// �˴�������·�����ö��������
		//String[] chineseMonth = {"", "һ��", "����", "����", "����", "����", "����", "����", "����", "����", "ʮ��", "ʮһ��", "ʮ����"};
		//return yearMonth.getYear() + "��" + chineseMonth[yearMonth.getMonthValue()] + "������";
		
		// ����ʽ��ӡ������Ϣ
		return yearMonth.getYear() + "��" + 
		MonthType.getNameById(yearMonth.getMonthValue()) + "������";
	}
	
	/**
	 * �������������
	 * @return ���ַ������鱣�����������
	 */
	private String[] getDays6601() {
		// ÿ���������ʾ42�죬�� ����
		String[] days = new String[42]; 
		
		LocalDate firstDay = yearMonth.atDay(1); // ��ǰ�µ�һ�������
		int start = firstDay.getDayOfWeek().getValue(); // ���µ�һ���Ӧ����������
		
		// ���µĵ�һ��ǰ��Ϊ��
		for (int i = 0; i < start; i++) {
			days[i] = "";
		}
		
		int n = firstDay.lengthOfMonth(); // ���µ�����
		
		// �ӱ��µĵ�һ�쿪ʼ��д����
		for (int i = 1; i <= n; i++) {
			// ���ڵĸ�ʽ
			days[start - 1 + i] = String.format("%2d", i);
		}
		
		// ����֮��Ϊ��
		for (int i = start + n; i < days.length; i++) {
			days[i] = "";
		}
		
		return days;
	}

	/**
	 * �����������ʾ��ʽ
	 * 
	 * @return ���������ݰ���ʽ���ɺõ��ַ���
	 */
	public String getLines6601() {
		StringBuilder builder = new StringBuilder();
		
		String[] titles = {"��", "һ", "��", "��", "��", "��", "��"}; // ���������ڱ���
		
		for (int i = 0; i < titles.length; i++) {
			builder.append(String.format("%s\t", titles[i])); // ���ڱ��ⰴ��ʽ������Ϊ�ַ���
		}
		
		// ��42��Ԫ�ذ�7��һ�У���ʽ��Ϊ�ַ���
		String[] days = getDays6601();
		
		for (int i = 0; i < days.length; i++) {
			// ÿ�߸�����
			if (i%7 == 0) {
				builder.append("\n");
			}
			// �������ݰ���ʽ��ʽ��Ϊ�ַ���
			builder.append(days[i] + "\t");
		}
		
		return builder.toString();
	}
}
