package com.sudoku;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SudokuSolver {

    private int boardSize;
    private int boxSize;

    boolean solveBoard(int[][] board) {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if(board[row][col] == 0){
                    for(int numberToTry = 1; numberToTry <= boardSize; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, col)){
                            board[row][col] = numberToTry;
                            // recursive
                            if(solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    boolean isNumberInRow(int[][] board, int number, int row) {
        for (int col = 0; col < boardSize; col++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    boolean isNumberInCol(int[][] board, int number, int col) {
        for (int row = 0; row < boardSize; row++) {
            if (board[row][col] == number) {
                return true;
            }
        }
        return false;
    }

    boolean isNumberInBox(int[][] board, int number, int inRow, int inCol) {
        int localBoxRow = inRow - inRow % boxSize;
        int localBoxCol = inCol - inCol % boxSize;

        for (int row = localBoxRow; row < localBoxRow + boxSize; row++) {
            for (int col = localBoxCol; col < localBoxCol + boxSize; col++) {
                if (board[row][col] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isValidPlacement(int[][] board, int number, int inRow, int inCol) {
        return !isNumberInRow(board, number, inRow) &&
                !isNumberInCol(board, number, inCol) &&
                !isNumberInBox(board, number, inRow, inCol);
    }
}
