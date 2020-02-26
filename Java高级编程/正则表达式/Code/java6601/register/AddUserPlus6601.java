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
public class AddUserPlus6601 {
	private static Register6601 register;
	private static String menuInfo;
	
	// 菜单格式
	static {
		StringBuilder sb = new StringBuilder();
		
		sb.append("=============学号：姓名，用户注册V1.1=============\n")
		  .append("            1.---------新用户注册\n")
		  .append("            2.---------显示已注册用户清单\n")
		  .append("            0.---------退出\n")
		  .append("=================================================\n")
		  .append("请输入选择：");
		
		menuInfo = sb.toString();
	}
	
	public static void main(String[] args) {
		boolean isQuit = false; // 是否退出
		// 初始化注册对象
		register = new Register6601();
		
		while (!isQuit) {
			showMenu6601(); // 界面菜单
			boolean isContinue = true; // 是否继续输入
			
			while (isContinue) {
				Scanner scanner = new Scanner(System.in);
				String choice = scanner.nextLine();
				boolean flag = false; // 是否提示
				
				// 对照用户的输入选择相应的功能
				switch (choice) {
				case "1":
					addNewUser6601(scanner);
					flag = true;
					break;
					
				case "2":
					showUserList6601();
					flag = true;
					break;
				
				case "0":
					System.out.print("谢谢使用。");
					isQuit = true;
					isContinue = false;
					break;
					
				default:
					System.out.println("输入错误，请重新选择");
					isContinue = false;
				}
				
				// 判断是否提示继续；错误的输入无此提示
				if (flag) {
					System.out.println("请按回车键继续！");
					choice = scanner.nextLine();
					
					// 对用户的操作在做一次判断，继续显示选择菜单
					if (choice.length() >= 0) {
						isContinue = false;
					}
				}
			}
		}
	}
	
	/**
	 * 显示界面菜单
	 */
	private static void showMenu6601() {
		System.out.print(menuInfo);
	}
	
	/**
	 * 注册用户
	 * @param scanner 扫描用户的输入
	 */
	private static void addNewUser6601(Scanner scanner) {	
		System.out.print("请输入用户名：");
		String userName = scanner.nextLine();
		System.out.print("请输入密码：");
		String password1 = scanner.nextLine();
		System.out.print("请重复密码：");
		String password2 = scanner.nextLine();
		System.out.print("请输入电话：");
		String phone = scanner.nextLine();
		
		// 判断addUser是否成功，否则 打印错误信息
		if (register.addUser(userName, password1, password2, phone)) {
			System.out.println("恭喜" + userName + ",注册成功");
			
		} else {
			System.out.println(register.getErrMessage());
		}
		
	}
	
	/**
	 * 打印已注册的用户
	 */
	private static void showUserList6601() {
		System.out.println("当前已注册用户");
		Iterator<User6601> it = register.getUserInfo().iterator();
		
		while(it.hasNext()) {
			User6601 user = (User6601)it.next();
			System.out.print(user.getUserName() + " ");
		}
		
		System.out.println();
	}
	
}
