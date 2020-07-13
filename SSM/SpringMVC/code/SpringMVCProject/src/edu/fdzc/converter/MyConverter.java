/**
 * 
 */
package edu.fdzc.converter;

import org.springframework.core.convert.converter.Converter;

import edu.fdzc.entity.Student;

/**
 * 自定义类型转换器
 * 
 * @author CAI
 *
 */
public class MyConverter implements Converter<String, Student> {

	public Student convert(String source) {
		// 接受前端传来的字符串
		String[] studentStrArr = source.split("-");
		Student student = new Student();
		student.setId(Integer.parseInt(studentStrArr[0]));
		student.setName(studentStrArr[1]);
		
		return student;
	}
}
