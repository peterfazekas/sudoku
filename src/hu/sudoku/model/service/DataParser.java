package hu.sudoku.model.service;

import hu.sudoku.model.domain.Step;

import java.util.List;
import java.util.stream.Collectors;

public class DataParser {

    private static final String SEPARATOR = " ";

    public int[][] getTable(List<String> lines) {
        int[][] table = new int[9][9];
        for (int row = 0; row < 9; row++) {
            String line = lines.get(row);
            String[] items = line.split(SEPARATOR);
            for (int column = 0; column < 9; column++) {
                table[row][column] = Integer.parseInt(items[column]);
            }
        }
        return table;
    }

    public List<Step> getSteps(List<String> lines) {
        List<String> strings = lines.subList(9, lines.size());
        return strings.stream()
                .map(this::createStep)
                .collect(Collectors.toList());
    }

    private Step createStep(String line) {
        String[] s = line.split(SEPARATOR);
        int value = Integer.parseInt(s[0]);
        int row = Integer.parseInt(s[1]);
        int column = Integer.parseInt(s[2]);
        return new Step(value, row, column);
    }
}
