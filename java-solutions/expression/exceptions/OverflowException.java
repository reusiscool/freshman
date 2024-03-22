package expression.exceptions;

public class OverflowException extends ExpressionException {
    public OverflowException(String message) {
        super("Overflow exception: " + message);
    }
}
