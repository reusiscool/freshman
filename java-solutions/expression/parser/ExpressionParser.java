package expression.parser;

import expression.*;
import expression.exceptions.OverflowException;
import expression.exceptions.ParenthesisSequenceException;
import expression.exceptions.WrongArgumentException;

import java.util.List;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) {
//        System.out.println(expression);
        Parser parser = new Parser(new StringSource(expression));
        return parser.parseExpression();
    }

    public static class Parser extends BaseParser {
        protected Parser(CharSource source) {
            super(source);
        }

        public ExtendedExpression parseExpression() {
            ExtendedExpression result = parseExpression(0);
            if (eof()) {
                return result;
            }
            skipWhitespace();
            if (test(')')) {
                throw new ParenthesisSequenceException("No opening parenthesis");
            }
            throw new WrongArgumentException("binary operator", String.valueOf(take()));
        }

        protected ExtendedExpression parseExpression(int priority) {
            if (priority == 6) {
                return primary();
            }
            ExtendedExpression left = parseExpression(priority + 1);
            skipWhitespace();
            outer: while (true) {
                skipWhitespace();
                List<String> operations = getOperationByPriority(priority);
                for (String op : operations) {
                    if (!test(op)) {
                        continue;
                    }
                    expect(op);
                    left = getBinary(op, left, parseExpression(priority + 1));
                    skipWhitespace();
                    continue outer;
                }
                break;
            }
            return left;
        }

        protected ExtendedExpression getNegate(ExtendedExpression expr) {
            return new Negate(expr);
        }

        private ExtendedExpression primary() {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    return new Const(parseNumber(new StringBuilder("-")));
                } else {
                    skipWhitespace();
                    return getNegate(primary());
                }
            }
            if (between('0', '9')) {
                return new Const(parseNumber(new StringBuilder()));
            }
            if (between('x', 'z')) {
                return new Variable(String.valueOf(take()));
            }
            if (take('(')) {
                ExtendedExpression expr = parseExpression(0);
                skipWhitespace();
                if (!take(')')) {
                    throw new ParenthesisSequenceException("Opening parenthesis has not been closed");
                }
                return expr;
            }
            if (take(')')) {
                throw new ParenthesisSequenceException("Expression in parenthesis is incomplete");
            }
            if (take('t')) {
                expect('1');
                return new TrailOnes(primary());
            }
            if (take('l')) {
                expect('1');
                return new LeadOnes(primary());
            }
            final String found;
            if (eof()) {
                found = "EOF";
            } else {
                found = String.valueOf(take());
            }
            throw new WrongArgumentException("unary operator, number or variable", found);
        }

        private int parseNumber(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
            try {
                return Integer.parseInt(sb.toString());
            } catch (NumberFormatException e) {
                throw new OverflowException(sb.toString());
            }
        }

        protected ExtendedExpression getBinary(String ch, ExtendedExpression left, ExtendedExpression right) {
            return switch (ch) {
                case "+" -> new Add(left, right);
                case "-" -> new Subtract(left, right);
                case "*" -> new Multiply(left, right);
                case "/" -> new Divide(left, right);
                case "&" -> new And(left, right);
                case "^" -> new Xor(left, right);
                case "|" -> new Or(left, right);
                default -> throw error("Something went wrong...");
            };
        }

        protected List<String> getOperationByPriority(int priority) {
            return switch (priority) {
                case 0 -> {
                    for (int i = 0; i < 10; i++){
                        if (test("min" + i) || test("max" + i)) {
                            throw new WrongArgumentException("something but a digit after min or max", Integer.toString(i));
                        }
                    }
                    yield List.of("min", "max");
                }
                case 1 -> List.of("|");
                case 2 -> List.of("^");
                case 3 -> List.of("&");
                case 4 -> List.of("+", "-");
                case 5 -> List.of("/", "*");
                default -> List.of();
            };
        }
    }
}
