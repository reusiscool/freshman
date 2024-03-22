package game;

public class RoundBoard extends AbstractBoard {
    private final boolean[][] used;
    private final int d;
    private static final int extraMoveCount = 4;
    private final int area;

    public RoundBoard(int d, int k) {
        super(d, d, k);
        this.d = d;
        used = new boolean[d][d];
        int area = 0;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (isInBoundaries(i, j)) {
                    area++;
                }
            }
        }
        this.area = area;
    }

    @Override
    protected boolean isInBoundaries(int row, int col) {
        return ((row + 1 - (1f * d + 1) / 2) * (row + 1 - (1f * d + 1) / 2)
                + (col + 1 - (1f * d + 1) / 2) * (col + 1 - (1f * d + 1) / 2))
                <= 1f * d / 2 * d / 2;
    }

    @Override
    public Result makeMove(Move move) {
        Result res = getResult(move);
        int row = move.getRow();
        int col = move.getColumn();
        if (res == Result.UNKNOWN) {
            for (var coef : coefficients) {
                if (countUnusedRay(row, col, coef[0], coef[1], extraMoveCount) +
                        countUnusedRay(row, col, -coef[0], -coef[1], extraMoveCount) - 1 < extraMoveCount) {
                    continue;
                }
                applyRay(row, col, coef[0], coef[1], getRowCount());
                applyRay(row, col, -coef[0], -coef[1], getColumnCount());
                used[row][col] = true;
                return Result.EXTRAMMOVE;
            }
        }
        passTurn();
        return res;
    }

    @Override
    protected boolean isDraw() {
        return filled >= area;
    }

    private int countUnusedRay(int row, int col, int rowCoef, int colCoef, int border) {
        int i = 0;
        while (checkMarkPosition(row, col)
                && !used[row][col] && i < border) {
            i++;
            row += rowCoef;
            col += colCoef;
        }
        return i;
    }

    private void applyRay(int startRow, int startCol, int rowCoef, int colCoef, int border) {
        int i = 1;
        while (checkMarkPosition(startRow + i * rowCoef , startCol + i * colCoef) &&
                !used[startRow + i * rowCoef][startCol + i * colCoef] && i < border) {
            used[startRow + i * rowCoef][startCol + i * colCoef] = true;
            i++;
        }
    }

    @Override
    public void clear() {
        super.clear();
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                used[row][col] = false;
            }
        }
    }
}
