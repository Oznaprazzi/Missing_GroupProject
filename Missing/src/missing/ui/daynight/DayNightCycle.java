/*	File: DayNightCycle.java
 * 	Author
 *  Edward Kelly	300334192
 *  
 * 	Date			Author				Changes
 *  6 Oct 16		Edward Kelly		created class
 */
package missing.ui.daynight;

import java.util.TimerTask;

import missing.ui.controller.VControl;
/**
 * Responsible of keeping track of time and Day and Night cycles
 * Units of time are in game minutes. Speed of game minutes
 * are determined by call frequency to run() method which is
 * specified by VControl.CLOCK_TICK.
 * @author Edward Kelly
 *
 */
public class DayNightCycle extends TimerTask {
	/** Darkness limit for alpha. Must be <= 255 */
	private final int DARK_LIMIT = 150;
	/** Start time of cycle */
	private final int START_TIME = 200;
	/** Last minute of day */
	private final int END_OF_DAY = 1440;
	/** Time at which the world starts getting lighter */
	private final int START_LIGHTER = 300; //5am
	/** Time at which the world stops getting lighter */
	private final int END_LIGHTER = 600; //10am
	/** Time at which the world starts getting darker */
	private final int START_DARKER = 960; //4pm
	/** Time at which the world stops getting darker */
	private final int END_DARKER = 1260; //9pm
	/** Number of game minutes time increases per clock tick */
	private final int TIME_INC = 1;

	/** VControl this DayNightCycle is for */
	private VControl controller;
	/** Alpha value for darkness of world */
	private double alpha;
	/** Current time in game */
	private int currentTime;
	/** Change in alpha per clock tick */
	private double dAlpha = (double)DARK_LIMIT/(double)((END_LIGHTER-START_LIGHTER))*TIME_INC;
	
	public DayNightCycle(VControl frame){
		this.controller = frame;
		this.alpha = DARK_LIMIT;
		currentTime = START_TIME;
		controller.updateAlpha((int)alpha);
		controller.repaint();
	}
	
	@Override
	public void run(){
		if (currentTime >= END_OF_DAY){
			// Reached end of day so set time back to 0
			currentTime = 0;
		}
		else{
			currentTime+=TIME_INC;
		}
		
		// Get darker in the afternoon
		if (currentTime >= START_DARKER && currentTime <= END_DARKER){
			alpha = (double)((int)(10*(alpha + dAlpha)))/10;
			if (currentTime==END_DARKER){
				// round to max
				alpha = DARK_LIMIT;
			}
			if (alpha%1==0){
				//only update on whole numbers
				controller.updateAlpha((int)alpha);
				controller.repaint();
			}
		}
		// Get lighter in morning
		else if (currentTime >= START_LIGHTER && currentTime <= END_LIGHTER){
			alpha = (double)((int)(10*(alpha - dAlpha)))/10;
			if (currentTime==END_LIGHTER){
				//round to 0
				alpha = 0;
			}
			if (alpha%1==0){
				// only update on whole numbers
				controller.updateAlpha((int)alpha);
				controller.repaint();
			}
		}
	}

}

