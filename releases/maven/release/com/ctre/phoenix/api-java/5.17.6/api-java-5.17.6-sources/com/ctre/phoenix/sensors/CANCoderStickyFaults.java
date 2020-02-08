package com.ctre.phoenix.sensors;

/**
 * Sticky Faults available to CANCoder (Currently has none)
 */
public class CANCoderStickyFaults {
	/**
	 * Device detects hardware failure
	 */
	public boolean HardwareFault;
	/**
	 * API error detected.  Make sure API and firmware versions are compatible.
	 */
	public boolean APIError;
	/**
	 * Device is under 6.5V
	 */
	public boolean UnderVoltage;
	/**
	 * Device was powered-on or reset while robot is enabled.
	 * Check your breakers and wiring.
	 */
	public boolean ResetDuringEn;
	/**
	 * Magnet strength is too weak to provide reliable results
	 * Make sure CANCoder is close to the magnet being used
	 */
	public boolean MagnetTooWeak;
	/**
	 * @return true if any faults are tripped
	 */
	public boolean hasAnyFault() {
		return 	HardwareFault |
				APIError |
				UnderVoltage |
				ResetDuringEn |
				MagnetTooWeak;
	}
	/**
	 * @return Current fault list as a bit field
	 */
	public int toBitfield() {
		int retval = 0;
		int mask = 1;
		retval |= HardwareFault ? mask : 0; mask <<= 1;
		retval |= APIError		? mask : 0; mask <<= 1;
		retval |= UnderVoltage 	? mask : 0; mask <<= 1;
		retval |= ResetDuringEn ? mask : 0; mask <<= 1; 
		mask <<= 3; /* 3 faults currently unused */
		retval |= MagnetTooWeak ? mask : 0; mask <<= 1;
		return retval;
	}
	/**
	 * Updates current sticky fault list with specified bit field of faults
	 * 
	 * @param bits bit field of sticky faults to update with
	 */
	public void update(int bits) {
		int mask = 1;
		HardwareFault = (bits & mask) != 0; mask <<= 1;
		APIError = 		(bits & mask) != 0; mask <<= 1;
		UnderVoltage = 	(bits & mask) != 0; mask <<= 1;
		ResetDuringEn = (bits & mask) != 0; mask <<= 1;
		mask <<= 3; /* 3 faults currently unused */
		MagnetTooWeak = (bits & mask) != 0; mask <<= 1;
	}
	public CANCoderStickyFaults() {
	}
};