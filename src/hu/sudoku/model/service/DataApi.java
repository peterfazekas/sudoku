package hu.sudoku.model.service;

import hu.sudoku.model.domain.Step;

import java.util.List;

public class DataApi {

    private final FileReader fileReader;
    private final DataParser dataParser;
    private final List<String> lines;

    public DataApi(String filename, FileReader fileReader, DataParser dataParser) {
        this.fileReader = fileReader;
        this.dataParser = dataParser;
        lines = fileReader.read(filename);
    }

    public int[][] getTable() {
        return dataParser.getTable(lines);
    }

    public List<Step> getSteps() {
        return dataParser.getSteps(lines);
    }
}
