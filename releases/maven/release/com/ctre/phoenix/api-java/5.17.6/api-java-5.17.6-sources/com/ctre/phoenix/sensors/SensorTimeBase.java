package com.ctre.phoenix.sensors;

public enum SensorTimeBase{
    /**
     * Legacy Mode
     */
    Per100Ms_Legacy (0),
    /**
     * Per-Second Velocities
     */
    PerSecond (1),
    /**
     * Per-Minute Velocities
     */
    PerMinute (2);

    public final int value;

    /**
	 * Create SensorTimeBase of initValue
	 * @param initValue Value of SensorTimeBase
	 */
	SensorTimeBase(int initValue)
	{
		this.value = initValue;
    }

    /**
     * String representation of specified SensorTimeBase
     * @param value SensorTimeBase to convert to a string
     * @return string representation of SensorTimeBase
     */
    public String toString() {
        switch (value) {
            case 0: return "Per 100Ms (legacy)";
            case 1: return "Per Second";
            case 2: return "Per Minute";
            default: return "InvalidValue";
        }
    }
    /** public lookup to convert int to enum */
    public static SensorTimeBase valueOf(int value) {
        switch (value) {
            default: // no break
            case 0: return SensorTimeBase.Per100Ms_Legacy;
            case 1: return SensorTimeBase.PerSecond;
            case 2: return SensorTimeBase.PerMinute;
        }
    }
}