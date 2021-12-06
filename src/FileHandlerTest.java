import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class FileHandlerTest {

    @Test
    void createNewSaveFile() {
        FileHandler fileHandler = new FileHandler();

        fileHandler.createNewSaveFile();

        if (! fileHandler.getGameFile().getPath().equals("game.dat"))
            fail("game.dat was not created.");
    }

    @Test
    void getGameFile() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createNewSaveFile();
        if (! fileHandler.getGameFile().isFile())
            fail("Did not return a File.");
    }

    @Test
    void saveMove() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.createNewSaveFile();
        String[] cardinals = {"N", "NE","E", "SE", "S", "SW", "W", "NW"};
        for (String cardinal: cardinals) {
            try { fileHandler.saveMove(cardinal); } catch (Exception e) {
                fail("Cardinal move could not be saved to writer." + e);
            }
        }
    }

    @Test
    void openSaveFile() {
        FileHandler fileHandler = new FileHandler();

        fileHandler.openSaveFile();

        if (! fileHandler.getGameFile().getPath().equals("game.dat"))
            fail("game.dat could not be found.");
    }

    @Test
    void convertToPosition() {
        FileHandler fileHandler = new FileHandler();
        LascaBoard board = new LascaBoard();
        BoardSpace currentPlayer = BoardSpace.Red;
        BoardPosition startingPosition = new BoardPosition(1, 1);
        String[] cardinals = {"NE", "SE", "SW", "NW"};

        for (String cardinal: cardinals) {
            if (! fileHandler.convertToPosition(startingPosition, cardinal, board, currentPlayer).getClass().equals(BoardPosition.class))
                fail("A correctly formatted cardinal direction did not return a Board Position.");
        }

        if (! fileHandler.convertToPosition(startingPosition, "Not a cardinal", board, currentPlayer).getClass().equals(BoardPosition.class))
            fail("An improperly formatted cardinal direction was not handled correctly.");
    }
}