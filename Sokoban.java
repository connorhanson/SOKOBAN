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
        for (int i = 0; i < levels[lvl].length; ++i) { // x axis for loop actually y
            boardInit[i] = new char[levels[lvl][i].length];
            for (int j = 0; j < levels[lvl][i].length; ++j) { // y axis actually x
                boardInit[i][j] = levels[lvl][i][j];
                if (boardInit[i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        for (int i = 0; i < goals[lvl].length; i += 2) { // runs thru goal array
            int yAxis = goals[lvl][i];
            int xAxis = goals[lvl][i + 1];
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

        for (int i = 0; i < board.length; ++i) { // body + side wall
            System.out.print(Config.WALL_CHAR);
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println(Config.WALL_CHAR);
        }

        for (int i = 0; i <= board[board.length - 1].length + 1; ++i) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
    }

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
        int[] moveVec = new int[2];
        int moveDir;
        int moveMag;
        boolean hasInt = false;
        moveMag = 100;

        if (moveStr.length() < 2) { // if string is more than one else branch happens
            moveMag = 1;
        } else {
           
            try { // checks to see if hasInt = false;
                Integer.parseInt(moveStr.substring(1, moveStr.length()));
                hasInt = true;
            } catch (NumberFormatException ex) {
                moveMag = 1;
            }
            if (hasInt == true) {
                moveMag = Integer.parseInt(moveStr.substring(1, moveStr.length()));
            }
        }

        moveDir = (int) moveStr.charAt(0);

        if ((char) moveDir == Config.UP_CHAR) {
            moveVec[0] = -1 * moveMag;
        } else if ((char) moveDir == Config.DOWN_CHAR) {
            moveVec[0] = moveMag;
        } else if ((char) moveDir == Config.LEFT_CHAR) {
            moveVec[1] = -1 * moveMag;
        } else if ((char) moveDir == Config.RIGHT_CHAR) {
            moveVec[1] = moveMag;
        }
        if ((char) moveDir != Config.UP_CHAR && (char) moveDir != Config.DOWN_CHAR
            && (char) moveDir != Config.LEFT_CHAR && (char) moveDir != Config.RIGHT_CHAR) { // if
                                                                                            // none
                                                                                            // of
                                                                                            // valid
                                                                                            // chars
                                                                                            // for
                                                                                            // 1st
                                                                                            // char
            moveVec[0] = 0;
            moveVec[1] = 0;
            return moveVec;
        }
        // System.out.println(moveVec[0] + moveVec[1] + "{}");
        return moveVec;
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

        if (pos == null || pos.length != 2 || pos[0] >= board.length || pos[0] < 0
            || pos[1] >= board[pos[0]].length || pos[1] < 0) { // checks if null
                                                                             // //added -1
            return -1;
        }



        boolean test2 = false;
        for (char validChars : valid) {
            if (validChars == board[pos[0]][pos[1]]) {
                test2 = true;
            }
        }
        if (test2 == false) {
            return -2;
        }


        if (delta == null) {
            return -3;
        }

        if (delta.length != 2) { // checks if delta is null WORKS
            return -3;
        }


        int newY = pos[0] + delta[0];
        int newX = pos[1] + delta[1];

        if (newY < 0 || newY >= board.length) {
            return -4;

        }
        if (newX < 0 || newX >= board[newY].length) {
            return -4;
        }

        if (board[newY][newX] == Config.WALL_CHAR) {
            //System.out.println("ran into wall");
            return -4;
        }

        if (board[newY][newX] == Config.BOX_CHAR || board[newY][newX] == Config.BOX_GOAL_CHAR) {
            //System.out.println("ran into box char");
            return -5;

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
     * @return
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        if (board[pos[0]][pos[1]] == val) {
            board[pos[0]][pos[1]] = opt1;
        } else {
            board[pos[0]][pos[1]] = opt2;
        }
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
        char[] validBox = new char[2];
        validBox[0] = Config.BOX_CHAR;
        validBox[1] = Config.BOX_GOAL_CHAR;
        int verifyD = checkDelta(board, pos, delta, validBox);
        if (verifyD != 1) {
             return verifyD;
        }
        
        if (verifyD == 1) {
            int[] newPos = new int[2];
            newPos[0] = pos[0] + delta[0];
            newPos[1] = pos[1] + delta[1];
            
            togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);
            togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        }
        return 1;
        
    }
        // char val = either an empty space or a goal char;
        // do we have to define opt1 and opt2 in this method?

        /*char[] validBox = new char[2];
        validBox[0] = Config.BOX_CHAR;
        validBox[1] = Config.BOX_GOAL_CHAR;
        char[] validWork = new char[2];
        validWork[0] = Config.WORKER_CHAR;
        validWork[1] = Config.WORK_GOAL_CHAR;
        
        int[] boxPos = new int[2]; //to help move box
        if (delta[0] != 0) {
            boxPos[0] = delta[0] / Math.abs(delta[0]);
            boxPos[1] = 0;
        } else {
            boxPos[0] = 0;
            boxPos[1] = delta[1] / Math.abs(delta[1]);
        }
        if (checkDelta(board, pos, delta, validBox) < 1) { // checks move is valid, returns <1 if
                                                           // not
            System.out.println("shiftbox" + checkDelta(board, pos, delta, validBox));
            togglePos(board, pos, Config.BOX_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR);
            togglePos(board, pos, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.BOX_GOAL_CHAR);
            return checkDelta(board, pos, delta, validBox);// FIXME
        } else {
            //togglePos(board, pos, Config.BOX_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR);
            //togglePos(board, pos, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.BOX_GOAL_CHAR); // if empty char,
                                                                                // becomes box char.
                                                                                // else, box lands
                                                                                // on goal and box
                                                                                // becomes box goal
                                                                                // char.
            return 1;
        }
    } */

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

        if (delta[0] == 0 && delta[1] == 0) {
            return 0;
        }

        int[] noMag = new int[2];
        if (delta[0] != 0) {
            noMag[0] = delta[0] / Math.abs(delta[0]);
            noMag[1] = 0;
        } else {
            noMag[0] = 0;
            noMag[1] = delta[1] / Math.abs(delta[1]);
        }
        
        for (int i = 0; i < Math.abs(delta[0]); ++i) { // runs thru [0] cell of delta // runs thru
            int doMoveRes =  doMove(board, pos, noMag);                                        // twice when not wanted
            if(doMoveRes < 1) {
            return doMoveRes;
             }
            
        }

        for (int j = 0; j < Math.abs(delta[1]); ++j) { // runs thru [1] cell of delta
            int doMoveRes = doMove(board, pos, noMag);
            if (doMoveRes < 1) {
                return doMoveRes;
            }
        }

        return 1;
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
     */ // shiftbix moves bix to other spot
    // shiftbox returns -5 if new spot is a box
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        // char workChar;
        /*char[] valid = new char[2];
        valid[0] = Config.WORK_GOAL_CHAR;
        valid[1] = Config.WORKER_CHAR;
        // System.out.println("happens this many times");
        if (checkDelta(board, pos, delta, valid) < 1 && checkDelta(board, pos, delta, valid) != -5) {
            System.out.println("checkdelta failed" + checkDelta(board, pos, delta, valid));
            return checkDelta(board, pos, delta, valid);
        } // doesnt reach past here
        if ((checkDelta(board, pos, delta, valid) == -5) && shiftBox(board, pos, delta) < 0) { // not
                                                                                               // sure
                                                                                               // what
                                                                                               // this
                                                                                               // does,
                                                                                               // replace
                                                                                               // Config.LEVELS
                                                                                               // with
                                                                                               // board
            System.out.println("problem here");
            return 0;
        }
        if (checkDelta(board, pos, delta, valid) == -5) { // if box contains character, shiftbox
            System.out.println("problem at shiftbox");
            shiftBox(board, pos, delta);
        }
        togglePos(board, pos, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR);
        // togglePos(board, pos, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR); //if
        // moves onto empty space, becomes worker char, otherwise moves to
        // goal char and becomes work_goal_char, step 3
        // after worker moves, if WORKER_CHAR, becomes EMPTY_CHAR, else is WOR_GOAL_CHAR
        // and becomes GOAL_CHAR after moving
        pos[0] = pos[0] + delta[0];
        pos[1] = pos[1] + delta[1];
        togglePos(board, pos, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WORK_GOAL_CHAR);
        System.out.println("pos[0] " + pos[0]);
        System.out.println("pos[1] " + pos[1]);
        // togglePos(board, pos, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR);
        // pos[0] = pos[0] + delta[0];
        // pos[1] = pos[1] + delta[1];
        // System.out.println(pos[0] + " " + pos[1] + "domove");
        // if ((checkDelta(Config.LEVELS[1], pos, delta, valid) < 1) &&
        // (checkDelta(Config.LEVELS[1], pos, delta, valid) != 5)) {
        // return (checkDelta(Config.LEVELS[1], pos, delta, valid));
        // }
        return 1;
    } */
     
        
            char[] valid = new char[2];
            valid[0] = Config.WORK_GOAL_CHAR;
            valid[1] = Config.WORKER_CHAR;
            int[] newPos = new int[2];
            newPos[0] = pos[0] + delta[0];
            newPos[1] = pos[1] + delta[1];
            int verifyD = checkDelta(board, pos, delta, valid);
            if (verifyD < 0) {
                if (verifyD == -5) {
                    int shiftVal = shiftBox(board, newPos, delta);
                    if (shiftVal < 0) {
                        return 0;
                    } else {
                        verifyD = 1; //so it runs thru later if statement
                    }
                }
                if (verifyD < 1) {
                    return verifyD;
                }
            }
            if (verifyD == 1) {
                togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
                togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);
                pos[0] = newPos[0];
                pos[1] = newPos[1];
            }

            return 1;
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

        char[] valid = new char[7];
        valid[0] = Config.WALL_CHAR;
        valid[1] = Config.GOAL_CHAR;
        valid[2] = Config.BOX_CHAR;
        valid[3] = Config.BOX_GOAL_CHAR;
        valid[4] = Config.EMPTY_CHAR;
        valid[5] = Config.WORK_GOAL_CHAR;
        valid[6] = Config.WORKER_CHAR;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Sokoban!");
        int maxLvl = Config.LEVELS.length - 1;
        int minLvl = -1;
        String charPrompt = "Play again? (y/n) ";
        int[] pos = new int[2];
        int moveCounter = 0;
        Random rand = new Random(Config.SEED);
        
        do {
            String prompt = "Choose a level between 0 and " + maxLvl + ": ";
            int input = promptInt(sc, prompt, minLvl, maxLvl); // lvl chosen
            String garbage = sc.nextLine();
            if (input == -1) {
                input = rand.nextInt(maxLvl + 1);
            }
            int levelCheck = checkLevel(input, Config.LEVELS, Config.GOALS);
            if (levelCheck == 1) { // check array
            System.out.println("Sokoban Level " + input);
            } else if (levelCheck == 0) {
                System.out.println("Level ​lvl must be 0 or greater!");
            } else if (levelCheck == -1) {
                System.out.println("Error with Config.LEVELS");
            } else if (levelCheck == -2) {
                System.out.println("Error with Config.GOALS");
            } else if (levelCheck == -3) {
                System.out.println("Level lvl does not contain any boxes.");
            } else if (levelCheck == -4) {
                System.out.println("Level lvl does not have the same number of boxes as goals.");
            } else if (levelCheck == -5) {
                System.out.println("Level lvl has a goal location that is a wall.");
            } else if (levelCheck == -6) {
                System.out.println("Level lvl has 0 or more than 1 worker(s).");
            } else if (levelCheck == -7) {
                System.out.println("Level lvl contains duplicate goals.");
            } else {
                System.out.println("Unknown error");
            }

            String prompt1 = ": ";
            String str1 = "";
            char[][] board = initBoard(input, Config.LEVELS, Config.GOALS, pos);
            while (true) {
                // char[][] board = initBoard(input, Config.LEVELS, Config.GOALS, pos);
                while (true) {
                    printBoard(board);
                    str1 = promptString(sc, prompt1); // movement vector string**
                    if (str1.length() == 0) {
                        continue;
                    }
                    break;
                }

                if (str1.charAt(0) == Config.QUIT_CHAR) { // if user inputs q
                    break;
                }

                else {
                    int[] delta = calcDelta(str1); // movement vector calculated
                    for (int i = 0; i < board.length; ++i) { // finds where worker be at
                        for (int j = 0; j < board[i].length; ++j) {
                            if (board[i][j] == Config.WORKER_CHAR) {
                                pos[0] = i;
                                pos[1] = j;
                                break;
                            }
                        }
                    }


                    if (delta[0] != 0 || delta[1] != 0) { // checks not {0,0}
                        processMove(board, pos, delta);
                        moveCounter += Math.abs(delta[0] + delta[1]);
                    }
                    if (checkWin(board) == true) {
                        System.out.println("Congratulations! You won in " + moveCounter + " moves!");
                        printBoard(board);
                        break;
                    }
                    
                    continue;
                }
            }
        } while (promptChar(sc, charPrompt) == 'y');
        System.out.println("Thanks for playing!");

    }

}
