/**
 * 
 */
package java6601.lesson09;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * 窗口事件
 * 
 * @author CAI
 *
 */
public class WindowEventFrame6601 extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new WindowEventFrame6601();
	}

	public WindowEventFrame6601() {
		super("6601窗口事件");
		this.setSize(400, 300);
		this.setLocation(300, 200);
		this.setVisible(true);
		// 请观察4种设置的运行效果
		// 当用户点击窗口上的关闭按钮，隐藏该窗口
		// this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// 当用户点击窗口上的关闭按钮，退出整个JVM
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 当用户点击窗口上的关闭按钮，仅关闭当前窗口
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 当用户点击窗口上的关闭按钮，不执行任何操作
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// 使用匿名类注册监听事件
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
			      System.out.println("windowOpened---窗体已经打开");
			    }

			    public void windowIconified(WindowEvent e) {
			      System.out.println("windowIconified---窗体最小化为图标");
			    }

			    public void windowDeiconified(WindowEvent e) {
			      System.out.println("windowDeiconified---窗体从最小化图标状态还原");
			    }

			    public void windowDeactivated(WindowEvent e) {
			      System.out.println("windowDeactivated---窗体变为不可用");
			    }

			    // 触发此事件执行退出操作
			    public void windowClosing(WindowEvent e) {
			      System.out.println("windowClosing---窗体正在准备关闭");
			      System.exit(0);
			    }

			    public void windowClosed(WindowEvent e) {
			      System.out.println("windowClosed---窗体已关闭");
			    }

			    public void windowActivated(WindowEvent e) {
			      System.out.println("windowActivated---窗体进入激活可用状态");
			    }
		});
	}
}
