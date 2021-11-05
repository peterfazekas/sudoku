package hu.sudoku;

import hu.sudoku.controller.SudokuService;
import hu.sudoku.model.domain.Table;
import hu.sudoku.model.service.Console;
import hu.sudoku.model.service.DataApi;
import hu.sudoku.model.service.DataParser;
import hu.sudoku.model.service.FileReader;

import java.util.Scanner;

public class App {

    private SudokuService service;
    private final Console console;

    public App() {
        console = new Console(new Scanner(System.in));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("1. feladat");
        System.out.print("Adja meg a bemeneti fájl nevét! ");
        String filename = console.read();
        service = initSudokuService(filename);
        System.out.print("Adja meg egy sor számát! ");
        int row = console.readInt();
        System.out.print("Adja meg egy oszlop számát! ");
        int column = console.readInt();
        System.out.println("3. feladat");
        System.out.println("Az adott helyen szereplő szám: " + service.getValue(row, column));
        System.out.println("A hely a(z) " + service.getSubTable(row, column) + " résztáblázathoz tartozik.");
        System.out.println("4. feladat");
        System.out.println("Az üres helyek aránya: " + service.getEmptySpacePercentage() + "%");
        System.out.println("5. feladat");
        System.out.println(service.validateSteps());

    }

    private SudokuService initSudokuService(String filename) {
        DataApi dataApi = new DataApi(filename, new FileReader(), new DataParser());
        return new SudokuService(new Table(dataApi.getTable()), dataApi.getSteps());
    }


}
