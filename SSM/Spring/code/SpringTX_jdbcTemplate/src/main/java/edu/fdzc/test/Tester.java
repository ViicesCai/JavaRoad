package edu.fdzc.test;

import edu.fdzc.bean.Employee;
import edu.fdzc.dao.EmployeeDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CAI
 * @time 2021/1/19
 */
public class Tester {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("conf/applicationContext.xml");
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    NamedParameterJdbcTemplate named = ioc.getBean(NamedParameterJdbcTemplate.class);

    /**
     * 获取数据源
     *
     * @throws SQLException 数据库异常
     */
    @Test
    public void test() throws SQLException {
        DataSource dataSource = ioc.getBean(DataSource.class);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }

    /**
     * 使用jdbcTemplate操作数据库
     * 将emp_id = 5的记录的salary字段更新为1300.00
     */
    @Test
    public void test02() {
        String sql = "UPDATE employee SET salary = ? WHERE emp_id = ?";
        int update = jdbcTemplate.update(sql, 1300.00, 5);
        System.out.println("更新影响了" + update + "行");
    }

    /**
     * 批量插入
     */
    @Test
    public void test03() {
        String sql = "INSERT INTO employee(emp_name, salary) VALUES (?, ?)";
        // List的长度就是sql语句执行的次数
        // Object[]：每次执行要用的参数
        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"张三", 1300});
        batchArgs.add(new Object[]{"李四", 2300});
        batchArgs.add(new Object[]{"王五", 4400});
        batchArgs.add(new Object[]{"赵六", 5500});


        int[] update = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int i : update) {
            System.out.println(i);
        }
    }

    /**
     * 查询emp_id = 5的数据库记录，封装一个Java对象返回
     * javabean需要和数据库字段名一致，否则无法封装
     *
     * jdbcTemplate在方法级别进行了区分：
     *  查询集合：jdbcTemplate.query()
     *  查询单个对象：jdbcTemplate.queryForObject()
     */
    @Test
    public void test04() {
        String sql = "SELECT emp_id empId, emp_name empName, salary empSalary FROM employee WHERE emp_id = ?";
        // RowMapper：每一行记录和JavaBean的属性如何映射
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 5);

        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        System.out.println(employee);
    }

    /**
     * 查询salary > 4000的数据库记录，封装为List集合返回
     */
    @Test
    public void test05() {
        String sql = "SELECT emp_id empId, emp_name empName, salary empSalary FROM employee WHERE salary > ?";
        List<Employee> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), 4000);

        for (Employee employee : query) {
            System.out.println(employee);
        }
    }

    /**
     * 查询工资最多的
     */
    @Test
    public void test06() {
        String sql = "SELECT MAX(salary) FROM employee";
        // 无论返回单个数据或是对象，都使用queryForObject()
        Double salary = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(salary);

    }

    /**
     * 使用带有具名参数的SQL语句插入一条员工记录，并以Map形式传入参数值
     *
     * 具名参数：具有名字的参数，不使用占位符而是变量名
     *  :参数名
     * Spring有一个能支持具名参数的JdbcTemplate
     *
     *
     * 占位符参数(?)：?的顺序不能乱，按位置传参
     */
    @Test
    public void test07() {
        String sql = "INSERT INTO employee(emp_name, salary) VALUES (:empName, :salary)";
        Map<String, Object> map = new HashMap<>();

        // 将所有具名参数都放在map中
        map.put("empName", "CAI");
        map.put("salary", 30000);
        int update = named.update(sql, map);

        System.out.println(update);
    }

    /**
     * 以SqlParameterSource形式传值
     */
    @Test
    public void test08() {
        String sql = "INSERT INTO employee(emp_name, salary) VALUES (:empName, :empSalary)";
        Employee employee = new Employee();
        employee.setEmpName("Pork");
        employee.setEmpSalary(12344.2);
        int update = named.update(sql, new BeanPropertySqlParameterSource(employee));
        System.out.println(update);
    }

    /**
     * 创建BookDao，自动装配JdbcTemplate对象
     */
    @Test
    public void test09() {
        EmployeeDao bean = ioc.getBean(EmployeeDao.class);
        Employee employee = new Employee();
        employee.setEmpName("JACK");
        employee.setEmpSalary(4000.00);

        bean.saveEmployee(employee);
    }
}
