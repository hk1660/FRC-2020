package com.ctre.phoenix.motorcontrol.can;

/**
 * Util class to help with talon configs
 */
class TalonFXConfigUtil {
	private static TalonFXConfiguration _default = new TalonFXConfiguration();
	
	static boolean SupplyCurrentLimitDifferent(TalonFXConfiguration settings) { return (!settings.supplyCurrLimit.equals(_default.supplyCurrLimit)) || !settings.enableOptimizations; }
	static boolean StatorCurrentDurationDifferent(TalonFXConfiguration settings) { return (!settings.statorCurrLimit.equals(_default.statorCurrLimit)) || !settings.enableOptimizations; }
	static boolean MotorCommutationDifferent(TalonFXConfiguration settings) { return (settings.motorCommutation != _default.motorCommutation) || !settings.enableOptimizations; }
	static boolean AbsoluteSensorRangeDifferent(TalonFXConfiguration settings) { return (settings.absoluteSensorRange != _default.absoluteSensorRange) || !settings.enableOptimizations; }
	static boolean IntegratedSensorOffsetDegreesDifferent(TalonFXConfiguration settings) { return (settings.integratedSensorOffsetDegrees != _default.integratedSensorOffsetDegrees) || !settings.enableOptimizations; }
	static boolean InitializationStrategyDifferent(TalonFXConfiguration settings) { return (settings.initializationStrategy != _default.initializationStrategy) || !settings.enableOptimizations; }
}