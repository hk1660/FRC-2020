package com.ctre.phoenix.sensors;

import com.ctre.phoenix.CustomParamConfiguration;

/**
 * Configurables available to CANCoder
 */
public class CANCoderConfiguration extends CustomParamConfiguration {
    /**
     * Velocity measurement period to use
     */
    public SensorVelocityMeasPeriod velocityMeasurementPeriod = SensorVelocityMeasPeriod.Period_100Ms;
    /**
     * Velocity measurement window to use
     */
    public int velocityMeasurementWindow = 64;

	/**
	 * Desired Sign / Range for the absolute position register.
	 * Choose unsigned for an absolute range of[0, +1) rotations, [0, 360) deg, etc.
	 * Choose signed for an absolute range of[-0.5, +0.5) rotations, [-180, +180) deg, etc.
	 */
	public AbsoluteSensorRange absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
	/**
	 * Adjusts the zero point for the absolute position register.
	 * The absolute position of the sensor will always have a discontinuity (360 -> 0 deg) or (+180 -> -180)
	 * and a hard-limited mechanism may have such a discontinuity in its functional range.
	 * In which case use this config to move the discontinuity outside of the function range.
	 */
	public double magnetOffsetDegrees = 0;
	/**
	 * Choose which direction is interpreted as positive displacement.
	 * This affects both "Position"and "Absolute Position".
	 * False(default) means positive rotation occurs when magnet
	 * is spun counter - clockwise when observer is facing the LED side of CANCoder.
	 */
	public boolean sensorDirection = false;
	/**
	 * The sensor initialization strategy to use.This will impact the behavior the next time CANCoder boots up.
	 *
	 * Pick the strategy on how to initialize the CANCoder's "Position" register.  Depending on the mechanism,
	 * it may be desirable to auto set the Position register to match the Absolute Position(swerve for example).
	 * Or it may be desired to zero the sensor on boot(drivetrain translation sensor or a relative servo).
	 *
	 * TIP: Tuner's self-test feature will report what the boot sensor value will be in the event the CANCoder is reset.
	 */
	public SensorInitializationStrategy initializationStrategy = SensorInitializationStrategy.BootToZero;
	/**
	 * Scalar to multiply the CANCoder's native 12-bit resolute sensor. Defaults to 0.087890625 to produce degrees.
	 */
	public double sensorCoefficient = 360.0 / 4096.0;
	/**
	 * String holding the unit to report in.  This impacts all routines(except for ConfigMagnetOffset) and the self-test in Tuner.
	 * The string value itself is arbitrary.The max number of letters will depend on firmware versioning, but generally CANCoder
	 * supports up to eight letters.However, common units such as "centimeters" are supported explicitly despite exceeding the eight-letter limit.
	 * Default is "deg"
	 */
	public String unitString = "deg";
	/**
	 * Desired denominator to report velocity in. This impacts GetVelocityand the reported velocity in self-test in Tuner.
	 * Default is "Per Second".
	 */
	public SensorTimeBase sensorTimeBase = SensorTimeBase.PerSecond;
			
	public CANCoderConfiguration() { }

    /**
     * @return String representation of configs
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
        String retstr = prependString + ".velocityMeasurementPeriod = " + velocityMeasurementPeriod.toString() + ";\n";
        retstr += prependString + ".velocityMeasurementWindow = " + String.valueOf(velocityMeasurementWindow) + ";\n";

		retstr += prependString + ".absoluteSensorRange = " + absoluteSensorRange.toString() + ";\n";
		retstr += prependString + ".magnetOffsetDegrees = " + magnetOffsetDegrees + ";\n";
		retstr += prependString + ".sensorDirection = " + sensorDirection + ";\n";
		retstr += prependString + ".initializationStrategy = " + initializationStrategy.toString() + ";\n";
		retstr += prependString + ".sensorCoefficient = " + sensorCoefficient + ";\n";
		retstr += prependString + ".unitString = \"" + unitString.toString() + "\";\n";
		retstr += prependString + ".sensorTimeBase = " + sensorTimeBase.toString() + ";\n";
        retstr += super.toString(prependString);

        return retstr;
    }
}
