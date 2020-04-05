/**
 * 
 */
package java6601.lambda;

/**
 * Õº È¿‡
 * 
 * @author CAI
 *
 */
public class Book6601 {
	private String ID;
	private String name;
	private int price;
	private int number;
	
	public Book6601() {
		super();
	}
	
	public Book6601(String ID, String name, int price, int number) {
		super();
		this.ID = ID;
		this.name = name;
		this.price = price;
		this.number = number;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return ID + "," + name + "," + price + "," + number;
	}
	
}
