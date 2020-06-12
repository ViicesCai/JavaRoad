/**
 * 
 */
package edu.fdzc.maven;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author CAI
 *
 */
public class TestHelloWorld {

	@Test
	public void testSayHelloWorldTime() {
		SayHelloWorldTime helloWorldTime = new SayHelloWorldTime();
		String result = helloWorldTime.sayHelloWorldTime("Morning");
		
		assertEquals("Hello Cai,Morning", result); // 断言：验证结果
	}
}
