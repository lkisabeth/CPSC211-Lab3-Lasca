public class GameView {
    private LascaBoard board;

    public GameView(LascaBoard board) { this.board = board; }

    public void displayBoard() {
        // Display the board
        System.out.println();
        System.out.println(" ****** LASCA ******");
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                switch (board.get(i, j)) {
                    case Available -> System.out.print(" - ");
                    case Red -> System.out.print(" R ");
                    case Black -> System.out.print(" B ");
                }
            }
            System.out.println();
        }
        System.out.println(" ****** BOARD ******");
        System.out.println();
    }

    public void askForRow(BoardSpace currentPlayer) {
        // Display Current Player
        System.out.println("** " + currentPlayer.toString() + "'s turn! **");
        System.out.println();
        // Display prompt for user input
        System.out.println("Please input the board position of the piece you would like to move.");
        System.out.println();
        System.out.println("First, which row is your piece in?");
        System.out.println("Choose a number 1-7 (top to bottom)");
        System.out.print("Type your choice here: ");
    }

    public void askForColumn() {
        // Display prompt for user input
        System.out.println();
        System.out.println("Now, which column is your piece in?");
        System.out.println("Choose a number 1-7 (left to right)");
        System.out.print("Type your choice here: ");
    }

    public void askForMove() {
        // Display prompt for user input
        System.out.println();
        System.out.println("Please enter the direction you would like to move the piece.");
        System.out.println("Moves should be represented by the cardinal direction in which you wish to move. You can only move diagonally in Lasca (much like Checkers)");
        System.out.println("Choose from: [ NE, SE, SW, NW ]");
        System.out.print("Type your choice here: ");
    }

    public void tryAgain() {
        System.out.println();
        System.out.println("The move you entered wasn't in the correct form. Please try again.");
        System.out.println("Choose from: [ NE, SE, SW, NW ]");
        System.out.print("Type your choice here: ");
    }

    public void displayWinner(BoardSpace currentPlayer) {
        System.out.println("*** " + currentPlayer.toString() + " Wins! ***");
    }
}