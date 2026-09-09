public class Player {
    // Variables
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    // Getters
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}