package com.ctre.phoenix.sensors;

public enum SensorInitializationStrategy{
    /**
     * On boot up, set position to zero.
     */
    BootToZero (0),
    /**
     * On boot up, sync to the Absolute Position signal.  The Absolute position signal will be signed according to the selected Absolute Sensor Range.
     */
    BootToAbsolutePosition (1);

    public final int value;

    /**
	 * Create SensorInitializationStrategy of initValue
	 * @param initValue Value of SensorInitializationStrategy
	 */
	SensorInitializationStrategy(int initValue)
	{
		this.value = initValue;
    }

    /**
     * String representation of specified SensorInitializationStrategy
     * @return string representation of SensorInitializationStrategy
     */
    public String toString() {
        switch (value) {
            case 0: return "On boot up, set position to zero.";
            case 1: return " On boot up, sync to the Absolute Position signal.";
            default: return "InvalidValue";
        }
    }
    /** public lookup to convert int to enum */
    public static SensorInitializationStrategy valueOf(int value) {
        switch (value) {
            default: // no break
            case 0: return SensorInitializationStrategy.BootToZero;
            case 1: return SensorInitializationStrategy.BootToAbsolutePosition;
        }
    }
}