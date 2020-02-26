/**
 * 
 */
package java6601.register;

import java.util.Iterator;
import java.util.Scanner;

/**
 * @author CAI
 *
 */
public class AddUser6601 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("请输入用户名：");
		String userName = scanner.nextLine();
		System.out.print("请输入密码：");
		String password1 = scanner.nextLine();
		System.out.print("请重复密码：");
		String password2 = scanner.nextLine();
		System.out.print("请输入电话：");
		String phone = scanner.nextLine();
			
		Register6601 register = new Register6601();
		
		// 判断addUser是否成功，否则 打印错误信息
		if (register.addUser(userName, password1, password2, phone)) {
			System.out.println("恭喜" + userName + ",注册成功");
			
			System.out.println("所有合法用户的名称：");
			Iterator<User6601> it = register.getUserInfo().iterator();
			
			while (it.hasNext()) {
				User6601 user = (User6601)it.next();
				System.out.print(user.getUserName() + " ");
			}
			
		} else {
			System.out.println(register.getErrMessage());
		}
	}
}
