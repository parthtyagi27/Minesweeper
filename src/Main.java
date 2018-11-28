public class Main
{

	public static void main(String[] args)
	{
		MainMenu menu = new MainMenu();
		menu.setVisible(true);
	}
	
	public static void playGUI()
	{
		new GUI("X");
	}
	
	public static void playConsole()
	{
		new Console();
	}

}
