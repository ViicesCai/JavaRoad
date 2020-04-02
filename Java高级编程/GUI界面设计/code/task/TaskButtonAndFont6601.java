package java6601.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
	 * 
	 */
/**
 * 
 * 
 * @author CAI
 *
 */
public class TaskButtonAndFont6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	private String fontName; // ��ǰ���������
	int fontStyle, fontSize; // ��ǰ������������ֺ�
	private String[] colors, names; // �ɹ�ѡ�����ɫ����������
	private Integer[] sizes; // �ɹ�ѡ����ֺ�
	private JPanel p, pStyle, pName, pColor;
	private JLabel lblFont;
	private JCheckBox jcbItalic, jcbBold;
	private JComboBox<Integer> jcbSize;
	private ButtonGroup gColor, gName; // ����ΪRadioButton����

	public TaskButtonAndFont6601() {
		super("6601��ѡ��������޸�����");
		this.setSize(700, 400);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ����Ĭ������
		fontName = "����";
		fontStyle = Font.PLAIN;
		fontSize = 20;
		Font font = new Font(fontName, fontStyle, fontSize);
		UIManager.put("RadioButton.font", font);
		UIManager.put("CheckBox.font", font);
		UIManager.put("ComboBox.font", font);
		UIManager.put("Label.font", font);

		initUI6601();
		eventHandler6601();
		this.setVisible(true);
	}

	private void initUI6601() {
		lblFont = new JLabel("��ť����ǳ�����AbstractButton������", JLabel.CENTER);
		this.add(lblFont);

		// ����������ɫ������RadioButton
		addName6601();
		addColor6601();

		// ����
		pStyle = new JPanel();
		pStyle.setBorder(new TitledBorder("����"));
		jcbItalic = new JCheckBox("����");
		jcbBold = new JCheckBox("�Ӵ�");
		pStyle.add(jcbItalic);
		pStyle.add(jcbBold);

		// �ֺ�
		sizes = new Integer[] { 10, 12, 14, 20, 28, 30, 36, 40 };
		jcbSize = new JComboBox<Integer>(sizes);
		jcbSize.setBorder(new TitledBorder("�ֺ�"));

		// ���ó�ʼ״̬
		jcbSize.setSelectedIndex(3);

		p = new JPanel();
		p.add(pName);
		p.add(pStyle);
		p.add(pColor);
		p.add(jcbSize);

		this.add(p, BorderLayout.SOUTH);
	}

	private void addName6601() {
		// һ��RadioButton�����ÿ�ѡ����������
		JRadioButton radioButton = null;
		names = new String[] { "����", "����", "����" };
		
		pName = new JPanel();
		pName.setBorder(new TitledBorder("����"));
		gName = new ButtonGroup();
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = null;
				// ��ȡ�¼�Դ�ı�
				String text = e.getActionCommand();
				
				// �ж�ѡ�������
				switch (text) {
				case "����":
					name = text;
					break;

				case "����":
					name = text;
					break;
					
				case "����":
					name = text;
					break;
				}
				
				fontName = name;
				// ���浱ǰ����
				lblFont.setFont(new Font(fontName, fontStyle, fontSize));
			}
		};
		
		// ��ѭ�������ٴ�����
		for (String text : names) {
			radioButton = new JRadioButton(text);
			pName.add(radioButton);
			gName.add(radioButton);// �������Ƶ�RadioButton����ͬһ����
			radioButton.addActionListener(listener);
		}
		
		// ���ó�ʼ״̬
		radioButton.setSelected(true);
	}

	private void addColor6601() {
		// һ��RadioButton�����ÿ�ѡ����ɫ
		// �������ѭ�������ٴ�������ʹ��������
		colors = new String[] { "��", "��", "��", "��" };
		
		JRadioButton radioButton = null;
		pColor = new JPanel();
		pColor.setBorder(new TitledBorder("��ɫ"));
		gColor = new ButtonGroup();

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = null;
				
				// ��ȡ�¼�Դ�ϵ��ı���Ϣ
				String text = e.getActionCommand();

				// �ж�ѡ�����ɫ
				switch (text) {
				case "��":
					color = Color.RED;
					break;

				case "��":
					color = Color.BLUE;
					break;

				case "��":
					color = Color.YELLOW;
					break;

				case "��":
					color = Color.BLACK;
					break;
				}

				// ����������ɫ
				lblFont.setForeground(color);
			}
		};

		for (String text : colors) {
			radioButton = new JRadioButton(text);
			pColor.add(radioButton);
			gColor.add(radioButton);// ��ɫ��RadioButton����ͬһ����
			radioButton.addActionListener(listener);
		}

		// ��ʼ״̬
		radioButton.setSelected(true);
	}

	private void eventHandler6601() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int mode = 0; // ������ʽ

				// �Ӵ�
				if (jcbBold.isSelected()) {
					mode += Font.BOLD;
				}

				// б��
				if (jcbItalic.isSelected()) {
					mode += Font.ITALIC;
				}
				
				int size = (int) jcbSize.getSelectedItem(); // ��ȡѡ��������С
				fontSize = size;
				fontStyle = mode;
				
				// ����������ʽ
				lblFont.setFont(new Font(fontName, fontStyle, fontSize));
			}
		};

		// Ϊ�¼�Դע��ලʱ��
		jcbBold.addActionListener(listener);
		jcbItalic.addActionListener(listener);
		jcbSize.addActionListener(listener);
	}

	public static void main(String[] args) {
		new TaskButtonAndFont6601();
	}
}
