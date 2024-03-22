package markup;

import java.util.List;

public final class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> list) {
        super(list, "[list=1]");
    }
}
