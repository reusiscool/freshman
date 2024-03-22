package expression.exceptions;

public class WrongArgumentException extends ParserException {
    public WrongArgumentException(String expected, String found) {
        super("Expected: " + expected + ". Found: '" + found + "'");
    }
}
