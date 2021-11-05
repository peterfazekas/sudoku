package hu.sudoku.model.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Table {

    private final int[][] table;

    public Table(int[][] table) {
        this.table = table;
    }

    public int getValue(int row, int column) {
        return table[row - 1][column - 1];
    }

    public boolean isEmpty(int row, int column) {
        return getValue(row, column) == 0;
    }

    public boolean hasNumberInRow(int number, int row) {
        return getNumbers(i -> table[row - 1][i]).contains(number);
    }

    public boolean hasNumberInColumn(int number, int column) {
        return getNumbers(i -> table[i][column - 1]).contains(number);
    }

    public boolean hasNumberInSubTable(int number, int row, int column) {
        return getNumbers(row, column).contains(number);
    }

    public int getSubTable(int row, int column) {
        int actRow = row / 3;
        int actColumn = column / 3;
        return actRow * 3 + actColumn + 1;
    }

    public int countZeros() {
        int count = 0;
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                if (isEmpty(row, column)) {
                    count++;
                }
            }
        }
        return count;
    }

    private Set<Integer> getNumbers(IntUnaryOperator mapValue) {
        return IntStream.range(0, 9)
                .map(mapValue)
                .boxed()
                .collect(Collectors.toSet());
    }

    private Set<Integer> getNumbers(int row, int column) {
        Set<Integer> numbers = new HashSet<>();
        int rowStart = ((row - 1) / 3) * 3;
        int columnStart = ((column - 1) / 3) * 3;
        for (int actRow = rowStart; actRow < rowStart + 3; actRow++) {
            for (int actColumn = columnStart; actColumn < columnStart + 3; actColumn++) {
                numbers.add(table[actRow][actColumn]);
            }
        }
        return numbers;
    }
}
