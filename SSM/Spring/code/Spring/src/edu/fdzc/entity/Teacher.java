/**
 * 
 */
package edu.fdzc.entity;

/**
 * @author CAI
 *
 */
public class Teacher {
	private String tname;
	private int tage;
	
	public Teacher() {
		super();
	}
	
	public Teacher(String tname, int tage) {
		super();
		this.tname = tname;
		this.tage = tage;
	}
	
	public String getTname() {
		return tname;
	}
	
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public int getTage() {
		return tage;
	}
	
	public void setTage(int tage) {
		this.tage = tage;
	}

	@Override
	public String toString() {
		return "[tname=" + tname + ", tage=" + tage + "]";
	}

}
