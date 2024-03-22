package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Const implements ExtendedExpression {
    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Const o && Objects.equals(value, o.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, getClass());
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        if (!(value instanceof BigInteger)) {
            throw new UnsupportedOperationException();
        }
        return (BigInteger) value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }
}
