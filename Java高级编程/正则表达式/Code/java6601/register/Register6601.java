/**
 * 
 */
package java6601.register;

import java.util.ArrayList;

/**
 * @author CAI
 *
 */
public class Register6601 {
	private ArrayList<User6601> userInfo = new ArrayList<User6601>(); // �û���Ϣ
	private String errMessage; // ������Ϣ
	
	public ArrayList<User6601> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(ArrayList<User6601> userInfo) {
		this.userInfo = userInfo;
	}

	public String getErrMessage() {
		return errMessage;
	}

	private void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @description Ԥ�ȴ洢һ���ѺϷ�������
	 */
	public Register6601() {
		super();
		userInfo.add(new User6601("Cai", "Aa123123", "15000000000"));
		userInfo.add(new User6601("Jay", "Zxc123123", "13888888888"));
		userInfo.add(new User6601("Kay", "Lilihappy365", "13333333333"));
	}
	
	/**
	 * ���ܣ�����û����Ƿ���Ϲ淶
	 * 
	 * @param userName String���ͣ��û���
	 * @return true ���ϣ� false ������
	 */
	public boolean checkName6601(String userName) {
		if (userName.equals("")) {
			setErrMessage("�û�������Ϊ�գ����������룡");
			return false;
		}
		
		for (User6601 user : userInfo) {
			if (user != null) {
				if (user.getUserName().equals(userName)) {
					setErrMessage("�û����ѱ�ʹ�ã����������룡");
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * ���ܣ���������Ƿ���Ϲ淶
	 * 
	 * @param password1 String���ͣ�����
	 * @param password2 String���ͣ��ظ�������
	 * @return true ���ϣ� false ������
	 */
	public boolean checkPassword6601(String password1, String password2) {
		if (password1.equals("") || password2.equals("")) {
			setErrMessage("�����������룡");
			return false;
		}
		
		if (!password1.equals(password2)) {
			setErrMessage("������������벻һ�£����������룡");
			return false;
		}
		
		String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}";
		boolean check = password1.matches(regex);
		
		if (!check) {
			setErrMessage("���볤������6λ�����ұ��������д��ĸ��Сд��ĸ�����֣����������룡");
		}
		
		return check;
	}
	
	/**
	 * ���ܣ�����ֻ������Ƿ���Ϲ淶
	 * 
	 * @param phone String���ͣ��ֻ�����
	 * @return true ���ϣ� false ������
	 */
	public boolean checkPhone6601(String phone) {
		String regex = "1[3578]\\d{9}";
		boolean check = phone.matches(regex);
		
		if (!check) {
			setErrMessage("�ֻ����������11λ���֣�������13��15��17��18��ͷ�����������룡");
		}
		
		return check;
	}
	
	/**
	 * ���ܣ�����������Զ�����Ҫ������һ��User����
	 * 
	 * @param userName String���ͣ��û���
	 * @param password1 String���ͣ�����
	 * @param password2 String���ͣ��ظ�������
	 * @param phone String���ͣ��ֻ�����
	 * @return true���������Ծ����Ϲ淶 false��ĳ�����Բ�����Ҫ��
	 */
	public boolean addUser(String userName, String password1, String password2, String phone) {
		if (!checkName6601(userName)) {
			return false;
		}
		
		if (!checkPassword6601(password1, password2)) {
			return false;
		}
		
		if (!checkPhone6601(phone)) {
			return false;
		}
		
		userInfo.add(new User6601(userName, password1, phone));
		
		return true;
	}
}
