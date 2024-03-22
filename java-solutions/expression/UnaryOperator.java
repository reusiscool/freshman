package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class UnaryOperator implements ExtendedExpression{
    ExtendedExpression param;
    public UnaryOperator(ExtendedExpression param) {
        this.param = param;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return apply(param.evaluate(x));
    }

    @Override
    public int evaluate(int x) {
        return apply(param.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return apply(param.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UnaryOperator o && o.getClass() == getClass() && param.equals(o.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(param, getClass());
    }

    protected abstract int apply(int value);
    protected abstract BigInteger apply(BigInteger value);
}
