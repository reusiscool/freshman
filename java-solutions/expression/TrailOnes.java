package expression;

import java.math.BigInteger;

public class TrailOnes extends UnaryOperator {
    public TrailOnes(ExtendedExpression param) {
        super(param);
    }

    @Override
    public String toString() {
        return "t1" + '(' + param.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (param instanceof BinaryOperator) {
            return "t1" + "(" + param.toMiniString() + ")";
        }
        return "t1" + " " + param.toMiniString();
    }

    @Override
    protected int apply(int value) {
        int i = 0;
        while ((value & (1 << i)) != 0 && i < 32) {
            i++;
        }
        return i;
    }

    @Override
    protected BigInteger apply(BigInteger value) {
        return null;
    }
}
