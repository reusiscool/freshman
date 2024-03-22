package expression;

import java.math.BigInteger;

public class Divide extends BinaryOperator {

    public Divide(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    protected boolean isDistrib() {
        return true;
    }

    @Override
    protected boolean hasPriority() {
        return true;
    }

    @Override
    protected int evaluate(int left, int right) {
        return left / right;
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    protected int getPriority() {
        return 2;
    }

    @Override
    protected String operationSign() {
        return "/";
    }
}
