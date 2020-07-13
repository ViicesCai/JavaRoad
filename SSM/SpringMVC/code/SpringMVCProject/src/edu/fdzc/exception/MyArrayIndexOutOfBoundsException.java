/**
 * 
 */
package edu.fdzc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 处理数组越界异常
 * 
 * @author CAI
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "数组越界异常") // 状态码-状态描述
public class MyArrayIndexOutOfBoundsException extends Exception { // 自定义异常

	
}
