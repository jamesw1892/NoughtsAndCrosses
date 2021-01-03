package jamesw1892.noughtsandcrosses;

import java.util.Scanner;

import jamesw1892.noughtsandcrosses.ai.MiniMax;
import jamesw1892.noughtsandcrosses.ai.RandomAI;

public class CLI {

    private static int getBoardSize(Scanner scanner) {
        System.out.print("Board size, must be odd, default 3: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a number, using default board size of 3");
            return 3;
        }
    }

    /**
     * Play the game by asking each the user where to play and
     * printing the board repeatedly until there is a winner
     */
    private static void multiplayer(Scanner scanner) {

        // get board size
        int boardSize = getBoardSize(scanner);

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
            currentPlayer = Piece.switchPlayer(currentPlayer);
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

    private static void singlePlayerRandom(Scanner scanner) {

        int boardSize = getBoardSize(scanner);

        System.out.print("Would you like to be noughts (o) or crosses (x), crosses go first? ");
        String choice = scanner.nextLine().toLowerCase();
        Piece user = Piece.CROSS;
        if (choice.equals("o")) {
            user = Piece.NOUGHT;
        } else if (!choice.equals("x")) {
            System.out.println("Invalid choice, defaulting to crosses");
        }

        Piece winner = null;
        Piece currentPlayer = Piece.CROSS;
        Board board = new Board(boardSize);
        int x, y;

        while (winner == null) {

            if (currentPlayer == user) {

                // print board and who's turn it is
                System.out.println(board);
                System.out.println("Your turn");

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

            // random player
            } else {

                int[] pos = RandomAI.getBestMove(board, currentPlayer);
                x = pos[0];
                y = pos[1];
            }

            // perform the move and switch players
            winner = board.place(currentPlayer, x, y);
            currentPlayer = Piece.switchPlayer(currentPlayer);
        }

        // print the final state of the board and the winner
        System.out.println(board);
        System.out.println("Game finished");
        if (winner == Piece.BLANK) {
            System.out.println("Draw");
        } else if (winner == user) {
            System.out.println("You won!");
        } else {
            System.out.println("The computer won");
        }
    }

    private static void singlePlayerMiniMax(Scanner scanner) {

        int boardSize = getBoardSize(scanner);

        System.out.print("Would you like to be noughts (o) or crosses (x), crosses go first? ");
        String choice = scanner.nextLine().toLowerCase();
        Piece user = Piece.CROSS;
        if (choice.equals("o")) {
            user = Piece.NOUGHT;
        } else if (!choice.equals("x")) {
            System.out.println("Invalid choice, defaulting to crosses");
        }

        Piece winner = null;
        Piece currentPlayer = Piece.CROSS;
        Board board = new Board(boardSize);
        int x, y;

        while (winner == null) {

            if (currentPlayer == user) {

                // print board and who's turn it is
                System.out.println(board);
                System.out.println("Your turn");

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

            // minimax player
            } else {

                int[] pos = MiniMax.getBestMove(board, currentPlayer);
                x = pos[0];
                y = pos[1];
            }

            // perform the move and switch players
            winner = board.place(currentPlayer, x, y);
            currentPlayer = Piece.switchPlayer(currentPlayer);
        }

        // print the final state of the board and the winner
        System.out.println(board);
        System.out.println("Game finished");
        if (winner == Piece.BLANK) {
            System.out.println("Draw");
        } else if (winner == user) {
            System.out.println("You won!");
        } else {
            System.out.println("The computer won");
        }
    }

    private static void minimax2(Scanner scanner) {

        Piece winner = null;
        Piece currentPlayer = Piece.CROSS;
        Board board = new Board(getBoardSize(scanner));
        int x, y;

        while (winner == null) {

            System.out.println(board);

            int[] pos = MiniMax.getBestMove(board, currentPlayer);
            x = pos[0];
            y = pos[1];

            // perform the move and switch players
            winner = board.place(currentPlayer, x, y);
            currentPlayer = Piece.switchPlayer(currentPlayer);
        }

        // print the final state of the board
        System.out.println(board);
        System.out.println("Game finished");
        switch (winner) {
            case BLANK:  System.out.println("Draw"); break;
            case NOUGHT: System.out.println("Noughts won"); break;
            case CROSS:  System.out.println("Crosses won"); break;
            default: throw new RuntimeException("Invalid piece");
        }
    }

    /**
     * Run the CLI to ask the user what they want to do
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("MENU:\n1) Multiplayer\n2) Single player random\n3) Single player minimax\n4) Minimax against each other\nChosen option: ");

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
                multiplayer(scanner);
                break;
            case 2:
                singlePlayerRandom(scanner);
                break;
            case 3:
                singlePlayerMiniMax(scanner);
                break;
            case 4:
                minimax2(scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}