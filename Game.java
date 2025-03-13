import java.awt.Color;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.HashSet;
import java.util.Set;

public class Game {
    public static void main(String[] args) {
        StdOut.print("Select difficulty level (1-6): ");
        int difficulty = StdIn.readInt();

        Board board = new Board(difficulty);
        Display screen = new Display();
        
        Set<String> previousConfigs = new HashSet<>();
        previousConfigs.add(boardToString(board.getGrid()));

        boolean gameRunning = true;
        while (gameRunning) {
            screen.drawBoard(board.getGrid());

            if (board.isSolved()) {
                StdOut.println("You win! The puzzle is solved.");
                break;
            }

            StdOut.println("Input move (a-d or 1-6): ");
            char play = screen.getKeyStroke();
            StdOut.println("Move registered: " + play);

            board.move(play);

            String currentConfig = boardToString(board.getGrid());
            if (previousConfigs.contains(currentConfig)) {
                StdOut.println("Game over! You repeated a prior state.");
                gameRunning = false;
            } else {
                previousConfigs.add(currentConfig);
            }
        }
    }
    
    /**
     * Converts the 2x6 arrangement of Colors into a String
     * so we can store/check it in a HashSet.
     */
    private static String boardToString(Color[][] grid) {
        StringBuilder result = new StringBuilder();
        for (Color[] row : grid) {
        	for (Color color : row) {
                result.append(colorToChar(color));
            }
        }
        return result.toString();
    }
    
    /**
     * Maps each color to a single char so we can quickly build a unique string.
     */
    private static char colorToChar(Color c) {
        if (c.equals(Display.RED))    return 'R';
        if (c.equals(Display.ORANGE)) return 'O';
        if (c.equals(Display.YELLOW)) return 'Y';
        if (c.equals(Display.GREEN))  return 'G';
        if (c.equals(Display.BLUE))   return 'B';
        if (c.equals(Display.VIOLET)) return 'V';
        return '?';
    }
}

