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
    private static final int ICONIFIED = 1; // 窗口最小化状态
    private static final int NORMAL = 0; // 窗口原始状态
    public static final int DISPOSE_ON_CLOSE = 2; // 隐藏当前窗口
	
	private JFrame wiondow; // 子窗口
	private Properties keyMap; // 菜单项对应的全类名
	private Class<?> cla; // Class对象
	
	public MenuItemAction() {
		super();
		initProperties(); // 初始化 Properties
	}
	
	/**
	 * 加载Properties中的数据
	 */
	private void initProperties() {
		keyMap = new Properties(); // 创建 properties对象
		
		try {
			// 加载数据
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
		initClass(e.getActionCommand()); // 初始化 Class 对象
		
		try {
			// 获取 Class 对象的构造方法
			Constructor<?> constructor = cla.getConstructor();
			
			// 如果窗口未创建
			if (wiondow == null) {
				// 窗口为 Class 的实例对象
				wiondow = (JFrame) constructor.newInstance();
				
			} else {
				// 显示该窗口
				wiondow.setVisible(true);
			}
			
			// 对窗口的当前状态进行判断
			// 最小花则恢复成原始状态
			if (wiondow.getState() == ICONIFIED) {
				wiondow.setState(NORMAL);
			}
			
			// 窗口的默认关闭方法
			wiondow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * 初始化 Class 对象
	 * 
	 * @param key - 事件源的文本
	 */
	private void initClass(String key) {
		try {
			// 通过事件源文本对应的全类名 创建 Class 对象
			cla = Class.forName(keyMap.getProperty(key));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
