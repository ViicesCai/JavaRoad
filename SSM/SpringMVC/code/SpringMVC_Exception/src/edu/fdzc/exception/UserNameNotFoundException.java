package edu.fdzc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 用户未找到异常
 *
 * @author CAI
 * @time 2021/2/22
 */
@ResponseStatus(reason = "用户被拒绝登录", value = HttpStatus.NOT_ACCEPTABLE)
public class UserNameNotFoundException extends RuntimeException {

}
