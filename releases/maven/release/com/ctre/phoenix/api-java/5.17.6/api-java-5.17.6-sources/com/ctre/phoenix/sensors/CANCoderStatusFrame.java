package com.ctre.phoenix.sensors;

public enum CANCoderStatusFrame{
    SensorData (0x041400),
    VbatAndFaults (0x041440);

    public final int value;

    /**
	 * Create CANCoderStatusFrame of initValue
	 * @param initValue Value of CANCoderStatusFrame
	 */
	CANCoderStatusFrame(int initValue)
	{
		this.value = initValue;
    }
}