package markup;

import java.util.List;

public class Strong extends UnListableContainer {
    public Strong(List<UnListable> elements) {
        super(elements, "__", "[b]", "[/b]");
    }
}
