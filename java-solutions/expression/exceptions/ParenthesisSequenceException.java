package expression.exceptions;

public class ParenthesisSequenceException extends ParserException{
    public ParenthesisSequenceException(String message) {
        super("Parenthesis sequence exception: " + message);
    }
}
