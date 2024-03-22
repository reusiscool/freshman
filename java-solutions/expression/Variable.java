package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Variable implements ExtendedExpression {

    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Variable o && o.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getClass());
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }
}
