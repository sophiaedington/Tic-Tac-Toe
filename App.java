import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe(scanner);
        ticTacToe.startGame();

        scanner.close();
    }
}