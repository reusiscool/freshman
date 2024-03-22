package expression;

import java.math.BigInteger;

public class Negate extends UnaryOperator {
    public Negate(ExtendedExpression param) {
        super(param);
    }

    @Override
    public String toString() {
        return "-" + '(' + param.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (param instanceof BinaryOperator) {
            return "-" + "(" + param.toMiniString() + ")";
        }
        return "-" + " " + param.toMiniString();
    }

    @Override
    protected int apply(int value) {
        return -value;
    }

    @Override
    protected BigInteger apply(BigInteger value) {
        return value.negate();
    }
}
