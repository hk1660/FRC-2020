package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.can.BaseTalonPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Configurables available to TalonFX's PID
 */
public class TalonFXPIDSetConfiguration extends BaseTalonPIDSetConfiguration {
    public TalonFXPIDSetConfiguration() {
        super(FeedbackDevice.IntegratedSensor);
    }
}


