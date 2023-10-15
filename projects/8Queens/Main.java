
/**
 * A Hill-Climbing Algorithm.
 * The final output is eight 1s (Representing chess Queens)
 * on a board where none of them can capture each other;
 * There are no two Queens on the same row,
 * column, or diagonal.
 *
 * @author Kay Grum
 */
public class Main {

    private static int[][] board = new int[8][8];
    private static int[][] current = new int[8][8];
    private static int bestH = 0;
    private static int originalH = 0;
    private static int[][] bestState = new int[8][8];
    private static int numRestarts = 0;
    private static int numStateChanges = 0;
    private static int neighbors = 0;

    public static void main(String[] args) {

        //restarts the board, checks for goal state in every iteration,
        //prints results
        restart();

        while (!isGoalState()) {
            play();
        }

        print();
        System.out.println("Solution found!");
        System.out.println("State changes: " + numStateChanges);
        System.out.println("Restarts: " + numRestarts);

    }

    /**
     * Main logic for the program. Moves the queens on the current board and
     * checks the resulting state.
     */
    public static void play() {
        
        movingQueens(); //move the queens

        print();

        System.out.println("Neighbors found with better h: " + neighbors);
        neighbors = 0;
        
        //restart if there is no better heuristic
        if (bestH >= originalH) {
            
            numRestarts++;
            System.out.println("RESTART");
            restart();
            
        } else {
            
            //if there is a better heuristic, set a new state
            System.out.println("Setting new current state\n");
            copyElements(bestState, current);
            originalH = bestH;
            numStateChanges++;

        }
    }

    /**
     * Randomly places 8 Queens in an 8x8 2d array, one per column. Queens are
     * represented by a 1.
     */
    public static void restart() {

        //empties the board, places queens randomly with one per column
        board = new int[8][8];

        for (int i = 0; i < 8; i++) {

            int x = set(); //set() is math.random*8

            if (board[x][i] == 0) {
                board[x][i] = 1;
            } else {
                i--;
            }
        }

        //make this board the current board, and make this board's heuristic
        //both the original and the best heuristic
        copyElements(board, current);
        originalH = heuristic();
        bestH = originalH;

    }

    /**
     * Generates a random number between 0-8 inclusive.
     *
     * @return A random number from 0-8.
     */
    public static int set() {
        return (int) (Math.random() * 8);
    }

    /**
     * Prints the 2d array, "current", in a grid.
     */
    public static void print() {

        System.out.println("Current h: " + heuristic());
        System.out.println("Current state");

        for (int i = 0; i < 8; i++) {

            System.out.println("");

            for (int j = 0; j < 8; j++) {
                System.out.print(current[i][j] + " ");
            }

        }

        System.out.println("");

    }

    /**
     * Checks if the board is in goal state. Goal state being no Queens are in
     * conflict.
     *
     * @return True if goal state, false otherwise.
     */
    public static boolean isGoalState() {

        return heuristic() == 0;
    }

    /**
     * Checks the amount of horizontal conflicts. Every time there is more than
     * 1 queen in a row, the extras are counted as conflicts.
     *
     * @return The amount of conflicts in the entire board.
     */
    public static int horizontalConflicts() {

        int conflicts = 0;

        //check every square for a queen, increment counter if there is one,
        //increment conflicts if there is more than one queen in the row,
        //reset counter for every new row 
        for (int i = 0; i < 8; i++) {

            int counter = 0;
            for (int j = 0; j < 8; j++) {
                if (current[i][j] == 1) {

                    counter++;

                    if (counter > 1) {
                        conflicts++;
                    }

                }

            }
        }
        return conflicts;
    }

    /**
     * Checks for diagonal conflicts going from bottom left to top right. (0,7)
     * to (7,0). A conflict is every queen past 1 on a diagonal.
     *
     * @return Number of conflicts
     */
    public static int BLTR() {

        //extra queens on a diagonal, total queens on a diagonal
        //x and coordinate for current square, start counter for
        //x coordinate
        int conflicts = 0;
        int queenCount = 0;
        int x = 0;
        int y = 0;
        int start = 1;

        for (int i = 0; i < 8; i++) {

            //y starts at 0 and x starts at 1 number lower,
            //it's a new diagonal so the queen count is 0
            y = 0;
            x = 7 - start;
            queenCount = 0;

            for (int j = 0; j < 8; j++) {

                //For every square, increment x and start a new diagonal
                //if x is out of bounds
                x++;

                if (x > 7) {

                    break;
                }

                //Check for a queen, increment the queen count if it's there,
                //and check for a conflict
                if (current[x][y] == 1) {

                    queenCount++;

                    if (queenCount > 1) {
                        conflicts++;
                    }

                }

                y++;

            }

            start++;
        }

        //This is the second half, above the middle diagonal
        
        start = 0;

        for (int i = 0; i < 8; i++) {

            //y starts 1 higher than it did last row, x starts at -1,
            //queen count is reset
            
            y = 1 + start;
            x = -1;
            queenCount = 0;

            for (int j = 0; j < 8; j++) {

                //x goes up every square, then check for out of bounds
                x++;

                if (x > 7 || y < 0 || x < 0 || y > 7) {

                    break;
                }

                //Check for queens, increment queen if applicable, check for conflict
                if (current[x][y] == 1) {
                    queenCount++;
                    
                    if (queenCount > 1) {
                        conflicts++;
                    }

                }

                y++;

            }

            start++;
        }

        return conflicts;
    }

    /**
     * This method checks from top left (0,0) to bottom right (7,7)
     *
     * @return The amount of conflicts. Conflicts are any extra queens on a
     * diagonal.
     */
    public static int TLBR() {

        //x coordinate, y coordinate, number of queens on a diagonal,
        //number of conflicts, start counter for y
        int x = 0;
        int y = 0;
        int queenCount = 0;
        int conflicts = 0;
        int start = 0;

        for (int i = 0; i < 8; i++) {
            //y starts 1 higher than it did last row, x starts at 0,
            //queen count is reset

            x = 0;
            y = 0 + start;
            queenCount = 0;

            for (int j = 0; j < 8; j++) {

                //y goes down, check for out of bounds
                y--;

                if (y < 0) {
                    break;
                }

                //check for queen, check for conflict
                if (current[x][y] == 1) {
                    queenCount++;
                    
                    if (queenCount > 1) {
                        conflicts++;
                    }
                    
                }
                x++;
            }
            start++;
        }

        //This is the part after the middle half
        x = 0;
        y = 0;
        queenCount = 0;

        start = 0;

        for (int i = 0; i < 8; i++) {

            //y starts at 7, x goes up each time, reset queen count
            y = 7;
            x = -1 + start;
            queenCount = 0;

            for (int j = 0; j < 8; j++) {

                //x goes up, check for out of bounds
                x++;

                if (x < 0 || y > 7 || x > 7 || y < 0) {
                    break;
                }

                //check for queen, check for conflict
                if (current[x][y] == 1) {
                    queenCount++;
                    
                    if (queenCount > 1) {
                        conflicts++;
                    }
                    
                }
                y--;
            }
            start++;
        }

        return conflicts;
    }

    /**
     * This method returns the total amount of conflicts in the current state.
     *
     * @return The amount of queens that can interact.
     */
    public static int heuristic() {

        return horizontalConflicts() + TLBR() + BLTR();
    }

    /**
     * This method holds the logic for moving the queens one square and
     * evaluating the new state.
     */
    public static void movingQueens() {

        //column and original position of the queen
        int column = 0;
        int originalPos1 = 0;
        int originalPos2 = 0;

        for (int i = 0; i < 8; i++) {

            //empties the column, keeps track of queen's original
            //position for after it's done moving
            for (int j = 0; j < 8; j++) {
                
                if (current[j][column] == 1) {
                    originalPos1 = j;
                    originalPos2 = column;
                }
                
                current[j][column] = 0;
            }

            //set square to 1, see if it's better, if so then copy elements
            //over to best state, then reset that square to 0
            for (int j = 0; j < 8; j++) {
                
                current[j][i] = 1;

                if (heuristic() < bestH) {
                    bestH = heuristic();
                    neighbors++;
                    copyElements(current, bestState);
                }
                
                current[j][i] = 0;

            }

            //put queen back where it was
            current[originalPos1][originalPos2] = 1;
            column++;
        }

    }

    /**
     * This method copies elements from one array to another.
     *
     * @param original The array to get the data from.
     * @param newArray The array to put the data in.
     */
    public static void copyElements(int[][] original, int[][] newArray) {
        
        for (int i = 0; i < original.length; i++) {
            
            for (int j = 0; j < original.length; j++) {
                
                newArray[i][j] = original[i][j];
            }
            
        }
        
    }

}
