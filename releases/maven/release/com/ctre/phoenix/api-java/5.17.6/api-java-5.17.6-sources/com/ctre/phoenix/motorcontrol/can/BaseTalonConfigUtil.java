package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Util class to help with talon configs
 */
class BaseTalonConfigUtil {
	private static BaseTalonConfiguration _default = new BaseTalonConfiguration(FeedbackDevice.QuadEncoder);
	/* Default feedback sensor is product specific. In order to ensure user always gets what they expect when selecting feedback sensor,
		(Sum/Diff)(0/1)TermDifferent will always return true */
	
	static boolean forwardLimitSwitchSourceDifferent (BaseTalonConfiguration settings) { return (!(settings.forwardLimitSwitchSource == _default.forwardLimitSwitchSource)) || !settings.enableOptimizations; }
	static boolean reverseLimitSwitchSourceDifferent (BaseTalonConfiguration settings) { return (!(settings.reverseLimitSwitchSource == _default.reverseLimitSwitchSource)) || !settings.enableOptimizations; }
	static boolean forwardLimitSwitchDeviceIDDifferent (BaseTalonConfiguration settings) { return (!(settings.forwardLimitSwitchDeviceID == _default.forwardLimitSwitchDeviceID)) || !settings.enableOptimizations; }
	static boolean reverseLimitSwitchDeviceIDDifferent (BaseTalonConfiguration settings) { return (!(settings.reverseLimitSwitchDeviceID == _default.reverseLimitSwitchDeviceID)) || !settings.enableOptimizations; }
	static boolean forwardLimitSwitchNormalDifferent (BaseTalonConfiguration settings) { return (!(settings.forwardLimitSwitchNormal == _default.forwardLimitSwitchNormal)) || !settings.enableOptimizations; }
	static boolean reverseLimitSwitchNormalDifferent (BaseTalonConfiguration settings) { return (!(settings.reverseLimitSwitchNormal == _default.reverseLimitSwitchNormal)) || !settings.enableOptimizations; }
	static boolean sum0TermDifferent (BaseTalonConfiguration settings) { return true; } //{ return (!(settings.sum0Term == _default.sum0Term)) || !settings.enableOptimizations; }
	static boolean sum1TermDifferent (BaseTalonConfiguration settings) { return true; } //{ return (!(settings.sum1Term == _default.sum1Term)) || !settings.enableOptimizations; }
	static boolean diff0TermDifferent (BaseTalonConfiguration settings) { return true; } //{ return (!(settings.diff0Term == _default.diff0Term)) || !settings.enableOptimizations; }
	static boolean diff1TermDifferent (BaseTalonConfiguration settings) { return true; } //{ return (!(settings.diff1Term == _default.diff1Term)) || !settings.enableOptimizations; }
	
	static boolean forwardLimitSwitchDifferent (BaseTalonConfiguration settings) {
		return forwardLimitSwitchDeviceIDDifferent(settings) || forwardLimitSwitchNormalDifferent(settings) || forwardLimitSwitchSourceDifferent(settings);
	}
	static boolean reverseLimitSwitchDifferent (BaseTalonConfiguration settings) {
		return reverseLimitSwitchDeviceIDDifferent(settings) || reverseLimitSwitchNormalDifferent(settings) || reverseLimitSwitchSourceDifferent(settings);
	}
}