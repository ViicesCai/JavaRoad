package edu.fdzc.component;

import edu.fdzc.bean.Employee;
import edu.fdzc.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转员工类型转换器
 *
 * 两个泛型：
 *  S:Source
 *  T:Target
 *  将S转为T
 *
 * @author CAI
 * @time 2021/2/17
 */
public class MyStringToEmployeeConverter implements Converter<String, Employee> {

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 自定义类型转换规则
     *
     * @param source 原始类型
     * @return 目标类
     */
    @Override
    public Employee convert(String source) {
        Employee employee = new Employee();
        if (source.contains("-")) {
            String[] split = source.split("-");
            employee.setLastName(split[0]);
            employee.setEmail(split[1]);
            employee.setGender(Integer.parseInt(split[2]));
            employee.setDepartment(departmentDao.getDepartment(Integer.parseInt(split[3])));
        }

        return employee;
    }
}
