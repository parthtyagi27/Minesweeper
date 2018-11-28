import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * This class was created using WindowBuilder to create an aesthetically please main menu. 
 * The class extends JFrame so all the JFrame methods are directly accessed by the object.
 * This makes the window resuable several times through out the program lifecycle. 
 */

public class MainMenu extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu()
	{
		setResizable(false);
		setTitle("Minesweeper MainMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblWelcomeToMinesweeper = new JLabel("Welcome To MineSweeper!");
		contentPane.add(lblWelcomeToMinesweeper);
		
		JLabel lblClickOnThe = new JLabel("Click on the following buttons to play the game.");
		contentPane.add(lblClickOnThe);
		
		JButton btnGui = new JButton("GUI");
		btnGui.setToolTipText("Click on this button to play the GUI version of Minesweeper");
		contentPane.add(btnGui);
		
		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message = "1) The buttons are arranged in a 8 by 8 grid and each button will now be referred to as a “square”. Each square has a “X” on it, denoting that it is an unexplored square.\n" + 
"2) In this 8 by 8 grid of squares, a finite amount of squares are actually mines but they are indistinguishable because mine squares and normal squares appear the same.\n" +
"3) The objective of the game is to uncover all the non mine squares so only the mine squares are left.\n" +
"4) By clicking a square either 2 things will happen. If the square is a mine then the game will stop as you have lost and you will be returned to the main menu.\n" +
"5) If the clicked square is not a mine, then a number will appear on the square. This number denotes the number of mines that are adjacent (beside, up, down, diagonal) to the selected square.\n" +
"6) You may continue clicking squares until you have uncovered a mine.";
				JOptionPane.showMessageDialog(null, message, "MineSweeper", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(btnInstructions);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Click on this button to quit");
		contentPane.add(btnQuit);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setToolTipText("Click on this button for credits.");
		contentPane.add(btnAbout);
		
		btnGui.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Main.playGUI();
				setVisible(false);
				dispose();
			}
		});
		
		btnAbout.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Created by: Parth Tyagi", "MineSweeper", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnQuit.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
	}



}
