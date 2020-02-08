package com.ctre.phoenix.sensors;

import java.util.HashMap;

public enum MagnetFieldStrength {
    /** Magnet Field strength cannot be determined */
    Invalid_Unknown (0),
    /** Magnet field is far too low (too far) or far too high (too close). */
    BadRange_RedLED (1),
    /** Magnet field is adequate, sensor can be used in this range with slightly reduced accuracy. */
    Adequate_OrangeLED (2),
    /** Magnet field is ideal */
    Good_GreenLED (3);

    public final int value;

    /**
	 * Create MagnetFieldStrength of initValue
	 * @param initValue Value of MagnetFieldStrength
	 */
	MagnetFieldStrength(int initValue)
	{
		this.value = initValue;
    }

    /** Keep singleton map to quickly lookup enum via int */
    private static HashMap<Integer, MagnetFieldStrength> _map = null;
	/** static c'tor, prepare the map */
    static {
    	_map = new HashMap<Integer, MagnetFieldStrength>();
		for (MagnetFieldStrength type : MagnetFieldStrength.values()) {
			_map.put(type.value, type);
		}
    }
    /** public lookup to convert int to enum */
	public static MagnetFieldStrength valueOf(int value) {
		MagnetFieldStrength retval = _map.get(value);
		if (retval != null)
			return retval;
		return Invalid_Unknown;
	}
}