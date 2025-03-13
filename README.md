# Rainbow-Shift-Puzzle-Game
Color Shift Puzzle is a logic game where players rearrange a 2x6 color grid to restore its original rainbow order. Moves include shifting rows left or right and swapping columns. The challenge is to solve the puzzle without repeating a previous board state. With multiple difficulty levels and randomized starts, each game is a fresh challenge.

## How to Play
- Players input moves (`a-d` or `1-6`) to shift colors in the grid.
- The goal is to return the board to a solved state where colors are arranged in "rainbow order."
- If a previous board configuration repeats, the game ends.

## Features
- Board initialization with a given difficulty level.
- Interactive gameplay using keyboard inputs.
- Game state tracking to detect repeated moves.

## Dependencies
- `edu.princeton.cs.algs4` for standard input and output.
- `dsUtils.Draw` for graphical rendering.

## Running the Game
1. Compile the Java files:
   ```sh
   javac p1/*.java
