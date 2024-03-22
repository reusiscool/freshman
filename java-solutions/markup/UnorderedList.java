package markup;

import java.util.List;

public final class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> elements) {
        super(elements, "[list]");
    }
}
