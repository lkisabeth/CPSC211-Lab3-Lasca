import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LascaBoardTest {
    @Test
    void testCreateBoard() {
        LascaBoard board = new LascaBoard();

        assertEquals(7, board.getWidth());
        assertEquals(7, board.getHeight());

        for (int row = 0; row < board.getHeight(); row++)
            for (int column = 0; column < board.getWidth(); column++)
                assertNotNull(board.get(row, column));
    }

    @Test
    void testMovePlayer() {
        LascaBoard board = new LascaBoard();

        BoardPosition startingPosition = new BoardPosition(2, 2);
        BoardSpace which = BoardSpace.Red;
        BoardPosition newPosition = new BoardPosition(3, 3);

        board.movePlayer(startingPosition, which, newPosition);

        assertNotNull(board.get(startingPosition.getRow(), startingPosition.getColumn()));
        assertNotNull(board.get(newPosition.getRow(), newPosition.getColumn()));
    }
}