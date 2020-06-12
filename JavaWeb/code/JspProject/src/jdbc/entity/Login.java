/**
 * 
 */
package jdbc.entity;

/**
 * Login 数据表
 * 
 * @author CAI
 *
 */
public class Login {
	private int id;
	private String name;
	private String pwd;
	
	public Login() {
		super();
	}
	
	public Login(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	
	public Login(int id, String name, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", pwd=" + pwd + "]";
	}
}
