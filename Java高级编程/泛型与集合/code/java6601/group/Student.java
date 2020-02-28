/**
 * 
 */
package java6601.group;

/**
 * @author CAI
 *
 */
public class Student {
	private String name;

	public Student() {
		super();
	}
	
	public Student(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
