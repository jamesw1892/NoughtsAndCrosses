package jamesw1892.noughtsandcrosses;

public enum Piece {
    BLANK,
    NOUGHT,
    CROSS;

    /**
     * Swap crosses for noughts and vice versa, else error
     */
    public static Piece switchPlayer(Piece player) {
        switch (player) {
            case CROSS:  return Piece.NOUGHT;
            case NOUGHT: return Piece.CROSS;
            default: throw new IllegalArgumentException("Player must be NOUGHT or CROSS");
        }
    }
}