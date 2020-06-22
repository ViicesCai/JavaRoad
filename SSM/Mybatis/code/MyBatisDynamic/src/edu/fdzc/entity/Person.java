/**
 * 
 */
package edu.fdzc.entity;

/**
 * Person 表
 * 
 * @author CAI
 *
 */
public class Person {
	private int id;
	private String name;
	private int age;
	private boolean sex;
	private Address address;
	private PersonCard card;
	
	public PersonCard getCard() {
		return card;
	}

	public void setCard(PersonCard card) {
		this.card = card;
	}

	private int communityid;
	
	public Person() {
		super();
	}
	
	public Person(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Person(int id, String name, int age, boolean sex) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public int getCommunityid() {
		return communityid;
	}

	public void setCommunityid(int communityid) {
		this.communityid = communityid;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]\n";
	}
}
