package jamesw1892.noughtsandcrosses.ai;

import java.util.Random;

import jamesw1892.noughtsandcrosses.Board;
import jamesw1892.noughtsandcrosses.Piece;

public class RandomAI {
    private static Random random = new Random();

    public static int[] getBestMove(Board board, Piece piece) {

        int x, y;
        do {
            x = random.nextInt(board.getBoardSize());
            y = random.nextInt(board.getBoardSize());
        } while (board.isOccupied(x, y));

        return new int[] {x, y};
    }
}