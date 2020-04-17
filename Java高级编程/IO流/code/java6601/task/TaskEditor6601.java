/**
 * 
 */
package java6601.task;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

/**
 * 文本编辑器
 * 
 * @author CAI
 *
 */
public class TaskEditor6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField txtFileName;
	private JButton btnOpen, btnSave, btnSaveAs;
	private JTextArea jtaContent;

	private boolean isChange;

	public TaskEditor6601() {
		super("6601文本编辑器");
		this.setBounds(300, 200, 780, 600);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 设置默认字体
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
	}

	// 界面设计
	private void initUI6601() {
		jtaContent = new JTextArea(20, 50);
		JScrollPane scrollP = new JScrollPane(jtaContent);
		this.add(scrollP);

		JPanel p = new JPanel();
		p.add(new JLabel("正在编辑的文件："));
		p.add(txtFileName = new JTextField("", 30));
		// 设置文本框不可修改
		txtFileName.setEditable(false);
		p.add(btnOpen = new JButton("打开"));
		p.add(btnSave = new JButton("保存"));
		p.add(btnSaveAs = new JButton("另存"));
		this.add(p, BorderLayout.NORTH);
	}

	// 事件编程
	private void eventHandler6601() {
		// 1.JTextArea组件的文本对象对应的事件处理程序
		// 事件源：为JTextArea组件的文本对象（Document）
		// 事件：Document的DocumentEvent事件
		// （1）用JTextArea组件的文getDocument()返回文本对象
		// （2）调用Document的addDocumentListener()注册监听对象
		// （3）用匿名内部类实现DocumentListener监听接口
		// （4）在DocumentListener的三个事件处理器中都将isChange设为true
		jtaContent.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				isChange = true;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				isChange = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				isChange = true;
			}
		});

		// 2.三个按钮的事件处理
		// 用Lambda表达式为三个按钮注册监听，分别调用openFile6601()、saveFile6601()、saveAsFile6601()
		btnOpen.addActionListener(e -> openFile6601());
		btnSave.addActionListener(e -> saveFile6601());
		btnSaveAs.addActionListener(e -> saveAsFile6601());
		
		// 3.窗口的事件处理
		// 为窗口注册用匿名内部类实现的Window事件适配器类
		this.addWindowListener(new WindowAdapter() {

			// 对windowClosing()事件处理器按下列步骤编程
			// （1）如果有未修改的文档，打开确认对话框：isChange、JOptionPane的showConfirmDialog(四个参数)
			// （2）如果确认对话框单击取消：什么也不做
			// （3）如果确认对话框单击否：关闭窗口
			// （4）如果确认对话框单击是：先保存（saveFileXXXX()），再关闭窗口
			public void windowClosing(WindowEvent e) {
				if (isChange) {
					int result = JOptionPane.showConfirmDialog(jtaContent, "是否保存文件", "文件未保存",
							JOptionPane.YES_NO_OPTION);

					switch (result) {
					case JOptionPane.CANCEL_OPTION:
						break;

					case JOptionPane.NO_OPTION:
						e.getWindow().dispose();
						break;

					case JOptionPane.YES_OPTION:
						saveFile6601();
						e.getWindow().dispose();
					}

				} else {
					e.getWindow().dispose();
				}
			};
		});
	}

	// 用匿名内部类实现文件过滤接口
	FileFilter txtFilter = new FileFilter() {
		// （2）getDescription()方法：返回的文本将显示在文件对话框的“文件类型”下拉列表框中
		@Override
		public String getDescription() {
			return "文本文件(*.txt;*.java)";
		}

		// （1）accept()方法：判断文件名的后缀是否为.txt或.java
		@Override
		public boolean accept(File f) {
			String name = f.getName();
			return name.endsWith(".txt") || name.endsWith(".java");
		}
	};

	// 打开选择文件对话框，选择文本文件，并将文件内容加载的文本域中
	private void openFile6601() {
		// 正在编辑的文件名
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty())
			currentFileName = ".";
		// 选择文件对话框
		JFileChooser jf = new JFileChooser(currentFileName);
		// 设置文件对话框的文件类型过滤器：setFileFilter()方法
		jf.setFileFilter(txtFilter);
		// 打开文件对话框：showOpenDialog()方法
		int opend = jf.showOpenDialog(getContentPane());
		// 如果在文件对话框中单击“打开”按钮
		if (opend == JFileChooser.APPROVE_OPTION) {
			// （1）得到在打开对话框中选择的文件：调用JFileChooser的getSelectedFile()
			File file = jf.getSelectedFile();

			try {
				// （2）使用Scanner从文件中读取文件内容，显示在文本域中：JTextArea的append()、加换行
				Scanner in = new Scanner(file);

				while (in.hasNextLine()) {
					jtaContent.append(in.nextLine() + "\n");
				}

				in.close();
				// （3）把文件名显示在文本框中
				txtFileName.setText(file.getName());
				// （4）将isChange设为false
				isChange = false;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// 保存文件
	private void saveFile6601() {
		// 正在编辑的文件名
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty()) {
			saveAsFile6601();
			
		} else {
			saveFile6601(new File(currentFileName));
		}
	}

	// 把文本域中的内容保存到指定文件中
	private void saveFile6601(File file) {
		try {
			// （1）使用打印输出流PrintStream，把文本域中的内容保存到文件中
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			writer.print(jtaContent.getText());
			writer.close();
			
			// （2）将isChange设为false
			isChange = false;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 打开保存文件对话框，选择保存文件的目录与文件名，保存文件
	private void saveAsFile6601() {
		// 正在编辑的文件名
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty())
			currentFileName = ".";
		JFileChooser jf = new JFileChooser(currentFileName);
		
		// 设置文件过滤
		jf.setFileFilter(txtFilter);
		
		// 打开文件对话框
		int choice = jf.showSaveDialog(getContentPane());
		
		// 如果在文件对话框中单击保存
		if (choice == JFileChooser.APPROVE_OPTION) {
			File currentFile = jf.getSelectedFile();
			// (1)调用saveFile6601保存文件
			saveFile6601(currentFile);
			
			// (2)把文件名显示在文本框中
			txtFileName.setName(currentFile.getName());
		}
	}

	public static void main(String[] args) {
		new TaskEditor6601();
	}
}
