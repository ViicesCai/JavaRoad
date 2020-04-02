/**
 * 
 */
package java6601.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import javax.swing.UIManager;


/**
 * GUI实验整合
 * 
 * @author CAI
 *
 */
public class TaskGUIMain6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar; // 菜单栏
	private JMenu guiMenu, eventMenu, othersMenu; // 菜单栏中的三个菜单
	private JPanel panel;
	private MenuItemAction createWindow; // 菜单项的点击事件

	public TaskGUIMain6601() {
		// 主窗口的基本配置
		super("6601GUI编程窗口");
		Font font = new Font("微软雅黑", Font.PLAIN, 15);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		initUI6601();
		this.setSize(500, 300);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initUI6601() {
		panel = new JPanel();
		JLabel nameLabel = new JLabel("211906601-蔡维恒");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel.add(nameLabel);

		this.add(panel);

		menuBar = new JMenuBar();
		// 将菜单栏放到窗体上
		this.setJMenuBar(menuBar);

		// GUI界面设计 菜单
		guiMenu = new JMenu("GUI界面设计");
		menuBar.add(guiMenu);

		// 事件驱动的编程模式 菜单
		eventMenu = new JMenu("事件驱动的编程模式");
		menuBar.add(eventMenu);

		// 常用的事件与组件 菜单
		othersMenu = new JMenu("常用的组件与事件");
		menuBar.add(othersMenu);

		menuGUI6601();
		menuEvent6601();
		menuOthers6601();
	}

	private void menuGUI6601() {
		// 菜单项显示的文本
		String[] name = {
				"练习1.计算器界面",
				"练习2.注册窗口界面",
				"练习3.新书登记界面",
				"练习4.icon图标的应用"};
		
		// 创建菜单项
		for (int i = 0; i < name.length; i++) {
			createWindow = new MenuItemAction();// 创建菜单项的点击事件
			JMenuItem menuItem = new JMenuItem(name[i]);
			guiMenu.add(menuItem); // 添加到菜单中
			menuItem.addActionListener(createWindow); // 注册监听事件
		}
	}

	private void menuEvent6601() {
		// 菜单项显示的文本
		String[] name = {
				"练习1.编程处理ActionEvent事件",
				"练习2.编程处理MouseEvent事件",
				"编程题.组件随鼠标移动",
				"附加题.计算器v1.0"};
		
		// 创建菜单项
		for (int i = 0; i < name.length; i++) {
			createWindow = new MenuItemAction();

			JMenuItem menuItem = new JMenuItem(name[i]);
			eventMenu.add(menuItem); // 添加到菜单中
			menuItem.addActionListener(createWindow); // 注册监听事件
		}
	}

	private void menuOthers6601() {
		// 菜单项显示的文本
		String[] name = {
				"练习1.WindowEvent事件",
				"练习2.窗口的关闭方式",
				"练习3.信息对话框与确认对话框",
				"练习4.下拉菜单",
				"编程题1.实验的主窗口",
				"编程题2.修改字体",
				"附加题3.对话框的特点"
		};
		
		for (int i = 0; i < name.length; i++) {
			createWindow = new MenuItemAction();

			JMenuItem menuItem = new JMenuItem(name[i]);
			othersMenu.add(menuItem);
			menuItem.addActionListener(createWindow);
		}
	}

	public static void main(String[] args) {
		new TaskGUIMain6601();
	}
}
