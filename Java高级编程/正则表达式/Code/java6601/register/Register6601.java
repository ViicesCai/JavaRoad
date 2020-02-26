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
	private ArrayList<User6601> userInfo = new ArrayList<User6601>(); // 用户信息
	private String errMessage; // 错误信息
	
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
	 * @description 预先存储一组已合法的数据
	 */
	public Register6601() {
		super();
		userInfo.add(new User6601("Cai", "Aa123123", "15000000000"));
		userInfo.add(new User6601("Jay", "Zxc123123", "13888888888"));
		userInfo.add(new User6601("Kay", "Lilihappy365", "13333333333"));
	}
	
	/**
	 * 功能：检查用户名是否符合规范
	 * 
	 * @param userName String类型，用户名
	 * @return true 符合， false 不符合
	 */
	public boolean checkName6601(String userName) {
		if (userName.equals("")) {
			setErrMessage("用户名不能为空，请重新输入！");
			return false;
		}
		
		for (User6601 user : userInfo) {
			if (user != null) {
				if (user.getUserName().equals(userName)) {
					setErrMessage("用户名已被使用，请重新输入！");
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 功能：检查密码是否符合规范
	 * 
	 * @param password1 String类型，密码
	 * @param password2 String类型，重复的密码
	 * @return true 符合， false 不符合
	 */
	public boolean checkPassword6601(String password1, String password2) {
		if (password1.equals("") || password2.equals("")) {
			setErrMessage("必须输入密码！");
			return false;
		}
		
		if (!password1.equals(password2)) {
			setErrMessage("两次输入的密码不一致，请重新输入！");
			return false;
		}
		
		String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}";
		boolean check = password1.matches(regex);
		
		if (!check) {
			setErrMessage("密码长度至少6位，而且必须包含大写字母、小写字母、数字，请重新输入！");
		}
		
		return check;
	}
	
	/**
	 * 功能：检查手机号码是否符合规范
	 * 
	 * @param phone String类型，手机号码
	 * @return true 符合， false 不符合
	 */
	public boolean checkPhone6601(String phone) {
		String regex = "1[3578]\\d{9}";
		boolean check = phone.matches(regex);
		
		if (!check) {
			setErrMessage("手机号码必须是11位数字，必须以13、15、17、18开头，请重新输入！");
		}
		
		return check;
	}
	
	/**
	 * 功能：如果所有属性都符合要求，生成一个User对象
	 * 
	 * @param userName String类型，用户名
	 * @param password1 String类型，密码
	 * @param password2 String类型，重复的密码
	 * @param phone String类型，手机号码
	 * @return true，所有属性均符合规范 false，某个属性不符合要求
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
