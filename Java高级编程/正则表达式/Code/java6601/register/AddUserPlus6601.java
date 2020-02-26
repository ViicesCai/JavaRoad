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
	
	// �˵���ʽ
	static {
		StringBuilder sb = new StringBuilder();
		
		sb.append("=============ѧ�ţ��������û�ע��V1.1=============\n")
		  .append("            1.---------���û�ע��\n")
		  .append("            2.---------��ʾ��ע���û��嵥\n")
		  .append("            0.---------�˳�\n")
		  .append("=================================================\n")
		  .append("������ѡ��");
		
		menuInfo = sb.toString();
	}
	
	public static void main(String[] args) {
		boolean isQuit = false; // �Ƿ��˳�
		// ��ʼ��ע�����
		register = new Register6601();
		
		while (!isQuit) {
			showMenu6601(); // ����˵�
			boolean isContinue = true; // �Ƿ��������
			
			while (isContinue) {
				Scanner scanner = new Scanner(System.in);
				String choice = scanner.nextLine();
				boolean flag = false; // �Ƿ���ʾ
				
				// �����û�������ѡ����Ӧ�Ĺ���
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
					System.out.print("ллʹ�á�");
					isQuit = true;
					isContinue = false;
					break;
					
				default:
					System.out.println("�������������ѡ��");
					isContinue = false;
				}
				
				// �ж��Ƿ���ʾ����������������޴���ʾ
				if (flag) {
					System.out.println("�밴�س���������");
					choice = scanner.nextLine();
					
					// ���û��Ĳ�������һ���жϣ�������ʾѡ��˵�
					if (choice.length() >= 0) {
						isContinue = false;
					}
				}
			}
		}
	}
	
	/**
	 * ��ʾ����˵�
	 */
	private static void showMenu6601() {
		System.out.print(menuInfo);
	}
	
	/**
	 * ע���û�
	 * @param scanner ɨ���û�������
	 */
	private static void addNewUser6601(Scanner scanner) {	
		System.out.print("�������û�����");
		String userName = scanner.nextLine();
		System.out.print("���������룺");
		String password1 = scanner.nextLine();
		System.out.print("���ظ����룺");
		String password2 = scanner.nextLine();
		System.out.print("������绰��");
		String phone = scanner.nextLine();
		
		// �ж�addUser�Ƿ�ɹ������� ��ӡ������Ϣ
		if (register.addUser(userName, password1, password2, phone)) {
			System.out.println("��ϲ" + userName + ",ע��ɹ�");
			
		} else {
			System.out.println(register.getErrMessage());
		}
		
	}
	
	/**
	 * ��ӡ��ע����û�
	 */
	private static void showUserList6601() {
		System.out.println("��ǰ��ע���û�");
		Iterator<User6601> it = register.getUserInfo().iterator();
		
		while(it.hasNext()) {
			User6601 user = (User6601)it.next();
			System.out.print(user.getUserName() + " ");
		}
		
		System.out.println();
	}
	
}
