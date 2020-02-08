package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.CustomParamConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Configurables available to TalonSRX
 */
public class BaseTalonConfiguration extends com.ctre.phoenix.motorcontrol.can.BaseMotorControllerConfiguration {

    /**
     * Primary PID configuration
     */
    public BaseTalonPIDSetConfiguration primaryPID;
    /**
     * Auxiliary PID configuration
     */
    public BaseTalonPIDSetConfiguration auxiliaryPID;
    /**
     * Forward Limit Switch Source
     * 
     * User can choose between the feedback connector, remote Talon SRX, CANifier, or deactivate the feature
     */
    public LimitSwitchSource forwardLimitSwitchSource;
    /**
     * Reverse Limit Switch Source
     * 
     * User can choose between the feedback connector, remote Talon SRX, CANifier, or deactivate the feature
     */
    public LimitSwitchSource reverseLimitSwitchSource;
    /**
     * Forward limit switch device ID
     * 
     * Limit Switch device id isn't used unless device is a remote
     */
    public int forwardLimitSwitchDeviceID; 
    /**
     * Reverse limit switch device ID
     * 
     * Limit Switch device id isn't used unless device is a remote
     */
    public int reverseLimitSwitchDeviceID;
    /**
     * Forward limit switch normally open/closed
     */
    public LimitSwitchNormal forwardLimitSwitchNormal;
    /**
     * Reverse limit switch normally open/closed
     */
    public LimitSwitchNormal reverseLimitSwitchNormal;
    /**
     * Feedback Device for Sum 0 Term
     * Note the FeedbackDevice enum holds all possible sensor types.  Consult product documentation to confirm what is available.
     * Alternatively the product specific enum can be used instead (see below).
     * <p>
     * <code>
     * configs.sum0Term = TalonSRXFeedbackDevice.QuadEncoder.toFeedbackDevice();
     * configs.sum0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
     * </code>
     * </p>
     */
    public FeedbackDevice sum0Term;
    /**
     * Feedback Device for Sum 1 Term
     * Note the FeedbackDevice enum holds all possible sensor types.  Consult product documentation to confirm what is available.
     * Alternatively the product specific enum can be used instead (see below).
     * <p>
     * <code>
     * configs.sum1Term = TalonSRXFeedbackDevice.QuadEncoder.toFeedbackDevice();
     * configs.sum1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
     * </code>
     * </p>
     */
    public FeedbackDevice sum1Term;
    /**
     * Feedback Device for Diff 0 Term
     * Note the FeedbackDevice enum holds all possible sensor types.  Consult product documentation to confirm what is available.
     * Alternatively the product specific enum can be used instead (see below).
     * <p>
     * <code>
     * configs.diff0Term = TalonSRXFeedbackDevice.QuadEncoder.toFeedbackDevice();
     * configs.diff0Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
     * </code>
     * </p>
     */
    public FeedbackDevice diff0Term;
    /**
     * Feedback Device for Diff 1 Term
     * Note the FeedbackDevice enum holds all possible sensor types.  Consult product documentation to confirm what is available.
     * Alternatively the product specific enum can be used instead (see below).
     * <p>
     * <code>
     * configs.diff1Term = TalonSRXFeedbackDevice.QuadEncoder.toFeedbackDevice();
     * configs.diff1Term = TalonFXFeedbackDevice.IntegratedSensor.toFeedbackDevice();
     * </code>
     * </p>
     */
    public FeedbackDevice diff1Term;


	public BaseTalonConfiguration(FeedbackDevice defaultFeedbackDevice) {
        primaryPID = new BaseTalonPIDSetConfiguration(defaultFeedbackDevice); 
        auxiliaryPID = new BaseTalonPIDSetConfiguration(defaultFeedbackDevice); 

        forwardLimitSwitchSource = LimitSwitchSource.FeedbackConnector;
        reverseLimitSwitchSource = LimitSwitchSource.FeedbackConnector;
        forwardLimitSwitchDeviceID = 0;
        reverseLimitSwitchDeviceID = 0;
        forwardLimitSwitchNormal = LimitSwitchNormal.NormallyOpen;
        reverseLimitSwitchNormal = LimitSwitchNormal.NormallyOpen;
        sum0Term = defaultFeedbackDevice;
        sum1Term = defaultFeedbackDevice;
        diff0Term = defaultFeedbackDevice;
        diff1Term = defaultFeedbackDevice;
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


        String retstr = primaryPID.toString(prependString + ".primaryPID");
        retstr += auxiliaryPID.toString(prependString + ".auxiliaryPID");
        retstr += prependString + ".forwardLimitSwitchSource = " + forwardLimitSwitchSource.toString() + ";\n";
        retstr += prependString + ".reverseLimitSwitchSource = " + reverseLimitSwitchSource.toString() + ";\n";
        retstr += prependString + ".forwardLimitSwitchDeviceID = " + String.valueOf(forwardLimitSwitchDeviceID) + ";\n";
        retstr += prependString + ".reverseLimitSwitchDeviceID = " + String.valueOf(reverseLimitSwitchDeviceID) + ";\n";
        retstr += prependString + ".forwardLimitSwitchNormal = " + forwardLimitSwitchNormal.toString() + ";\n";
        retstr += prependString + ".reverseLimitSwitchNormal = " + reverseLimitSwitchNormal.toString() + ";\n";
        retstr += prependString + ".sum0Term = " + sum0Term.toString() + ";\n";
        retstr += prependString + ".sum1Term = " + sum1Term.toString() + ";\n";
        retstr += prependString + ".diff0Term = " + diff0Term.toString() + ";\n";
        retstr += prependString + ".diff1Term = " + diff1Term.toString() + ";\n";
         retstr += super.toString(prependString);

       return retstr;
    }

}

