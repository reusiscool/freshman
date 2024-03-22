package expression.exceptions;

import expression.Divide;
import expression.ExtendedExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    public int evaluate(int left, int right) {
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException(getEvaluatedString(left, right));
        }
        if (right == 0) {
            throw new DivisionByZeroException(getEvaluatedString(left, right));
        }
        return super.evaluate(left, right);
    }
}
