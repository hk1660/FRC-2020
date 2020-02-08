package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Util class to help with TalonSRX's PID configs
 */
public class BaseTalonPIDSetConfigUtil {
	private static BaseTalonPIDSetConfiguration _default = new BaseTalonPIDSetConfiguration(FeedbackDevice.QuadEncoder);
	/* Default feedback sensor is product specific. In order to ensure user always gets what they expect when selecting feedback sensor,
		(Sum/Diff)(0/1)TermDifferent will always return true */
	
	static boolean selectedFeedbackSensorDifferent (BaseTalonPIDSetConfiguration settings) { return true; } //{ return (!(settings.selectedFeedbackSensor == _default.selectedFeedbackSensor)); }
	static boolean selectedFeedbackCoefficientDifferent (BaseTalonPIDSetConfiguration settings) { return (!(settings.selectedFeedbackCoefficient == _default.selectedFeedbackCoefficient)); }
}