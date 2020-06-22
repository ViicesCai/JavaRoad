/**
 * 
 */
package edu.fdzc.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 各自集合类型
 * 
 * @author CAI
 *
 */
public class AllCollectionType {
	private List<String> list;
	private String[] array;
	private Set<String> set;
	private Map<String, String> map;
	private Properties props;
	
	public AllCollectionType(Properties props) {
		super();
		this.props = props;
	}
	
	public AllCollectionType() {
		super();
	}
	
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public String[] getArray() {
		return array;
	}
	
	public void setArray(String[] array) {
		this.array = array;
	}
	
	public Set<String> getSet() {
		return set;
	}
	
	public void setSet(Set<String> set) {
		this.set = set;
	}
	
	public Map<String, String> getMap() {
		return map;
	}
	
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	public Properties getProps() {
		return props;
	}
	
	public void setProps(Properties props) {
		this.props = props;
	}

	@Override
	public String toString() {
		return "[list=" + list + "\n array=" + Arrays.toString(array) + "\n set=" + set + "\n map=" + map
				+ "\n props=" + props + "]";
	}
	
	
}
