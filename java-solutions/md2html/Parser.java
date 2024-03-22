package md2html;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Parser {
    private enum MarkdownMarks {
        STRONG, EMPHASIS, STRIKEOUT, CODE, PRE;
        
        private static String toHtml(MarkdownMarks mark) {
            return switch (mark) {
                case STRONG -> "strong";
                case EMPHASIS -> "em";
                case STRIKEOUT -> "s";
                case CODE -> "code";
                case PRE -> "pre";
            };
        }

        private static MarkdownMarks fromMarkdown(String markdown) {
            return switch (markdown) {
                case "```" -> PRE;
                case "**", "__" -> STRONG;
                case "*", "_" -> EMPHASIS;
                case "`" -> CODE;
                case "--" -> STRIKEOUT;
                default -> null;
            };
        }
    }

    private final StringBuilder res = new StringBuilder();
    private final Deque<MarkdownMarks> lastOpened = new ArrayDeque<>();
    private final List<String> lines;
    private int curIndex = 0;
    private int curLineIndex = -1;
    private String curLine;
    private String closing;

    public Parser(List<String> lines) {
        this.lines = lines;
    }

    public String parse() {
//        for (updateLine(); curLine < lines.size(); updateLine()) {
        updateLine();
        while (curLineIndex < lines.size()) {
            updateLineStart();
            final int size = lastOpened.size();
            tryParseMarkdown();
            if (size == lastOpened.size()) {
                String s = getShielded(curLine.charAt(curIndex));
                if (s == null) {
                    res.append(curLine.charAt(curIndex));
                } else {
                    res.append(s);
                }
                curIndex++;
            }
            updateLine();
        }
        return res.toString();
    }

    private void append(MarkdownMarks mark, int len) {
        curIndex += len;
        res.append("<");
        if (lastOpened.peek() == mark) {
            lastOpened.pop();
            res.append("/");
        } else {
            lastOpened.push(mark);
        }
        res.append(MarkdownMarks.toHtml(mark)).append(">");
    }

    private void updateLineStart() {
        if (closing != null) {
            return;
        }
        while (curLine.charAt(curIndex) == '#') {
            curIndex++;
        }
        if (curIndex == 0 || !Character.isWhitespace(curLine.charAt(curIndex))) {
            curIndex = 0;
            res.append("<p>");
            closing = "</p>";
        } else {
            res.append("<h").append(curIndex).append(">");
            closing = "</h" + curIndex + ">";
            curIndex++;
        }
    }


    private void updateLine() {
        if (curLine != null && curIndex < curLine.length()) {
            return;
        }
        curIndex = 0;
        curLineIndex++;
        while (lines.size() > curLineIndex && lines.get(curLineIndex).isEmpty()) {
            if (closing != null) {
                res.append(closing);
                closing = null;
            }
            curLineIndex++;
        }
        if (curLineIndex == lines.size()) {
            if (closing != null) {
                res.append(closing);
            }
            return;
        }
        if (curLine != null) {
            res.append(System.lineSeparator());
        }
        curLine = lines.get(curLineIndex);
    }

    private void tryParseMarkdown() {
        final int border = MarkdownMarks.PRE == lastOpened.peek() ? 2 : 0;
        for (int i = 3; i > border; i--) {
            if (curIndex + i > curLine.length()) {
                continue;
            }
            String sub = curLine.substring(curIndex, curIndex + i);
            MarkdownMarks mark = MarkdownMarks.fromMarkdown(sub);
            if (mark == null){
                continue;
            }
            if (isShielded(curIndex, curLine)) {
                res.deleteCharAt(res.length() - 1);
                return;
            }
            if (lastOpened.peek() == mark && isNotSpace(curIndex - 1)
                    || isNotSpace(curIndex + sub.length())) {
                append(mark, i);
                return;
            }
            return;
        }
    }

    private static String getShielded(char c) {
        return switch (c) {
            case '&' -> "&amp;";
            case '<' -> "&lt;";
            case '>' -> "&gt;";
            default -> null;
        };
    }

    private boolean isNotSpace(int atIndex) {
        return 0 <= atIndex && atIndex < curLine.length() && !Character.isWhitespace(curLine.charAt(atIndex));
    }

    private static boolean isShielded(int curIndex, String line) {
        return curIndex > 0 && line.charAt(curIndex - 1) == '\\';
    }
}
