package edu.fdzc.dao;

import edu.fdzc.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/19
 */
@Repository
public class EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee(emp_name, salary) VALUES (?, ?)";
        int update = jdbcTemplate.update(sql, employee.getEmpName(), employee.getEmpSalary());

        if (update == 1) {
            System.out.println("保存成功");
        } else {
            System.out.println("保存失败");
        }
    }
}
