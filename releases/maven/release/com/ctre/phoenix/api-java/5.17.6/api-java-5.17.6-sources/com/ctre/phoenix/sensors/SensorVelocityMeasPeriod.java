package com.ctre.phoenix.sensors;

import java.util.HashMap;

/** 
* Enum for velocity periods used for CANifier 
*/ 
public enum SensorVelocityMeasPeriod { 
	/**
	 * 1ms velocity measurement period
	 */
	Period_1Ms(1), 
	/**
	 * 2ms velocity measurement period
	 */
	Period_2Ms(2), 
	/**
	 * 5ms velocity measurement period
	 */
	Period_5Ms(5), 
	/**
	 * 10ms velocity measurement period
	 */
	Period_10Ms(10), 
	/**
	 * 20ms velocity measurement period
	 */
	Period_20Ms(20), 
	/**
	 * 25ms velocity measurement period
	 */
	Period_25Ms(25), 
	/**
	 * 50ms velocity measurement period
	 */
	Period_50Ms(50), 
	/**
	 * 100ms velocity measurement period
	 */
	Period_100Ms(100); 

	/** Value of velocity period */
	public final int value; 

	/** 
	 * Create a SensorVelocityMeasPeriod of initValue
	 * @param initValue Value of SensorVelocityMeasPeriod
	 */
	SensorVelocityMeasPeriod(int initValue) { 
		this.value = initValue; 
	} 
    /** Keep singleton map to quickly lookup enum via int */
    private static HashMap<Integer, SensorVelocityMeasPeriod> _map = null;
	/** static c'tor, prepare the map */
    static {
    	_map = new HashMap<Integer, SensorVelocityMeasPeriod>();
		for (SensorVelocityMeasPeriod type : SensorVelocityMeasPeriod.values()) {
			_map.put(type.value, type);
		}
    }
	/**
	 * Get SensorVelocityMeasPeriod of specified value
	 * @param value value of SensorVelocityMeasPeriod
	 * @return SensorVelocityMeasPeriod of specified value
	 */
	public static SensorVelocityMeasPeriod valueOf(int value) {
		SensorVelocityMeasPeriod retval = _map.get(value);
		if (retval != null)
			return retval;
		return Period_100Ms;
	}
	/**
	 * Get SensorVelocityMeasPeriod of specified value
	 * @param value value of SensorVelocityMeasPeriod
	 * @return SensorVelocityMeasPeriod of specified value
	 */
    public static SensorVelocityMeasPeriod valueOf(double value) {
        return valueOf((int) value); 
	}
	/**
	 * @return String representation of specified SensorVelocityMeasPeriod
	 */
    public String toString() {
        switch(value) {
            case 1 : return "SensorVelocityMeasPeriod.Period_1Ms";
            case 2 : return "SensorVelocityMeasPeriod.Period_2Ms";
            case 5 : return "SensorVelocityMeasPeriod.Period_5Ms";
            case 10 : return "SensorVelocityMeasPeriod.Period_10Ms";
            case 20 : return "SensorVelocityMeasPeriod.Period_20Ms";
            case 25 : return "SensorVelocityMeasPeriod.Period_25Ms";
            case 50 : return "SensorVelocityMeasPeriod.Period_50Ms";
            case 100 : return "SensorVelocityMeasPeriod.Period_100Ms";
            default : return "InvalidValue";
        }
    }
} 
