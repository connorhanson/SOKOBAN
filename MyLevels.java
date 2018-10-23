import java.util.Arrays;

public class MyLevels {
	
	public static final char EMPTY_CHAR = ' ';
	public static final char BOX_CHAR = '='; // Box character
	public static final char WALL_CHAR   = '#'; // Wall character
	public static final char WORKER_CHAR  = '@'; // Worker character
	public static final char GOAL_CHAR  = '.'; // Goal character
	public static final char BOX_GOAL_CHAR  = '*'; // Box on a goal character
	public static final char WORK_GOAL_CHAR  = '+'; // Worker on a goal character
	
	public static final long SEED            = 1234; //the random seed

	public static final char[][][] LEVELS = {
			{
				//{'#', '#', '#'}
				//{' ', '=', '.'}
				//{'@', '#', ' '}
				
				{WALL_CHAR, WALL_CHAR, WALL_CHAR}, 
				{EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR},
				{WORKER_CHAR, WALL_CHAR, EMPTY_CHAR},
			}
			,
			{
				//{'@', ' ', '#', '#', ' ', ' '}
				//{'#', ' ', ' ', ' ', ' ', ' '}
				//{'#', ' ', '#', '#', '#', ' '}
				//{'#', '=', '#', '#', '#', '.'}
				//{' ', '=', ' ', ' ', ' ', '.'}
				//{' ', ' ', ' ', '#', ' ', ' '}
				
				{EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				{WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				{WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR},
				{WALL_CHAR, BOX_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR},
				{EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				
			}
			,
			{
				//{'#', '#', '#', '#', '#', '#', ' ', ' ', '#'}
				//{'@', '=', ' ', ' ', ' ', ' ', ' ', ' ', '#'}
				//{'#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#'}
				//{' ', '=', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '#'}
				//{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '#'}
				//{' ', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', '.', '#'}
				//{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#'}
				//{' ', '=', '#', ' ', ' ', ' ', ' ', ' ', ' '}
				//{' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' '}
				
				{WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
				{WORKER_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
				{WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
				{EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
				{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
				{EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR},
				{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR},
				{EMPTY_CHAR, BOX_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				{EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
				
			}
	};
	
	public static final int[][] GOALS = {
			{2, 1},
			{5, 3, 5, 4},
			{10, 3, 10, 4, 10, 5}
	};
	
    public static final char UP_CHAR = '8';
    public static final char DOWN_CHAR = '2';
    public static final char LEFT_CHAR = '4';
    public static final char RIGHT_CHAR = '6';
    public static final char QUIT_CHAR = 'q';
	//public static void main(String[] args)
	//{
		//System.out.print(Arrays.toString(LEVELS));
	//}
}
