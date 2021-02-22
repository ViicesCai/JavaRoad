package edu.fdzc.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.fdzc.bean.Department;

/**
 * 部门操作
 */
@Repository
public class DepartmentDao {

	private static Map<Integer, Department> departments = null;
	
	static{ // 初始部门
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "D-AA"));
		departments.put(102, new Department(102, "D-BB"));
		departments.put(103, new Department(103, "D-CC"));
		departments.put(104, new Department(104, "D-DD"));
		departments.put(105, new Department(105, "D-EE"));
	}

	/**
	 * 获取所有的部分
	 *
	 * @return 集合
	 */
	public Collection<Department> getDepartments(){
		return departments.values();
	}

	/**
	 * 根据部门id查询部门
	 *
	 * @param id 部门id
	 * @return 部门
	 */
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
}
