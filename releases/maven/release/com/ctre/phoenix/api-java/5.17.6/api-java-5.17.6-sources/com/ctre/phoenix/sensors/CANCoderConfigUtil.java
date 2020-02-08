package com.ctre.phoenix.sensors;
import com.ctre.phoenix.CustomParamConfigUtil;

/**
 * Util class to help with configuring CANifier
 */
class CANCoderConfigUtil extends CustomParamConfigUtil{
	private static CANCoderConfiguration _default = new CANCoderConfiguration();
	
	static boolean velocityMeasurementPeriodDifferent (CANCoderConfiguration settings) { return (!(settings.velocityMeasurementPeriod == _default.velocityMeasurementPeriod)) || !settings.enableOptimizations; }
	static boolean velocityMeasurementWindowDifferent (CANCoderConfiguration settings) { return (!(settings.velocityMeasurementWindow == _default.velocityMeasurementWindow)) || !settings.enableOptimizations; }
	static boolean CustomParam0Different(CANCoderConfiguration settings) { return (!(settings.customParam0 == _default.customParam0)) || !settings.enableOptimizations; }
	static boolean CustomParam1Different(CANCoderConfiguration settings) { return (!(settings.customParam1 == _default.customParam1)) || !settings.enableOptimizations; }
	static boolean absoluteSensorRangeDifferent(CANCoderConfiguration settings) { return (!(settings.absoluteSensorRange == _default.absoluteSensorRange)) || !settings.enableOptimizations; }
	static boolean magnetOffsetDegreesDifferent(CANCoderConfiguration settings) { return (!(settings.magnetOffsetDegrees == _default.magnetOffsetDegrees)) || !settings.enableOptimizations; }
	static boolean sensorDirectionDifferent(CANCoderConfiguration settings) { return (!(settings.sensorDirection == _default.sensorDirection)) || !settings.enableOptimizations; }
	static boolean initializationStrategyDifferent(CANCoderConfiguration settings) { return (!(settings.initializationStrategy == _default.initializationStrategy)) || !settings.enableOptimizations; }
	static boolean sensorCoefficientDifferent(CANCoderConfiguration settings) { return (!(settings.sensorCoefficient == _default.sensorCoefficient)) || !settings.enableOptimizations; }
	static boolean unitStringDifferent(CANCoderConfiguration settings) { return (!(settings.unitString == _default.unitString)) || !settings.enableOptimizations; }
	static boolean sensorTimeBaseDifferent(CANCoderConfiguration settings) { return (!(settings.sensorTimeBase == _default.sensorTimeBase)) || !settings.enableOptimizations; }
}