package java6601.ktv;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskKTVSelect6601 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		initList6601(list); // ��ʼ�������б�
		menu6601(list); // ����˵�
	}

	/**
	 * �˵�����
	 * 
	 * @param list - �����б�
	 */
	public static void menu6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			boolean isContinue = true; // �Ƿ������ʾ�˵�
			
			while (isContinue) {
				showMenu6601(list); // ��ʾ�˵�
				String choice = scanner.nextLine().trim(); // �û�����

				switch (choice) { // �е��û�ѡ��Ĺ���
				case "1":
					addSong6601(list);
					isContinue = false;
					break;

				case "2":
					setTop6601(list);
					isContinue = false;
					break;

				case "3":
					setBefore6601(list);
					isContinue = false;
					break;

				case "0":
					System.out.println("��л����ʹ�ã�");
					System.exit(0);

				default:
					System.out.println("ѡ�����������0-3��ѡ�");
					continue;
				}
			}
			
			// ����ִ����Ϻ��ж��û��Ƿ����
			System.out.println("�밴�س�����...");
			scanner.nextLine();
		}
	}

	/**
	 * ��ʼ�������б�
	 * 
	 * @param list - �����б�
	 */
	private static void initList6601(ArrayList<String> list) {
		list.add("���Ľ�ë");
		list.add("�����");
		list.add("�����ֻ�");
		list.add("�ɰ�Ů��");
		list.add("�Ϻ�һ������");
	}

	/**
	 * ��ʾ�˵�
	 * 
	 * @param list - �����б�
	 */
	private static void showMenu6601(ArrayList<String> list) {
		System.out.println("=============��ӭ�������ϵͳ=============");
		System.out.println("��ǰ�����б�" + list);
		System.out.println("======================================");
		System.out.println("         1........��Ӹ������б�");
		System.out.println("         2........�������ö�");
		System.out.println("         3........������ǰ��һλ");
		System.out.println("         0........�˳�");
		System.out.println("======================================");
		System.out.print("��ѡ��Ҫִ�й��ܣ�");
	}

	/**
	 * ��Ӹ���
	 * 
	 * @param list    �����б�
	 * @param scanner ɨ���û�������
	 */
	private static void addSong6601(ArrayList<String> list) {
		System.out.print("������Ҫ��ӵĸ������ƣ�");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		
		// �жϸø����Ƿ��ڸ����б���
		if (list.contains(name)) {
			System.out.println("������" + name + "��" + "���ڸ����б��У�");

		} else {
			// �Ѹ�������������б�
			list.add(name);
		}
	}

	/**
	 * �ö�����
	 * 
	 * @param list - �����б�
	 */
	private static void setTop6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������Ҫ�ö��ĸ������ƣ�");

		String name = scanner.next();

		// �жϸø����Ƿ��ڸ����б���
		if (list.contains(name)) {
			// ����������ڣ���ɾ�����ٽ���ŵ���һ��
			list.remove(name);
			list.add(0, name);
			System.out.println("�ѽ�������" + name + "��" + "�ö���");

		} else {
			System.out.println("������" + name + "��" + "���ڸ����б��У�");
		}
	}

	/**
	 * ������ǰ��һλ
	 * 
	 * @param list - �����б�
	 */
	private static void setBefore6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("������Ҫ�ö��ĸ������ƣ�");
		
		String name = scanner.next();
		
		// �жϸø����Ƿ��ڵ�һλ
		if (list.get(0).equals(name)) {
			System.out.println("������" + name + "��" + "���ڵ�һλ��");
			return;
		}
		
		// �жϸø����Ƿ��ڸ����б���
		if (list.contains(name)) {
			// ��ȡ�ø�����Ԫ���±�
			int index = list.indexOf(name);
			// ��ɾ���ø���
			list.remove(index);
			// �ڽ��ø�������ǰһλ����
			list.add(index + 1, name);
			System.out.println("�ѽ�������" + name + "��" + "ǰ��һλ");

		} else {
			System.out.println("������" + name + "��" + "���ڸ����б��У�");
		}
	}
}
