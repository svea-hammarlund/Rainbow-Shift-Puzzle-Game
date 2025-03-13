import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
	private Color[][] grid;

	public Board(int count) {
        Color[][] solvedState = {
                {Display.RED, Display.ORANGE, Display.YELLOW, Display.GREEN, Display.BLUE, Display.VIOLET},
                {Display.RED, Display.ORANGE, Display.YELLOW, Display.GREEN, Display.BLUE, Display.VIOLET}
            };

            grid = new Color[2][6];
            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 6; col++) {
                    grid[row][col] = solvedState[row][col];
                }
            }

            Set<String> visited = new HashSet<>();
            visited.add( boardToString(grid));  

            char[] moveOptions = {'a','b','c','d','1','2','3','4','5','6'};
            Random random = new Random();

            for (int i = 0; i < count; i++) {
            	boolean validMove = false;
                while (!validMove) {
                    char selectedMove = moveOptions[random.nextInt(moveOptions.length)];
                    applyMove(selectedMove, grid);
                    String newState = boardToString(grid);

                    if (!visited.contains(newState)) {
                        visited.add(newState);
                    	validMove = true;
                    } else {
                        applyReverseMove(selectedMove, grid);
                    }
                }
            }
	}
	

	public Board(Color[][] layout) {
        grid = new Color[2][6];

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 6; col++) {
                grid[row][col] = layout[row][col];
            }
        }
	}
	

	public void move(char move) {
		applyMove(move, grid);
    }
	
    private void applyMove(char move, Color[][] state) {
        if (move >= '1' && move <= '6') {
            switchColor(state, move - '1');
            return;
        }
        switch (move) {
            case 'a' -> moveLeft(state, 0);
            case 'b' -> moveRight(state, 0);
            case 'c' -> moveLeft(state, 1);
            case 'd' -> moveRight(state, 1);
        }
    }

    private void applyReverseMove(char move, Color[][] state) {
        if (move >= '1' && move <= '6') {
            switchColor(state, move - '1');
            return;
        }
        switch (move) {
            case 'a' -> moveRight(state, 0);
            case 'b' -> moveLeft(state, 0);
            case 'c' -> moveRight(state, 1);
            case 'd' -> moveLeft(state, 1);
        }
    }
	
	private void moveRight(Color[][] state, int row) {
        Color last = state[row][5];
        for (int col = 5; col > 0; col--) {
            state[row][col] = state[row][col - 1];
        }
        state[row][0] = last;
    }
    
    private void moveLeft(Color[][] state, int row) {
        Color first = state[row][0];
        for (int col = 0; col < 5; col++) {
            state[row][col] = state[row][col + 1];
        }
        state[row][5] = first;
    }
	
    private void switchColor(Color[][] state, int col) {
        Color temp = state[0][col];
        state[0][col] = state[1][col];
        state[1][col] = temp;
	}
	

	public boolean isSolved() {
		Color[] rainbow = {
	            Display.RED, 
	            Display.ORANGE, 
	            Display.YELLOW, 
	            Display.GREEN, 
	            Display.BLUE, 
	            Display.VIOLET
	        };
	        for (int col = 0; col < 6; col++) {
	        	if (!grid[0][col].equals(grid[1][col]) || !grid[0][col].equals(rainbow[col])) {
	        		return false;
	                }
	            }
	            return true;
	}
	

	public Color[][] getGrid() {
		Color[][] copy = new Color[2][6];
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 6; col++) {
                copy[row][col] = grid[row][col];
            }
        }
        return copy;
    }
	
	private String boardToString(Color[][] grid) {
        StringBuilder result = new StringBuilder();
        for (Color[] row : grid) {
            for (Color color : row) {
                result.append(colorToChar(color));
            }
        }
        return result.toString();
    }

    private char colorToChar(Color color) {
        if (color.equals(Display.RED))    return 'R';
        if (color.equals(Display.ORANGE)) return 'O';
        if (color.equals(Display.YELLOW)) return 'Y';
        if (color.equals(Display.GREEN))  return 'G';
        if (color.equals(Display.BLUE))   return 'B';
        if (color.equals(Display.VIOLET)) return 'V';
        return '?';
    }
}

