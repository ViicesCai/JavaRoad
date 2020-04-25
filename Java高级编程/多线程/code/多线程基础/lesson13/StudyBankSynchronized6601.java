/**
 * 
 */
package java6601.lesson13;

/**
 * 多线程完成存取款
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

		if (name.equals("会计")) {
			money = 300;
			action = "存入";

		} else if (name.equals("出纳")) {
			money = -100;
			action = "取出";
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

		Thread t1 = new Thread(bank::bank6601, "会计");
		Thread t2 = new Thread(bank::bank6601, "出纳");
		System.out.println(account.getBankID() + "账上开始余额：" + account.getBalance() + "元。");
		UseTimeTool.getInstance().start();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(account.getBankID() + "账上最终余额：" + account.getBalance() + "元。");

		UseTimeTool.getInstance().stop();
	}

}
