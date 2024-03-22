package Contest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


interface OneParamFunction {
    boolean check(char param);
}

interface ZeroParamFunction {
    boolean check();
}


class CustomScanner {
    private final Reader reader;
    private final char[] buffer = new char[1024];
    private char c = 0;
    private final char[] sep = System.lineSeparator().toCharArray();
    private boolean hasChar = false;
    private int read;
    private int l;

    public CustomScanner(String in) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8));
    }

    public CustomScanner(InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    public boolean hasNextWordInLine() {
        return hasNext(this::hitSeparator, this::isWordChar);
    }

    public boolean hasNextWord() {
        while (hasChar || readChar()) {
            if (isWordChar(c)) {
                return true;
            }
            hasChar = false;
        }
        return false;
    }

    public String nextWord() {
        return readNext(this::isWordChar);
    }

    public boolean hitSeparator() {
        return c == sep[sep.length - 1];
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private boolean isWordChar(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION || Character.isAlphabetic(c) || c == '\'';
    }

    public String next() {
        readNext(Character::isWhitespace);
        return readNext(c -> !Character.isWhitespace(c));
    }

    private String readNext(OneParamFunction func) {
        StringBuilder sb = new StringBuilder();
        while (hasNext()) {
            if (!func.check(c)) {
                break;
            }
            sb.append(c);
            hasChar = false;
        }
        return sb.toString();
    }

    public boolean hasNext()  {
        return hasChar || readChar();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public boolean hasNextInLine() {
        return hasNext(this::hitSeparator, c -> !Character.isWhitespace(c));
    }

    private boolean hasNext(ZeroParamFunction zeroFunc, OneParamFunction oneFunc) {
        while (hasNext()) {
            if (zeroFunc.check() || oneFunc.check(c)) {
                break;
            }
            hasChar = false;
        }
        if (zeroFunc.check()) {
            hasChar = false;
            return false;
        }
        return true;
    }

    private boolean readChar() {
        if (l >= read) {
            try {
                read = reader.read(buffer);
            } catch (IOException e) {
                return false;
            }
            if (read < 0) {
                hasChar = false;
                return false;
            }
            l = 0;
        }
        c = buffer[l++];
        hasChar = true;
        return true;
    }
}

public class H {
    public static void main(String[] args) {
        CustomScanner scanner = new CustomScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        int[] pref = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int A;
        int max = a[0];
        pref[0] = a[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(a[i], max);
            pref[i] = a[i] + pref[i - 1];
        }
        A = pref[n - 1];
        int[] f = new int[A];
        int curGroup = 0;
        int curSum = 0;
        for (int i = 1; i < A; i++) {
            if (++curSum >= a[curGroup]) {
                curGroup++;
                curSum = 0;
            }
            f[i] = curGroup;
        }

        int q = scanner.nextInt();
        int[] cached = new int[A + 1];
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            if (t < max) {
                System.out.println("Impossible");
                continue;
            }
            if (cached[t] != 0) {
                System.out.println(cached[t]);
                continue;
            }
            int prev = 0;
            int res = 0;
            while (true) {
                res++;
//                System.out.print(prev);
//                System.out.print(" ");
//                System.out.print(res);
//                System.out.println();
                if (prev + t >= A) {
                    break;
                }
                prev = pref[f[prev + t] - 1];
            }
            cached[t] = res;
            System.out.println(res);
        }
    }
}
