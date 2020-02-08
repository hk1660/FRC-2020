package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Configurables available to TalonSRX
 */
public class TalonSRXConfiguration extends com.ctre.phoenix.motorcontrol.can.BaseTalonConfiguration {

    /**
     * Peak current in amps
     * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
     */
    public int peakCurrentLimit;
    /**
     * Peak Current duration in milliseconds
     * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
     */
    public int peakCurrentDuration;
    /**
     * Continuous current in amps
     * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
     */
    public int continuousCurrentLimit;

	public TalonSRXConfiguration() {
        super(FeedbackDevice.QuadEncoder);
        peakCurrentLimit = 1;
        peakCurrentDuration = 1;
        continuousCurrentLimit = 1;
	}

    /**
     * @return String representation of all the configs
     */
	public String toString() {
		return toString("");
	}

    /**
     * @param prependString
     *              String to prepend to all the configs
     * @return String representation of all the configs
     */
    public String toString(String prependString) {


        String retstr = prependString + ".peakCurrentLimit = " + String.valueOf(peakCurrentLimit) + ";\n";
        retstr += prependString + ".peakCurrentDuration = " + String.valueOf(peakCurrentDuration) + ";\n";
        retstr += prependString + ".continuousCurrentLimit = " + String.valueOf(continuousCurrentLimit) + ";\n";
         retstr += super.toString(prependString);

       return retstr;
    }

}

