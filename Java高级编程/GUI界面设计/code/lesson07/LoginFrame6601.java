/**
 * 
 */
package java6601.lesson07;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author CAI
 *
 */
public class LoginFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Box labelBox, textBox, buttonBox, upBox, box; // Box ����
	private JLabel lblName, lblPW1, lblPW2, lblPhone;
	private JTextField txtName, txtPhone; // �ı���
	private JPasswordField txtPW1, txtPW2; // �����
	private JButton btnLogin, btnClear;

	public LoginFrame6601() {
		super("ע�����û�");
		this.setSize(300, 200);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initUI();
		this.setVisible(true);
	}

	private void initUI() {
		lblName = new JLabel("��       ��:");
		lblPW1 = new JLabel("��       ��:");
		lblPW2 = new JLabel("ȷ������:");
		lblPhone = new JLabel("�ֻ�����:");
		labelBox = Box.createVerticalBox();
		labelBox.add(lblName);
		labelBox.add(Box.createVerticalStrut(6));
		labelBox.add(lblPW1);
		labelBox.add(Box.createVerticalStrut(6));
		labelBox.add(lblPW2);
		labelBox.add(Box.createVerticalStrut(6));
		labelBox.add(lblPhone);

		txtName = new JTextField(16);
		txtPW1 = new JPasswordField(16);
		txtPW2 = new JPasswordField(16);
		txtPhone = new JTextField(16);
		textBox = Box.createVerticalBox();
		textBox.add(txtName);
		textBox.add(Box.createVerticalStrut(6));
		textBox.add(txtPW1);
		textBox.add(Box.createVerticalStrut(6));
		textBox.add(txtPW2);
		textBox.add(Box.createVerticalStrut(6));
		textBox.add(txtPhone);

		upBox = Box.createHorizontalBox();
		upBox.add(labelBox);
		upBox.add(Box.createHorizontalStrut(10));
		upBox.add(textBox);

		btnLogin = new JButton("ע��");
		btnClear = new JButton("���");
		buttonBox = Box.createHorizontalBox();
		buttonBox.add(btnLogin);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(btnClear);

		box = Box.createVerticalBox();
		box.add(upBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);

		this.setLayout(new FlowLayout(FlowLayout.LEFT)); // �����
		this.add(box);
	}

	public static void main(String[] args) {
		new LoginFrame6601();
	}
}
