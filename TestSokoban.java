/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;
import java.util.ArrayList;

/**
 * This class contains a few methods for testing methods in the Sokoban
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * you are ready for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard(); //out of bounds: 2, maybe previous exception?
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }
    
    private static void testCheckLevel() {
        int numTests = 8;
        int passed = numTests;
        int res;
        
        //Test 1
        if((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == 0) {
            System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }
        
        //Test 2
        char[][][] lvl = new char[2][3][0];
        if((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) == -1) {
            System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }
        
        //Test 3
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -2) {
            System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
            passed--;
        }
        
        //Test 4
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -3) {
            System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
            passed--;
        }

        //Test 5
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -4) {
            System.out.println("FAILED: Sokoban.checkLevel Test 5. Expected -4, but value returned " + res);
            passed--;
        }
        
        //Test 6
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -5) {
            System.out.println("FAILED: Sokoban.checkLevel Test 6. Expected -5, but value returned " + res);
            passed--;
        }
        
        //Test 7
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -6) {
            System.out.println("FAILED: Sokoban.checkLevel Test 7. Expected -6, but value returned " + res);
            passed--;
        }
        
        //Test 8
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, Config.GOALS)) == -7) {
            System.out.println("FAILED: Sokoban.checkLevel Test 8. Expected -7, but value returned " + res);
            passed--;
        }
       
        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if(a == null || b == null)
            return false;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++) {
            if(!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static void testInitBoard() {
        int numTests = 1;
        int passed = numTests;

        //Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1); //out of bounds :2
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest1, bCompTest1)){
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);            
            passed--;
        }
        //End of Test 1
        
        //Test 2
        
        int[] pTest2 = new int[2];
        char[][] bTest2 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest2);
        if(!Arrays.equals(pTest2, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest2));
            passed--;
        }
        char[][] bCompTest2 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                                           {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest2, bCompTest2)){
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);            
            passed--;
        } 
        
        //FIXME Add more tests
        
        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }
    
    public static void testCheckWin() {
        
        int numTests = 3;
        int passed = numTests;
        
        ArrayList <Integer> goalList = new ArrayList <Integer>(); 
        for (int m=0; m < Config.GOALS.length; ++m) {
            for (int n = 0; n < Config.GOALS[m].length; ++n) {
                goalList.add(new Integer (Config.GOALS[m][n]));
                System.out.println(Arrays.toString(goalList.toArray()));

            }}
        
        // Level 1
        for (int i = 0; i < Config.LEVELS[0].length; i++ ) {
            for (int j = 0; j < Config.LEVELS[0][i].length; j++) {
                if (Config.LEVELS[0][i][j] == Config.BOX_CHAR) {
                    if (i == goalList.get(0)) {
                        if (j == goalList.get(1)) {
                            passed++;
                        }
                    }
        }}}
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
        //Level 2
        
        for (int i = 0; i < Config.LEVELS[1].length; i++ ) {
            for (int j = 0; j < Config.LEVELS[1][i].length; j++) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) {
                    if (i == goalList.get(2)) {
                        if (j == goalList.get(3)) {
                            passed++;
                        }
                    }}}}
        
        //Level 3
        
       /** for (int i = 0; i < Config.LEVELS[2].length; i++ ) {
            for (int j = 0; j < Config.LEVELS[2][i].length; j++) {
                if (Config.LEVELS[2][i][j] == Config.BOX_CHAR) {
                    if (i == goalList.get(4)) {
                        if (j == goalList.get(5)) {
                            passed++;
                        }
                    }}}} */
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");

    }
    
    private static void testCalcDelta() {
        int passed = 0;
        int numTests = 2;
        
        
        int[] plsWork = Sokoban.calcDelta("81");
        
        System.out.println(plsWork[0]);

      if (plsWork[0] == -1) {
          if (plsWork[1] == 0) {
             passed ++;
          
      }}
      
      int[] plsWorkPt2 = Sokoban.calcDelta("65");
      
      if (plsWorkPt2[0] == 0) {
          if (plsWorkPt2[1] == 5) {
              passed++;
          }
      }
      
      System.out.println("testCalcDelta passed" + passed + " of " + numTests + " tests.");
    }
    
    private static void testCheckDelta() {
        //FIXME
        int passed = 0;
        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                    System.out.println(Config.LEVELS[1][i][j]);
                    break;
                }
            }
        }
        
        
    }
    
    private static void testTogglePos() {
        int passed = 0; 
        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        System.out.print("Should return " + Config.BOX_CHAR + " returns: ");
        Sokoban.togglePos(Config.LEVELS[1], pos, Config.WORKER_CHAR, Config.BOX_CHAR, Config.WORKER_CHAR);

        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        System.out.println("Should return " + Config.WORK_GOAL_CHAR + " returns: ");
        Sokoban.togglePos(Config.LEVELS[1], pos, Config.BOX_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);
    }

    private static void testShiftBox() {
        int passed = 0; 
        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                    System.out.print( i + j +  "this is i and j");
                } if (Config.LEVELS[1][i][j] == Config.WORKER_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                    System.out.print( i + j +  "this worker ");
                }
            }
        } 
        System.out.print(pos[0] + " " + pos[1] + "this is pos"); 
        System.out.print("testShiftBox: Should return 1, returns : " + Sokoban.shiftBox(Config.LEVELS[1], pos, Sokoban.calcDelta("81")));
    }

    private static void testDoMove() {
        int[] pos = new int[2];
        for (int i = 0; i < Config.LEVELS[1].length; ++i) {
            for (int j = 0; j < Config.LEVELS[1][i].length; ++j) {
                if (Config.LEVELS[1][i][j] == Config.BOX_CHAR) {
                    pos[0] = i;
                    pos[1] = j; }}}
        System.out.print("testDoMove: Should return 1, returns : " + Sokoban.doMove(Config.LEVELS[1], pos, Sokoban.calcDelta("81")));
    }

    private static void testProcessMove() {
        //FIXME
    }    

}
