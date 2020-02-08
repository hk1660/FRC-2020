package com.ctre.phoenix.motorcontrol;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.MotControllerJNI;

/**
 * Collection of sensors available to the Talon FX.
 * <p>
 * For best performance and update-rate, we recommend using the
 * configSelectedFeedbackSensor() and getSelectedSensor*() routines. However
 * there are occasions where accessing raw sensor values may be useful or
 * convenient. Particularly if you are seeding one sensor based on another, or
 * need to circumvent sensor-phase.
 * <p>
 * Use the getTalonFXSensorCollection() routine inside your motor controller to create
 * a sensor collection.
 */
public class TalonFXSensorCollection {
    private long _handle;

    /**
     * Constructor for SensorCollection
     *
     * @param motorController Motor Controller to connect Collection to
     */
    public TalonFXSensorCollection(BaseTalon motorController) {
        _handle = motorController.getHandle();
    }

    /**
     * Get the IntegratedSensor position of the Talon FX, regardless of whether
     * it is actually being used for feedback.  The units are 2048 per rotation.
     * Note : Future versions of software may support scaling features (rotations, radians, degrees, etc) depending on the configuration.
     *
     * @return the IntegratedSensor position.
     */
    public double getIntegratedSensorPosition() {
        return MotControllerJNI.GetIntegratedSensorPosition(_handle);
    }

    /**
     * Get the IntegratedSensor absolute position of the Talon FX, regardless of whether
     * it is actually being used for feedback.  This will be within one rotation (2048 units).
     * The signage and range will depend on the configuration.
     * Note : Future versions of software may support scaling features (rotations, radians, degrees, etc) depending on the configuration.
     *
     * @return the IntegratedSensor absolute position.
     */
    public double getIntegratedSensorAbsolutePosition() {
        return MotControllerJNI.GetIntegratedSensorAbsolutePosition(_handle);
    }

    /**
     * Get the IntegratedSensor velocity of the Talon FX, regardless of whether
     * it is actually being used for feedback.
     * One unit represents one position unit per 100ms (2048 position units per 100ms).
     * The signage and range will depend on the configuration.
     * Note : Future versions of software may support scaling features (rotations, radians, degrees, etc) depending on the configuration.
     *
     * @return the IntegratedSensor velocity.
     */
    public double getIntegratedSensorVelocity() {
        return MotControllerJNI.GetIntegratedSensorVelocity(_handle);
    }

    /**
     * Set the IntegratedSensor reported position.  Typically this is used to "zero" the
     * sensor. This only works with IntegratedSensor.  To set the selected sensor position
     * regardless of what type it is, see SetSelectedSensorPosition in the motor controller class.
     *
     * @param newPosition The position value to apply to the sensor.
     * @param timeoutMs   Timeout value in ms. If nonzero, function will wait for
     *                    config success and report an error if it times out.
     *                    If zero, no blocking or checking is performed.
     * @return error code.
     */
    public ErrorCode setIntegratedSensorPosition(double newPosition,
                                                 int timeoutMs) {
        return ErrorCode.valueOf(MotControllerJNI.SetIntegratedSensorPosition(_handle, newPosition, timeoutMs));
    }

    /**
     * Set the IntegratedSensor reported position based on the absolute position.
     * This can also be done automatically on power boot depending on configuration.
     *
     * @param timeoutMs Timeout value in ms. If nonzero, function will wait for
     *                  config success and report an error if it times out.
     *                  If zero, no blocking or checking is performed.
     * @return error code.
     */
    public ErrorCode setIntegratedSensorPositionToAbsolute(int timeoutMs) {
        return ErrorCode.valueOf(MotControllerJNI.SetIntegratedSensorPositionToAbsolute(_handle, timeoutMs));
    }

    /**
     * Is forward limit switch closed.
     *
     * @return '1' iff forward limit switch is closed, 0 iff switch is open. This function works
     * regardless if limit switch feature is enabled.  Remote limit features do not impact this routine.
     */
    public int isFwdLimitSwitchClosed() {
        return MotControllerJNI.IsFwdLimitSwitchClosed(_handle);
    }

    /**
     * Is reverse limit switch closed.
     *
     * @return '1' iff reverse limit switch is closed, 0 iff switch is open. This function works
     * regardless if limit switch feature is enabled.  Remote limit features do not impact this routine.
     */
    public int isRevLimitSwitchClosed() {
        return MotControllerJNI.IsRevLimitSwitchClosed(_handle);
    }
}