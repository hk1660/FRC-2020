package com.ctre.phoenix.motorcontrol;

import java.util.HashMap;

/**
 * Choose the feedback device for a selected sensor
 */
public enum TalonFXFeedbackDevice {	
	/**
	 * TalonFX supports an integrated sensor.
	 */
	IntegratedSensor(1),
	/**
	 * Sum0 + Sum1
	 */
	SensorSum(9),
	/**
	 * Diff0 - Diff1
	 */
	SensorDifference(10),
	/**
	 * Sensor configured in RemoteFilter0
	 */
	RemoteSensor0(11),
	/**
	 * Sensor configured in RemoteFilter1
	 */
	RemoteSensor1(12),
	/**
	 * Position and velocity will read 0.
	 */
	None(14),
	/**
	 * Motor Controller will fake a sensor based on applied motor output.
	 */
	SoftwareEmulatedSensor(15);
	
	/** Value of Feedback Device */
	public final int value;

	/**
	 * Create Feedback device of initValue
	 * @param initValue Value of TalonFXFeedbackDevice
	 */
	TalonFXFeedbackDevice(int initValue)
	{
		this.value = initValue;
	}
    /** Keep singleton map to quickly lookup enum via int */
    private static HashMap<Integer, TalonFXFeedbackDevice> _map = null;
	/** static c'tor, prepare the map */
    static {
    	_map = new HashMap<Integer, TalonFXFeedbackDevice>();
		for (TalonFXFeedbackDevice type : TalonFXFeedbackDevice.values()) {
			_map.put(type.value, type);
		}
    }
	/**
	 * Get TalonFXFeedbackDevice from specified value
	 * @param value Value of TalonFXFeedbackDevice
	 * @return TalonFXFeedbackDevice of specified value
	 */
	public static TalonFXFeedbackDevice valueOf(int value) {
		TalonFXFeedbackDevice retval = _map.get(value);
		if (retval != null)
			return retval;
		return IntegratedSensor;
	}
	/**
	 * Get TalonFXFeedbackDevice from specified value
	 * @param value Value of TalonFXFeedbackDevice
	 * @return TalonFXFeedbackDevice of specified value
	 */
    public static TalonFXFeedbackDevice valueOf(double value) {
        return valueOf((int) value); 
	}
	
	/**
	 * @return string representation of specified TalonFXFeedbackDevice
	 */
    public String toString() {
        switch(value) {
            case 1 : return "TalonFXFeedbackDevice.IntegratedSensor";
            case 9 : return "TalonFXFeedbackDevice.SensorSum";
            case 10: return "TalonFXFeedbackDevice.SensorDifference";
            case 11: return "TalonFXFeedbackDevice.RemoteSensor0";
            case 12: return "TalonFXFeedbackDevice.RemoteSensor1";
			case 14: return "TalonFXFeedbackDevice.None";
            case 15: return "TalonFXFeedbackDevice.SoftwareEmulatedSensor";
            default: return "InvalidValue";

        }

	}
	
	/**
	 * Helper method to convert to generic FeedbackDevice enum.
	 * @return value cast as FeedbackDevice
	 */
	public FeedbackDevice toFeedbackDevice(){
		return FeedbackDevice.values()[value];
	}

};
