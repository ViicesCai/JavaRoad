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
		super("6601�����˵�");
		Font font = new Font("����", Font.PLAIN, 20);
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
		// �Ѳ˵����ŵ���ǰ������
		this.setJMenuBar(menuBar);
		// �˵������ļ����༭�����������˵����
		menuFile = new JMenu("�ļ�");
		menuEdit = new JMenu("�༭");
		menuHelp = new JMenu("����");

		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuHelp);

		// �ļ��˵�����һ���˵�
		menuNew = new JMenu("�½�");
		menuFile.add(menuNew);
		// �ڲ˵�����ӷָ���
		menuFile.addSeparator();
		// �����˵���
		itemOpen = new JMenuItem("��");
		itemSave = new JMenuItem("����");
		itemExit = new JMenuItem("�˳�");

		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.add(itemExit);

		// �½��˵�����һ���˵�
		itemC = new JMenuItem("Class");
		itemJava = new JMenuItem("Java Project");
		itemOther = new JMenuItem("Other...");
		menuNew.add(itemC);
		menuNew.add(itemJava);
		menuNew.add(itemOther);

		// �༭�˵�����һ���˵�
		itemCopy = new JMenuItem("����");
		itemPaste = new JMenuItem("ճ��");
		itemCut = new JMenuItem("����");
		menuEdit.add(itemCopy);
		menuEdit.add(itemPaste);
		menuEdit.add(itemCut);

		// �����˵�����һ���˵�
		itemAbout = new JMenuItem("����");
		menuHelp.add(itemAbout);

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��ǰѡ��Ĳ˵��" + e.getActionCommand(), "ϵͳ��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
		
		itemOpen.addActionListener(action);
		itemSave.addActionListener(action);
		itemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �û���ѡ��
				int result = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ��", "��ʾ6601", 
						JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);

				// �û�ѡ���ǣ��˳�
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
