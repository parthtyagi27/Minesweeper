import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * This class plays the sound file, which is a bomb exploding
 * There is no external method playing the sound, the sound is played in the constructor itself
 * The constructor plays loads and plays the sound
 */
public class Sound implements Runnable
{
	//the sound class implements Runnable so it can be used another thread

	@Override
	public void run()
	{
		//create a new URL pointing to the sound file
		URL url = Sound.class.getClassLoader().getResource("sound.wav");
		AudioInputStream audioIn;
		try
		{
			//get info from the sound file and play the sound
			audioIn = AudioSystem.getAudioInputStream(url);
			AudioFormat format = audioIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioIn);
			clip.start();
			Thread.sleep((long) ((clip.getMicrosecondLength()) * 0.001) - 1200); //Pause the main thread until the sound finishes playing
			clip.close();
			audioIn.close();
			//catch exceptions
		} catch (UnsupportedAudioFileException | IOException e)
		{
			e.printStackTrace();
		} catch (LineUnavailableException e)
		{
			e.printStackTrace();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}
}