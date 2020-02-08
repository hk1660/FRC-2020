package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BasePIDSetConfiguration;

/**
 * Configurables available to TalonSRX's PID
 */
public class BaseTalonPIDSetConfiguration extends BasePIDSetConfiguration {
    /**
     * Feedback device for a particular PID loop.
     * Note the FeedbackDevice enum holds all possible sensor types.  Consult product documentation to confirm what is available.
     * Alternatively the product specific enum can be used instead (see below).
     * <p>
     * <code>
     * configs.primaryPID.selectedFeedbackSensor = TalonSRXFeedbackDevice.QuadEncoder.toFeedbackDevice();
     * configs.primaryPID.selectedFeedbackSensor = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
     * </code>
     * </p>
     */
    public FeedbackDevice selectedFeedbackSensor;

    public BaseTalonPIDSetConfiguration(FeedbackDevice defaultFeedbackDevice) {
        selectedFeedbackSensor = defaultFeedbackDevice;
    }

    /**
     * @return string representation of configs
     */
	public String toString() {
		return toString("");
	}

    /**
     * @param prependString
     *              String to prepend to configs
     * @return String representation of configs
     */
    public String toString(String prependString) {

        String retstr = prependString + ".selectedFeedbackSensor = " + selectedFeedbackSensor.toString() + ";\n";
        retstr += super.toString(prependString);
        return retstr;
    }

}


