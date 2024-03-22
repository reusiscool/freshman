package markup;

import java.util.List;

public abstract class AbstractList implements Listable {
    List<ListItem> elements;
    private final String start;

    public AbstractList(List<ListItem> elements, String start) {
        this.elements = elements;
        this.start = start;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(start);
        for (var element : elements) {
            element.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
