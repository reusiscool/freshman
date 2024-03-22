package expression;

import java.math.BigInteger;

public class Or extends BinaryOperator {
    public Or(ExtendedExpression leftParam, ExtendedExpression rightParam) {
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
    protected int evaluate(int left, int right) {
        return left | right;
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return null;
    }

    @Override
    protected int getPriority() {
        return -3;
    }

    @Override
    protected String operationSign() {
        return "|";
    }
}
