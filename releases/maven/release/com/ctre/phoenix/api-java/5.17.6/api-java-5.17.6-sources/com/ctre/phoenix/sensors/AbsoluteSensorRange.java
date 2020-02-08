package com.ctre.phoenix.sensors;

public enum AbsoluteSensorRange{
    /**
     * Express the absolute position as an unsigned value.
     * E.g. [0,+1) rotations or [0,360) deg.
     */
    Unsigned_0_to_360 (0),
    /**
     * Express the absolute position as an signed value.
     * E.g. [-0.5,+0.5) rotations or [-180,+180) deg.
     */
    Signed_PlusMinus180 (1);

    public final int value;

    /**
	 * Create AbsoluteSensorRange of initValue
	 * @param initValue Value of AbsoluteSensorRange
	 */
	AbsoluteSensorRange(int initValue)
	{
		this.value = initValue;
    }
    
    /**
     * @return string representation of AbsoluteSensorRange
     */
    public String toString() {
        switch (value) {
            case 0: return "Unsigned: 0 to 360 deg (positive full rotation";
            case 1: return "Signed: -180 to 180 deg (plus/minus half a rotation)";
            default: return "InvalidValue";
        }
    }
    /** public lookup to convert int to enum */
    public static AbsoluteSensorRange valueOf(int value) {
        switch (value) {
            default: // no break
            case 0: return AbsoluteSensorRange.Unsigned_0_to_360;
            case 1: return AbsoluteSensorRange.Signed_PlusMinus180;
        }
    }
}