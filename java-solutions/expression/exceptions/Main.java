package expression.exceptions;

import expression.TripleExpression;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        final TripleExpression expr;
        try {
            expr = parser.parse("1000000*x*x*x*x*x/(x-1)");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("x\tf");
        for (int i = 0; i < 11; i++) {
            try {
                System.out.println(i + "\t" + expr.evaluate(i, 0, 0));
            } catch (RuntimeException e) {
                System.out.println(i + "\t" + e.getMessage());
            }
        }
    }
}
