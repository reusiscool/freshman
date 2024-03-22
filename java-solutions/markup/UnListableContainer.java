package markup;

import java.util.List;

public abstract class UnListableContainer implements UnListable {
    List<UnListable> elements;
    private final String mark;
    private final String start;
    private final String end;

    public UnListableContainer(List<UnListable> elements, String mark, String start, String end) {
        this.elements = elements;
        this.mark = mark;
        this.start = start;
        this.end = end;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(mark);
        for (var element : elements) {
            element.toMarkdown(sb);
        }
        sb.append(mark);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(start);
        for (var element : elements) {
            element.toBBCode(sb);
        }
        sb.append(end);
    }
}
