package game;

public class MnkBoard extends AbstractBoard {
    public MnkBoard(int rowCount, int columnCount, int k) {
        super(rowCount, columnCount, k);
    }

    @Override
    protected boolean isDraw() {
        return filled >= getRowCount() * getColumnCount();
    }

    @Override
    protected boolean isInBoundaries(int row, int col) {
        return 0 <= row && row < rowCount && 0 <= col && col < columnCount;
    }
}
