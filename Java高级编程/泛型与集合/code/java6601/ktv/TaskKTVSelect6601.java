package java6601.ktv;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskKTVSelect6601 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		// ��ʼ�������б�
		initList6601(list);
		menu6601(list);
		// ����˵�;

	}

	// �˵�����
	public static void menu6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			boolean isContinue = true; // �Ƿ������ʾ�˵�
			
			while (isContinue) {
				// ��ʾ�Ĳ˵�
				showMenu6601(list);
				String choice = scanner.nextLine().trim(); // �û���ѡ��

				switch (choice) {
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
			
			System.out.println("�밴�س�����...");
			scanner.nextLine();
		}
	}

	// ��ʼ�������б�
	private static void initList6601(ArrayList<String> list) {
		list.add("���Ľ�ë");
		list.add("�����");
		list.add("�����ֻ�");
		list.add("�ɰ�Ů��");
		list.add("�Ϻ�һ������");
	}

	// ��ʾ�˵�
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

	// �������ö�
	private static void setTop6601(ArrayList<String> list) {
		// 1.�Ӽ�������Ҫ�ö��ĸ�����
		System.out.print("������Ҫ�ö��ĸ������ƣ�");
		Scanner scanner = new Scanner(System.in);

		String name = scanner.next();

		// �жϸø����Ƿ��ڸ����б���
		if (list.contains(name)) {
			// ����������ڣ���ɾ�����ٽ���ŵ���һ��
			list.remove(name);
			list.add(name);
			System.out.println("�ѽ�������" + name + "��" + "�ö���");

		} else {
			System.out.println("������" + name + "��" + "���ڸ����б��У�");
		}
	}

	// ��������ǰһλ
	private static void setBefore6601(ArrayList<String> list) {
		// 1.�Ӽ�������Ҫǰ�Ƶĸ�����
		Scanner scanner = new Scanner(System.in);

		String name = scanner.next();

		// �жϸø����Ƿ��ڸ����б���
		if (list.contains(name)) {
			// ��ȡ�ø�����Ԫ���±�
			int index = list.indexOf(name);
			// ��ɾ���ø���
			list.remove(index);
			// �ڸø�����ǰһλ����
			list.add(index + 1, name);

		} else {
			System.out.println("��ǰ�б���û�д˵ĸ�����");
		}
	}
}
