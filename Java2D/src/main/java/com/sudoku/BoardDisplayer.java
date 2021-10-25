package com.sudoku;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardDisplayer {

    private static final char[] CHAR_TABLE =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    .toCharArray();

    private int boardSize;
    private int boxSize;

    public String display(int[][] board) {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            if(row % boxSize == 0) {
                displayLineBreak(buf);
            }
            int[] boardRow = board[row];
            buf.append(String.format("[%02d] ", row));
            for (int col = 0; col < boardRow.length; col++) {
                if(col % boxSize == 0) {
                    buf.append("| ");
                }
                buf.append(String.format("%s ",
                        lookupValue(board[row][col])));
            }
            buf.append("| ");
            buf.append("\n");
        }
        displayLineBreak(buf);
        return buf.toString();
    }

    private String lookupValue(int i) {
        if(i >= 0 && i < CHAR_TABLE.length) {
            return ""+ CHAR_TABLE[i];
        }
        return "?";
    }

    private void displayLineBreak(StringBuilder buf) {
        buf.append("---- ");
        for (int col = 0; col < boardSize; col++) {
            if(col % boxSize == 0) {
                buf.append("| ");
            }
            buf.append("- ");
        }
        buf.append("| ");
        buf.append("\n");
    }
}
