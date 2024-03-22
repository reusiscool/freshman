package game;

public class PositionProxy implements Position {
    private final Position position;

    public PositionProxy(Position position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return position.getCell(r, c);
    }

    @Override
    public String toString() {
        return position.toString();
    }

    @Override
    public int getColumnCount() {
        return position.getColumnCount();
    }

    @Override
    public int getRowCount() {
        return position.getRowCount();
    }
}
