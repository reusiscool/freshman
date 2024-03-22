package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public String peekNext(int amount) {
        if (pos + amount > data.length()) {
            return null;
        }
        return data.substring(pos, pos + amount);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
