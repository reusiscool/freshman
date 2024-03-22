package expression;

import java.math.BigInteger;

public class Add extends BinaryOperator {

    public Add(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    protected boolean isDistrib() {
        return false;
    }

    @Override
    protected boolean hasPriority() {
        return false;
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected String operationSign() {
        return "+";
    }

    @Override
    protected int evaluate(int left, int right) {
        return left + right;
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.add(right);
    }
}
