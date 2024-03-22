package markup;

public class Text implements UnListable {
    private final String value;

    public Text (String value) {
        super();
        this.value = value;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(value);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(value);
    }
}
