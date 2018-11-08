//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: TestSokoban
// Files: TestSokoban.java
// Course: CS 200 Fall 2018
//
// Author: Laura Garling
// Email: garling@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Connor Hanson
// Partner Email: chanson@wisc.edu
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
// Persons: NONE
// Online Sources: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;
import java.util.ArrayList;

/**
 * This class contains a few methods for testing methods in the Sokoban class as they are developed.
 * These methods are all private as they are only intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }

    /**
     * This method tests the Sokoban.checkLevel method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testCheckLevel() {
        int numTests = 8; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails
        int res; // represents the result of calling Sokoban.checkLevel

        // Test 1
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == 0) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }

        // Test 2
        char[][][] lvl = new char[2][3][0];
        if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) == -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 3
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
            passed--;
        }

        // Test 4
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -3) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
            passed--;
        }

        // Test 5
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -4) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned " + res);
            passed--;
        }

        // Test 6
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -5) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 6. Expected -5, but value returned " + res);
            passed--;
        }

        // Test 7
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -6) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 7. Expected -6, but value returned " + res);
            passed--;
        }

        // Test 8
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -7) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 8. Expected -7, but value returned " + res);
            passed--;
        }

        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) { // tests that a and b are parallel
                                                                // arrays
        if (a == null || b == null)
            return false;
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method tests the Sokoban.InitBoard method based on different input values and prints out
     * number of tests passed out of total number of tests.
     */
    public static void testInitBoard() {
        int numTests = 3; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails
        // Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if (!Arrays.equals(pTest1, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        if (!compBoards(bTest1, bCompTest1)) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);
            passed--;
        }
        // End of Test 1

        // Test 2

        int[] pTest2 = new int[2];
        char[][] bTest2 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest2);
        if (!Arrays.equals(pTest2, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest2));
            passed--;
        }
        char[][] bCompTest2 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        if (!compBoards(bTest2, bCompTest2)) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);
            passed--;
        }
        // End of Test 2

        // Test 3

        int[] pTest3 = new int[2];
        char[][] bTest3 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest3);
        if (!Arrays.equals(pTest3, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
                    + Arrays.toString(pTest3));
            passed--;
        }
        char[][] bCompTest3 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        if (!compBoards(bTest3, bCompTest3)) {
            System.out.println("FAILED: Sokoban.initBoard Test 3. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest3);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest3);
            passed--;
        }
        // End of Test 3

        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method tests the Sokoban.checkWin method based on different input values and prints out
     * number of tests passed out of total number of tests.
     */
    public static void testCheckWin() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        ArrayList<Integer> goalList = new ArrayList<Integer>(); // creates an arrayList of the goals
                                                                // in Config.GOALS
        for (int m = 0; m < Config.GOALS.length; ++m) {
            for (int n = 0; n < Config.GOALS[m].length; ++n) {
                goalList.add(new Integer(Config.GOALS[m][n]));


            }
        }

        // Level 1
        for (int i = 0; i < Config.LEVELS[0].length; i++) {
            for (int j = 0; j < Config.LEVELS[0][i].length; j++) {
                if (Config.LEVELS[0][i][j] == Config.BOX_CHAR) { // iterates through the first
                                                                 // level, if locates a
                                                                 // Config.BOX_CHAR, checks to make
                                                                 // sure that it is in the goalsList
                                                                 // i.e. a valid goal
                    if (i == goalList.get(0)) {
                        if (j == goalList.get(1)) {
                            passed--;
                        }
                    }
                }
            }
        }

        // Level 2

        for (int i = 0; i < Config.LEVELS[1].length; i++) {
            for (int j = 0; j < Config.LEVELS[1][i].length; j++) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) { // iterates through the second
                                                                 // level, if locates a
                                                                 // Config.BOX_CHAR, checks to make
                                                                 // sure that it is in the goalsList
                                                                 // i.e. a valid goal
                    if (i == goalList.get(2)) {
                        if (j == goalList.get(3)) {
                            passed--;
                        }
                    }
                }
            }
        }

        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the Sokoban.calcDelta method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testCalcDelta() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails
        // Test 1

        int[] calcDelta1 = Sokoban.calcDelta("81"); // creates an array of the movement vector
                                                    // calculated in Sokoban.calcDelta() with a test
                                                    // movement string of "81"

        if (calcDelta1[0] != -1) {
            if (calcDelta1[1] != 0) {
                passed--;

            }
        }
        // End of Test 1

        // Test 2
        int[] calcDelta2 = Sokoban.calcDelta("65"); // creates an array of the movement vector
                                                    // calculated in Sokoban.calcDelta() with a test
                                                    // movement string of "65"

        if (calcDelta2[0] != 0) {
            if (calcDelta2[1] != 5) {
                passed--;
            }
        }
        // End of Test 2
        System.out.println("testCalcDelta passed: " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method tests the Sokoban.checkDelta method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testCheckDelta() {
        int numTests = 2;
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        char[] valid = new char[7]; // an array containing all the characters that are contained in
                                    // Config.LEVELS
        valid[0] = Config.WALL_CHAR;
        valid[1] = Config.GOAL_CHAR;
        valid[2] = Config.BOX_CHAR;
        valid[3] = Config.BOX_GOAL_CHAR;
        valid[4] = Config.EMPTY_CHAR;
        valid[5] = Config.WORK_GOAL_CHAR;
        valid[6] = Config.WORKER_CHAR;

        // Test 1
        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.WORKER_CHAR) { // iterates through the first
                                                                    // level to locate
                                                                    // Config.WORKER_CHAR
                    pos[0] = i;
                    pos[1] = j;
                    break;
                }
            }
        }
        // Test 1
        if (Sokoban.checkDelta(Config.LEVELS[1], pos, Sokoban.calcDelta("81"), valid) != 1) { // checks
                                                                                              // the
                                                                                              // first
                                                                                              // level
                                                                                              // with
                                                                                              // example
                                                                                              // movement
                                                                                              // strings
            passed--;
        }
        // End of Test 1

        // Test 2
        if (Sokoban.checkDelta(Config.LEVELS[1], pos, Sokoban.calcDelta("42"), valid) != 1) {
            passed--;
        }
        // End of Test 2

        System.out.println("testCheckDelta passed: " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the Sokoban.togglePos method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testTogglePos() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.WORKER_CHAR) { // iterates through the first
                                                                    // level and locates
                                                                    // Config.WORKER_CHAR and stores
                                                                    // the row and column in the pos
                                                                    // array
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // Test 1
        Sokoban.togglePos(Config.LEVELS[1], pos, Config.WORKER_CHAR, Config.BOX_CHAR,
            Config.WORKER_CHAR);
        if (Config.LEVELS[1][pos[0]][pos[1]] != Config.BOX_CHAR) {
            passed--;
        }
        // End of Test 1
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) { // iterates through the first level
                                                                 // and locates Config.BOX_CHAR and
                                                                 // stores the row and column in the
                                                                 // pos array
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // Test 2
        Sokoban.togglePos(Config.LEVELS[1], pos, Config.BOX_CHAR, Config.WORK_GOAL_CHAR,
            Config.WORKER_CHAR);
        if (Config.LEVELS[1][pos[0]][pos[1]] != Config.WORK_GOAL_CHAR) {
            passed--;
        }
        // End of Test 2
        System.out.println("testTogglePos passed: " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the Sokoban.shiftBox method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testShiftBox() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) { // iterates through the first
                                                                 // level and locates
                                                                 // Config.BOX_CHAR and stores the
                                                                 // row and column in the pos array
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // Test 2
        if (Sokoban.shiftBox(Config.LEVELS[1], pos, Sokoban.calcDelta("81")) != 1) { // tests the
                                                                                     // example
                                                                                     // movement
                                                                                     // string of
                                                                                     // "81"
            passed--;
        }
        // End of Test 2

        for (int m = 0; m < Config.LEVELS[1].length; ++m) {
            for (int n = 0; n < Config.LEVELS[1][m].length; ++n) {
                if (Config.LEVELS[1][m][n] == Config.BOX_CHAR) {// iterates through the first level
                                                                // and locates Config.BOX_CHAR and
                                                                // stores the row and column in the
                                                                // pos array
                    pos[0] = m;
                    pos[1] = n;
                }
            }
        }
        // Test 1
        if (Sokoban.shiftBox(Config.LEVELS[1], pos, Sokoban.calcDelta("62")) != 1) { // tests the
                                                                                     // example
                                                                                     // movement
                                                                                     // string of
                                                                                     // "62"
            passed--;
        }
        // End of Test 1

        System.out.println("testShiftBox passed : " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method tests the Sokoban.doMove method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testDoMove() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) { // iterates through the first level
                                                                 // and locates Config.BOX_CHAR and
                                                                 // stores the row and column in the
                                                                 // pos array
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // Test 1
        if (Sokoban.doMove(Config.LEVELS[1], pos, Sokoban.calcDelta("42")) != -2) {
            passed--;
        }
        // End of Test 1
        // Test 2
        if (Sokoban.doMove(Config.LEVELS[0], pos, Sokoban.calcDelta("81")) != -1) {
            passed--;
        }
        // End of Test 2

        System.out.println("testDoMove: passed : " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method tests the Sokoban.processMove method based on different input values and prints
     * out number of tests passed out of total number of tests.
     */
    private static void testProcessMove() {
        int numTests = 2; // total number of tests in method
        int passed = numTests; // number of tests that are passed, initialized at numTests because
                               // the value of passed decreases by 1 if the test fails

        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) { // iterates through the first level
                                                                 // and locates Config.BOX_CHAR and
                                                                 // stores the row and column in the
                                                                 // pos array
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        // Test 1
        if (Sokoban.processMove(Config.LEVELS[1], pos, Sokoban.calcDelta("00")) != 0) {
            passed--;
        }
        // End of Test 1
        // Test 2
        if (Sokoban.processMove(Config.LEVELS[1], pos, Sokoban.calcDelta("20")) != 0) {
            passed--;
        }
        // End of Test 2
        System.out.print("testProcessMove: passed : " + passed + " of " + numTests + " tests.");
    }

}
