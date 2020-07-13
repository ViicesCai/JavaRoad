/**
 * 
 */
package edu.fdzc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.fdzc.entity.Student;
import edu.fdzc.service.StudentService;

/**
 * 学生控制器
 * 
 * @author CAI
 *
 */
@RequestMapping("controller")
@Controller
public class StudentController {
	// 控制器依赖于 Service
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping("queryStudentBySno/{sno}")
	public String queryStudentBySno(@PathVariable("sno") Integer sno, Map<String, Object> map) {
		Student student = studentService.queryStudentBySno(sno);
		map.put("student", student);
		return "result";
	}
}
