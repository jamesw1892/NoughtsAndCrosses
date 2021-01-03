package jamesw1892.noughtsandcrosses.ai;

import jamesw1892.noughtsandcrosses.Board;
import jamesw1892.noughtsandcrosses.Piece;

class BestMove {
    int score;
    int x;
    int y;
    public BestMove(int score, int x, int y) {
        this.score = score;
        this.x = x;
        this.y = y;
    }
}

public class MiniMax {

    private static BestMove recurse(Board board, int depth, boolean shouldMaximise, Piece piece) {

        int bestX = -1;
        int bestY = -1;
        Integer bestScore = null;

        // consider every unoccupied tile:
        for (int x = 0; x < board.getBoardSize(); x++) {
            for (int y = 0; y < board.getBoardSize(); y++) {
                if (!board.isOccupied(x, y)) {

                    // place the piece in that location on a copy of the board
                    // and calculate whether there is a winner
                    Board copy = board.copy();
                    Piece winner = copy.place(piece, x, y);
                    int score;

                    // if no winner, recurse
                    if (winner == null) {
                        score = recurse(copy, depth + 1, !shouldMaximise, Piece.switchPlayer(piece)).score;

                    // tie
                    } else if (winner == Piece.BLANK) {
                        score = 0;

                    // we win
                    } else if (!(winner == piece ^ shouldMaximise)) {
                        score = 1;

                    // opponent wins
                    } else {
                        score = -1;
                    }

                    // adjust score - if maximising player, maximise score, otherwise minimise it
                    if (bestScore == null || (
                        shouldMaximise ? score > bestScore : score < bestScore
                    )) {
                        bestScore = score;
                        bestX = x;
                        bestY = y;
                    }
                }
            }
        }

        // return new BestMove(bestScore * (board.getBoardSize() * board.getBoardSize() - depth), bestX, bestY);
        return new BestMove(bestScore, bestX, bestY);
    }

    public static int[] getBestMove(Board board, Piece piece) {

        BestMove bestMove = recurse(board, 0, true, piece); // or should depth be 1? Doesn't really matter
        return new int[] {bestMove.x, bestMove.y};
    }
}