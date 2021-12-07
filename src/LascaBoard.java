public class LascaBoard {
    private BoardSpace [][] board = new BoardSpace [7][7];

    public LascaBoard () {
        createBoard(7, 7);
    }

    public int getWidth () {
        return board[0].length;
    }

    public int getHeight () {
        return board.length;
    }

    public BoardSpace get (int row, int column) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length)
            return null;

        return board[row][column];
    }

    public void movePlayer (BoardPosition startingPosition, BoardSpace which, BoardPosition newPosition) {
        // All moves passed to this method should already have been checked
        // to make sure they're legal.  Models need to check again, though, just
        // in case the Controller has a bug.  But we don't need to try to return
        // an error message, since if the Controller has a bug the programmer
        // can debug into this method to see what went wrong.

        // It makes no sense to ask us to move a non-player
        if (which != BoardSpace.Red && which != BoardSpace.Black)
            return;

        // It makes no sense to move to a spot outside the board
        if (illegalBoardPosition(newPosition.getRow(), newPosition.getColumn()))
            return;

        // Make sure the move is legal.  A move is legal if:
        //
        // 1) And the destination is available
        // 2) It is 1 space away from the current position
        if (board[newPosition.getRow()][newPosition.getColumn()] != BoardSpace.Available)
            return;

        if (Math.abs(startingPosition.getRow() - newPosition.getRow()) > 1 || Math.abs(startingPosition.getColumn() - newPosition.getColumn()) > 1)
            return;

        // Should be a legal move, make it
        board[startingPosition.getRow()][startingPosition.getColumn()] = BoardSpace.Available;
        board[newPosition.getRow()][newPosition.getColumn()] = which;
        board[newPosition.getRow()][newPosition.getColumn()].pieces.addAll(board[startingPosition.getRow()][startingPosition.getColumn()].pieces);
    }

    public boolean winner () {
        // This returns BoardSpace.Player1 if player 1 has won,
        // BoardSpace.Player2 if player 2 has won, and
        // BoardSpace.Available if the game is not over yet
        //
        // This is complicated a bit when both players are isolated.
        // In that case the person who took the last move wins,
        // because the person who has the next move would be unable
        // to do so.

        return false;
    }

    private void createBoard (int width, int height) {
        board = new BoardSpace[height][width];

        for (int row = 0; row < height; row++)
            for (int column = 0; column < width; column++)
                board[row][column] = BoardSpace.Available;

        // Place the pieces in their starting spaces. Using loops would be better here but this was easier to figure out quickly.
        board[0][0] = BoardSpace.Red;
        board[0][2] = BoardSpace.Red;
        board[0][4] = BoardSpace.Red;
        board[0][6] = BoardSpace.Red;
        board[1][1] = BoardSpace.Red;
        board[1][3] = BoardSpace.Red;
        board[1][5] = BoardSpace.Red;
        board[2][0] = BoardSpace.Red;
        board[2][2] = BoardSpace.Red;
        board[2][4] = BoardSpace.Red;
        board[2][6] = BoardSpace.Red;
        board[height-1][0] = BoardSpace.Black;
        board[height-1][2] = BoardSpace.Black;
        board[height-1][4] = BoardSpace.Black;
        board[height-1][6] = BoardSpace.Black;
        board[height-2][1] = BoardSpace.Black;
        board[height-2][3] = BoardSpace.Black;
        board[height-2][5] = BoardSpace.Black;
        board[height-3][0] = BoardSpace.Black;
        board[height-3][2] = BoardSpace.Black;
        board[height-3][4] = BoardSpace.Black;
        board[height-3][6] = BoardSpace.Black;

        // Add 'pieces' to the Deques
        for (int row = 0; row < height; row++)
            for (int column = 0; column < width; column++)
                if (board[row][column] == BoardSpace.Red) {
                    board[row][column].pieces.addFirst("R");
                } else if (board[row][column] == BoardSpace.Black) {
                    board[row][column].pieces.addFirst("B");
                }
    }

    private boolean illegalBoardPosition (int row, int column) {
        return (row < 0 || row >= board.length || column < 0 || column >= board[0].length);
    }
}