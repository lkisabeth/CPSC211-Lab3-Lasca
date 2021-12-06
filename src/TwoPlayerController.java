import java.util.Arrays;
import java.util.Scanner;

public class TwoPlayerController {
    private LascaBoard board;
    private BoardSpace currentPlayer;

    public static void main(String[] args) {
        TwoPlayerController controller = new TwoPlayerController();
        controller.go();
    }

    public void go() {
        // Create board
        board = new LascaBoard();
        // Create a view attached to the board
        GameView view = new GameView(board);

        // Create FileHandler
        FileHandler fileHandler = new FileHandler();
        // Create game.dat
        fileHandler.createNewSaveFile();

        // Set currentPlayer to Player 1 at start of new game
        currentPlayer = BoardSpace.Red;

        // while game is not over
        while (!board.winner()) {
            // display board
            view.displayBoard();

            // ask current player for the coordinates of the piece they would like to move.
            // start with the row
            view.askForRow(currentPlayer);
            // accept row input from player
            Scanner scan = new Scanner(System.in);
            int row = scan.nextInt();
            // now get the column
            view.askForColumn();;
            int column = scan.nextInt();
            // instantiate a board position with those coordinates
            BoardPosition startingPosition = new BoardPosition(row, column);

            // go ahead and jump to next line on scanner to avoid issues.
            scan.nextLine();

            // ask current player for the direction they want to move the chosen piece.
            view.askForMove();
            // accept input from player
            String moveAsCardinal = scan.nextLine().toUpperCase();

            // check to see if the move is in the correct format
            moveAsCardinal = this.checkMoveFormat(moveAsCardinal, view, scan);

            // write move to game.dat (Might add this functionality later. Not needed right now.)
            // fileHandler.saveMove(moveAsCardinal);

            // make that move on the board
            BoardPosition move = fileHandler.convertToPosition(moveAsCardinal, board, currentPlayer);
            board.movePlayer(startingPosition, currentPlayer, move);

            // switch to the next player
            switchPlayer();
        }

        // display board
        view.displayBoard();
        // display winner
        view.displayWinner(currentPlayer);

        // close game.dat
        fileHandler.closeWriter();
    }

    private String checkMoveFormat(String moveAsCardinal, GameView view, Scanner scan) {
        // Unlike Isola, players can only move diagonally in Lasca (much like Checkers)
        String[] validCardinalInputs = {"NE", "SE", "SW", "NW"};
        while (! Arrays.asList(validCardinalInputs).contains(moveAsCardinal)) {
            view.tryAgain();
            moveAsCardinal = scan.nextLine().toUpperCase();
        }
        return moveAsCardinal;
    }

    private void switchPlayer() {
        if (currentPlayer == BoardSpace.Red) {
            currentPlayer = BoardSpace.Black;
        } else {
            currentPlayer = BoardSpace.Red;
        }
    }
}