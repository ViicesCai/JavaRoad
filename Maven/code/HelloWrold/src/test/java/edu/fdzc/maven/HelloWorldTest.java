/**
 * 
 */
package edu.fdzc.maven;

import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * @author CAI
 *
 */
public class HelloWorldTest {
	
	@Test
	public void testHello() {
		HelloWorld hello =  new HelloWorld();
		String result = hello.sayHello("CAI");
		assertEquals("Hello CAI", result); // 断言：验证结果
	}

}
