package java6601.calc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * ������
 * 
 * @author CAI
 *
 */
public class TaskCalculator6601 extends JFrame {
	private static final long serialVersionUID = -2483248511457097960L; // ��֤�汾һ��
	private JTextField resultTextField; // �������ı���
	private JButton clearButton; // ��հ�ť
	private JPanel p1, p2; // �м�����

	/**
	 * ���ô������
	 */
	public TaskCalculator6601() {
		super("������");
		this.setSize(230, 230);
		this.setLocation(200, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رհ�ť
		initUI6601();
		this.setVisible(true); // ����ɼ�

		// ���ô��ڴ�С���ɸı�
		this.setResizable(false);
	}

	/**
	 * ��ʼ�����
	 */
	private void initUI6601() {
		p1 = new JPanel();
		clearButton = new JButton("���");
		resultTextField = new JTextField(13);

		resultTextField.setEditable(false); // ����ֱ�����ı�������

		p1.add(resultTextField);
		p1.add(clearButton);

		this.add(p1, BorderLayout.NORTH); // ���� P1 ���ַ�ʽ

		p2 = new JPanel(new GridLayout(4, 4)); // ���ð�ť�Ĳ��ַ�ʽ

		// �洢�������а�ť�ϵ����֣����ֵ�����������˳��һ��
		String name = "789+456-123*0.=/";
		ButtonClick6601 click = new ButtonClick6601();

		// ͨ��ѭ������ڴ�������Ӱ�ť
		for (int i = 0; i < name.length(); i++) {
			JButton button = new JButton("" + name.charAt(i));
			button.addActionListener(click); // ע�ᰴť����
			p2.add(button);
		}

		clearButton.addActionListener(click);

		this.add(p2, BorderLayout.CENTER); // ���� P2 ���ַ�ʽ
	}

	public static void main(String[] args) {
		new TaskCalculator6601(); // ��������
	}

	class ButtonClick6601 implements ActionListener {
		private OperationFactory operationFactory; // ����������
		private double number1; // ����1
		private double number2; // ����2
		private boolean flag; // �ж��Ƿ�����ڶ�����
		private boolean numValidity; // �ж����ֵĺϷ���
		private boolean isOperation; // ִ�е��Ƿ�ִ��������
		private boolean isFinish; // �ж��Ƿ�ó����

		// ��ʼ������
		public ButtonClick6601() {
			super();
			this.operationFactory = null;
			this.number1 = 0;
			this.number2 = 0;
			this.flag = false;
			this.numValidity = true;
			this.isOperation = false;
			this.isFinish = false;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonName = e.getActionCommand(); // ͨ����ȡ��ť�ϵ��ı����ж�ִ�еĹ���

			// ��չ���
			if (buttonName == "���") {
				resultTextField.setText(""); // ����ı���

				// �����еĳ�Ա��������
				this.operationFactory = null;
				this.number1 = 0;
				this.number2 = 0;
				this.flag = false;
				this.numValidity = true;
				this.isOperation = false;

				return;

			} else if (Character.isDigit(buttonName.charAt(0)) || buttonName.equals(".")) {
				// ������ʾ����
				genNum(buttonName.charAt(0));

			} else {
				// ִ�������
				operator(buttonName.charAt(0));
			}
		}

		/**
		 * ���㹦��
		 * 
		 * @param ch - �����
		 */
		public void operator(char ch) {
			// ������ݵĺϷ���
			// �������������Ϸ�������ǰ���������ְ�������������.��ִ�����㣩
			if (!numValidity) {
				return;
			}

			// ������
			if (ch == '=') {
				// �û�û�����룬���ڶ���������
				// ����������ǽ� switch ���޸ĵ� number2 �ָ�����
				if (flag) {
					if (!isOperation) {
						if (!isFinish) {
							this.number2 = 0;
						}
					}
				}

				// δִ�й������������ ������Ϊ��
				// ���û�������������δ���κβ������� ֱ�Ӱ��Ⱥŵ����
				if (this.operationFactory == null) {
					// ���δָ�����������ԭ�����
					resultTextField.setText(String.valueOf(this.number1));

				} else {
					// ��������������
					Operation operation = this.operationFactory.createOperation();

					// ָ���������
					operation.number1 = this.number1;
					operation.number2 = this.number2;

					// ��ʾ����Ľ��
					double result = operation.getResult();
					resultTextField.setText(String.valueOf(result));

					// ������Ľ������num1��������������
					this.number1 = result;
					this.isFinish = true;
				}

				// ִ�е��ǵȺţ������ж��Ƿ�Ϊ������ ��
				this.isOperation = false;

			} else {
				// ���ж��Ƿ�ִ�й��������
				if (this.isOperation) {
					// flag �����ж��Ƿ������˵ڶ�����
					// �������Ҫ�����ǵ��û����ж������ ȴδִ�еȺŵ����
					// �� 1 + 2 + 3 �������
					if (!this.flag) {
						// ���ڶ����� ���� ��һ��
						this.number1 = this.number2;
						// ��ʱ�� �ڶ������� �ȴ��û�����
						this.number2 = 0;
						
					} else {
						// ���������������û�ִ���˶�ε���������� ȴû������ڶ���ֵ�������
						// �� +-*/= ��ϵ�еĲ��� ��Ҫ�ر����ͨ��
						// ������������������� ��ͻ
						isOperation = false;
					}
				}

				// ѡ��ִ�е������
				switch (ch) {
				case '+':
					this.operationFactory = new AddFactory();
					break;

				case '-':
					this.operationFactory = new SubFactory();
					break;

				case '*':
					this.operationFactory = new MulFactory();
					// Ϊ�˱�֤�ڶ�����û���������� �������ԭ������
					// ��Ҫ�� �ڶ����� ��ʱ���ó� 1
					if (!isOperation) {
						this.number2 = 1;
					}
					break;

				case '/':
					this.operationFactory = new DivFactory();
					if (!isOperation) {
						this.number2 = 1;
					}
					break;
				}

				// ִ���������� ��Ϊ true
				this.isOperation = true;
			}

			// ִ�й����������ܣ����Լ�������������Ϊ true
			this.flag = true;
		}

		/**
		 * ���������������
		 * 
		 * @param ch - [1-9 .]�İ�ť
		 */
		public void genNum(char ch) {
			// ����� .
			if (ch == '.') {
				// ���ж��Ƿ���� .
				// ���ж�������������Ƿ���� . ��ʾ�Ƿ���ӹ�
				if (!resultTextField.getText().contains(".")) {
					this.numValidity = true;

				} else {
					// ���������Ѿ����� . ���Ҽ��� ��� . ʱ���� ���ݲ��Ϸ�
					this.numValidity = false;
				}

				// ��ԭ�е���������� .
				resultTextField.setText(resultTextField.getText() + ch);

			} else {
				// flag ��Ϊ�ж��Ƿ�������
				// �����û����������ʱ ��������������� �򸲸��Ժ������
				if (flag) {
					resultTextField.setText("" + ch);
					// �����flag �����ж���ʾ���ݵĸ��ǲ�����Ҳ��Ϊ���������Ƿ���ɵı�־
					this.flag = false; 

				} else {
					resultTextField.setText(resultTextField.getText() + ch);
				}

				// ������ݲ��Ϸ����򲻽���ת�� ����ᱨ��
				if (!this.numValidity) {
					return;
				}
				
				// ��ȡ��ǰ����ֵ
				double number = Double.parseDouble(resultTextField.getText());

				// ͨ���ж��û��Ƿ�ִ�� ��������ж� �������
				// ִ��������� ��������ǵڶ�����
				if (isOperation) {
					this.number2 = number;

				} else {
					this.number1 = number;
				}
			}
		}
	}
}
