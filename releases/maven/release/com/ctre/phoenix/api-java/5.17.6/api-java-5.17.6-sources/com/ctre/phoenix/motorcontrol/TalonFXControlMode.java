package com.ctre.phoenix.motorcontrol;

/**
 * Choose the control mode for a TalonFX / Falcon 500.
 */
public enum TalonFXControlMode
{
	/**
	 * Percent output [-1,1]
	 */
	PercentOutput(0),
	/**
	 * Position closed loop
	 */
	Position(1),
	/**
	 * Velocity closed loop
	 */
	Velocity(2),
	/**
	 * Input current closed loop
	 */
	Current(3),
	/**
	 * Follow other motor controller
	 */
	Follower(5),
	/**
	 * Motion Profile
	 */
	MotionProfile(6),
	/**
	 * Motion Magic
	 */
	MotionMagic(7),
	/**
	 * Motion Profile with auxiliary output
	 */
	MotionProfileArc(10),

	/**
	 * Disable Motor Controller
	 */
	Disabled(15);

	/**
	 * Value of control mode
	 */
	public final int value;

	/**
	 * Create TalonFXControlMode of initValue
	 * @param initValue Value of TalonFXControlMode
	 */
	TalonFXControlMode(int initValue)
	{
		this.value = initValue;
	}

	/**
	 * Helper method to convert to generic ControlMode enum.
	 * @return value cast as ControlMode
	 */
	public ControlMode toControlMode(){
		return ControlMode.values()[value];
	}
};
