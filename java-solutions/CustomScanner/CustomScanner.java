package CustomScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;


interface OneParamFunction {
    boolean check(char param);
}

interface ZeroParamFunction {
    boolean check();
}


public class CustomScanner {
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