package expression.exceptions;

import expression.ExtendedExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(ExtendedExpression param) {
        super(param);
    }

    @Override
    public int apply(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException("- " + value);
        }
        return super.apply(value);
    }
}
