/**
 * 
 */
package java6601.bank;

/**
 * 银行账户类
 * 
 * @author CAI
 *
 */
public class BankAccount6601 {
	private String bankID;
	private int balance;
	
	public BankAccount6601(String bankID, int balance) {
		super();
		this.bankID = bankID; // 账户
		this.balance = balance; // 余额
	}

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
