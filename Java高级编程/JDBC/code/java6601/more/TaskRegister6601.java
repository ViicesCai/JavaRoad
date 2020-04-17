/**
 * 
 */
package java6601.more;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java6601.dao.User6601;
import java6601.dao.User6601Dao;

/**
 * 
 * 
 * @author CAI
 *
 */
public class TaskRegister6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtName, txtTelNumber;
	private JPasswordField txtPW1, txtPW2;
	private JButton btnRegister, btnClear, btnList;
	private User6601 register;

	public TaskRegister6601() {
	    super("注册新用户");
	    this.setSize(340, 240);
	    this.setLocation(200, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    initUI6601();
	    this.setVisible(true);
	    register = new User6601();
	  }

	private void initUI6601() {
		// 设置默认字体(在initUI之后再设置则无效!!)
		Font font = new Font("宋体", Font.PLAIN, 20);
		UIManager.put("RadioButton.font", font);
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("PasswordField.font", font);
		UIManager.put("List.font", font);

		JLabel lblName = new JLabel(" 姓   名:");
		JLabel lblPW1 = new JLabel(" 密   码:");
		JLabel lblPW2 = new JLabel("确认密码:");
		JLabel lblTelNumber = new JLabel("手机号码:");

		Box boxLabel = Box.createVerticalBox();
		boxLabel.add(lblName);
		boxLabel.add(Box.createVerticalStrut(6));
		boxLabel.add(lblPW1);
		boxLabel.add(Box.createVerticalStrut(6));
		boxLabel.add(lblPW2);
		boxLabel.add(Box.createVerticalStrut(6));
		boxLabel.add(lblTelNumber);

		txtName = new JTextField(20);
		txtPW1 = new JPasswordField(20);
		txtPW2 = new JPasswordField(20);
		txtTelNumber = new JTextField(20);

		Box boxText = Box.createVerticalBox();
		boxText.add(txtName);
		boxText.add(Box.createVerticalStrut(6));
		boxText.add(txtPW1);
		boxText.add(Box.createVerticalStrut(6));
		boxText.add(txtPW2);
		boxText.add(Box.createVerticalStrut(6));
		boxText.add(txtTelNumber);

		Box boxUp = Box.createHorizontalBox();
		boxUp.add(boxLabel);
		boxUp.add(Box.createHorizontalStrut(10));
		boxUp.add(boxText);

		btnRegister = new JButton("注册");
		btnClear = new JButton("清空");
		btnList = new JButton("用户列表");

		Box boxButton = Box.createHorizontalBox();
		boxButton.add(btnRegister);
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnClear);
		boxButton.add(Box.createHorizontalStrut(20));
		boxButton.add(btnList);

		Box box = Box.createVerticalBox();
		box.add(boxUp);
		box.add(Box.createVerticalStrut(10));
		box.add(boxButton);

		this.setLayout(new FlowLayout(FlowLayout.LEFT));// 左对齐（默认是居中对齐）
		this.add(box);

		btnRegister.addActionListener(e -> register6601());
		btnClear.addActionListener(e -> clear6601());
		btnList.addActionListener(e -> list6601());
	}

	private void clear6601() {
		txtName.setText("");
		txtPW1.setText("");
		txtPW2.setText("");
		txtTelNumber.setText("");
	}

	private void register6601() {
		String name = txtName.getName();
		String pw1 = txtPW1.getText();
		String pw2 = txtPW2.getText();
		String telNumber = txtTelNumber.getText();
		
		if (name == null || pw1 == null || pw2 == null || telNumber == null) {
			JOptionPane.showMessageDialog(this, "错误", "信息不能为空", JOptionPane.ERROR_MESSAGE);
			return;
			
		} else if (!pw1.equals(pw2)) {
			JOptionPane.showMessageDialog(this, "错误", "输入的密码不一致", JOptionPane.ERROR_MESSAGE);
			return;
			
		}
		register.setName(name);
		register.setPassword(pw2);
		register.setTelNumber(telNumber);
		new User6601Dao().addUser(register);
		
		register = new User6601();
	}

	private void list6601() {
	
			User6601[] users = new User6601Dao().findAll6601().toArray(new User6601[0]);
			JFrame listFrame = new JFrame();
			listFrame.setTitle("已注册用户");
			listFrame.setSize(260, 300);
			listFrame.setLocation(400, 200);
			JScrollPane jsp = new JScrollPane(new JList<User6601>(users));
			listFrame.add(jsp, BorderLayout.CENTER);

			listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			listFrame.setVisible(true);
			
		
	}

	public static void main(String[] args) {
		new TaskRegister6601();
	}
}
