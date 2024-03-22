package game;

import java.util.Arrays;
import java.util.Map;

public abstract class AbstractBoard implements Board, Position {
    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    protected static final int[][] coefficients = {{1, -1}, {1, 1}, {0, 1}, {1, 0}};
    protected final Cell[][] cells;
    protected final int rowCount;
    protected final int columnCount;
    private final int k;
    protected Cell turn;
    protected int filled;

    @Override
    public void clear() {
        for (int row = 0; row < getRowCount(); row++) {
            for (int col = 0; col < getColumnCount(); col++) {
                if (isInBoundaries(row, col)) {
                    cells[row][col] = Cell.E;
                }
            }
        }
        filled = 0;
        turn = Cell.X;
    }

    public AbstractBoard(int rowCount, int columnCount, int k) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.k = k;
        this.cells = new Cell[rowCount][columnCount];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    protected void passTurn() {
        turn = turn == Cell.X ? Cell.O : Cell.X;
    }

    @Override
    public Position getPosition() {
        return new PositionProxy(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        Result res = getResult(move);
        passTurn();
        return res;
    }

    protected Result getResult(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        filled++;
        for (var coeffs : coefficients) {
            if (countInRow(move.getRow(), move.getColumn(), coeffs[0], coeffs[1]) >= k) {
                return Result.WIN;
            }
        }
        if (isDraw()) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    protected abstract boolean isDraw();

    protected int countInRow(int startRow, int startCol, int rowCoef, int colCoef) {
        return countInRay(startRow, startCol, rowCoef, colCoef) +
                countInRay(startRow, startCol, -rowCoef, -colCoef) - 1;
    }

    private int countInRay(int startRow, int startCol, int rowCoef, int colCoef) {
        int i = 1;
        while (checkMarkPosition(startRow + i * rowCoef , startCol + i * colCoef) && i < k) {
            i++;
        }
        return i;
    }

    protected boolean checkMarkPosition(int row, int col) {
        return isInBoundaries(row, col) && cells[row][col] == turn;
    }

    @Override
    public boolean isValid(final Move move) {
        return isInBoundaries(move.getRow(), move.getColumn())
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < columnCount; i++) {
            sb.append(i);
        }
        for (int r = 0; r < rowCount; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < columnCount; c++) {
                if (isInBoundaries(r, c)) {
                    sb.append(SYMBOLS.get(cells[r][c]));
                } else {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    protected abstract boolean isInBoundaries(int row, int col);

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }
}
