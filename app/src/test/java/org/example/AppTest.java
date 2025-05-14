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
        for (int i = 0; i < 9; i++) {
            assertEquals((char) ('1' + i), board.sQ[i], "Board not initialized correctly at position " + i);
        }
    }

@Test
public void testValidMove() {
    assertEquals('1', board.sQ[0], "Position 1 should initially be available.");
    board.makeMove(1, 'X');
    assertTrue(board.sQ[0] == 'X', "Position 1 should now be taken by X.");
    assertTrue(board.sQ[1] != 'X' && board.sQ[1] != 'O', "Position 2 should still be available.");
}



    @Test
    public void testIsWinnerHorizontal() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertTrue(board.isWinner(), "The game should detect a winner with a horizontal line.");
    }

    @Test
    public void testIsWinnerVertical() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertTrue(board.isWinner(), "The game should detect a winner with a vertical line.");
    }

    @Test
    public void testIsWinnerDiagonal() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertTrue(board.isWinner(), "The game should detect a winner with a diagonal line.");
    }

    @Test
    public void testComputerFirstMoveCorner() {
        OpportunisticComputer computer = new OpportunisticComputer('X');
        int move = computer.makeMove(board.sQ, 0);
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9, "First move should be one of the corners.");
    }

    @Test
    public void testComputerSecondMoveCenter() {
        OpportunisticComputer computer = new OpportunisticComputer('X');
        board.sQ[0] = 'O';
        int move = computer.makeMove(board.sQ, 1);
        assertEquals(5, move, "Second move should be center if available.");
    }

    @Test
    public void testComputerCanWin() {
        OpportunisticComputer computer = new OpportunisticComputer('X');
        board.sQ[0] = 'X';
        board.sQ[1] = 'X';
        board.sQ[2] = '3';
        int move = computer.makeMove(board.sQ, 4);
        assertEquals(3, move, "Computer should choose winning move.");
    }

    @Test
    public void testComputerBlocksOpponent() {
        OpportunisticComputer computer = new OpportunisticComputer('X');
        board.sQ[3] = 'O';
        board.sQ[4] = 'O';
        board.sQ[5] = '6';
        int move = computer.makeMove(board.sQ, 4);
        assertEquals(6, move, "Computer should block opponent's winning move.");
    }
}