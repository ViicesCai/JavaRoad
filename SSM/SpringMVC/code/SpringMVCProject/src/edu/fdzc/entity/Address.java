/**
 * 
 */
package edu.fdzc.entity;

/**
 * 地址类
 * 
 * @author CAI
 *
 */
public class Address {
	private String homeAddress;
	private String schoolAddress;
	
	public String getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getSchoolAddress() {
		return schoolAddress;
	}
	
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	@Override
	public String toString() {
		return "Address [homeAddress=" + homeAddress + ", schoolAddress=" + schoolAddress + "]";
	}
	
}
