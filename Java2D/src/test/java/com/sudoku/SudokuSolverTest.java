package com.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SudokuSolverTest {

    private SudokuSolver underTest;
    private static final int[][] BOARD_INPUT = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3},
    };
    private static final int[][] BOARD_SOLVED = {
            {7, 3, 2, 4, 5, 8, 6, 1, 9},
            {9, 5, 6, 1, 7, 3, 8, 2, 4},
            {1, 8, 4, 6, 2, 9, 5, 3, 7},
            {8, 7, 1, 5, 6, 4, 3, 9, 2},
            {6, 4, 3, 8, 9, 2, 7, 5, 1},
            {2, 9, 5, 3, 1, 7, 4, 6, 8},
            {3, 2, 9, 7, 8, 6, 1, 4, 5},
            {4, 1, 8, 2, 3, 5, 9, 7, 6},
            {5, 6, 7, 9, 4, 1, 2, 8, 3},
    };

    @BeforeEach
    void setUp() {
        underTest = new SudokuSolver(9, 3);
    }

    @Test
    void solveBoard() {
        int[][] inBoard = copyOf(BOARD_INPUT);
        boolean actual = underTest.solveBoard(inBoard);
        assertTrue(compareEquals(BOARD_SOLVED, inBoard));
        assertTrue(actual);
    }

    @Test
    void whenIsNumberInRowValidOk() {
        int[] numbers = {1, 2};
        boolean[] expecteds = {false, true};
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            boolean expected = expecteds[i];
            int row = 0;
            boolean actual = underTest.isNumberInRow(
                    copyOf(BOARD_INPUT), number, row
            );
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenIsNumberInColValidOk() {
        int[] numbers = {1, 3};
        boolean[] expecteds = {true, false};
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            boolean expected = expecteds[i];
            int col = 0;
            boolean actual = underTest
                    .isNumberInCol(
                            copyOf(BOARD_INPUT), number, col
                    );
            assertEquals(expected, actual);
        }
    }

    @Test
    void whenIsNumberInBoxValidOk() {
        int[] numbers = {1, 3};
        boolean[] expecteds = {true, false};
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            boolean expected = expecteds[i];
            int row = 0;
            int col = 0;
            boolean actual = underTest
                    .isNumberInBox(
                            BOARD_INPUT, number,
                            row, col
                    );
            assertEquals(expected, actual);
        }
    }

    @Test
    void isValidPlacement() {
        int[] numbers = {1, 3};
        boolean[] expecteds = {false, true};
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            boolean expected = expecteds[i];
            int row = 0;
            int col = 0;
            boolean actual = underTest
                    .isValidPlacement(
                            copyOf(BOARD_INPUT), number,
                            row, col
                    );
            assertEquals(expected, actual);
        }
    }

    private boolean compareEquals(int[][] boardSolved, int[][] inBoard) {
        for (int i = 0; i < inBoard.length; i++) {
            for (int j = 0; j < inBoard[0].length; j++) {
                if (boardSolved[i][j] != inBoard[i][j]) {
                    System.out.println(String.format(
                            "Differs at row: %d, col: %d",
                            i, j));
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] copyOf(int[][] boardInput) {
        int[][] copyBoard =
                new int[boardInput.length][boardInput[0].length];
        for (int i = 0; i < boardInput.length; i++) {
            for (int j = 0; j < boardInput[0].length; j++) {
                copyBoard[i][j] = boardInput[i][j];
            }
        }
        return copyBoard;
    }
}