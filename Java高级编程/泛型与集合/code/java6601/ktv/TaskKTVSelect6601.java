package java6601.ktv;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskKTVSelect6601 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		initList6601(list); // 初始化歌曲列表
		menu6601(list); // 进入菜单
	}

	/**
	 * 菜单控制
	 * 
	 * @param list - 歌曲列表
	 */
	public static void menu6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			boolean isContinue = true; // 是否继续显示菜单
			
			while (isContinue) {
				showMenu6601(list); // 显示菜单
				String choice = scanner.nextLine().trim(); // 用户输入

				switch (choice) { // 判单用户选择的功能
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
					System.out.println("感谢您的使用！");
					System.exit(0);

				default:
					System.out.println("选择错误，请输入0-3的选项！");
					continue;
				}
			}
			
			// 功能执行完毕后，判断用户是否继续
			System.out.println("请按回车继续...");
			scanner.nextLine();
		}
	}

	/**
	 * 初始化歌曲列表
	 * 
	 * @param list - 歌曲列表
	 */
	private static void initList6601(ArrayList<String> list) {
		list.add("她的睫毛");
		list.add("你好吗");
		list.add("浪漫手机");
		list.add("可爱女人");
		list.add("上海一九四三");
	}

	/**
	 * 显示菜单
	 * 
	 * @param list - 歌曲列表
	 */
	private static void showMenu6601(ArrayList<String> list) {
		System.out.println("=============欢迎来到点歌系统=============");
		System.out.println("当前歌曲列表：" + list);
		System.out.println("======================================");
		System.out.println("         1........添加歌曲至列表");
		System.out.println("         2........将歌曲置顶");
		System.out.println("         3........将歌曲前移一位");
		System.out.println("         0........退出");
		System.out.println("======================================");
		System.out.print("请选择要执行功能：");
	}

	/**
	 * 添加歌曲
	 * 
	 * @param list    歌曲列表
	 * @param scanner 扫描用户的输入
	 */
	private static void addSong6601(ArrayList<String> list) {
		System.out.print("请输入要添加的歌曲名称：");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		
		// 判断该歌曲是否在歌曲列表中
		if (list.contains(name)) {
			System.out.println("歌曲【" + name + "】" + "已在歌曲列表中！");

		} else {
			// 把歌曲添加至歌曲列表
			list.add(name);
		}
	}

	/**
	 * 置顶歌曲
	 * 
	 * @param list - 歌曲列表
	 */
	private static void setTop6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入要置顶的歌曲名称：");

		String name = scanner.next();

		// 判断该歌曲是否在歌曲列表中
		if (list.contains(name)) {
			// 如果歌曲存在，先删除，再将其放到第一个
			list.remove(name);
			list.add(0, name);
			System.out.println("已将歌曲【" + name + "】" + "置顶！");

		} else {
			System.out.println("歌曲【" + name + "】" + "不在歌曲列表中！");
		}
	}

	/**
	 * 将歌曲前移一位
	 * 
	 * @param list - 歌曲列表
	 */
	private static void setBefore6601(ArrayList<String> list) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入要置顶的歌曲名称：");
		
		String name = scanner.next();
		
		// 判断该歌曲是否在第一位
		if (list.get(0).equals(name)) {
			System.out.println("歌曲【" + name + "】" + "已在第一位！");
			return;
		}
		
		// 判断该歌曲是否在歌曲列表中
		if (list.contains(name)) {
			// 获取该歌曲的元素下标
			int index = list.indexOf(name);
			// 先删除该歌曲
			list.remove(index);
			// 在将该歌曲在其前一位插入
			list.add(index + 1, name);
			System.out.println("已将歌曲【" + name + "】" + "前移一位");

		} else {
			System.out.println("歌曲【" + name + "】" + "不在歌曲列表中！");
		}
	}
}
