package expression.exceptions;

import expression.BinaryOperator;
import expression.ExtendedExpression;

import java.math.BigInteger;

public class Min extends BinaryOperator {
    public Min(ExtendedExpression leftParam, ExtendedExpression rightParam) {
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
        return Math.min(left, right);
    }

    @Override
    protected BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.min(right);
    }

    @Override
    protected int getPriority() {
        return -4;
    }

    @Override
    protected String operationSign() {
        return "min";
    }
}
