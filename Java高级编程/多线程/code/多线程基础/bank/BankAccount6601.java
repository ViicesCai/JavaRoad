/**
 * 
 */
package java6601.bank;

/**
 * �����˻���
 * 
 * @author CAI
 *
 */
public class BankAccount6601 {
	private String bankID;
	private int balance;
	
	public BankAccount6601(String bankID, int balance) {
		super();
		this.bankID = bankID; // �˻�
		this.balance = balance; // ���
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
