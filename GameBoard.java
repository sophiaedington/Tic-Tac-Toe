public class GameBoard {
    private char[][] board;
    
    public static final int SIZE = 3;

    public GameBoard() {
        board = new char[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = '-';
            }
        }
    }

    public boolean makeMove(int row, int col, char symbol) {
        if (board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }

        return false;
    }

    public boolean checkWin() {
        return checkRow() || checkColumn() || checkDiagonal();
    }

    private boolean checkRow() {
        for (int row = 0; row < SIZE; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }

        return false;
    }

    private boolean checkColumn() {
        for (int col = 0; col < SIZE; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonal() {
        boolean diagonalLeft = board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2];
        boolean diagonalRight = board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0];

        return diagonalLeft || diagonalRight;
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }

        return true;
    }

    public void printBoard() {
        System.out.println();
        String line = "|---|---|---|";

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
