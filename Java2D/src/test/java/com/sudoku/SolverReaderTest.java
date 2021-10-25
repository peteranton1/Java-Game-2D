package com.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverReaderTest {

    private SolverReader underTest;

    @BeforeEach
    void setUp(){
        underTest = new SolverReader();
    }

    @Test
    void shouldReadTestFileOk() {
        String path = "src/test/resources/solver/TestInput.txt";
        int[][] expected2D = {
                {7,0,2,0,5,0,6,0,0},
                {0,0,0,0,0,3,0,0,0},
                {1,0,0,0,0,9,5,0,0},
                {8,0,0,0,0,0,0,9,0},
                {0,4,3,0,0,0,7,5,0},
                {0,9,0,0,0,0,0,0,8},
                {0,0,9,7,0,0,0,0,5},
                {0,0,0,2,0,0,0,0,0},
                {0,0,7,0,4,0,2,0,3},
        };
        int[][] actual2D = underTest.read(path);
        assertEquals(expected2D.length,actual2D.length);
        for(int row=0;row<expected2D.length;row++){
            int[] expected1D = expected2D[row];
            int[] actual1D = actual2D[row];
            assertEquals(expected1D.length,actual1D.length);
            for(int col=0;col<expected1D.length;col++){
                assertEquals(expected1D[col],actual1D[col]);
            }
        }
    }
}