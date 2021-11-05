package hu.sudoku.model.domain;

public class Step {

    private final int value;
    private final int row;
    private final int column;

    public Step(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
    }

    public int getValue() {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
