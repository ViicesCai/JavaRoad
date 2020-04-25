/**
 * 
 */
package java6601.lesson13;

/**
 * 银行账户类
 * 
 * @author CAI
 *
 */
public class BankAccount6601 {
	private String bankID;
	private Double balance;
	
	public BankAccount6601(String bankID, Double balance) {
		super();
		this.bankID = bankID;
		this.balance = balance;
	}

	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
}
