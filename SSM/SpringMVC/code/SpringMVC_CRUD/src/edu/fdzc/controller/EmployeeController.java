package edu.fdzc.controller;

import edu.fdzc.bean.Department;
import edu.fdzc.bean.Employee;
import edu.fdzc.dao.DepartmentDao;
import edu.fdzc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工控制器
 *
 * @author CAI
 * @time 2021/2/16
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    /**
     * 查询所有员工
     * 访问 index.jsp -> getEmps() -> 查询所有员工信息 -> 存放在请求域中 -> 转发到 list.jsp
     *
     * @return 员工列表视图
     */
    @RequestMapping("/emps")
    public String getEmps(Model model) {
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);

        return "list";
    }

    /**
     * 修改员工信息
     *
     *
     * @return 修改员工页面
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String getEmp(@PathVariable("id")Integer id, Model model) {
        // 查询员工信息
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "edit";
    }

    /**
     * 添加员工
     * list.jsp -> addEmps() -> 查询部门信息 -> 创建员工对象 -> 存放在请求域中 -> 根据用户表单填充员工对象的属性
     * 添加之前需要查询所有的部分信息用于展示
     *
     * @return 添加员工视图
     */
    @RequestMapping("/toaddpage")
    public String toAddPage(Model model) {
        model.addAttribute("employee", new Employee());

        return "add";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String updateEmp(@ModelAttribute("employee") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @ModelAttribute
    public void MyModelAttribute(@RequestParam(value = "id", required = false)Integer id, Model model) {
        if (id != null) {
            Employee employee = employeeDao.get(id);
            model.addAttribute("employee", employee);
        }

        // 查询所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        // 放到请求域中
        model.addAttribute("depts", departments);
    }

    /**
     * 保存员工
     *
     * @return 员工列表视图
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String addEmp(@Valid Employee employee, BindingResult bindingResult, Model model) { // @Valid:告诉SpringMVC按照JavaBean的规则进行校验
        // BindingResult 为该校验的结果，必须紧跟在@Valid之后
        boolean hasErrors = bindingResult.hasErrors();
        Map<String, Object> errorMap = new HashMap<>();

        if (hasErrors) { // 如果校验结果存在错误，返回添加页面
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errorInfo", errorMap);

            return "add";
        }

        employeeDao.save(employee);
        // 返回列表页面：重定向至列表页面
        return "redirect:/emps";
    }

    /**
     * 快速添加员工信息
     *
     * @return 员工列表视图
     */
    @RequestMapping("/quickAdd")
    public String quickAdd(@RequestParam("empinfo")Employee employee) {
        employeeDao.save(employee);

        return "redirect:/emps";
    }
}
