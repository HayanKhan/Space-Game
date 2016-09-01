import java.util.Timer;
import java.util.TimerTask;

public class AbilityTimer {
	
	private int interval; 
	private Timer timer;
	private int seconds;
	private boolean isOnCooldown = false;
	private int delay = 1000;
    private int period = 1000;
	
	public AbilityTimer(int seconds){
		this.seconds = seconds;
	}
	
	public void resetTimer(){
		
	    isOnCooldown  = true;
	    timer = new Timer();
	    interval = seconds;;
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	          	setInterval();
	            if (interval == 0)
	            	isOnCooldown = false;
	        }
	    }, delay, period);
	}
	
	private final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	
	public boolean isOnCooldown(){
		return isOnCooldown;
	}
	
	public int getInterval(){
		return interval;
	}
}

