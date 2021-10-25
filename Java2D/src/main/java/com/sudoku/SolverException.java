package com.sudoku;

public class SolverException extends RuntimeException {
    public SolverException() {
    }

    public SolverException(String message) {
        super(message);
    }

    public SolverException(String message, Throwable cause) {
        super(message, cause);
    }

    public SolverException(Throwable cause) {
        super(cause);
    }
}
