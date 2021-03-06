package com.ctre.phoenix.motorcontrol.can;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.MotControllerJNI;
import com.ctre.phoenix.ErrorCollection;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.SensorTerm;

/**
 * CTRE Talon SRX Motor Controller when used on CAN Bus.
 */
public class TalonSRX extends com.ctre.phoenix.motorcontrol.can.BaseTalon {


	/**
	 * Constructor for TalonSRX object
	 * @param deviceNumber CAN Device ID of Device
	 */
	public TalonSRX(int deviceNumber) {
		super(deviceNumber, "Talon SRX");
	}

	/**
	 * @return object that can get/set individual raw sensor values.
	 */
	public SensorCollection getSensorCollection() {
		return super.getTalonSRXSensorCollection();
	}

	/**
	 * Select the feedback device for the motor controller.
	 *
	 * @param feedbackDevice
	 *            Talon SRX Feedback Device to select.
	 * @param pidIdx
	 *            0 for Primary closed-loop. 1 for auxiliary closed-loop.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configSelectedFeedbackSensor(TalonSRXFeedbackDevice feedbackDevice, int pidIdx, int timeoutMs)
	{
		return super.configSelectedFeedbackSensor(FeedbackDevice.valueOf(feedbackDevice.value), pidIdx, timeoutMs);
	}


	// ------ Current Lim ----------//
	/**
	 * Configures the supply (input) current limit.
	 * @param currLimitConfigs  Current limit configuration
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for
	 *            config success and report an error if it times out.
	 *            If zero, no blocking or checking is performed.
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitConfigs, int timeoutMs){
		ErrorCollection retval = new ErrorCollection();
		retval.NewError(configPeakCurrentLimit((int)currLimitConfigs.triggerThresholdCurrent, timeoutMs));
		retval.NewError(configPeakCurrentDuration((int)(1000.0 * currLimitConfigs.triggerThresholdTime), timeoutMs));
		retval.NewError(configContinuousCurrentLimit((int)currLimitConfigs.currentLimit, timeoutMs));
		enableCurrentLimit(currLimitConfigs.enable);
		return retval._worstError;
	}
	/**
	 * Configures the supply (input) current limit.
	 * @param currLimitConfigs  Current limit configuration
	 * @return Error Code generated by function. 0 indicates no error.
	 */
	public ErrorCode configSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitConfigs){
		int timeoutMs = 50;
		return configSupplyCurrentLimit(currLimitConfigs, timeoutMs);
	}

	/**
	 * Configure the peak allowable current (when current limit is enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configPeakCurrentLimit(int amps, int timeoutMs) {
		int retval =  MotControllerJNI.ConfigPeakCurrentLimit(m_handle, amps, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	/**
	 * Configure the peak allowable current (when current limit is enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 * 
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configPeakCurrentLimit(int amps) {
		int timeoutMs = 0;
		return configPeakCurrentLimit( amps,  timeoutMs);
	}

	/**
	 * Configure the peak allowable duration (when current limit is enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param milliseconds
	 *            How long to allow current-draw past peak limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configPeakCurrentDuration(int milliseconds, int timeoutMs) {
		int retval = MotControllerJNI.ConfigPeakCurrentDuration(m_handle, milliseconds, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	/**
	 * Configure the peak allowable duration (when current limit is enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param milliseconds
	 *            How long to allow current-draw past peak limit.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configPeakCurrentDuration(int milliseconds) {
		int timeoutMs = 0;
		return configPeakCurrentDuration( milliseconds,  timeoutMs);
	}

	/**
	 * Configure the continuous allowable current-draw (when current limit is
	 * enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
	 * @param timeoutMs
	 *            Timeout value in ms. If nonzero, function will wait for config
	 *            success and report an error if it times out. If zero, no
	 *            blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configContinuousCurrentLimit(int amps, int timeoutMs) {
		int retval =  MotControllerJNI.ConfigContinuousCurrentLimit(m_handle, amps, timeoutMs);
		return ErrorCode.valueOf(retval);
	}
	/**
	 * Configure the continuous allowable current-draw (when current limit is
	 * enabled).
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 *
	 * Current limit is activated when current exceeds the peak limit for longer
	 * than the peak duration. Then software will limit to the continuous limit.
	 * This ensures current limiting while allowing for momentary excess current
	 * events.
	 *
	 * For simpler current-limiting (single threshold) use
	 * ConfigContinuousCurrentLimit() and set the peak to zero:
	 * ConfigPeakCurrentLimit(0).
	 * 
	 * @param amps
	 *            Amperes to limit.
     * @return Error Code generated by function. 0 indicates no error. 
	 */
	public ErrorCode configContinuousCurrentLimit(int amps) {
		int timeoutMs = 0;
		return configContinuousCurrentLimit( amps,  timeoutMs); 	
	}

	/**
	 * Enable or disable Current Limit.
	 *
	 * Supply current limiting is also available via configSupplyCurrentLimit(),
	 * which is a common routine with Talon FX.
	 * 
	 * @param enable
	 *            Enable state of current limit.
	 * @see #configPeakCurrentLimit(int,int)
	 * @see #configPeakCurrentDuration(int,int)
	 * @see #configContinuousCurrentLimit(int,int)
	 */
	public void enableCurrentLimit(boolean enable) {
		MotControllerJNI.EnableCurrentLimit(m_handle, enable);
	}

    /**
     * Configures all PID set persistent settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
	 * @param pidIdx            0 for Primary closed-loop. 1 for auxiliary closed-loop.
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	protected ErrorCode configurePID(TalonSRXPIDSetConfiguration pid, int pidIdx, int timeoutMs) {
        return super.configurePID(pid, pidIdx, timeoutMs, false);
	

	}
    /**
     * Configures all PID set persistent settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	protected ErrorCode configurePID(TalonSRXPIDSetConfiguration pid) {
        return super.configurePID(pid);
    }

    /**
     * Gets all PID set persistant settings.
     *
	 * @param pid               Object with all of the PID set persistant settings
	 * @param pidIdx            0 for Primary closed-loop. 1 for auxiliary closed-loop.
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     */
    public void getPIDConfigs(TalonSRXPIDSetConfiguration pid, int pidIdx, int timeoutMs){
        super.getPIDConfigs(pid, pidIdx, timeoutMs);
    }
    /**
     * Gets all PID set persistant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     */
	public void getPIDConfigs(TalonSRXPIDSetConfiguration pid) {
        int pidIdx = 0;
        int timeoutMs = 50;
        getPIDConfigs(pid, pidIdx, timeoutMs);
    }
	


    /**
     * Configures all persistent settings.
     *
	 * @param allConfigs        Object with all of the persistant settings
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configAllSettings(TalonSRXConfiguration allConfigs, int timeoutMs) {
        ErrorCollection errorCollection = new ErrorCollection();

        errorCollection.NewError(super.configAllSettings(allConfigs, timeoutMs));

		if(TalonSRXConfigUtil.peakCurrentLimitDifferent(allConfigs)) errorCollection.NewError(configPeakCurrentLimit(allConfigs.peakCurrentLimit, timeoutMs));
		if(TalonSRXConfigUtil.peakCurrentDurationDifferent(allConfigs)) errorCollection.NewError(configPeakCurrentDuration(allConfigs.peakCurrentDuration, timeoutMs));
		if(TalonSRXConfigUtil.continuousCurrentLimitDifferent(allConfigs)) errorCollection.NewError(configContinuousCurrentLimit(allConfigs.continuousCurrentLimit, timeoutMs));

        return errorCollection._worstError; 	

	}
	
    /**
     * Configures all persistent settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error. 
     */
	public ErrorCode configAllSettings(TalonSRXConfiguration allConfigs) {
		int timeoutMs = 50;
		return configAllSettings(allConfigs, timeoutMs);
	}

    /**
     * Gets all persistant settings.
     *
	 * @param allConfigs        Object with all of the persistant settings
     * @param timeoutMs
     *              Timeout value in ms. If nonzero, function will wait for
     *              config success and report an error if it times out.
     *              If zero, no blocking or checking is performed.
     */
    public void getAllConfigs(TalonSRXConfiguration allConfigs, int timeoutMs) {
    
        super.getAllConfigs(allConfigs, timeoutMs);
    
        allConfigs.peakCurrentLimit        = (int) configGetParameter(ParamEnum.ePeakCurrentLimitAmps, 0, timeoutMs);
        allConfigs.peakCurrentDuration     = (int) configGetParameter(ParamEnum.ePeakCurrentLimitMs, 0, timeoutMs);
        allConfigs.continuousCurrentLimit  = (int) configGetParameter(ParamEnum.eContinuousCurrentLimitAmps, 0, timeoutMs);
    
    }
    /**
     * Gets all persistant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     */
    public void getAllConfigs(TalonSRXConfiguration allConfigs) {
        int timeoutMs = 50;
        getAllConfigs(allConfigs, timeoutMs);
    }



}
