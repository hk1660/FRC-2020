package com.ctre.phoenix.motorcontrol;

import java.util.HashMap;

/**
 * Choose the feedback device for a selected sensor
 */
public enum TalonSRXFeedbackDevice {	
	/**
	 * Quadrature encoder
	 */
	QuadEncoder(0),
	/**
	 * Analog potentiometer/encoder
	 */
	Analog(2),
	/**
	 * Tachometer
	 */
	Tachometer(4),
	/**
	 * CTRE Mag Encoder in Relative mode or
	 * any other device that uses PWM to encode its output
	 */
	PulseWidthEncodedPosition(8),

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
	SoftwareEmulatedSensor(15),

	/**
	 * CTR mag encoder configured in absolute, is the same 
	 * as a PWM sensor.
	 */
	CTRE_MagEncoder_Absolute(8),
	/**
	 * CTR mag encoder configured in relative, is the same 
	 * as an quadrature encoder sensor.
	 */
	CTRE_MagEncoder_Relative(0);
	
	/** Value of Feedback Device */
	public final int value;

	/**
	 * Create Feedback device of initValue
	 * @param initValue Value of TalonSRXFeedbackDevice
	 */
	TalonSRXFeedbackDevice(int initValue)
	{
		this.value = initValue;
	}
    /** Keep singleton map to quickly lookup enum via int */
    private static HashMap<Integer, TalonSRXFeedbackDevice> _map = null;
	/** static c'tor, prepare the map */
    static {
    	_map = new HashMap<Integer, TalonSRXFeedbackDevice>();
		for (TalonSRXFeedbackDevice type : TalonSRXFeedbackDevice.values()) {
			_map.put(type.value, type);
		}
    }
	/**
	 * Get TalonSRXFeedbackDevice from specified value
	 * @param value Value of TalonSRXFeedbackDevice
	 * @return TalonSRXFeedbackDevice of specified value
	 */
	public static TalonSRXFeedbackDevice valueOf(int value) {
		TalonSRXFeedbackDevice retval = _map.get(value);
		if (retval != null)
			return retval;
		return QuadEncoder;
	}
	/**
	 * Get TalonSRXFeedbackDevice from specified value
	 * @param value Value of TalonSRXFeedbackDevice
	 * @return TalonSRXFeedbackDevice of specified value
	 */
    public static TalonSRXFeedbackDevice valueOf(double value) {
        return valueOf((int) value); 
	}
	
	/**
	 * @return string representation of specified TalonSRXFeedbackDevice
	 */
    public String toString() {
        switch(value) {
            case 0 : return "TalonSRXFeedbackDevice.QuadEncoder";
            case 2 : return "TalonSRXFeedbackDevice.Analog";
            case 4 : return "TalonSRXFeedbackDevice.Tachometer";
            case 8 : return "TalonSRXFeedbackDevice.PulseWidthEncodedPosition";
            case 9 : return "TalonSRXFeedbackDevice.SensorSum";
            case 10: return "TalonSRXFeedbackDevice.SensorDifference";
            case 11: return "TalonSRXFeedbackDevice.RemoteSensor0";
            case 12: return "TalonSRXFeedbackDevice.RemoteSensor1";
			case 14: return "TalonSRXFeedbackDevice.None";
            case 15: return "TalonSRXFeedbackDevice.SoftwareEmulatedSensor";
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
