package expression.exceptions;

import expression.Add;
import expression.ExtendedExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    public int evaluate(int left, int right) {
        if (right > 0 && Integer.MAX_VALUE - right < left || right < 0 && Integer.MIN_VALUE - right > left) {
            throw new OverflowException(getEvaluatedString(left, right));
        }
        return super.evaluate(left, right);
    }
}
