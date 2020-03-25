/**
 * 
 */
package java6601.calc;

/**
 * 加法运算器类
 * 
 * @author CAI
 *
 */
public class OperationAdd extends Operation {

	@Override
	public double getResult() {
		return number1 + number2; // 返回两数之和
	}
}
