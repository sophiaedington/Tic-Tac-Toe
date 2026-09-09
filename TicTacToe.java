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
        // Read player names
        System.out.println("    Tic-Tac-Toe!");

        // Player 1
        System.out.print("Player 1's Name: ");
        String name1 = scanner.nextLine().trim();
        if (name1.isEmpty()) {
            name1 = "Player 1";
        }

        // Player 2
        System.out.print("Player 2's Name: ");
        String name2 = scanner.nextLine().trim();
        if (name2.isEmpty()) {
            name1 = "Player 2";
        }

        // Players
        player1 = new Player(name1, 'X');
        player2 = new Player(name2, 'O');

        // Player1 starts the game
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

        // After game ends plays again if player answered yes
        if (playAgain()) {
            gameBoard = new GameBoard();
            currentPlayer = player1;
            startGame();
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    // Prompts the player to place a move and checks for its validity
    private void promptPlayerMove() {
        boolean validMove = false;
        // Ask the player to place a move
        // Check whether the move is valid, if not, ask the player to place a move again
        // If the player's move is valid, the move is placed on the gameBoard
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

    // Read a move from the user as two integers: row and column
    private int[] requestMove() {
        // Prompt the user until a valid row and column have been entered
        // Also apply proper error handling: non-integer input; keep prompting until the user enters two numbers
        int row = -1;
        int col = -1;
        boolean validInput = false;

        while (!validInput) {
            // Check for valid input in for the row
            System.out.print("Enter row (1-3): ");
            try {
                row = scanner.nextInt() - 1;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Enter a number between 1 and 3: ");
                scanner.nextLine();
                continue;
            }

            // Check for valid input in for the column
            System.out.print("Enter column (1-3): ");
            try {
                col = scanner.nextInt() - 1;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Enter a number between 1 and 3: ");
                scanner.nextLine();
                continue;
            }

            // Checks bounds for row and column
            if (row < 0 || row >= GameBoard.SIZE || col < 0 || col >= GameBoard.SIZE) {
                System.out.println("The row and column numbers must be between 1 and 3. Try again.");
            } else {
                // If the numbers are in the range the loop ends
                validInput = true;
            }
        }

        return new int[]{row, col};
    }

    // Validate the move (bounds check) and attempts to place it on the board
    // true if the move is successfully placed; false otherwise
    private boolean attemptMove(int row, int col) {
        // Bounds check (move within GameBoard)
        if (row < 0 || row >= GameBoard.SIZE || col < 0 || col >= GameBoard.SIZE) {
            return false;
        }

        // Delegate to GameBoard for "cell occupied?" logic
        return gameBoard.makeMove(row, col, currentPlayer.getSymbol());
    }

    // Switch players
    // Sets the player that is not the currentPlayer as currentPlayer
    private void switchPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    // Asks player to play again
    private boolean playAgain() {
        System.out.print("Play again? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        return answer.equals("y");
    }
}