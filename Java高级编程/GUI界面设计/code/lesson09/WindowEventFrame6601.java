/**
 * 
 */
package java6601.lesson09;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * �����¼�
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
		super("6601�����¼�");
		this.setSize(400, 300);
		this.setLocation(300, 200);
		this.setVisible(true);
		// ��۲�4�����õ�����Ч��
		// ���û���������ϵĹرհ�ť�����ظô���
		// this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// ���û���������ϵĹرհ�ť���˳�����JVM
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���û���������ϵĹرհ�ť�����رյ�ǰ����
		// this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// ���û���������ϵĹرհ�ť����ִ���κβ���
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// ʹ��������ע������¼�
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
			      System.out.println("windowOpened---�����Ѿ���");
			    }

			    public void windowIconified(WindowEvent e) {
			      System.out.println("windowIconified---������С��Ϊͼ��");
			    }

			    public void windowDeiconified(WindowEvent e) {
			      System.out.println("windowDeiconified---�������С��ͼ��״̬��ԭ");
			    }

			    public void windowDeactivated(WindowEvent e) {
			      System.out.println("windowDeactivated---�����Ϊ������");
			    }

			    // �������¼�ִ���˳�����
			    public void windowClosing(WindowEvent e) {
			      System.out.println("windowClosing---��������׼���ر�");
			      System.exit(0);
			    }

			    public void windowClosed(WindowEvent e) {
			      System.out.println("windowClosed---�����ѹر�");
			    }

			    public void windowActivated(WindowEvent e) {
			      System.out.println("windowActivated---������뼤�����״̬");
			    }
		});
	}
}
