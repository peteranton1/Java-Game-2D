package com.sudoku;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SolverReader {

    public int[][] read(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            StringIntMapper mapper = new StringIntMapper();
            return mapper.linesToIntArray(lines);
        } catch(Exception e){
            throw new SolverException(path, e);
        }
    }
}
