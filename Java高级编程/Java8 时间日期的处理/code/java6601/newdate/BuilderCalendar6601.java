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
	private YearMonth yearMonth; // 月历对应的年、月

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * 用当前日期初始化yearMonth：使用YearMonth的now()
	 */
	public BuilderCalendar6601() {
		super();
		this.yearMonth = YearMonth.now();
	}
	
	/**
	 * 用传入的参数初始化yearMonth
	 * 
	 * @param yearMonth 日期时间对象
	 */
	public BuilderCalendar6601(YearMonth yearMonth) {
		super();
		this.yearMonth = yearMonth;
	}
	
	@Override
	public String toString() {
		// 对于月份输出格式的替换
		// 此处如果将月份生成枚举类会更好
		//String[] chineseMonth = {"", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
		//return yearMonth.getYear() + "年" + chineseMonth[yearMonth.getMonthValue()] + "的日历";
		
		// 按格式打印年月信息
		return yearMonth.getYear() + "年" + 
		MonthType.getNameById(yearMonth.getMonthValue()) + "的日历";
	}
	
	/**
	 * 获得日历的数据
	 * @return 以字符串数组保存的日历数据
	 */
	private String[] getDays6601() {
		// 每个月最多显示42天，即 六周
		String[] days = new String[42]; 
		
		LocalDate firstDay = yearMonth.atDay(1); // 当前月第一天的日期
		int start = firstDay.getDayOfWeek().getValue(); // 本月第一天对应的星期整数
		
		// 本月的第一天前都为空
		for (int i = 0; i < start; i++) {
			days[i] = "";
		}
		
		int n = firstDay.lengthOfMonth(); // 本月的天数
		
		// 从本月的第一天开始填写日期
		for (int i = 1; i <= n; i++) {
			// 日期的格式
			days[start - 1 + i] = String.format("%2d", i);
		}
		
		// 本月之后都为空
		for (int i = start + n; i < days.length; i++) {
			days[i] = "";
		}
		
		return days;
	}

	/**
	 * 获得日历的显示格式
	 * 
	 * @return 按日历数据按格式生成好的字符串
	 */
	public String getLines6601() {
		StringBuilder builder = new StringBuilder();
		
		String[] titles = {"日", "一", "二", "三", "四", "五", "六"}; // 日历的星期标题
		
		for (int i = 0; i < titles.length; i++) {
			builder.append(String.format("%s\t", titles[i])); // 星期标题按格式化生成为字符串
		}
		
		// 把42个元素按7个一行，格式化为字符串
		String[] days = getDays6601();
		
		for (int i = 0; i < days.length; i++) {
			// 每七个换行
			if (i%7 == 0) {
				builder.append("\n");
			}
			// 日历数据按格式格式化为字符串
			builder.append(days[i] + "\t");
		}
		
		return builder.toString();
	}
}
