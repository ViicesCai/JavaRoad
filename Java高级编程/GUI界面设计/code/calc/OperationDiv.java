/**
 * 
 */
package java6601.calc;

/**
 * 除法运算器
 * 
 * @author CAI
 *
 */
public class OperationDiv extends Operation {

	@Override
	public double getResult() {
		System.out.println(number1 + " = " + number2);
		return number1 / number2; // 两数之商
	}

}
