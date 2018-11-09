//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: Sokoban.java
// Course: CS 200 Fall 2018
//
// Author: Connor Hanson
// Email: cbhanson2@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Laura Garling
// Partner Email: garling@wisc.edu
// Lecturer's Name: Marc Renualt
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// _X__ We have both read and understand the course Pair Programming Policy.
// _X__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: Kael Hanson: He helped work through an issue with the Scanner.NextInt()
// Online Sources: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.util.Scanner;
import java.util.Random;


public class Sokoban {
    // This class runs the game, Sokoban, where a worker pushed a box onto a goal in order to win.

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
                int input = sc.nextInt(); // input is the user's input of what level they choose to
                                          // play
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Invalid value."); // prints if the user's input is smallest
                                                          // than the minimum level or larger than
                                                          // the maximum level
                }
            } else {
                System.out.println("Invalid value."); // prints if user's input is not an integer
                String s1 = sc.next(); // closes nextInt()
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
        System.out.print(prompt); // prompts the user input
        char input = sc.next().toLowerCase().charAt(0); // reads in the user's input of as char,
                                                        // converts to lower case and parses white
                                                        // space
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
        System.out.print(prompt); // prompts the user input
        String input = sc.nextLine().trim().toLowerCase(); // reads in the user's input of as a
                                                           // string, converts to lower case and
                                                           // parses white space
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

        char[][] boardInit = new char[levels[lvl].length][]; // creates a new array containing the
                                                             // length of each row
        for (int i = 0; i < levels[lvl].length; ++i) {
            boardInit[i] = new char[levels[lvl][i].length];
            for (int j = 0; j < levels[lvl][i].length; ++j) { // adds a dimension containing the
                                                              // length of each column
                boardInit[i][j] = levels[lvl][i][j];
                if (boardInit[i][j] == Config.WORKER_CHAR) { // assigns pos array with location of
                                                             // Config.WORKER_CHAR
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        for (int i = 0; i < goals[lvl].length; i += 2) { // runs through goal array
            int yAxis = goals[lvl][i];
            int xAxis = goals[lvl][i + 1];
            if (boardInit[yAxis][xAxis] == Config.WORKER_CHAR) { // changes worker char to worker
                                                                 // goal char if on goal
                boardInit[yAxis][xAxis] = Config.WORK_GOAL_CHAR;
            } else if (boardInit[yAxis][xAxis] == Config.BOX_CHAR) { // changed box char to box goal
                                                                     // char if on goal
                boardInit[yAxis][xAxis] = Config.BOX_GOAL_CHAR;
            } else {
                boardInit[yAxis][xAxis] = Config.GOAL_CHAR; // otherwise, leaves as a goal char
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

        for (int i = 0; i <= board[0].length + 1; ++i) { // iterates through to print top wall
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();

        for (int i = 0; i < board.length; ++i) { // iterates through to print body + side wall
            System.out.print(Config.WALL_CHAR);
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println(Config.WALL_CHAR);
        }

        for (int i = 0; i <= board[board.length - 1].length + 1; ++i) {// iterates through to print
                                                                       // bottom wall
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
            return -1;
        }

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
        // Test 8 -- checks for duplicate goals
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
        int[] moveVec = new int[2]; //returned movement vector
        int moveDir; //Direction of movement
        int moveMag; //Magnitude of movement
        boolean hasInt = false; //Checks if string has an int after first char
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

        moveDir = (int) moveStr.charAt(0); //finds movement direction

        if ((char) moveDir == Config.UP_CHAR) { //branches assign movement direction
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
        // Check 1
        if (pos == null || pos.length != 2 || pos[0] >= board.length || pos[0] < 0
            || pos[1] >= board[pos[0]].length || pos[1] < 0) { // checks if null
            return -1;
        }
        // End of Check 1

        // Check 2
        boolean test2 = false;
        for (char validChars : valid) { // uses enhanced for loop to iterate through valid array
            if (validChars == board[pos[0]][pos[1]]) { // checks if pos is a valid character in the
                                                       // valid array
                test2 = true;
            }
        }
        if (test2 == false) {
            return -2;
        }
        // End of Check 2

        // Check 3
        if (delta == null) { // checks if null
            return -3;
        }
        // End of Check 3

        // Check 4
        if (delta.length != 2) { // checks if delta is length 3
            return -3;
        }
        // End of Check 4

        // Check 5
        int newY = pos[0] + delta[0]; // creates new pos by adding on delta (user's input converted
                                      // to move)
        int newX = pos[1] + delta[1];

        if (newY < 0 || newY >= board.length) { // checks if on board
            return -4;

        }
        if (newX < 0 || newX >= board[newY].length) { // checks if on board
            return -4;
        }

        if (board[newY][newX] == Config.WALL_CHAR) { // checks if new position is a wall character
            return -4;
        }
        // End of Check 5

        // Check 6
        if (board[newY][newX] == Config.BOX_CHAR || board[newY][newX] == Config.BOX_GOAL_CHAR) { // checks
                                                                                                 // if
                                                                                                 // new
                                                                                                 // position
                                                                                                 // is
                                                                                                 // a
                                                                                                 // box
                                                                                                 // character
            return -5;

        }
        // End of Check 6

        return 1; // if all tests pass, returns 1
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
        if (board[pos[0]][pos[1]] == val) { //if position is a certain char, then becomes opt1
            board[pos[0]][pos[1]] = opt1;
        } else {
            board[pos[0]][pos[1]] = opt2; //otherwise becomes opt2
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
        char[] validBox = new char[2]; //valid chars the box can be
        validBox[0] = Config.BOX_CHAR;
        validBox[1] = Config.BOX_GOAL_CHAR;
        int verifyD = checkDelta(board, pos, delta, validBox); //stores the return value of checkDelta
        if (verifyD != 1) { //prevents being able to go into the wall 
            return verifyD; 
        }

        if (verifyD == 1) { //if checkdelta passes, box shifts
            int[] newPos = new int[2]; //new position of the box
            newPos[0] = pos[0] + delta[0];
            newPos[1] = pos[1] + delta[1];

            togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR); //changes character
            togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        }
        return 1;

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

        if (delta[0] == 0 && delta[1] == 0) {
            return 0;
        }

        int[] noMag = new int[2]; //sets movement magnitude to 1 so that the loops 
                                  //can iterate right amount of times
        if (delta[0] != 0) {
            noMag[0] = delta[0] / Math.abs(delta[0]);
            noMag[1] = 0;
        } else {
            noMag[0] = 0;
            noMag[1] = delta[1] / Math.abs(delta[1]);
        }

        for (int i = 0; i < Math.abs(delta[0]); ++i) { // runs thru [0] cell of delta // runs thru
            int doMoveRes = doMove(board, pos, noMag); // twice when not wanted
            if (doMoveRes < 1) {
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
     */ 
    public static int doMove(char[][] board, int[] pos, int[] delta) {

        char[] valid = new char[2]; //valid characters the worker can be
        valid[0] = Config.WORK_GOAL_CHAR;
        valid[1] = Config.WORKER_CHAR;
        int[] newPos = new int[2]; //creates new position array with delta for worker
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
        int verifyD = checkDelta(board, pos, delta, valid); //makes sure that checkdelta works
        if (verifyD < 0) {
            if (verifyD == -5) {
                int shiftVal = shiftBox(board, newPos, delta);
                if (shiftVal < 0) {
                    return 0;
                } else {
                    verifyD = 1; // so it runs through later if statement
                }
            }
            if (verifyD < 1) {
                return verifyD;
            }
        }
        if (verifyD == 1) {
            togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR); 
                                                                    //changes character with togglePos
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
            for (int j = 0; j < board[i].length; ++j) { //loop verifies that all the goal chars are
                                                        //covered by boxes only
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
        int maxLvl = Config.LEVELS.length - 1; //max level it can be
        int minLvl = -1; //min level it can be, level -1 randomly chooses between levels
        String charPrompt = "Play again? (y/n) "; //prompts to play again at end of game
        int[] pos = new int[2]; //worker position array
        Random rand = new Random(Config.SEED); //random generator for lvl = -1
        int moveCounter = 0; //counts the moves

        do {
            String prompt = "Choose a level between 0 and " + maxLvl + ": ";
            int input = promptInt(sc, prompt, minLvl, maxLvl); // lvl chosen
            String garbage = sc.nextLine(); //reads next line after sc.nextInt
            if (input == -1) {
                input = rand.nextInt(maxLvl + 1);
            }
            int levelCheck = checkLevel(input, Config.LEVELS, Config.GOALS); //checkLevel return val
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
            String prompt1 = ": "; //prompts input for movement
            String str1 = ""; //initializes string input
            char[][] board = initBoard(input, Config.LEVELS, Config.GOALS, pos); //board array initialized
            while (true) { //game loop
                while (true) { //game loop pt 2
                    printBoard(board); 
                    str1 = promptString(sc, prompt1); // movement vector string**
                    if (str1.length() == 0) {
                        continue;
                    }
                    break;
                }
                if (str1.charAt(0) == Config.QUIT_CHAR) { // if user inputs q
                    moveCounter = 0; //resets movement counter to 0 if user quits game loop
                    break;
                } else {
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
                        moveCounter += Math.abs(delta[0] + delta[1]); //adds each spot moved to
                                                                      //movement counter
                    }
                    if (checkWin(board) == true) {
                        System.out
                            .println("Congratulations! You won in " + moveCounter + " moves!");
                        printBoard(board);
                        moveCounter = 0; //resets movement counter after win
                        break;
                    }

                    continue;
                }
            }
        } while (promptChar(sc, charPrompt) == 'y');
        System.out.println("Thanks for playing!");
    }
}

