import javax.swing.JButton;

public class Mine
{
	//respective fields in the mine object
	private boolean isMine;
	public boolean isMine()
	{
		return isMine;
	}

	public void setMine(boolean isMine)
	{
		this.isMine = isMine;
	}

	public String getValue()
	{
		return value;
	}
	public JButton getButton()
	{
		return button;
	}

	public void setValue(String value)
	{
		this.value = value;
		button.setText(value);
	}
	

	private String value;
	private JButton button;
	
	public Mine(String value, boolean isMine)
	{
		this.value = value;
		this.isMine = isMine;
		button = new JButton(value + "");
	}
}
