package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class BinaryOperator implements ExtendedExpression {

    private final ExtendedExpression leftParam;
    private final ExtendedExpression rightParam;

    public BinaryOperator(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        this.leftParam = leftParam;
        this.rightParam = rightParam;
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        append(leftParam, sb, check(leftParam, false));
        sb.append(" ").append(operationSign()).append(" ");
        append(rightParam, sb, check(rightParam, true));
        return sb.toString();
    }

    protected String getEvaluatedString(int left, int right) {
        return left + operationSign() + right;
    }

    protected abstract boolean isDistrib();
    protected abstract boolean hasPriority();

    private void append(Expression param, StringBuilder sb, boolean claused) {
        if (claused) {
            sb.append('(').append(param.toMiniString()).append(')');
        } else {
            sb.append(param.toMiniString());
        }
    }

    private boolean check(Expression exp, boolean isRight) {
        if (!(exp instanceof BinaryOperator o)) {
            return false;
        }
        if (isRight) {
            if (hasPriority() && getPriority() >= o.getPriority() || o.hasPriority() && isDistrib()
                    || getPriority() == o.getPriority() && getClass() != o.getClass() && !o.hasPriority()) {
                return true;
            }
        }
        return o.getPriority() < getPriority();
    }

    @Override
    public String toString() {
        return "(" + leftParam + " " + operationSign() + " " + rightParam + ')';
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof BinaryOperator o && getClass() == other.getClass()
                && o.leftParam.equals(leftParam) && o.rightParam.equals(rightParam);
    }

    @Override
    public int evaluate(int x) {
        return evaluate(leftParam.evaluate(x), rightParam.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(leftParam.evaluate(x, y, z), rightParam.evaluate(x, y, z));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return evaluate(leftParam.evaluate(x), rightParam.evaluate(x));
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftParam, rightParam, getClass());
    }

    protected abstract int evaluate(int left, int right);
    protected abstract BigInteger evaluate(BigInteger left, BigInteger right);

    protected abstract int getPriority();

    protected abstract String operationSign();
}
