import java.util.Arrays;

public class MyLevels {
	
	
	public static final long SEED            = 1234; //the random seed

	public static final char[][][] LEVELS = {
			{
				//{'#', '#', '#'}
				//{' ', '=', '.'}
				//{'@', '#', ' '}
				
				{Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}, 
				{Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
				{Config.WORKER_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
			}
			,
			{
				//{'@', ' ', '#', '#', ' ', ' '}
				//{'#', ' ', ' ', ' ', ' ', ' '}
				//{'#', ' ', '#', '#', '#', ' '}
				//{'#', '=', '#', '#', '#', '.'}
				//{' ', '=', ' ', ' ', ' ', '.'}
				//{' ', ' ', ' ', '#', ' ', ' '}
				
				{Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				{Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				{Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
				{Config.WALL_CHAR, Config.BOX_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR},
				{Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				
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
				
				{Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
				{Config.WORKER_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
				{Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
				{Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
				{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
				{Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
				{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
				{Config.EMPTY_CHAR, Config.BOX_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
				
			}
	};
	
	public static final int[][] GOALS = {
			{1, 2}, //switch again if no work
			{3, 5, 4, 5},
			{3, 10, 4, 10, 5, 10}
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
