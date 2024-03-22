package markup;

import java.util.List;

public final class Paragraph implements Listable {
    List<UnListable> elements;

    public Paragraph(List<UnListable> elements) {
        this.elements = elements;
    }

    public void toMarkdown(StringBuilder sb) {
        for (var element : elements) {
            element.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (var element : elements) {
            element.toBBCode(sb);
        }
    }
}
