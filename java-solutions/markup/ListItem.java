package markup;

import java.util.List;

public class ListItem {
    List<Listable> elements;

    public ListItem(List<Listable> elements) {
        this.elements = elements;
    }

    public void toBBCode(StringBuilder sb) {
        sb.append("[*]");
        for (var element : elements) {
            element.toBBCode(sb);
        }
    }
}
