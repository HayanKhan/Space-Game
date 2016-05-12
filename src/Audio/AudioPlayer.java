package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	//Class properties
	private Clip clip;
	public static int LOOP_CONTINOUSLY = -1;
	private static boolean audioOn = true;
	/**
	 * Constructor for initializing the audio player
	 * @param s String path to the music file
	 */
	public AudioPlayer(String s){
		try{
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels() * 2, baseFormat.getSampleRate(),false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** Plays the audio player*/
	public void play(boolean loopContinously){
		if (clip == null || !audioOn) //makes it so if the audio was off from the beginning, the audio clip will not play
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		if (loopContinously)
			clip.loop(LOOP_CONTINOUSLY);
		
	}
	/** Stops the audio player*/
	public void stop(){
		if (clip.isRunning())
			clip.stop();
	}
	
	public void close(){
		stop();
		clip.close();
	}
	/** Changes the audio state from on to off or off to on */
	public static void changeAudioState(){
		if (audioOn)
			AudioPlayer.audioOn = false;
		else if (!audioOn)
			AudioPlayer.audioOn = true;
	}
	/** @return The boolean true or false audio state */
	public static boolean getAudioState(){
		return audioOn;
	}
}
