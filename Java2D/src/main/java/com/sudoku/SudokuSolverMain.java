package com.sudoku;

public class SudokuSolverMain {
    public static void main(String[] args) {
        String path = "Java2D/src/main/resources/solver/TestInput.txt";

        SolverReader reader = new SolverReader();
        int[][] board = reader.read(path);

        SudokuSolver solver = new SudokuSolver(9, 3);

        if (solver.solveBoard(board)) {
            println("Board Solved!");
        } else {
            println("Board NOT Solved!");
        }

        BoardDisplayer displayer = new BoardDisplayer();
        println(displayer.display(board));
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
