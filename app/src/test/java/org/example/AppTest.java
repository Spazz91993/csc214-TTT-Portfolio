package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private Interface board;

    @BeforeEach
    public void setup() {
        board = new Interface();
    }

    @Test
    public void testBoardInitialization() {
        char[] expectedBoard = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < 9; i++) {
            assertEquals(expectedBoard[i], board.sQ[i], "Board not initialized correctly.");
        }
    }

    @Test
    public void testValidMove() {
        board.sQ[0] = 'X';
        assertFalse(board.isValidMove(1), "The move should be invalid for an already taken spot.");
        assertTrue(board.isValidMove(2), "The move should be valid for an empty spot.");
    }

    @Test
    public void testIsWinnerHorizontal() {
        board.sQ[0] = 'X';
        board.sQ[1] = 'X';
        board.sQ[2] = 'X';
        assertTrue(board.isWinner(), "The game should detect a winner with a horizontal line.");
    }

    @Test
    public void testIsWinnerVertical() {
        board.sQ[0] = 'O';
        board.sQ[3] = 'O';
        board.sQ[6] = 'O';
        assertTrue(board.isWinner(), "The game should detect a winner with a vertical line.");
    }

    @Test
    public void testIsWinnerDiagonal() {
        board.sQ[0] = 'X';
        board.sQ[4] = 'X';
        board.sQ[8] = 'X';
        assertTrue(board.isWinner(), "The game should detect a winner with a diagonal line.");
    }
}