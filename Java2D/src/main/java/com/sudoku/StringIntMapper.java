package com.sudoku;

import java.util.ArrayList;
import java.util.List;

public class StringIntMapper {

    public int[][] linesToIntArray(List<String> lines) {
        int[][] outArray = new int[lines.size()][];
        for (int row = 0; row < lines.size(); row++) {
            outArray[row] = lineToIntArray(lines.get(row));
        }
        return outArray;
    }

    int[] lineToIntArray(String line) {
        List<Integer> out = new ArrayList<>();
        for(char ch: line.toCharArray()){
            if(Character.isDigit(ch)) {
                out.add(ch - '0');
            }
        }
        return listToIntArray(out);
    }

    int[] listToIntArray(List<Integer> inList) {
        int[] out = new int[inList.size()];
        for(int i=0;i<out.length;i++){
            out[i] = inList.get(i);
        }
        return out;
    }

}
