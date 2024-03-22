package markup;

import java.util.Collections;
import java.util.List;

public class Emphasis extends UnListableContainer {
    public Emphasis(List<UnListable> elements) {
        super(Collections.unmodifiableList(elements), "*", "[i]", "[/i]");
    }
}
