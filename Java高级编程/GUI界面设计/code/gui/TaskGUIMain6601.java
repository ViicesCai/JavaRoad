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
 * GUIʵ������
 * 
 * @author CAI
 *
 */
public class TaskGUIMain6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar; // �˵���
	private JMenu guiMenu, eventMenu, othersMenu; // �˵����е������˵�
	private JPanel panel;
	private MenuItemAction createWindow; // �˵���ĵ���¼�

	public TaskGUIMain6601() {
		// �����ڵĻ�������
		super("6601GUI��̴���");
		Font font = new Font("΢���ź�", Font.PLAIN, 15);
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
		JLabel nameLabel = new JLabel("211906601-��ά��");
		nameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		panel.add(nameLabel);

		this.add(panel);

		menuBar = new JMenuBar();
		// ���˵����ŵ�������
		this.setJMenuBar(menuBar);

		// GUI������� �˵�
		guiMenu = new JMenu("GUI�������");
		menuBar.add(guiMenu);

		// �¼������ı��ģʽ �˵�
		eventMenu = new JMenu("�¼������ı��ģʽ");
		menuBar.add(eventMenu);

		// ���õ��¼������ �˵�
		othersMenu = new JMenu("���õ�������¼�");
		menuBar.add(othersMenu);

		menuGUI6601();
		menuEvent6601();
		menuOthers6601();
	}

	private void menuGUI6601() {
		// �˵�����ʾ���ı�
		String[] name = {
				"��ϰ1.����������",
				"��ϰ2.ע�ᴰ�ڽ���",
				"��ϰ3.����Ǽǽ���",
				"��ϰ4.iconͼ���Ӧ��"};
		
		// �����˵���
		for (int i = 0; i < name.length; i++) {
			createWindow = new MenuItemAction();// �����˵���ĵ���¼�
			JMenuItem menuItem = new JMenuItem(name[i]);
			guiMenu.add(menuItem); // ��ӵ��˵���
			menuItem.addActionListener(createWindow); // ע������¼�
		}
	}

	private void menuEvent6601() {
		// �˵�����ʾ���ı�
		String[] name = {
				"��ϰ1.��̴���ActionEvent�¼�",
				"��ϰ2.��̴���MouseEvent�¼�",
				"�����.���������ƶ�",
				"������.������v1.0"};
		
		// �����˵���
		for (int i = 0; i < name.length; i++) {
			createWindow = new MenuItemAction();

			JMenuItem menuItem = new JMenuItem(name[i]);
			eventMenu.add(menuItem); // ��ӵ��˵���
			menuItem.addActionListener(createWindow); // ע������¼�
		}
	}

	private void menuOthers6601() {
		// �˵�����ʾ���ı�
		String[] name = {
				"��ϰ1.WindowEvent�¼�",
				"��ϰ2.���ڵĹرշ�ʽ",
				"��ϰ3.��Ϣ�Ի�����ȷ�϶Ի���",
				"��ϰ4.�����˵�",
				"�����1.ʵ���������",
				"�����2.�޸�����",
				"������3.�Ի�����ص�"
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
