import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * This class is the GUI version of the game itself. 
 * The console version was deprecated due to useless features, and it was not practical. 
 * The class is build on a JFrame.
 * A GridLayout is used to arrange the buttons in a grid-like structure and keeps them proportional through any resizing
 * The Buttons are actually a separate object called Mines, which have a boolean, a String, wrapped around a button.
 * 
 */

public class GUI
{
	/*
	 * Declare Global Variables such as the board. 
	 */
	private static Mine[][] matrix;
	
	public static final short SIZE = 8;
	private static JFrame window; 
	private static int MINESCOUNT;
	private static ArrayList<int[]> mineCoordinates;
	
	public GUI(String n)
	{
		/*
		 * Initialize the GUI elements, initialize the matrix of mines and call appropriate functions
		 */
		matrix = new Mine[SIZE][SIZE];
		mineCoordinates = new ArrayList<>();
		populateBoard();
		displayBoard();
		generateMines();
		displayMines();
		bindHandlers();
	}
	private void bindHandlers()
	{
		/*
		 * This function binds a single actionEvent Handler to every mine object. 
		 * The mine object is accessed through a nested for loop.
		 * The same actionEvent is binded to all the mines. 
		 */
		for(short i = 0; i < SIZE; i++)
			for(short j = 0; j < SIZE; j++)
			{
				//Create an instance of the current mine to use later
				final Mine currentButton = matrix[i][j];
				matrix[i][j].getButton().addActionListener(new ActionListener()
				{
					
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						//check if the current square is a mine
						//if it is a mine, disable the entire board
						if(currentButton.isMine() == true)
							disable();
						else
							findAdjacent();
					}

					private void findAdjacent()
					{
						//loop through the entire board to find its coordinates to find adjacent mines
						int counter = 0;
						int x = 0, y = 0;
						
						for(short i = 0; i < SIZE; i++)
							for(short j = 0; j < SIZE; j++)
							{
								if(currentButton == matrix[i][j])
								{
									x = i;
									y = j;
								}
							}
						//use a brute try catch method to see if the adjacent squares are mines
						//we use a brute try catch system because adjacent mines could exceed the matrix dimensions
						try {if(matrix[x][y + 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x][y - 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x + 1][y].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x - 1][y].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x + 1][y + 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x - 1][y + 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x + 1][y - 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						try {if(matrix[x - 1][y - 1].isMine() == true) {counter++;}}catch(Exception ex) {};
						//update the value of the square to the adjacent mines
						currentButton.setValue(counter + "");
					}
					
					private void disable()
					{
						//Disable all the buttons as the user has lost using a for loop
						currentButton.setValue("*");
						for(short i = 0; i < SIZE; i++)
							for(short j = 0; j < SIZE; j++)
							{
								matrix[i][j].getButton().setEnabled(false);
							}
						//Create a new thread which plays the sound in a separate thread so the program is not frozen 
						Thread t = new Thread(new Sound());
						t.start();
						//Tell the player they have lost the game
						JOptionPane.showMessageDialog(null, "                       You've lost :/ \n You will now be returned to the main menu.", "MineSweeper", JOptionPane.INFORMATION_MESSAGE);
						//Close the current window and display the main menu
						MainMenu menu = new MainMenu();
						menu.setVisible(true);
						//dispose the current window - free its resources from RAM
						window.setVisible(false);
						window.dispose();
					}
				});
			}
	}
	private static void displayMines()
	{
		//Set the matrix coordinates in the mine arraylist to mines
		
		for(short i = 0; i < mineCoordinates.size(); i++)
		{
			//mine coordinate at index 0 is the x coordinate
			//mine coordinate at index 1 is the y coordinate
			matrix[mineCoordinates.get(i)[0]][mineCoordinates.get(i)[1]].setMine(true);
		}
	}
	private static void generateMines()
	{
		//Generate a random amount of mines between 6 and 20
		MINESCOUNT = ThreadLocalRandom.current().nextInt(6, 20);
		//Print out the amount of mines for testing 
		System.out.println("Mine Count: " + MINESCOUNT);
		for(short i = 0; i < MINESCOUNT; i++)
		{
			//create an array ints to hold mine coordinates
			int[] coordinates = new int[2];
			//Generate random numbers
			coordinates[0] = ThreadLocalRandom.current().nextInt(0, SIZE - 1);
			coordinates[1] = ThreadLocalRandom.current().nextInt(0, SIZE - 1);
			mineCoordinates.add(coordinates);
		}
	}
	private void displayBoard()
	{
		//display the window to the user 
		window = new JFrame("MineSweeper");
		//set the layout
		window.setLayout(new GridLayout(SIZE, SIZE));
		for(short i = 0; i < SIZE; i++)
			for(short j = 0; j < SIZE; j++)
				window.add(matrix[i][j].getButton());
		//set the size 
		window.setSize(500, 500);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void populateBoard()
	{
		//create a new mine object for every element in the matrix
		for(short i = 0; i < SIZE; i++)
			for(short j = 0; j < SIZE; j++)
			{
				matrix[i][j] = new Mine("X", false);
				matrix[i][j].getButton().setSize(50, 500);
			}
	}
	
}
