package java6601.lesson09;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JOptionPane
 * 
 * @author CAI
 *
 */
public class JOptionPaneDemo6601 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel p;
	private Box box;
	private JLabel lblMessage, lblResult;
	private JButton btnMsg2, btnMsg4, btnConfirm2, btnConfirm5;

	public JOptionPaneDemo6601() {
		super("6601��Ϣ��ȷ�϶Ի���");
		this.setSize(700, 400);
//		Font font = new Font("����", Font.PLAIN, 20);
//		UIManager.put("Label.font", font);
//		UIManager.put("Button.font", font);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI6601();

		this.setVisible(true);
		eventHandler6601();
	}

	private void initUI6601() {
		String str = "<html><br>1.��Ϣ�Ի����ϵĸ������" + "<br><br>2.ȷ�϶Ի����ϵĸ������</html>";
		lblMessage = new JLabel(str, JLabel.CENTER);
		lblMessage.setFont(new Font("����", Font.PLAIN, 24));

		lblResult = new JLabel();
		lblResult.setHorizontalAlignment(JLabel.CENTER);
		box = Box.createVerticalBox();
		box.add(lblMessage);
		box.add(Box.createVerticalStrut(100));
		box.add(lblResult);
		lblMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblResult.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(box);

		p = new JPanel();
		btnMsg2 = new JButton("��Ϣ�Ի���2");
		btnMsg4 = new JButton("��Ϣ�Ի���4");
		btnConfirm2 = new JButton("ȷ�϶Ի���2");
		btnConfirm5 = new JButton("ȷ�϶Ի���5");
		p.add(btnMsg2);
		p.add(btnMsg4);
		p.add(btnConfirm2);
		p.add(btnConfirm5);
		
		Font font = new Font("����", Font.PLAIN, 20);
		lblMessage.setFont(font);
		lblResult.setFont(font);
		btnMsg2.setFont(font);
		btnMsg4.setFont(font);
		btnConfirm2.setFont(font);
		btnConfirm5.setFont(font);
		this.add(p, BorderLayout.SOUTH);
	}

	private void eventHandler6601() {
		// ע�����
		btnMsg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog((Component) e.getSource(), "��������");
			}
		});
		
		// ������Ϊ��������
		btnMsg4.addActionListener(this);
		
		btnConfirm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "��������");
				switch (result) {
				case JOptionPane.YES_OPTION:
					lblResult.setText("�ڶԻ�����ѡ����'��'");
					break;
				case JOptionPane.NO_OPTION:
					lblResult.setText("�ڶԻ�����ѡ����'��'");
					break;
				case JOptionPane.CANCEL_OPTION:
					lblResult.setText("�ڶԻ�����ѡ����'ȡ��'");
					break;
				case JOptionPane.CLOSED_OPTION:
					lblResult.setText("�����رհ�ť");
					break;
				}
			}
		});
		
		btnConfirm5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �������ΪJOptionPane.OK_OPTION����ȻΪȷ��ȡ��
				int result = JOptionPane.showConfirmDialog(lblResult, "�������", "��ѡ��", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					lblResult.setText("�ڶԻ�����ѡ����'ȷ��'");
				} else if (result == JOptionPane.CANCEL_OPTION) {
					lblResult.setText("�ڶԻ�����ѡ����'ȡ��'");
				} else {
					lblResult.setText("�����رհ�ť");
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "�ĸ�����", "ϵͳ��Ϣ", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		new JOptionPaneDemo6601();
	}
}
