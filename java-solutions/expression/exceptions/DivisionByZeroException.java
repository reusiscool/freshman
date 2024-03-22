package expression.exceptions;

public class DivisionByZeroException extends EvaluationException{
    public DivisionByZeroException(String message) {
        super("Division by zero exception: " + message);
    }
}
