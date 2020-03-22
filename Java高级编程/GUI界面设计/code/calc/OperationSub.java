/**
 * 
 */
package java6601.calc;

/**
 * 减法运算器类
 * 
 * @author CAI
 *
 */
public class OperationSub extends Operation {

	@Override
	public double getResult() {
		return number1 - number2; // 返回两数之差
	}
	
}
