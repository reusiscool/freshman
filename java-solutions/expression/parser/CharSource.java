package expression.parser;

public interface CharSource {
    boolean hasNext();
    char next();
    String peekNext(int amount);
    IllegalArgumentException error(String message);
}
