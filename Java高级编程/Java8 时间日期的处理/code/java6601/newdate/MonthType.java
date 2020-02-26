/**
 * 
 */
package java6601.newdate;

/**
 * 月份的枚举类
 * @author CAI
 *
 */
public enum MonthType {
	JANUARY("一月", 1),
	FEBRUARY("二月", 2),
	MARCH("三月", 3),
	APRILL("四月", 4),
	MAY("五月", 5),
	JUNE("六月", 6),
	JULY("七月", 7),
	AUGUST("八月", 8),
	SEPTEMBER("九月", 9),
	October("十月", 10),
	NOVEMBER("十一月", 11),
	DECEMBER("十二月", 12);
	
	
	private final String name; // 月份的中文格式
	private final int id; // 月份的数字格式
	
	private MonthType(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	/**
	 * 根据数字月份获取中文月份
	 * @param id 月份的数字格式
	 * @return 月份的中文格式
	 */
	public static String getNameById(int id){
        for(MonthType monthType:MonthType.values()){
        	if (id == monthType.getId()) {
				return monthType.getName();
			}
        }
        return  null;
    }
}
