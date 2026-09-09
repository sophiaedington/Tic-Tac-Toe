public class GameBoard {
    private char[][] board;

    // 'final' means that SIZE is a constant and cannot be changed after initialization
    // Java convention: Constants in Java are written in all upper-case letters
    // The size indicates the gameboard size (3x3)
    public static final int SIZE = 3;

    public GameBoard() {
        // Creates new game board
        board = new char[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = '-';
            }
        }
    }

    // makeMove marks a non-empty cell with the respective symbol
    public boolean makeMove(int row, int col, char symbol) {
        // Checks if cell is occupied
        if (board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }

        // Rejects players move if space is occupied
        return false;
    }

    // Checks if a player has placed three symbols in a row, column, or diagonal
    // If yes, that player has won
    public boolean checkWin() {
        // Hint: Define additional methods to keep your code organized and readable!        
        return checkRow() || checkColumn() || checkDiagonal();
    }

    // Checks for a winning row
    private boolean checkRow() {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }

        return false;
    }

    // Checks for a winning column
    private boolean checkColumn() {
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }

        return false;
    }

    // Checks for a winning diagonal
    private boolean checkDiagonal() {
        // Checks top lef to bottom right
        boolean diagonalLeft = board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2];

        // Checks top right to bottom left
        boolean diagonalRight = board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0];

        return diagonalLeft || diagonalRight;
    }

    // Checks if the gameboard is full but no player won
    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == '-') {
                    // Returns if an empty cell was found
                    return false;
                }
            }
        }
        // Returns if no empty cells were found
        return true;
    }

    public void printBoard() {
        System.out.println();
        String line = "|---|---|---|";

        // Prints board
        for (int row = 0; row < SIZE; row++) {
            String c0;
            if (board[row][0] == '-') {
                c0 = " ";
            } else {
                c0 = String.valueOf(board[row][0]);
            }

            String c1;
            if (board[row][1] == '-') {
                c1 = " ";
            } else {
                c1 = String.valueOf(board[row][1]);
            }

            String c2;
            if (board[row][2] == '-') {
                c2 = " ";
            } else {
                c2 = String.valueOf(board[row][2]);
            }
            System.out.println("| " + c0 + " | " + c1 + " | " + c2 + " |");

            if (row < SIZE - 1) {
                System.out.println(line);
            }
        }

        System.out.println();
    }
}

/* 
Board Template
      1   2   3
  1 |   |   |   |
    |---|---|---|
  2 |   |   |   |
    |---|---|---|
  3 |   |   |   |

*/