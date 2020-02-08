package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ErrorCollection;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

public class TalonFX extends BaseTalon {

    /**
     * Constructor
     * @param deviceNumber [0,62]
     */
    public TalonFX(int deviceNumber) {
        super(deviceNumber, "Talon FX");
    }

    // ------ Set output routines. ----------//
    /**
     * Sets the appropriate output on the talon, depending on the mode.
     * @param mode The output mode to apply.
     * In PercentOutput, the output is between -1.0 and 1.0, with 0.0 as stopped.
     * In Current mode, output value is in amperes.
     * In Velocity mode, output value is in position change / 100ms.
     * In Position mode, output value is in encoder ticks or an analog value,
     *   depending on the sensor.
     * In Follower mode, the output value is the integer device ID of the talon to
     * duplicate.
     *
     * @param value The setpoint value, as described above.
     *
     *
     *	Standard Driving Example:
    *	_talonLeft.set(ControlMode.PercentOutput, leftJoy);
    *	_talonRght.set(ControlMode.PercentOutput, rghtJoy);
    */
    public void set(TalonFXControlMode mode, double value) {
        super.set(mode.toControlMode(), value);
    }
    /**
     * @param mode Sets the appropriate output on the talon, depending on the mode.
     * @param demand0 The output value to apply.
     * 	such as advanced feed forward and/or auxiliary close-looping in firmware.
     * In PercentOutput, the output is between -1.0 and 1.0, with 0.0 as stopped.
     * In Current mode, output value is in amperes.
     * In Velocity mode, output value is in position change / 100ms.
     * In Position mode, output value is in encoder ticks or an analog value,
     *   depending on the sensor. See
     * In Follower mode, the output value is the integer device ID of the talon to
     * duplicate.
     *
     * @param demand1Type The demand type for demand1.
     * Neutral: Ignore demand1 and apply no change to the demand0 output.
     * AuxPID: Use demand1 to set the target for the auxiliary PID 1.  Auxiliary
     *   PID is always executed as standard Position PID control.
     * ArbitraryFeedForward: Use demand1 as an arbitrary additive value to the
     *	 demand0 output.  In PercentOutput the demand0 output is the motor output,
    *   and in closed-loop modes the demand0 output is the output of PID0.
    * @param demand1 Supplmental output value.
    * AuxPID: Target position in Sensor Units
    * ArbitraryFeedForward: Percent Output between -1.0 and 1.0
    *
    *
    *  Arcade Drive Example:
    *		_talonLeft.set(ControlMode.PercentOutput, joyForward, DemandType.ArbitraryFeedForward, +joyTurn);
    *		_talonRght.set(ControlMode.PercentOutput, joyForward, DemandType.ArbitraryFeedForward, -joyTurn);
    *
    *	Drive Straight Example:
    *	Note: Selected Sensor Configuration is necessary for both PID0 and PID1.
    *		_talonLeft.follow(_talonRght, FollwerType.AuxOutput1);
    *		_talonRght.set(ControlMode.PercentOutput, joyForward, DemandType.AuxPID, desiredRobotHeading);
    *
    *	Drive Straight to a Distance Example:
    *	Note: Other configurations (sensor selection, PID gains, etc.) need to be set.
    *		_talonLeft.follow(_talonRght, FollwerType.AuxOutput1);
    *		_talonRght.set(ControlMode.MotionMagic, targetDistance, DemandType.AuxPID, desiredRobotHeading);
    */
    public void set(TalonFXControlMode mode, double demand0, DemandType demand1Type, double demand1) {
        super.set(mode.toControlMode(), demand0, demand1Type, demand1);
    }
    //------ Invert behavior ----------//
    /**
     * Inverts the hbridge output of the motor controller in relation to the master if present
     *
     * This does not impact sensor phase and should not be used to correct sensor polarity.
     *
     * This will allow you to either:
     *  - Spin counterclockwise (default)
     *  - Spin Clockwise (invert direction)
     *  - Always follow the master regardless of master's inversion
     *  - Always oppose the master regardless of master's inversion
     *
     * @param invertType
     *            Invert state to set.
     */
    public void setInverted(TalonFXInvertType invertType)
    {
        super.setInverted(invertType.toInvertType());
    }

    //------ sensor selection ----------//
    /**
     * Select the feedback device for the motor controller.
     *
     * @param feedbackDevice
     *            Talon FX feedback Device to select.
     * @param pidIdx
     *            0 for Primary closed-loop. 1 for auxiliary closed-loop.
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configSelectedFeedbackSensor(TalonFXFeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) {
        return super.configSelectedFeedbackSensor(feedbackDevice.toFeedbackDevice(), pidIdx, timeoutMs);
    }
    //------ Current Lim ----------//

    /**
     * Configures the supply (input) current limit.
     * @param currLimitCfg  Current limit configuration
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitCfg, int timeoutMs) {
        double[] doubles = currLimitCfg.toArray();
        return ErrorCode.valueOf(MotControllerJNI.ConfigSupplyCurrentLimit(getHandle(), doubles, timeoutMs));
    }
    /**
     * Configures the supply (input) current limit.
     * @param currLimitCfg  Current limit configuration
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitCfg) {
        int timeoutMs = 50;
        return configSupplyCurrentLimit(currLimitCfg, timeoutMs);
    }
    /**
     * Configures the stator (output) current limit.
     * @param currLimitCfg  Current limit configuration
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configStatorCurrentLimit(StatorCurrentLimitConfiguration currLimitCfg, int timeoutMs)
    {
        double[] doubles = currLimitCfg.toArray();
        return ErrorCode.valueOf(MotControllerJNI.ConfigStatorCurrentLimit(getHandle(), doubles, timeoutMs));
    }
    /**
     * Configures the stator (output) current limit.
     * @param currLimitCfg  Current limit configuration
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configStatorCurrentLimit(StatorCurrentLimitConfiguration currLimitCfg)
    {
        int timeoutMs = 50;
        return configStatorCurrentLimit(currLimitCfg, timeoutMs);
    }
    /**
     * Gets the supply (input) current limit configuration.
     * @param currLimitConfigsToFill  Configuration object to fill with read values.
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configGetSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitConfigsToFill, int timeoutMs)
    {
        double toFill[] = new double[10];
        int fillCnt = MotControllerJNI.ConfigGetSupplyCurrentLimit(getHandle(), toFill, timeoutMs);
        currLimitConfigsToFill.deserialize(toFill);
        return getLastError();
    }
    /**
     * Gets the supply (input) current limit configuration.
     * @param currLimitConfigsToFill  Configuration object to fill with read values..
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configGetSupplyCurrentLimit(SupplyCurrentLimitConfiguration currLimitConfigsToFill)
    {
        int timeoutMs = 50;
        return configGetSupplyCurrentLimit(currLimitConfigsToFill);
    }
    /**
     * Gets the stator (output) current limit configuration.
     * @param currLimitConfigsToFill  Configuration object to fill with read values.
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configGetStatorCurrentLimit(StatorCurrentLimitConfiguration currLimitConfigsToFill, int timeoutMs)
    {
        double toFill[] = new double[10];
        int fillCnt = MotControllerJNI.ConfigGetStatorCurrentLimit(getHandle(), toFill, timeoutMs);
        currLimitConfigsToFill.deserialize(toFill);
        return getLastError();
    }
    /**
     * Gets the stator (output) current limit configuration.
     * @param currLimitConfigsToFill  Configuration object to fill with read values.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configGetStatorCurrentLimit(StatorCurrentLimitConfiguration currLimitConfigsToFill)
    {
        int timeoutMs = 50;
        return configGetStatorCurrentLimit(currLimitConfigsToFill);
    }
	
    /**
     * Configure the motor commutation type.
     *
     * @param motorCommutation  Motor Commutation Type.
     *
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for config
     *            success and report an error if it times out. If zero, no
     *            blocking or checking is performed.
     */
    public ErrorCode configMotorCommutation(MotorCommutation motorCommutation, int timeoutMs)
    {
        return ErrorCode.valueOf(MotControllerJNI.ConfigMotorCommutation(getHandle(), motorCommutation.value, timeoutMs));
    }
    /**
     * Configure the motor commutation type.
     *
     * @param motorCommutation  Motor Commutation Type.
     */
    public ErrorCode configMotorCommutation(MotorCommutation motorCommutation)
    {
        int timeoutMs = 50;
        return configMotorCommutation(motorCommutation, timeoutMs);
    }

    /**
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for config
     *            success and report an error if it times out.
     * @return  The motor commutation type.
     */
    public MotorCommutation configGetMotorCommutation(int timeoutMs)
    {
        return MotorCommutation.values()[MotControllerJNI.ConfigGetMotorCommutation(getHandle(), timeoutMs)];
    }
    /**
     * @return  The motor commutation type.
     */
    public MotorCommutation configGetMotorCommutation()
    {
        int timeoutMs = 0;
        return configGetMotorCommutation(timeoutMs);
    }

    /**
     * Sets the signage and range of the "Absolute Position" signal.
     * Choose unsigned for an absolute range of [0,+1) rotations, [0,360) deg, etc...
     * Choose signed for an absolute range of [-0.5,+0.5) rotations, [-180,+180) deg, etc...
     * @param absoluteSensorRange
     *            Desired Sign/Range for the absolute position register.
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorAbsoluteRange(AbsoluteSensorRange absoluteSensorRange, int timeoutMs)
    {
        return ErrorCode.valueOf(MotControllerJNI.ConfigIntegratedSensorAbsoluteRange(getHandle(), absoluteSensorRange.value, timeoutMs));
    }

    /**
     * Sets the signage and range of the "Absolute Position" signal.
     * Choose unsigned for an absolute range of [0,+1) rotations, [0,360) deg, etc...
     * Choose signed for an absolute range of [-0.5,+0.5) rotations, [-180,+180) deg, etc...
     * @param absoluteSensorRange
     *            Desired Sign/Range for the absolute position register.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorAbsoluteRange(AbsoluteSensorRange absoluteSensorRange)
    {
        int timeoutMs = 0;
        return configIntegratedSensorAbsoluteRange(absoluteSensorRange, timeoutMs);
    }
    /**
     * Adjusts the zero point for the integrated sensor absolute position register.
     * The absolute position of the sensor will always have a discontinuity (360 -> 0 deg) or (+180 -> -180)
     * and a hard-limited mechanism may have such a discontinuity in its functional range.
     * In which case use this config to move the discontinuity outside of the function range.
     * @param offsetDegrees
     *            Offset in degrees
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorOffset(double offsetDegrees, int timeoutMs)
    {
        return ErrorCode.valueOf(MotControllerJNI.ConfigIntegratedSensorOffset(getHandle(), offsetDegrees, timeoutMs));
    }
    /**
     * Adjusts the zero point for the integrated sensor absolute position register.
     * The absolute position of the sensor will always have a discontinuity (360 -> 0 deg) or (+180 -> -180)
     * and a hard-limited mechanism may have such a discontinuity in its functional range.
     * In which case use this config to move the discontinuity outside of the function range.
     * @param offsetDegrees
     *            Offset in degrees
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorOffset(double offsetDegrees)
    {
        int timeoutMs = 0;
        return configIntegratedSensorOffset(offsetDegrees, timeoutMs);
    }
    /**
     * Pick the strategy on how to initialize the integrated sensor register.  Depending on the mechanism,
     * it may be desirable to auto set the Position register to match the Absolute Position (swerve for example).
     * Or it may be desired to zero the sensor on boot (drivetrain translation sensor or a relative servo).
     *
     * TIP: Tuner's self-test feature will report what the boot sensor value will be in the event the product is reset.
     *
     * @param initializationStrategy
     *            The sensor initialization strategy to use.  This will impact the behavior the next time product boots up.
     * @param timeoutMs
     *            Timeout value in ms. If nonzero, function will wait for
     *            config success and report an error if it times out.
     *            If zero, no blocking or checking is performed.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorInitializationStrategy(SensorInitializationStrategy initializationStrategy, int timeoutMs)
    {
        return ErrorCode.valueOf(MotControllerJNI.ConfigIntegratedSensorInitializationStrategy(getHandle(), initializationStrategy.value, timeoutMs));
    }
    /**
     * Pick the strategy on how to initialize the integrated sensor register.  Depending on the mechanism,
     * it may be desirable to auto set the Position register to match the Absolute Position (swerve for example).
     * Or it may be desired to zero the sensor on boot (drivetrain translation sensor or a relative servo).
     *
     * TIP: Tuner's self-test feature will report what the boot sensor value will be in the event the product is reset.
     *
     * @param initializationStrategy
     *            The sensor initialization strategy to use.  This will impact the behavior the next time product boots up.
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configIntegratedSensorInitializationStrategy(SensorInitializationStrategy initializationStrategy)
    {
        int timeoutMs = 0;
        return configIntegratedSensorInitializationStrategy(initializationStrategy, timeoutMs);
    }

    /**
     * @return object that can get/set individual raw sensor values.
     */
    public TalonFXSensorCollection getSensorCollection() {
        return super.getTalonFXSensorCollection();
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
	protected ErrorCode configurePID(TalonFXPIDSetConfiguration pid, int pidIdx, int timeoutMs) {
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
	protected ErrorCode configurePID(TalonFXPIDSetConfiguration pid) {
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
    public void getPIDConfigs(TalonFXPIDSetConfiguration pid, int pidIdx, int timeoutMs){
        super.getPIDConfigs(pid, pidIdx, timeoutMs);
    }
    /**
     * Gets all PID set persistant settings (overloaded so timeoutMs is 50 ms
     * and pidIdx is 0).
     *
	 * @param pid               Object with all of the PID set persistant settings
     */
	public void getPIDConfigs(TalonFXPIDSetConfiguration pid) {
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
    public ErrorCode configAllSettings(TalonFXConfiguration allConfigs, int timeoutMs)
    {
        ErrorCollection ec = new ErrorCollection();

        ec.NewError(super.configAllSettings(allConfigs, timeoutMs));

        if (TalonFXConfigUtil.SupplyCurrentLimitDifferent(allConfigs))
        {
            double[] doubles = allConfigs.supplyCurrLimit.toArray();
            ec.NewError(MotControllerJNI.ConfigSupplyCurrentLimit(getHandle(), doubles, timeoutMs));
        }
        if (TalonFXConfigUtil.StatorCurrentDurationDifferent(allConfigs))
        {
            double[] doubles = allConfigs.statorCurrLimit.toArray();
            ec.NewError(MotControllerJNI.ConfigStatorCurrentLimit(getHandle(), doubles, timeoutMs));
        }
        if (TalonFXConfigUtil.MotorCommutationDifferent(allConfigs))
        {
            ec.NewError(configMotorCommutation(allConfigs.motorCommutation, timeoutMs));
        }
        if (TalonFXConfigUtil.AbsoluteSensorRangeDifferent(allConfigs))
        {
            ec.NewError(configIntegratedSensorAbsoluteRange(allConfigs.absoluteSensorRange, timeoutMs));
        }
        if (TalonFXConfigUtil.IntegratedSensorOffsetDegreesDifferent(allConfigs))
        {
            ec.NewError(configIntegratedSensorOffset(allConfigs.integratedSensorOffsetDegrees, timeoutMs));
        }
        if (TalonFXConfigUtil.InitializationStrategyDifferent(allConfigs))
        {
            ec.NewError(configIntegratedSensorInitializationStrategy(allConfigs.initializationStrategy, timeoutMs));
        }

        return ec._worstError;
    }
    /**
     * Configures all persistent settings.
     *
     * @param allConfigs        Object with all of the persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode configAllSettings(TalonFXConfiguration allConfigs)
    {
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
     *
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode getAllConfigs(TalonFXConfiguration allConfigs, int timeoutMs)
    {
        ErrorCollection ec = new ErrorCollection();

        super.getAllConfigs(allConfigs, timeoutMs);
        ec.NewError(configGetSupplyCurrentLimit(allConfigs.supplyCurrLimit, timeoutMs));
        ec.NewError(configGetStatorCurrentLimit(allConfigs.statorCurrLimit, timeoutMs));
        allConfigs.motorCommutation = configGetMotorCommutation(timeoutMs);
        ec.NewError(getLastError());
        allConfigs.absoluteSensorRange = AbsoluteSensorRange.valueOf((int)configGetParameter(ParamEnum.eAbsSensorRange, 0, timeoutMs));
        ec.NewError(getLastError());
        allConfigs.integratedSensorOffsetDegrees = configGetParameter(ParamEnum.eMagnetOffset, 0, timeoutMs);
        ec.NewError(getLastError());
        allConfigs.initializationStrategy = SensorInitializationStrategy.valueOf((int)configGetParameter(ParamEnum.eSensorInitStrategy, 0, timeoutMs));
        ec.NewError(getLastError());

        return ec._worstError;
    }
    /**
     * Gets all persistant settings (overloaded so timeoutMs is 50 ms).
     *
	 * @param allConfigs        Object with all of the persistant settings
     *
     * @return Error Code generated by function. 0 indicates no error.
     */
    public ErrorCode getAllConfigs(TalonFXConfiguration allConfigs) {
        int timeoutMs = 50;
        return getAllConfigs(allConfigs, timeoutMs);
    }
}