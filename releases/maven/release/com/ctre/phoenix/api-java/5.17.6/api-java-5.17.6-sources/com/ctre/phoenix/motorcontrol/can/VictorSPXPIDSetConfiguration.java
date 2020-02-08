package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BasePIDSetConfiguration;

/**
 * Configs available to VictorSPX's PID
 */
public class VictorSPXPIDSetConfiguration extends BasePIDSetConfiguration {
    /**
     * Remote feedback device to select for this PID
     */
    public RemoteFeedbackDevice selectedFeedbackSensor;

    public VictorSPXPIDSetConfiguration() {
        selectedFeedbackSensor = RemoteFeedbackDevice.None;
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

        String retstr = prependString + ".selectedFeedbackSensor = " + selectedFeedbackSensor.toString() + ";\n";
        retstr += super.toString(prependString);
        return retstr;
    }


}

