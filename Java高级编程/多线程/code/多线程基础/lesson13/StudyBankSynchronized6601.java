/**
 * 
 */
package java6601.lesson13;

/**
 * ���߳���ɴ�ȡ��
 * 
 * @author CAI
 *
 */
public class StudyBankSynchronized6601 {
	private BankAccount6601 account;

	public StudyBankSynchronized6601(BankAccount6601 account) {
		this.account = account;
	}

	public void bank6601() {
		String name = Thread.currentThread().getName();
		String action = "";
		double money = 0, b = 0;

		if (name.equals("���")) {
			money = 300;
			action = "����";

		} else if (name.equals("����")) {
			money = -100;
			action = "ȡ��";
		}

		synchronized (this) {
			for (int i = 1; i <= 3; i++) {

				b = account.getBalance() + money;
				System.out.println(name + action + Math.abs(money));

				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				account.setBalance(b);

			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		BankAccount6601 account = new BankAccount6601("211906601", 1000.0);
		StudyBankSynchronized6601 bank = new StudyBankSynchronized6601(account);

		Thread t1 = new Thread(bank::bank6601, "���");
		Thread t2 = new Thread(bank::bank6601, "����");
		System.out.println(account.getBankID() + "���Ͽ�ʼ��" + account.getBalance() + "Ԫ��");
		UseTimeTool.getInstance().start();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(account.getBankID() + "����������" + account.getBalance() + "Ԫ��");

		UseTimeTool.getInstance().stop();
	}

}
