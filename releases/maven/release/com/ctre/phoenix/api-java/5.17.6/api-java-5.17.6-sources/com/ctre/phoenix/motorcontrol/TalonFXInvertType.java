package com.ctre.phoenix.motorcontrol;

/**
 * Choose the invert type of the motor controller.
 * None is the equivalent of SetInverted(false), where positive request yields positive voltage on M+.
 * InvertMotorOutput is the equivelant of SetInverted(true), where positive request yields positive voltage on M-.
 * FollowMaster/OpposeMaster will match/oppose a master Talon/Victor.  This requires device to be configured as a follower.
 */
public enum TalonFXInvertType {
	/** Same as SetInverted(false) */
	CounterClockwise(0),
	/** Same as SetInverted(true) */
	Clockwise(1),
	/** Follow the invert of the master this MC is following */
	FollowMaster(2),
	/** Oppose the invert of the master this MC is following */
    OpposeMaster(3);
	
	/**
	 * Value of Invert Type
	 */
	public int value;
	/**
	 * Create TalonFXInvertType of specified value
	 * @param value Value of Invert Type
	 */
	TalonFXInvertType(int value)
	{
		this.value = value;
	}

	/**
	 * Helper method to convert to generic InvertType enum.
	 * @return value cast as InvertType
	 */
	public InvertType toInvertType(){
		return InvertType.values()[value];
	}
};