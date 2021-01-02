package jamesw1892.noughtsandcrosses;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AppTest {

    @Test
    public void draw3() {

        Board board = new Board();
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 0));
        assertNull(board.place(Piece.CROSS, 2, 0));
        assertNull(board.place(Piece.CROSS, 0, 1));
        assertNull(board.place(Piece.CROSS, 1, 1));
        assertNull(board.place(Piece.NOUGHT, 2, 1));
        assertNull(board.place(Piece.NOUGHT, 0, 2));
        assertNull(board.place(Piece.CROSS, 1, 2));
        assertEquals(Piece.BLANK, board.place(Piece.CROSS, 2, 2));
    }

    @Test
    public void row3() {

        Board board = new Board();
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 0));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 2, 0));
    }

    @Test
    public void column3() {

        Board board = new Board();
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 0, 1));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 0, 2));
    }

    @Test
    public void leadingDiagonal3() {

        Board board = new Board();
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 1));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 2, 2));
    }

    @Test
    public void trailingDiagonal3() {

        Board board = new Board();
        assertNull(board.place(Piece.NOUGHT, 2, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 1));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 0, 2));
    }

    @Test
    public void draw5() {

        Board board = new Board(5);
        assertNull(board.place(Piece.CROSS, 0, 0));
        assertNull(board.place(Piece.CROSS, 1, 0));
        assertNull(board.place(Piece.NOUGHT, 2, 0));
        assertNull(board.place(Piece.NOUGHT, 3, 0));
        assertNull(board.place(Piece.CROSS, 4, 0));
        assertNull(board.place(Piece.NOUGHT, 0, 1));
        assertNull(board.place(Piece.CROSS, 1, 1));
        assertNull(board.place(Piece.CROSS, 2, 1));
        assertNull(board.place(Piece.CROSS, 3, 1));
        assertNull(board.place(Piece.NOUGHT, 4, 1));
        assertNull(board.place(Piece.CROSS, 0, 2));
        assertNull(board.place(Piece.NOUGHT, 1, 2));
        assertNull(board.place(Piece.NOUGHT, 2, 2));
        assertNull(board.place(Piece.NOUGHT, 3, 2));
        assertNull(board.place(Piece.CROSS, 4, 2));
        assertNull(board.place(Piece.NOUGHT, 0, 3));
        assertNull(board.place(Piece.CROSS, 1, 3));
        assertNull(board.place(Piece.CROSS, 2, 3));
        assertNull(board.place(Piece.CROSS, 3, 3));
        assertNull(board.place(Piece.NOUGHT, 4, 3));
        assertNull(board.place(Piece.CROSS, 0, 4));
        assertNull(board.place(Piece.CROSS, 1, 4));
        assertNull(board.place(Piece.NOUGHT, 2, 4));
        assertNull(board.place(Piece.NOUGHT, 3, 4));
        assertEquals(Piece.BLANK, board.place(Piece.CROSS, 4, 4));
    }

    @Test
    public void row5() {

        Board board = new Board(5);
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 0));
        assertNull(board.place(Piece.NOUGHT, 2, 0));
        assertNull(board.place(Piece.NOUGHT, 3, 0));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 4, 0));
    }

    @Test
    public void column5() {

        Board board = new Board(5);
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 0, 1));
        assertNull(board.place(Piece.NOUGHT, 0, 2));
        assertNull(board.place(Piece.NOUGHT, 0, 3));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 0, 4));
    }

    @Test
    public void leadingDiagonal5() {

        Board board = new Board(5);
        assertNull(board.place(Piece.NOUGHT, 0, 0));
        assertNull(board.place(Piece.NOUGHT, 1, 1));
        assertNull(board.place(Piece.NOUGHT, 2, 2));
        assertNull(board.place(Piece.NOUGHT, 3, 3));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 4, 4));
    }

    @Test
    public void trailingDiagonal5() {

        Board board = new Board(5);
        assertNull(board.place(Piece.NOUGHT, 4, 0));
        assertNull(board.place(Piece.NOUGHT, 3, 1));
        assertNull(board.place(Piece.NOUGHT, 2, 2));
        assertNull(board.place(Piece.NOUGHT, 1, 3));
        assertEquals(Piece.NOUGHT, board.place(Piece.NOUGHT, 0, 4));
    }
}