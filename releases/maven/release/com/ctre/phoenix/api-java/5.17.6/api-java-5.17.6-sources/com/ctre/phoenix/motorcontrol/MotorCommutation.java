package com.ctre.phoenix.motorcontrol;

/**
 * Choose the type of motor commutation.  This is for products that support selectable commutation strategies.
 */
public enum MotorCommutation{
    /**
     * Trapezoidal commutation
     */
    Trapezoidal(0);

    /**
     * Value of MotorCommutation
     */
    public final int value;

    /**
	 * Create MotorCommutation of initValue
	 * @param initValue Value of MotorCommutation
	 */
	MotorCommutation(int initValue)
	{
		this.value = initValue;
	}
}