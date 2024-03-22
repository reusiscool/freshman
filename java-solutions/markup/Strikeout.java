package markup;

import java.util.List;

public class Strikeout extends UnListableContainer {
    public Strikeout(List<UnListable> elements) {
        super(elements, "~", "[s]", "[/s]");
    }
}
