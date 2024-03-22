package expression.exceptions;

import expression.ExtendedExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(ExtendedExpression leftParam, ExtendedExpression rightParam) {
        super(leftParam, rightParam);
    }

    @Override
    public int evaluate(int left, int right) {
        int value = super.evaluate(left, right);
        if (left != 0 && right != 0 && (value / right != left || value / left != right)) {
            throw new OverflowException(getEvaluatedString(left, right));
        }
        return value;
    }
}
