package java6601.lesson09;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * JMenu
 * 
 * @author CAI
 *
 */
public class JMenuDemo6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuHelp;
	private JMenu menuNew;
	private JMenuItem itemOpen, itemSave, itemExit, itemAbout, itemC, itemJava, itemOther;
	private JMenuItem itemCopy, itemCut, itemPaste;

	public JMenuDemo6601() {
		super("6601下拉菜单");
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Menu.font", font);
		UIManager.put("MenuItem.font", font);
		initMenu6601();
		this.setSize(500, 300);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void initMenu6601() {
		menuBar = new JMenuBar();
		// 把菜单栏放到当前窗体上
		this.setJMenuBar(menuBar);
		// 菜单栏由文件、编辑、帮助三个菜单组成
		menuFile = new JMenu("文件");
		menuEdit = new JMenu("编辑");
		menuHelp = new JMenu("帮助");

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);

		// 文件菜单的下一级菜单
		menuNew = new JMenu("新建");
		menuFile.add(menuNew);
		// 在菜单中添加分隔线
		menuFile.addSeparator();
		// 创建菜单项
		itemOpen = new JMenuItem("打开");
		itemSave = new JMenuItem("保存");
		itemExit = new JMenuItem("退出");

		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.add(itemExit);

		// 新建菜单的下一级菜单
		itemC = new JMenuItem("Class");
		itemJava = new JMenuItem("Java Project");
		itemOther = new JMenuItem("Other...");
		menuNew.add(itemC);
		menuNew.add(itemJava);
		menuNew.add(itemOther);

		// 编辑菜单的下一级菜单
		itemCopy = new JMenuItem("复制");
		itemPaste = new JMenuItem("粘贴");
		itemCut = new JMenuItem("剪切");
		menuEdit.add(itemCopy);
		menuEdit.add(itemPaste);
		menuEdit.add(itemCut);

		// 帮助菜单的下一级菜单
		itemAbout = new JMenuItem("关于");
		menuHelp.add(itemAbout);

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "当前选择的菜单项：" + e.getActionCommand(), "系统提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
		
		itemOpen.addActionListener(action);
		itemSave.addActionListener(action);
		itemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 用户的选择
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统？", "提示6601", 
						JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);

				// 用户选择是，退出
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		itemC.addActionListener(action);
		itemJava.addActionListener(action);
		itemOther.addActionListener(action);
		itemCopy.addActionListener(action);
		itemPaste.addActionListener(action);
		itemCut.addActionListener(action);
		itemAbout.addActionListener(action);
	}

	public static void main(String[] args) {
		new JMenuDemo6601();
	}
}
