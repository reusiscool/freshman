package expression;

import java.math.BigInteger;

public class Multiply extends BinaryOperator {
    public Multiply(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    protected boolean isDistrib() {
        return true;
    }

    @Override
    protected boolean hasPriority() {
        return false;
    }

    @Override
    protected int evaluate(int left, int right) {
        return left * right;
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected String operationSign() {
        return "*";
    }
}
