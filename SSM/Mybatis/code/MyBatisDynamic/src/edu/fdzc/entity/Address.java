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
	private String homeAddress; // 家庭地址
	private String workAddress; // 工作地址
	
	public Address() {
		super();
	}
	
	public Address(String homeAddress, String workAddress) {
		super();
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getWorkAddress() {
		return workAddress;
	}
	
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
}
