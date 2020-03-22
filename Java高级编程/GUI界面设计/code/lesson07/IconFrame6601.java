/**
 * 
 */
package java6601.lesson07;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author CAI
 *
 */
public class IconFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblImage1, lblImage2;
	private JButton btnImage;
	private Box box;
	private JPanel p;

	public IconFrame6601() {
		super("XXXXIcon图标的应用");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(540, 580);
		this.setLocation(200, 100);
		initUI();
		this.setVisible(true);
	}

	private void initUI() {
		this.setLayout(new GridLayout(2, 2));
		lblImage1 = new JLabel(new ImageIcon("images\\earth.png"));
		lblImage2 = new JLabel(new ImageIcon("images/monitor.png"));
		btnImage = new JButton(new ImageIcon("images/desk.jpg"));
		btnImage.setText("书桌");
		p = new JPanel();
		p.add(lblImage1);
		box = Box.createHorizontalBox();
		box.add(lblImage2);
		this.add(p);
		this.add(box);
		this.add(btnImage);
		this.add(new JButton("第三个是按钮"));

		ImageIcon icon = new ImageIcon("images/house.png");
		this.setIconImage(icon.getImage());
	}

	public static void main(String[] args) {
		new IconFrame6601();
	}
}
