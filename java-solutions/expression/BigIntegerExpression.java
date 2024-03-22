package expression;

import java.math.BigInteger;

public interface BigIntegerExpression extends ToMiniString {
    BigInteger evaluate(BigInteger x);
}
