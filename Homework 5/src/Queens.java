
public class Queens {
    // number of solutions to be displayed.

    private int numberOfSolution = 1;

    // number of calls to placeQueens.
    private int callsToplaceQueens = 0;

    // number of calls to isUnderAttack.
    private int callsToisUnderAttack = 0;

    // number of backtrack.
    private int backTrack = 0;

    // squares per row or column
    public static final int BOARD_SIZE = 8;

    // used to indicate an empty square
    public static final int EMPTY = 0;

    // used to indicate square contains a queen
    public static final int QUEEN = 1;
    private int board[][]; // chess board

    public Queens() {
        // -------------------------------------------------
        // Constructor: Creates an empty square board.
        // -------------------------------------------------
        board = new int[BOARD_SIZE][BOARD_SIZE];
        board[0][0] = QUEEN;
    } // end constructor

    public void clearBoard() {
        // -------------------------------------------------
        // Clears the board.
        // Precondition: None.
        // Postcondition: Sets all squares to EMPTY.
        // -------------------------------------------------
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
    } // end clearBoard

    public void displayBoard() {
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
        // |   |   |   |   | Q |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   |   |   | Q |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   | Q |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   | Q |   |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   |   |   |   | Q |   |
        // +---+---+---+---+---+---+---+---+
        // |   | Q |   |   |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        // |   |   |   | Q |   |   |   |   |
        // +---+---+---+---+---+---+---+---+
        //
        // -------------------------------------------------
        System.out.println("Solution Number: " + numberOfSolution
                + "\nThe number of calls to isUnderAttack is: " + callsToisUnderAttack
                + "\nThe number of calls to placeQueens is: " + callsToplaceQueens
                + "\nThe number of backtracks is: " + backTrack);
        System.out.println("+---+---+---+---+---+---+---+---+");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print("|");
                if (board[i][j] == QUEEN) {
                    System.out.print(" Q ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("|\n");
            System.out.println("+---+---+---+---+---+---+---+---+");
            numberOfSolution++;
        }
    } // end displayBoard

    public boolean placeQueens(int column) {
        // -------------------------------------------------
        // Places queens in columns of the board beginning
        // at the column specified.
        // Precondition: Queens are placed correctly in
        // columns 1 through column-1.
        // Postcondition: If a solution is found, each
        // column of the board contains one queen and method
        // returns true; otherwise, returns false (no
        // solution exists for a queen anywhere in column
        // specified).
        // -------------------------------------------------
        callsToplaceQueens++;
        if (column >= BOARD_SIZE) {
            return true; // base case
        } else {
            boolean queenPlaced = false;
            // number of square in column
            for (int row = 0; row < BOARD_SIZE; row++) {
                // if square can be attacked
                if (!isUnderAttack(column, row)) {
                    setQueen(column, row);// place queen
                    // backtrack: remove queen placed earlier
                    // and try next square in column
                    if (!placeQueens(column + 1)) {
                        removeQueen(column, row);
                    } // end if.
                    else {
                        queenPlaced = true;
                        return queenPlaced;
                    } // end else.
                } // end if.
            } // end for loop.
            return queenPlaced;
        } // end else.
    } // end placeQueens

    private void setQueen(int column, int row) {
        // --------------------------------------------------
        // Sets a queen at square indicated by row and
        // column.
        // Precondition: None.
        // Postcondition: Sets the square on the board in a
        // given row and column to QUEEN.
        // --------------------------------------------------
        board[column][row] = QUEEN;
    } // end setQueen

    private void removeQueen(int column, int row) {
        // --------------------------------------------------
        // Removes a queen at square indicated by row and
        // column.
        // Precondition: None.
        // Postcondition: Sets the square on the board in a
        // given row and column to EMPTY.
        // --------------------------------------------------
        backTrack++;
        board[column][row] = EMPTY;
    } // end removeQueen

    private boolean isUnderAttack(int row, int column) {
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
        // -------------------------------------------
        callsToisUnderAttack++;
        int i, j;
        // possible horizontal attack.
        for (i = 0; i < row; i++) {
            if (board[i][column] == 1) {
                return true;
            }
        }
        i = row - 1;
        j = column - 1;
        // possible upper left horizontal attack.
        while ((i >= 0) && (j >= 0)) {
            if (board[i--][j--] == 1) {
                return true;
            }// ends if.
        } // ends while.
        i = row - 1;
        j = column + 1;
        // possible lower left horizontal attack.
        while ((i >= 0) && (j < BOARD_SIZE)) {
            if (board[i--][j++] == 1) {
                return true;
            } // ends if.
        } // ends while.
        return false;
    } // end isUnderAttack

    @SuppressWarnings("unused")
    private int index(int number) {
        // --------------------------------------------------
        // Returns the array index that corresponds to
        // a row or column number.
        // Precondition: 1 <= number <= BOARD_SIZE.
        // Postcondition: Returns adjusted index value.
        // --------------------------------------------------
        int index;
        if (number > 0 && number <= BOARD_SIZE) {
            index = board[number][number];
        } else {
            System.out.println("Number does not exsist.");
            index = -1;
        }
        return index;
    } // end index
} // end Queens
