package com.sudoku;

public class BoardDisplayer {

    public String display(int[][] board) {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            int[] boardRow = board[row];
            buf.append(String.format("[%03d] ", row));
            for (int col = 0; col < boardRow.length; col++) {
                buf.append(String.format("%03d ", board[row][col]));
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
