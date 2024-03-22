import CustomScanner.CustomScanner;

import java.util.Arrays;

public class ReverseSumHexAbc {
    public static void main (String[] args) {
        CustomScanner scanner = new CustomScanner(System.in);
        int[] row = new int[512];
        int[] addition = new int[512];
        while (scanner.hasNext()) {
            int len = 0;
            int prefSum = 0;
            while (scanner.hasNextInLine()) {
                if (len == row.length) {
                    row = Arrays.copyOf(row, len * 2);
                }
                if (addition.length > len) {
                    prefSum += addition[len];
                    addition[len] = 0;
                }
                prefSum += fromABC(scanner.next());
                row[len++] += prefSum;
            }
            if (len >= addition.length) {
                addition = Arrays.copyOf(addition, len + 1);
            }
            addition[len] += prefSum;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(toABC(row[i]));
                sb.append(' ');
            }
            System.out.println(sb);
        }
        scanner.close();
    }

    private static int fromABC (String s) {
        if (s.charAt(0) == '0') {
            return Integer.parseUnsignedInt(s.substring(2), 16);
        }
        int res = 0;
        long power = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                res *= -1;
                break;
            }
            res += (c - 'a') * power;
            power *= 10;
        }
        return res;
    }

    private static String toABC (int n) {
        if (n == 0) {
            return "a";
        }
        StringBuilder sb = new StringBuilder();
        boolean negative = false;
        if (n < 0) {
            negative = true;
            n = -n;
        }
        while (n > 0) {
            char c = (char)(n % 10 + 'a');
            sb.append(c);
            n /= 10;
        }
        if (negative) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}