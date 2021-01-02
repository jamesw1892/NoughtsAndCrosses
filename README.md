# Noughts and Crosses

A simulation of the simple game Noughts and Crosses AKA Tic Tac Toe.

It includes the ability for one or two users to play it on the command line and I aim to add some AI too.

# Usage

Run `gradle -q --console plain run` to run the CLI. The extra options mean Gradle doesn't print everywhere around the user input and standard output. To test run `gradle test`.

# How the board game works

In the standard game, the grid is 3 by 3, but it works with different sized grids.

There are two players, one of which is assigned noughts and the other is assigned crosses. That take turns to place their piece in an unoccupied tile on the board. The winner is the first player to make a whole row, column or diagonal of their pieces.

# Files

- `Piece.java`: An enum containing the possible pieces - nought, cross or blank.
- `Board.java`: An object representing the board, with methods to place a piece in a location and check for the winner, etc.
- `CLI.java`: The command line interface.

# CLI

Available modes for the command line interface

- Single player random
- Multiplayer