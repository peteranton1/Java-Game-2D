package com.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringIntMapperTest {

    private StringIntMapper underTest ;

    @BeforeEach
    void setUp() {
        underTest = new StringIntMapper();
    }

    @Test
    void whenLinesToIntArrayValidOk() {
        List<String> lines = Arrays.asList(
                "123|456|789",
                "123|456|789",
                "123|456|789"
        );
        int[][] expected = {
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,5,6,7,8,9},
        };

        int[][] actual = underTest.linesToIntArray(lines);

        assertEquals(expected.length, actual.length);
        for(int i=0;i<expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length);
            for(int j=0;j<expected.length; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }

    @Test
    void whenLineToIntArrayValidOk() {
        String[] cases = {
                "12|34",
                "123|456|789",
                "1234|1234|1234|1234",
        };
        int[][] expecteds = {
                {1,2,3,4},
                {1,2,3,4,5,6,7,8,9},
                {1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,},
        };
        for(int caseNo=0;caseNo<cases.length;caseNo++){
            String item = cases[caseNo];
            int[] expected = expecteds[caseNo];
            int[] actual = underTest.lineToIntArray(item);

            assertEquals(expected.length, actual.length);
            for(int i=0;i<expected.length; i++) {
                assertEquals(expected[i], actual[i]);
            }
        }
    }
}