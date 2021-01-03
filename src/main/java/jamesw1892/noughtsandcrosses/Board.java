package jamesw1892.noughtsandcrosses;

public class Board {
    public static final int DEFAULT_BOARD_SIZE = 3;
    private final int boardSize;
    private Piece[][] board;
    private int piecesPlaced;

    /**
     * Create an empty board with specified board size
     */
    public Board(int boardSize) {
        this.boardSize = boardSize;

        if (this.boardSize % 2 == 0 || this.boardSize < 1) throw new IllegalArgumentException("Board size must be odd and at least 1");

        this.piecesPlaced = 0;
        this.board = new Piece[this.boardSize][this.boardSize];
        for (int y = 0; y < this.boardSize; y++) {
            for (int x = 0; x < this.boardSize; x++) {
                this.board[y][x] = Piece.BLANK;
            }
        }
    }

    /**
     * Create an empty board with default board size
     */
    public Board() {
        this(DEFAULT_BOARD_SIZE);
    }

    /**
     * Return the board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Return piece of winner or blank if draw or null if no winner yet
     */
    private Piece checkForWinner(int x, int y, Piece piece) {

        /**
         * We only need to check the row and column (plus diagonal if on diagonal)
         * of the piece most recently placed since we check after every piece is
         * placed. Also, we only need to compare to the piece that was just played since
         * they are the only player that can have won
         */

        boolean allEqual;

        // row
        allEqual = true;
        for (int otherY = 0; otherY < this.boardSize; otherY++) {
            if (piece != this.board[otherY][x]) {
                allEqual = false;
            }
        }
        if (allEqual) return piece;

        // column
        allEqual = true;
        for (int otherX = 0; otherX < this.boardSize; otherX++) {
            if (piece != this.board[y][otherX]) {
                allEqual = false;
            }
        }
        if (allEqual) return piece;

        // leading diagonal
        if (x == y) {
            allEqual = true;
            for (int other = 0; other < this.boardSize; other++) {
                if (piece != this.board[other][other]) {
                    allEqual = false;
                }
            }
            if (allEqual) return piece;
        }

        // trailing diagonal
        if (x + y == this.boardSize - 1) { // this holds if on trailing diagonal
            allEqual = true;
            for (int other = 0; other < this.boardSize; other++) {
                if (piece != this.board[other][this.boardSize - 1 - other]) {
                    allEqual = false;
                }
            }
            if (allEqual) return piece;
        }

        // draw if no winner (haven't already returned from
        // this function) and all tiles have been filled
        if (piecesPlaced == this.boardSize * this.boardSize) {
            return Piece.BLANK;
        }

        // otherwise no winner
        return null;
    }

    /**
     * Return whether the specified tile is occupied
     */
    public boolean isOccupied(int x, int y) {
        return this.board[y][x] != Piece.BLANK;
    }

    /**
     * Place the given piece in the specified location if valid
     * and return the winner or blank if it is draw or null if
     * the game has not finished yet
     */
    public Piece place(Piece piece, int x, int y) {
        if (piece == Piece.BLANK) {
            throw new IllegalArgumentException("Piece cannot be blank");
        }

        if (this.board[y][x] != Piece.BLANK) {
            throw new IllegalArgumentException("Position must be blank");
        }

        this.board[y][x] = piece;
        this.piecesPlaced++;

        return this.checkForWinner(x, y, piece);
    }

    /**
     * Return labelled string showing the board
     */
    public String toString() {

        String hline = System.lineSeparator() + "  ";
        for (int z = 0; z < this.boardSize * 4 + 1; z++) { // times 2 plus 1 for vertical lines between and outside as well as pieces themselves
            hline += '-';
        }

        String hlabel = System.lineSeparator() + ' ';
        for (int x = 0; x < this.boardSize; x++) {
            hlabel += "   " + String.valueOf(x + 1);
        }

        String out = hlabel + hline;

        for (int y = 0; y < this.boardSize; y++) {
            out += System.lineSeparator() + String.valueOf(y + 1) + ' ';
            for (int x = 0; x < this.boardSize; x++) {
                out += "| ";
                switch (this.board[y][x]) {
                    case BLANK:  out += ' '; break;
                    case NOUGHT: out += 'O'; break;
                    case CROSS:  out += 'X'; break;
                    default: throw new RuntimeException("Invalid piece");
                }
                out += ' ';
            }
            out += "| " + String.valueOf(y + 1) + hline;
        }

        return out + hlabel + System.lineSeparator();
    }
}