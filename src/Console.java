import java.util.Random;
import java.util.Scanner;

/*
 * This deprecated class plays the console version of Minesweeper.
 * This class was made for testing purposes in order to polish the GUI version.
 * This class contains many of the underlying principles used in the GUI version.
 */
public class Console
{
	public String[][] board;
	public static int SIZE = 7;
	public static int MINESSIZE = 6;
	
	public Console()
	{
		board = new String[SIZE][SIZE];
		populateBoardEmpty();
		populateMines();
		displayScreen();
		startGame();
	}
	
	private void startGame()
	{
		Scanner input = new Scanner(System.in);
		int x, y;
		while(isFinished() == false)
		{
			System.out.println("Enter coordinates of your next turn in format x y: ");
			String[] lines = input.nextLine().split(" ");
			x = Integer.parseInt(lines[0]);
			y = Integer.parseInt(lines[1]);
			findAdjacent(y, x);
			displayScreen();
		}
		input.close();
	}
	
	private boolean isFinished()
	{
		for(short i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				if(board[i][j] == "X")
					return false;
			}
		}
		return false;
	}

	public void displayScreen()
	{
		for(short i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	private void populateBoardEmpty()
	{
		for(short i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				board[i][j] = "X";
	}
	
	private void populateMines()
	{
		Random random = new Random();
		for(short i = 0; i < MINESSIZE; i++)
		{
			int x, y;
			x = random.nextInt(SIZE);
			y = random.nextInt(SIZE);
			board[x][y] = "*";
		}
	}
	
	private void findAdjacent(int x, int y)
	{
		int counter = 0;
		try {if(board[x + 1][y] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x + 1][y + 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x + 1][y - 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x - 1][y] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x - 1][y + 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x - 1][y - 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x][y + 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		try {if(board[x][y - 1] == "*") {counter++;}}catch(Exception ex) {ex.printStackTrace();}
		board[x][y] = "" + counter;
	}
}
