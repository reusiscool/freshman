package expression;

import java.math.BigInteger;

public class LeadOnes extends UnaryOperator {
    public LeadOnes(ExtendedExpression param) {
        super(param);
    }

    @Override
    public String toString() {
        return "l1" + '(' + param.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (param instanceof BinaryOperator) {
            return "l1" + "(" + param.toMiniString() + ")";
        }
        return "l1" + " " + param.toMiniString();
    }

    @Override
    protected int apply(int value) {
        if (value >= 0) {
            return 0;
        }
        int i = 0;
        while (((value >> 31) & 1) != 0) {
            i++;
            value <<= 1;
        }
        return i;
    }

    @Override
    protected BigInteger apply(BigInteger value) {
        return null;
    }
}
