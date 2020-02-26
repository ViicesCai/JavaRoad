/**
 * 
 */
package java6601.newdate;

/**
 * �·ݵ�ö����
 * @author CAI
 *
 */
public enum MonthType {
	JANUARY("һ��", 1),
	FEBRUARY("����", 2),
	MARCH("����", 3),
	APRILL("����", 4),
	MAY("����", 5),
	JUNE("����", 6),
	JULY("����", 7),
	AUGUST("����", 8),
	SEPTEMBER("����", 9),
	October("ʮ��", 10),
	NOVEMBER("ʮһ��", 11),
	DECEMBER("ʮ����", 12);
	
	
	private final String name; // �·ݵ����ĸ�ʽ
	private final int id; // �·ݵ����ָ�ʽ
	
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
	 * ���������·ݻ�ȡ�����·�
	 * @param id �·ݵ����ָ�ʽ
	 * @return �·ݵ����ĸ�ʽ
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
