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
		
		System.out.print("�������û�����");
		String userName = scanner.nextLine();
		System.out.print("���������룺");
		String password1 = scanner.nextLine();
		System.out.print("���ظ����룺");
		String password2 = scanner.nextLine();
		System.out.print("������绰��");
		String phone = scanner.nextLine();
			
		Register6601 register = new Register6601();
		
		// �ж�addUser�Ƿ�ɹ������� ��ӡ������Ϣ
		if (register.addUser(userName, password1, password2, phone)) {
			System.out.println("��ϲ" + userName + ",ע��ɹ�");
			
			System.out.println("���кϷ��û������ƣ�");
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
