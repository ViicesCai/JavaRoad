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
 * �ı��༭��
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
		super("6601�ı��༭��");
		this.setBounds(300, 200, 780, 600);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// ����Ĭ������
		Font font = new Font("����", Font.PLAIN, 20);
		UIManager.put("Button.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("TextArea.font", font);
		initUI6601();
		eventHandler6601();
		this.setVisible(true);
	}

	// �������
	private void initUI6601() {
		jtaContent = new JTextArea(20, 50);
		JScrollPane scrollP = new JScrollPane(jtaContent);
		this.add(scrollP);

		JPanel p = new JPanel();
		p.add(new JLabel("���ڱ༭���ļ���"));
		p.add(txtFileName = new JTextField("", 30));
		// �����ı��򲻿��޸�
		txtFileName.setEditable(false);
		p.add(btnOpen = new JButton("��"));
		p.add(btnSave = new JButton("����"));
		p.add(btnSaveAs = new JButton("���"));
		this.add(p, BorderLayout.NORTH);
	}

	// �¼����
	private void eventHandler6601() {
		// 1.JTextArea������ı������Ӧ���¼��������
		// �¼�Դ��ΪJTextArea������ı�����Document��
		// �¼���Document��DocumentEvent�¼�
		// ��1����JTextArea�������getDocument()�����ı�����
		// ��2������Document��addDocumentListener()ע���������
		// ��3���������ڲ���ʵ��DocumentListener�����ӿ�
		// ��4����DocumentListener�������¼��������ж���isChange��Ϊtrue
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

		// 2.������ť���¼�����
		// ��Lambda���ʽΪ������ťע��������ֱ����openFile6601()��saveFile6601()��saveAsFile6601()
		btnOpen.addActionListener(e -> openFile6601());
		btnSave.addActionListener(e -> saveFile6601());
		btnSaveAs.addActionListener(e -> saveAsFile6601());
		
		// 3.���ڵ��¼�����
		// Ϊ����ע���������ڲ���ʵ�ֵ�Window�¼���������
		this.addWindowListener(new WindowAdapter() {

			// ��windowClosing()�¼������������в�����
			// ��1�������δ�޸ĵ��ĵ�����ȷ�϶Ի���isChange��JOptionPane��showConfirmDialog(�ĸ�����)
			// ��2�����ȷ�϶Ի��򵥻�ȡ����ʲôҲ����
			// ��3�����ȷ�϶Ի��򵥻��񣺹رմ���
			// ��4�����ȷ�϶Ի��򵥻��ǣ��ȱ��棨saveFileXXXX()�����ٹرմ���
			public void windowClosing(WindowEvent e) {
				if (isChange) {
					int result = JOptionPane.showConfirmDialog(jtaContent, "�Ƿ񱣴��ļ�", "�ļ�δ����",
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

	// �������ڲ���ʵ���ļ����˽ӿ�
	FileFilter txtFilter = new FileFilter() {
		// ��2��getDescription()���������ص��ı�����ʾ���ļ��Ի���ġ��ļ����͡������б����
		@Override
		public String getDescription() {
			return "�ı��ļ�(*.txt;*.java)";
		}

		// ��1��accept()�������ж��ļ����ĺ�׺�Ƿ�Ϊ.txt��.java
		@Override
		public boolean accept(File f) {
			String name = f.getName();
			return name.endsWith(".txt") || name.endsWith(".java");
		}
	};

	// ��ѡ���ļ��Ի���ѡ���ı��ļ��������ļ����ݼ��ص��ı�����
	private void openFile6601() {
		// ���ڱ༭���ļ���
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty())
			currentFileName = ".";
		// ѡ���ļ��Ի���
		JFileChooser jf = new JFileChooser(currentFileName);
		// �����ļ��Ի�����ļ����͹�������setFileFilter()����
		jf.setFileFilter(txtFilter);
		// ���ļ��Ի���showOpenDialog()����
		int opend = jf.showOpenDialog(getContentPane());
		// ������ļ��Ի����е������򿪡���ť
		if (opend == JFileChooser.APPROVE_OPTION) {
			// ��1���õ��ڴ򿪶Ի�����ѡ����ļ�������JFileChooser��getSelectedFile()
			File file = jf.getSelectedFile();

			try {
				// ��2��ʹ��Scanner���ļ��ж�ȡ�ļ����ݣ���ʾ���ı����У�JTextArea��append()���ӻ���
				Scanner in = new Scanner(file);

				while (in.hasNextLine()) {
					jtaContent.append(in.nextLine() + "\n");
				}

				in.close();
				// ��3�����ļ�����ʾ���ı�����
				txtFileName.setText(file.getName());
				// ��4����isChange��Ϊfalse
				isChange = false;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// �����ļ�
	private void saveFile6601() {
		// ���ڱ༭���ļ���
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty()) {
			saveAsFile6601();
			
		} else {
			saveFile6601(new File(currentFileName));
		}
	}

	// ���ı����е����ݱ��浽ָ���ļ���
	private void saveFile6601(File file) {
		try {
			// ��1��ʹ�ô�ӡ�����PrintStream�����ı����е����ݱ��浽�ļ���
			PrintWriter writer = new PrintWriter(new FileWriter(file));
			writer.print(jtaContent.getText());
			writer.close();
			
			// ��2����isChange��Ϊfalse
			isChange = false;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �򿪱����ļ��Ի���ѡ�񱣴��ļ���Ŀ¼���ļ����������ļ�
	private void saveAsFile6601() {
		// ���ڱ༭���ļ���
		String currentFileName = txtFileName.getText();
		if (currentFileName.isEmpty())
			currentFileName = ".";
		JFileChooser jf = new JFileChooser(currentFileName);
		
		// �����ļ�����
		jf.setFileFilter(txtFilter);
		
		// ���ļ��Ի���
		int choice = jf.showSaveDialog(getContentPane());
		
		// ������ļ��Ի����е�������
		if (choice == JFileChooser.APPROVE_OPTION) {
			File currentFile = jf.getSelectedFile();
			// (1)����saveFile6601�����ļ�
			saveFile6601(currentFile);
			
			// (2)���ļ�����ʾ���ı�����
			txtFileName.setName(currentFile.getName());
		}
	}

	public static void main(String[] args) {
		new TaskEditor6601();
	}
}
