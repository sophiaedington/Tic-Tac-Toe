import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public TicTacToe(Scanner scanner) {
        gameBoard = new GameBoard();
        this.scanner = scanner;
        System.out.println("    Tic-Tac-Toe!");

        System.out.print("Player 1's Name: ");
        String name1 = scanner.nextLine().trim();
        if (name1.isEmpty()) {
            name1 = "Player 1";
        }

        System.out.print("Player 2's Name: ");
        String name2 = scanner.nextLine().trim();
        if (name2.isEmpty()) {
            name1 = "Player 2";
        }

        player1 = new Player(name1, 'X');
        player2 = new Player(name2, 'O');

        currentPlayer = player1;
        gameBoard = new GameBoard();
    }

    public void startGame() {
        boolean gameEnded = false;
        while (!gameEnded) {
            gameBoard.printBoard();
            promptPlayerMove();
            if (gameBoard.checkWin()) {
                gameBoard.printBoard();
                System.out.println(currentPlayer.getName() +" wins!");
                gameEnded = true;
            } else if (gameBoard.isFull()) {
                gameBoard.printBoard();
                System.out.println("The game ended in a tie!");
                gameEnded = true;
            } else {
                switchPlayers();
            }
        }

        if (playAgain()) {
            gameBoard = new GameBoard();
            currentPlayer = player1;
            startGame();
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private void promptPlayerMove() {
        boolean validMove = false;
        
        System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") it's your turn.");

        while (!validMove) {
            int[] move = requestMove();
            int row = move[0];
            int col = move[1];

            validMove = attemptMove(row, col);

            if (!validMove) {
            System.out.println("Invalid move, try again.");
            }
        }
    }

    private int[] requestMove() {
        int row = -1;
        int col = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter row (1-3): ");
            try {
                row = scanner.nextInt() - 1;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Enter a number between 1 and 3: ");
                scanner.nextLine();
                continue;
            }

            System.out.print("Enter column (1-3): ");
            try {
                col = scanner.nextInt() - 1;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Enter a number between 1 and 3: ");
                scanner.nextLine();
                continue;
            }

            if (row < 0 || row >= GameBoard.SIZE || col < 0 || col >= GameBoard.SIZE) {
                System.out.println("The row and column numbers must be between 1 and 3. Try again.");
            } else {
                validInput = true;
            }
        }

        return new int[]{row, col};
    }
    
    private boolean attemptMove(int row, int col) {
        if (row < 0 || row >= GameBoard.SIZE || col < 0 || col >= GameBoard.SIZE) {
            return false;
        }
        
        return gameBoard.makeMove(row, col, currentPlayer.getSymbol());
    }
    
    private void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private boolean playAgain() {
        System.out.print("Play again? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        return answer.equals("y");
    }
}
