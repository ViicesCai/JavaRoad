/**
 * 
 */
package edu.fdzc.maven;

/**
 * @author CAI
 *
 */
public class SayHelloWorldTime {
	
	public String sayHelloWorldTime(String time) {
		HelloWorld hello = new HelloWorld();
		return hello.sayHello("Cai") + "," + time;
	}
}
