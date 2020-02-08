package com.ctre.phoenix.motorcontrol.can;

/**
 * Util class to help with talon configs
 */
class TalonSRXConfigUtil {
	private static TalonSRXConfiguration _default = new TalonSRXConfiguration();
	
	static boolean peakCurrentLimitDifferent (TalonSRXConfiguration settings) { return (!(settings.peakCurrentLimit == _default.peakCurrentLimit)) || !settings.enableOptimizations; }
	static boolean peakCurrentDurationDifferent (TalonSRXConfiguration settings) { return (!(settings.peakCurrentDuration == _default.peakCurrentDuration)) || !settings.enableOptimizations; }
	static boolean continuousCurrentLimitDifferent (TalonSRXConfiguration settings) { return (!(settings.continuousCurrentLimit == _default.continuousCurrentLimit)) || !settings.enableOptimizations; }
}