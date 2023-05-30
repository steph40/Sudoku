public class Move {
    private int line;
    private int column;
    private int value;

    public Move(int line, int column, int value) {
        this.line = line;
        this.column = column;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }
    
}
