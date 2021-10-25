package com.sudoku;

public class SudokuSolverMain {
    public static void main(String[] args) {
        String path = "Java2D/src/main/resources/solver/TestInput.txt";

        SolverReader reader = new SolverReader();
        int[][] board = reader.read(path);

        int boardSize = 9;
        int boxSize = 3;
        SudokuSolver solver = new SudokuSolver(boardSize, boxSize);

        if (solver.solveBoard(board)) {
            println("Board Solved!");
        } else {
            println("Board NOT Solved!");
        }

        BoardDisplayer displayer = new BoardDisplayer(boardSize, boxSize);
        println(displayer.display(board));
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
