package jamesw1892.noughtsandcrosses;

import java.util.Scanner;

public class CLI {

    /**
     * Swap crosses for noughts and vice versa, else error
     */
    private static Piece switchPlayer(Piece player) {
        switch (player) {
            case CROSS:  return Piece.NOUGHT;
            case NOUGHT: return Piece.CROSS;
            default: throw new IllegalArgumentException("Player must be NOUGHT or CROSS");
        }
    }

    /**
     * Play the game by asking each the user where to play and printing the board repeatedly until there is a winner
     */
    private static void play(Scanner scanner) {

        // get board size
        System.out.print("Board size, must be odd, default 3: ");
        int boardSize;
        try {
            boardSize = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a number, using default board size of 3");
            boardSize = 3;
        }

        Piece winner = null;
        Piece currentPlayer = Piece.CROSS;
        Board board = new Board(boardSize);
        int x, y;

        while (winner == null) {

            // print board and who's turn it is
            System.out.println(board);
            switch (currentPlayer) {
                case CROSS: System.out.println("Cross's turn"); break;
                case NOUGHT: System.out.println("Nought's turn"); break;
                default: throw new RuntimeException("Invalid piece");
            }

            // get x
            System.out.print("X position to play: ");
            try {
                x = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Must input a number, try again:");
                continue;
            }
            if (x < 0 || x >= board.getBoardSize()) {
                System.out.println(String.format("x must be between 1 and %d inclusive, try again:", board.getBoardSize()));
                continue;
            }

            // get y
            System.out.print("Y position to play: ");
            try {
                y = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Must input a number, try again:");
                continue;
            }
            if (y < 0 || y >= board.getBoardSize()) {
                System.out.println(String.format("y must be between 1 and %d inclusive, try again:", board.getBoardSize()));
                continue;
            }

            // verify position is unoccupied
            if (board.isOccupied(x, y)) {
                System.out.println("Cannot play in an already occupied tile, try again:");
                continue;
            }

            // perform the move and switch players
            winner = board.place(currentPlayer, x, y);
            currentPlayer = switchPlayer(currentPlayer);
        }

        // print the final state of the board and the winner
        System.out.println(board);
        System.out.println("Game finished");
        switch (winner) {
            case CROSS: System.out.println("Cross won"); break;
            case NOUGHT: System.out.println("Nought won"); break;
            case BLANK: System.out.println("Draw"); break;
            default: throw new RuntimeException("Invalid piece");
        }
    }

    /**
     * Run the CLI to ask the user what they want to do
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("MENU:\n1) Play\nChosen option: ");
        
        int option;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            scanner.close();
            return;
        }

        switch (option) {
            case 1:
                play(scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}