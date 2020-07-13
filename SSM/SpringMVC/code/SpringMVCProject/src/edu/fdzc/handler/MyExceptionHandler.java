/**
 * 
 */
package edu.fdzc.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理类
 * 
 * @author CAI
 *
 */
@ControllerAdvice
public class MyExceptionHandler {

	// 可以处理任何类中的异常
	@ExceptionHandler({Exception.class})
	public String handlerArithmeticException(Exception e, Model model) {
		System.out.println(e);
		model.addAttribute("error", e);
			
		return "error";
	}
}
