package expression;

import java.math.BigInteger;

public class Subtract extends BinaryOperator {
    public Subtract(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    protected boolean isDistrib() {
        return false;
    }

    @Override
    protected boolean hasPriority() {
        return true;
    }

    @Override
    protected int evaluate(int left, int right) {
        return left - right;
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    @Override
    protected String operationSign() {
        return "-";
    }
}
