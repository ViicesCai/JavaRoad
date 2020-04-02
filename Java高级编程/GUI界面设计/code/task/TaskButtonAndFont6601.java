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

	private String fontName; // 当前字体的名字
	int fontStyle, fontSize; // 当前字体的字形与字号
	private String[] colors, names; // 可供选择的颜色和字体名字
	private Integer[] sizes; // 可供选择的字号
	private JPanel p, pStyle, pName, pColor;
	private JLabel lblFont;
	private JCheckBox jcbItalic, jcbBold;
	private JComboBox<Integer> jcbSize;
	private ButtonGroup gColor, gName; // 用于为RadioButton分组

	public TaskButtonAndFont6601() {
		super("6601用选择性组件修改字体");
		this.setSize(700, 400);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置默认字体
		fontName = "宋体";
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
		lblFont = new JLabel("按钮组件是抽象类AbstractButton的子类", JLabel.CENTER);
		this.add(lblFont);

		// 字体名与颜色的两组RadioButton
		addName6601();
		addColor6601();

		// 字形
		pStyle = new JPanel();
		pStyle.setBorder(new TitledBorder("字形"));
		jcbItalic = new JCheckBox("钭体");
		jcbBold = new JCheckBox("加粗");
		pStyle.add(jcbItalic);
		pStyle.add(jcbBold);

		// 字号
		sizes = new Integer[] { 10, 12, 14, 20, 28, 30, 36, 40 };
		jcbSize = new JComboBox<Integer>(sizes);
		jcbSize.setBorder(new TitledBorder("字号"));

		// 设置初始状态
		jcbSize.setSelectedIndex(3);

		p = new JPanel();
		p.add(pName);
		p.add(pStyle);
		p.add(pColor);
		p.add(jcbSize);

		this.add(p, BorderLayout.SOUTH);
	}

	private void addName6601() {
		// 一组RadioButton，设置可选的字体名称
		JRadioButton radioButton = null;
		names = new String[] { "黑体", "隶书", "宋体" };
		
		pName = new JPanel();
		pName.setBorder(new TitledBorder("字体"));
		gName = new ButtonGroup();
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = null;
				// 获取事件源文本
				String text = e.getActionCommand();
				
				// 判断选择的字体
				switch (text) {
				case "黑体":
					name = text;
					break;

				case "隶书":
					name = text;
					break;
					
				case "宋体":
					name = text;
					break;
				}
				
				fontName = name;
				// 保存当前设置
				lblFont.setFont(new Font(fontName, fontStyle, fontSize));
			}
		};
		
		// 用循环来减少代码量
		for (String text : names) {
			radioButton = new JRadioButton(text);
			pName.add(radioButton);
			gName.add(radioButton);// 字体名称的RadioButton放在同一组中
			radioButton.addActionListener(listener);
		}
		
		// 设置初始状态
		radioButton.setSelected(true);
	}

	private void addColor6601() {
		// 一组RadioButton，设置可选的颜色
		// 用数组和循环，减少代码量，使代码更简洁
		colors = new String[] { "红", "兰", "黄", "黑" };
		
		JRadioButton radioButton = null;
		pColor = new JPanel();
		pColor.setBorder(new TitledBorder("颜色"));
		gColor = new ButtonGroup();

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = null;
				
				// 获取事件源上的文本信息
				String text = e.getActionCommand();

				// 判断选择的颜色
				switch (text) {
				case "红":
					color = Color.RED;
					break;

				case "兰":
					color = Color.BLUE;
					break;

				case "黄":
					color = Color.YELLOW;
					break;

				case "黑":
					color = Color.BLACK;
					break;
				}

				// 设置字体颜色
				lblFont.setForeground(color);
			}
		};

		for (String text : colors) {
			radioButton = new JRadioButton(text);
			pColor.add(radioButton);
			gColor.add(radioButton);// 颜色的RadioButton放在同一组中
			radioButton.addActionListener(listener);
		}

		// 初始状态
		radioButton.setSelected(true);
	}

	private void eventHandler6601() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int mode = 0; // 字体样式

				// 加粗
				if (jcbBold.isSelected()) {
					mode += Font.BOLD;
				}

				// 斜体
				if (jcbItalic.isSelected()) {
					mode += Font.ITALIC;
				}
				
				int size = (int) jcbSize.getSelectedItem(); // 获取选择的字体大小
				fontSize = size;
				fontStyle = mode;
				
				// 设置字体样式
				lblFont.setFont(new Font(fontName, fontStyle, fontSize));
			}
		};

		// 为事件源注册监督时间
		jcbBold.addActionListener(listener);
		jcbItalic.addActionListener(listener);
		jcbSize.addActionListener(listener);
	}

	public static void main(String[] args) {
		new TaskButtonAndFont6601();
	}
}
