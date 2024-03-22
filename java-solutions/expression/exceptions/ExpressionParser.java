package expression.exceptions;

import expression.*;
import expression.parser.CharSource;
import expression.parser.StringSource;
import expression.parser.TripleParser;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
        Parser parser = new Parser(new StringSource(expression));
        return parser.parseExpression();
    }

    private static class Parser extends expression.parser.ExpressionParser.Parser {
        protected Parser(CharSource source) {
            super(source);
        }

        @Override
        protected ExtendedExpression getNegate(ExtendedExpression expr) {
            return new CheckedNegate(expr);
        }

        protected ExtendedExpression getBinary(String ch, ExtendedExpression left, ExtendedExpression right) {
            return switch (ch) {
                case "min" -> new Min(left, right);
                case "max" -> new Max(left, right);
                case "+" -> new CheckedAdd(left, right);
                case "-" -> new CheckedSubtract(left, right);
                case "*" -> new CheckedMultiply(left, right);
                case "/" -> new CheckedDivide(left, right);
                case "&" -> new And(left, right);
                case "^" -> new Xor(left, right);
                case "|" -> new Or(left, right);
                default -> throw error("Something went wrong...");
            };
        }

    }
}
