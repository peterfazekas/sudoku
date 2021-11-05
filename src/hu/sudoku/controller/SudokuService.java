package hu.sudoku.controller;

import hu.sudoku.model.domain.Step;
import hu.sudoku.model.domain.Table;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuService {

    private static final String DELIMITER = "\n";
    private final Table table;
    private final List<Step> steps;

    public SudokuService(Table table, List<Step> steps) {
        this.table = table;
        this.steps = steps;
    }

    public String getValue(int row, int column) {
        return table.isEmpty(row, column)
                ? "„Az adott helyet még nem töltötték ki."
                : String.valueOf(table.getValue(row, column));
    }

    public int getSubTable(int row, int column) {
        return table.getSubTable(row, column);
    }

    public String getEmptySpacePercentage() {
        double percent = table.countZeros() * 100.0 / (9 * 9);
        return String.format("%4.1f", percent);
    }

    public String validateSteps() {
        return steps.stream()
                .map(this::printMessage)
                .collect(Collectors.joining(DELIMITER));
    }

    private String printMessage(Step step) {
        return String.format("A kiválasztott sor: %d oszlop: %d a szám: %d\n%s\n",step.getRow(), step.getColumn(), step.getValue(), validateStep(step));
    }

    private String validateStep(Step step) {
        String result;
        if (!table.isEmpty(step.getRow(), step.getColumn())) {
            result = "A helyet már kitöltötték";
        } else if (table.hasNumberInRow(step.getValue(), step.getRow())) {
            result = "Az adott sorban már szerepel a szám";
        } else if (table.hasNumberInColumn(step.getValue(), step.getColumn())) {
            result = "Az adott oszlopban már szerepel a szám";
        } else if (table.hasNumberInSubTable(step.getValue(), step.getRow(), step.getColumn())) {
            result = "Az adott résztáblázatban már szerepel a szám";
        } else {
            result = "A lépés megtehető";
        }
        return result;
    }
}
