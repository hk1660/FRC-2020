package com.ctre.phoenix.motorcontrol.can;
import com.ctre.phoenix.motorcontrol.MotorCommutation;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

/**
 * Configurables available to TalonFX
 */
public class TalonFXConfiguration extends com.ctre.phoenix.motorcontrol.can.BaseTalonConfiguration {

    /**
     * Supply-side current limiting.  This is typically used to prevent breakers from tripping.
     */
    public SupplyCurrentLimitConfiguration supplyCurrLimit = new SupplyCurrentLimitConfiguration();
    /**
     * Stator-side current limiting.  This is typically used to limit acceleration/torque and heat generation.
     */
    public StatorCurrentLimitConfiguration statorCurrLimit = new StatorCurrentLimitConfiguration();

    /**
     * Choose the type of motor commutation.
     */
    public MotorCommutation motorCommutation = MotorCommutation.Trapezoidal;

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
    public double integratedSensorOffsetDegrees = 0;
    /**
     * The sensor initialization strategy to use.This will impact the behavior the next time device boots up.
     *
     * Pick the strategy on how to initialize the "Position" register.  Depending on the mechanism,
     * it may be desirable to auto set the Position register to match the Absolute Position(swerve for example).
     * Or it may be desired to zero the sensor on boot(drivetrain translation sensor or a relative servo).
     *
     * TIP: Tuner's self-test feature will report what the boot sensor value will be in the event the device is reset.
     */
    public SensorInitializationStrategy initializationStrategy = SensorInitializationStrategy.BootToZero;

	public TalonFXConfiguration() {
        super(FeedbackDevice.IntegratedSensor);
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
        String retstr = prependString + ".supplyCurrLimit = " + supplyCurrLimit.toString() + ";\n";
        retstr += prependString + ".statorCurrLimit = " + statorCurrLimit.toString() + ";\n";
        retstr += prependString + ".motorCommutation = " + motorCommutation.toString() + ";\n";
        retstr += prependString + ".absoluteSensorRange = " + absoluteSensorRange.toString() + ";\n";
        retstr += prependString + ".integratedSensorOffsetDegrees = " + integratedSensorOffsetDegrees + ";\n";
        retstr += prependString + ".initializationStrategy = " + initializationStrategy.toString() + ";\n";
        retstr += super.toString(prependString);

        return retstr;
    }

}

