
public class QueensExtraCredit {
    // number of solutions to be displayed.

    private static int numberOfSolution = 1;

    // number of calls to placeQueens.
    private static int callsToplaceQueens = 0;

    // number of calls to isUnderAttack.
    private static int callsToisUnderAttack = 0;
    // squares per row or column.
    public static final int BOARD_SIZE = 8;

    private static int board[];//chess board.

    public QueensExtraCredit(int boardSize) {
        // -------------------------------------------------
        // Constructor: Creates an empty square board.
        // -------------------------------------------------
        board = new int[boardSize];
    }// end constructor.

    public static boolean isUnderAttack(int row, int column) {
        // --------------------------------------------------
        // Determines whether the square on the board at a
        // given row and column is under attack by any queens
        // in the columns 1 through column-1.
        // Precondition: Each column between 1 and column-1
        // has a queen placed in a square at a specific row.
        // None of these queens can be attacked by any other
        // queen.
        // Postcondition: If the designated square is under
        // attack, returns true; otherwise, returns false.
        // --------------------------------------------------
        callsToisUnderAttack++;
        for (int i = 0; i < row; i++) {
            if (board[i] == column || (i - row) == (board[i] - column)
                    || (i - row) == (column - board[i])) {
                return true;
            }
        }
        return false;
    } // end isUnderAttack

    public static void displayBoard() {
        // -------------------------------------------------
        // Displays the board.
        // Precondition: None.
        // Postcondition: Board is displayed as follows
        // with a blank indicating an EMPTY square, and a Q
        // is a square containing a queen.
        //
        // +---+---+---+---+---+---+---+---+
        // | Q |   |   |   |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   |   | Q |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   | Q |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   |   |   | Q |
        // +---+---+---+---+---+---+---+---+
        // |   | Q |   |   |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   | Q |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   | Q |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   | Q |   |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        //
        // -------------------------------------------------
        System.out.println("Solution Number: " + numberOfSolution
                + "\nThe number of calls to isUnderAttack is: " + callsToisUnderAttack
                + "\nThe number of calls to placeQueens is: " + callsToplaceQueens);
        System.out.println("+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("|");
                if (board[i] == j) {
                    System.out.print(" Q ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("|\n");
            System.out.println("+---+---+---+---+---+---+---+---+");
        }
        System.out.println();
        numberOfSolution++;
    }// end displayBoard.

    public void placeQueens(int column) {
        // -------------------------------------------------
        // Places queens in columns of the board beginning
        // at the column specified.
        // Precondition: Queens are placed correctly in
        // columns 0 through column-1.	
        // -------------------------------------------------
        callsToplaceQueens++;
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (!isUnderAttack(column, row)) {
                board[column] = row;
                if (column == BOARD_SIZE - 1) {
                    displayBoard();
                } else {
                    placeQueens(column + 1);
                } // ends else.
            }// ends if.
        }// end for loop.
    }// end placeQueens

    public static void main(String[] args) {
        QueensExtraCredit Q = new QueensExtraCredit(8);
        Q.placeQueens(0);
    }
}
