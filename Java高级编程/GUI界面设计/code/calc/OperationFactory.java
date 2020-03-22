package java6601.calc;

/**
 * 运算器工厂接口
 * 
 * @author CAI
 *
 */
public interface OperationFactory {
	public Operation createOperation();
}

// 加法工厂
class AddFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationAdd(); // 返回加法运算器
	}
}

// 减法工厂
class SubFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationSub(); // 返回减法运算器
	}
}

// 乘法工厂
class MulFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationMul(); // 返回乘法运算器
	}
}

// 除法工厂
class DivFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationDiv(); // 返回除法运算器
	}
}
