/**
 * 
 */
package java6601.dao;

/**
 * User表 实体类
 * 
 * @author CAI
 *
 */
public class User6601 {
	private int id;
	private String name;
	private String password;
	private String telNumber;
	
	public User6601() {
		super();
	}

	public User6601(String name) {
		super();
		this.name = name;
	}
	
	public User6601(String name, String password, String telNumber) {
		super();
		this.name = name;
		this.password = password;
		this.telNumber = telNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	@Override
	public String toString() {
		return "User6601 [id=" + id + ", name=" + name + ", password=" + password + ", telNumber=" + telNumber + "]";
	}
	
}
