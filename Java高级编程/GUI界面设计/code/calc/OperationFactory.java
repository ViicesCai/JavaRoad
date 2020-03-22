package java6601.calc;

/**
 * �����������ӿ�
 * 
 * @author CAI
 *
 */
public interface OperationFactory {
	public Operation createOperation();
}

// �ӷ�����
class AddFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationAdd(); // ���ؼӷ�������
	}
}

// ��������
class SubFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationSub(); // ���ؼ���������
	}
}

// �˷�����
class MulFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationMul(); // ���س˷�������
	}
}

// ��������
class DivFactory implements OperationFactory {

	@Override
	public Operation createOperation() {
		return new OperationDiv(); // ���س���������
	}
}
