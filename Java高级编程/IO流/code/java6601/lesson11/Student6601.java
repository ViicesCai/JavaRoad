/**
 * 
 */
package java6601.lesson11;

import java.io.Serializable;

/**
 * @author CAI
 *
 */
public class Student6601 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	
	public Student6601(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return String.format("name=%s,age=%d", name, age);
	}

}
