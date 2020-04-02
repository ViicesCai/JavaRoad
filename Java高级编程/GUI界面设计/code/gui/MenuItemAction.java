/**
 * 
 */
package java6601.gui;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.JFrame;


/**
 * @author CAI
 *
 */
public class MenuItemAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
    private static final int ICONIFIED = 1; // ������С��״̬
    private static final int NORMAL = 0; // ����ԭʼ״̬
    public static final int DISPOSE_ON_CLOSE = 2; // ���ص�ǰ����
	
	private JFrame wiondow; // �Ӵ���
	private Properties keyMap; // �˵����Ӧ��ȫ����
	private Class<?> cla; // Class����
	
	public MenuItemAction() {
		super();
		initProperties(); // ��ʼ�� Properties
	}
	
	/**
	 * ����Properties�е�����
	 */
	private void initProperties() {
		keyMap = new Properties(); // ���� properties����
		
		try {
			// ��������
			InputStream is=this.getClass().getResourceAsStream("MenuItem.properties");   
	        BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
			keyMap.load(br);
			keyMap.list(System.out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		initClass(e.getActionCommand()); // ��ʼ�� Class ����
		
		try {
			// ��ȡ Class ����Ĺ��췽��
			Constructor<?> constructor = cla.getConstructor();
			
			// �������δ����
			if (wiondow == null) {
				// ����Ϊ Class ��ʵ������
				wiondow = (JFrame) constructor.newInstance();
				
			} else {
				// ��ʾ�ô���
				wiondow.setVisible(true);
			}
			
			// �Դ��ڵĵ�ǰ״̬�����ж�
			// ��С����ָ���ԭʼ״̬
			if (wiondow.getState() == ICONIFIED) {
				wiondow.setState(NORMAL);
			}
			
			// ���ڵ�Ĭ�Ϲرշ���
			wiondow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * ��ʼ�� Class ����
	 * 
	 * @param key - �¼�Դ���ı�
	 */
	private void initClass(String key) {
		try {
			// ͨ���¼�Դ�ı���Ӧ��ȫ���� ���� Class ����
			cla = Class.forName(keyMap.getProperty(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
