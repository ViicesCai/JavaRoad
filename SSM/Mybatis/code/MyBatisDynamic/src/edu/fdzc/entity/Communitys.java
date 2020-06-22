/**
 * 
 */
package edu.fdzc.entity;

import java.util.List;

/**
 * 社区数据表
 * 
 * @author CAI
 *
 */
public class Communitys {
	private int communityId; // 社区 id
	private String communityName; // 社区名
	List<Person> persons; // 包含的人们（使两个类关联）
	
	public int getCommunityId() {
		return communityId;
	}
	
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	
	public String getCommunityName() {
		return communityName;
	}
	
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "[communityId=" + communityId + ", communityName=" + communityName + ", persons=\n" + persons
				+ "]";
	}
}
