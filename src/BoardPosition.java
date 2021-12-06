import java.util.Deque;

public class BoardPosition {
    private int row;
    private int column;
    private Deque pieces;

    public BoardPosition (int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Deque getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "BoardPosition [row=" + row + ", column=" + column + "]";
    }
}