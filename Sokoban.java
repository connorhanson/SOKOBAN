import java.util.Scanner;
import java.util.Random;

// https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        do {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid value.");
                }
            } else {
                System.out.println("Invalid value.");
                String s1 = sc.next();
            }
        } while (true);
    }


    /**
     * Prompts the user for a char value by displaying prompt. Note: This method should not be a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        System.out.print(prompt);
        char input = sc.next().toLowerCase().charAt(0);
        return input;
    }

    /**
     * Prompts the user for a string value by displaying prompt. Note: This method should not be a
     * new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, remove any leading
     * and trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and
     *         trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim().toLowerCase();
        return input;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. a - For each row, copy the values from the
     * corresponding row in the 2-d array contained at index lvl in levels. b - When the worker is
     * located, it's position should be recorded in the pos parameter. 2 - For each goal described
     * in the array at index lvl of goals, convert the character at the goal coordinate to: -
     * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it contains a box -
     * Config.GOAL_CHAR otherwise
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and
     *        index 1 is the column.
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
       
        char[][] boardInit = new char[levels[lvl].length][];
        for (int i = 0; i < levels[lvl].length; ++i) { //x axis for loop actually y
            boardInit[i] = new char[levels[lvl][i].length];
                for (int j = 0; j < levels[lvl][i].length; ++j) { //y axis actually x
                    boardInit[i][j] = levels[lvl][i][j];
                    if (boardInit[i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                    }
                }
        }
        
        for (int i = 0; i < goals[lvl].length; i += 2) { //runs thru goal array
            int yAxis = goals[lvl][i];
            int xAxis = goals[lvl][i+1];
            if (boardInit[yAxis][xAxis] == Config.WORKER_CHAR) {
                boardInit[yAxis][xAxis] = Config.WORK_GOAL_CHAR;
            } else if (boardInit[yAxis][xAxis] == Config.BOX_CHAR) {
                boardInit[yAxis][xAxis] = Config.BOX_GOAL_CHAR;
            } else {
                boardInit[yAxis][xAxis] = Config.GOAL_CHAR;
            }
        } 
        return boardInit;  
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer wall
     * to the left and the right. 2 - For each row in board, print out a Config.WALL_CHAR, followed
     * by the contents of the row, followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence
     * of Config.WALL_CHAR with a length equal to that of the last row of board, plus the outer wall
     * to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        
        for (int i = 0; i <= board[0].length + 1; ++i) { // top wall
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
        
        for (int i = 0; i < board.length; ++i) { //body + side wall
            System.out.print(Config.WALL_CHAR);
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
            } 
            System.out.println(Config.WALL_CHAR);
            }
        
        for (int i = 0; i <= board[board.length -1].length + 1; ++i) {
            System.out.print(Config.WALL_CHAR);
        }
    }
    
        //initBoard(int lvl, char[][][] Config.LEVELS, int[][] Config.GOALS, int[] pos);
        /*for (int i = 0; i <= (board[0].length + 1); ++i) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
        for (int j = 0; j < board.length; ++j) {
            System.out.print(Config.WALL_CHAR);
            for (int k = 0; k < board[j].length; ++k) {
                System.out.print(board[j][k]);
            }
            System.out.println(Config.WALL_CHAR);
        }
        int numRows = board.length - 1;
        for (int m = 0; m <= (board[numRows].length + 1); ++m) {
            System.out.print(Config.WALL_CHAR);
        }
        for(int i = 0; i < board.length; ++i)
        {
            for(int k = 0; k < board[i].length; k++)
            {
                System.out.println(board[i][k]);
            }
            System.out.println();
        }
    } */

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is a valid index in
     * levels, that the 2-d array at index lvl exists and that it contains at least 1 row. 3 - lvl
     * is a valid index in goals, the 1-d array at index lvl exists and that it contains an even
     * number of cells. 4 - the number of boxes is more than 0. 5 - the number of boxes equals the
     * number of goals. 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check for duplicate
     * goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2 fails: -1 - Test 3
     *         fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 - Test 6 fails: -5 - Test 7 fails: -6
     *         - Test 8 fails: -7
     * 
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {
        // Test 1 -- lvl>= 0
        if (lvl < 0) {
            return 0;
        }
        // Test 2 -- lvl is a valid index in levels, that the 2-d array at index lvl exists and that
        // it contains at least 1 row.
        if (lvl >= levels.length || levels[lvl].length < 1) {

            // index not exists
            return -1;
        }
        // index exists
        // Test 3 -- lvl is a valid index in goals, the 1-d array at index lvl exists and that it
        // contains an even number of cells.
        // lvl !< 0 or < goal.length
        if (lvl >= goals.length || (goals[lvl].length % 2 != 0)) {
            return -2;
        }
        // Test 4 -- the number of boxes is more than 0.
        int boxCounter = 0;
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == '=') {
                    boxCounter++;
                }
            }
        }

        if (boxCounter == 0) {
            return -3;
        }
        // Test 5 -- the number of boxes equals the number of goals.
        if (boxCounter != goals[lvl].length / 2) {
            return -4;
        }

        // Test 6 -- the coordinate of each goal is valid for the given lvl and does not correspond
        // to a wall cell.
        for (int i = 0; i < goals[lvl].length; i += 2) {

            int yCoord = goals[lvl][i];
            int xCoord = goals[lvl][i + 1];
            if (levels[lvl][yCoord][xCoord] == '\u0000') {
                return -5;
            }
            if (levels[lvl][yCoord][xCoord] == Config.WALL_CHAR) {
                return -5;
            }
        }
        // Test 7 -- the number of workers is exactly 1.
        int workerCounter = 0;
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int k = 0; k < levels[lvl][i].length; k++) {
                if (levels[lvl][i][k] == Config.WORKER_CHAR) {
                    workerCounter++;
                }
            }
        }
        if (workerCounter != 1) {
            return -6;
        }
        // Test 8 -- Add in comments to explain the code
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }
        return 1;

    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows: - The 1st character of the String
     * represents the direction. - The remaining characters (if there are any) are interpreted as
     * integer and represent the magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would represent moving up
     * by one character. - If the parameter moveStr is "65": An array {0, 5} would represent moving
     * right by 5 characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        int[] movement = new int [2];
        Scanner sc = new Scanner(moveStr);
        int moveMag = 0;
        int moveDirect = 0;
       
        if (moveStr.charAt(0) != Config.UP_CHAR && moveStr.charAt(0) != Config.DOWN_CHAR && moveStr.charAt(0) != Config.RIGHT_CHAR && moveStr.charAt(0) != Config.LEFT_CHAR) { 
             moveDirect = 0;
             moveMag = 0; 
             movement[0] = moveDirect;
             movement[1] = moveMag;
             return movement;}
             
        
        char num = moveStr.charAt(0);
        
        if (num == Config.UP_CHAR) {
            moveDirect = -1;
        } else if (num == Config.DOWN_CHAR) {
            moveDirect = 1;
        } else if (num == Config.RIGHT_CHAR) {
            moveDirect = 0;
        } else if (num == Config.LEFT_CHAR) {
            moveDirect = 0;
        } else {
            moveDirect = 0;
        }
       int num2 = Integer.parseInt(moveStr.substring(1, 2));
       moveMag = num2;
       System.out.print(num2);
        
        if (moveMag == 1) {
            moveMag = 0;
        }
        
       movement[0] = moveDirect;
       System.out.print(movement[0]);
        movement[1] = moveMag;
        System.out.print(movement[1]);
        return movement;
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check: 1 - that pos is valid. 2 - that the
     * character at pos in board is in the valid array. 3 - that the delta is valid. 4 - that the
     * new position is valid and not a wall character. 5 - that the new position is not a box
     * character For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and index 1
     *        is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2, or not on the
     *         board. -2 : if the character at pos is not valid (not in the valid array). -3 : if
     *         delta is null or not length 2. -4 : if the new position is off the board or a wall
     *         character -5 : if the new position is a box character
     */

    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        
        if (pos == null) { //checks if null
            return -1;
        }
        
        
        for (int i = 0; i < pos.length; ++i) { 
            if (pos[i] == (int) pos[i]) {
                return -1;
            }
        } 

        if (pos.length != -2) { //checks if not length 2
            return -1;
        }
        
        for (int i = 0; i < board.length; ++i) { //checks if on board
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != pos[i]) //used to be ==
                    return -1;
            }
        }

        for (int i = 0; i <= valid.length; ++i) { //checks if in valid array
            for (int j = 0; j <= valid.length; ++j) {
            if (pos[i] != valid[j]) {
                return -2;
            }
            }
        }
        
        /*for (int i = 0; i < pos.length; ++i) { //checks if in valid array
            if (new String(valid).indexOf(pos[i]) <= 0) {
                return -2;
            }
        }*/
        
        if (delta == null) { //checks if delta is null
            return -3;
        }

        /*for (int i = 0; i < delta.length; ++i) { //checks if delta is null
            if (delta[i] == (int) delta[i]) {
                return -3;
            }
        }*/

        if (delta.length != -2) { //checks if delta is 2
            return -3;
        }

        for (int i = 0; i < board.length; ++i) { //checks if new pos is wall char
            if(pos[i] == Config.WALL_CHAR) {
                return -4;
            }
        }
        
        for (int i = 0; i < board.length; ++i) { //checks if on board
            for (int j = 0; j < board[i].length; ++j) {
                if (pos[i] > board[i].length) {
                    
                }
            }
        }

        for (int i = 0; i < board.length; ++i) { //if new pos is box char
            if (pos[i] == Config.BOX_CHAR) {
                return -5;
            }
        }

        return 1;
    }



    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        // FIX ME
    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent a box. Step 2: Use your togglePos method to correctly change
     * the character at the new position to the appropriate box character. Step 3: Again use your
     * togglePos method to correctly change the character at pos to the the appropriate character
     * without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        // FIX ME
        return -99;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. That is, if the delta is {0,
     * -3}, your method should call doMove three times with an argument of {0, -1} for the delta
     * parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove six times with an
     * argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0. If the call to doMove returns a value
     *         less than 1, return that value. Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        // FIX ME
        return -99;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent the worker. Step 2: If checkDelta returns -5, use your shiftBox
     * method to move the box by delta before moving the worker. Step 3: Use your togglePos method
     * to correctly change the character at the new position to the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the the
     * appropriate character without a worker. Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. If
     *         checkDelta returns -5 and shiftBox returns a value less than 0, return 0. Otherwise,
     *         return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        // FIX ME
        return -99;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) { // how to fix??
                if (board[i][j] == Config.GOAL_CHAR) {
                    return false;
                }
                if (board[i][j] == Config.WORK_GOAL_CHAR) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Sokoban!");
        int maxLvl = Config.LEVELS.length - 1;
        int minLvl = 0;
        String charPrompt = "Play again? (y/n) ";
        int[] pos = new int[2]; 
        
        do {
            String prompt = "Choose a level between 0 and " + maxLvl + ": ";
            int input = promptInt(sc, prompt, minLvl, maxLvl); // lvl chosen
            if (checkLevel(input, Config.LEVELS, Config.GOALS) == 1) { // check array parameters);
                System.out.println("Sokoban Level " + input);
            }
            String prompt1 = ":";
            do {
            char[][] board = initBoard(input, Config.LEVELS, Config.GOALS, pos);
            printBoard(board);
            promptString(sc, prompt1);
            } while (promptString(sc, prompt1).equals(Config.QUIT_CHAR) == false);
            // game loop
            // play stuff

            if (promptChar(sc, charPrompt) == 'y') { // fix method call
                input = promptInt(sc, prompt, minLvl, maxLvl);
                if (checkLevel(input, Config.LEVELS, Config.GOALS) == 1) { // check array
                                                                           // parameters);
                    System.out.println("Sokoban Level " + input);
                }
            }
        } while (promptChar(sc, charPrompt) == 'y');
        System.out.println("Thanks for playing!");

        // TestSokoban.testInitBoard();
    }

}



