package com.ctre.phoenix.motorcontrol.can;

import com.ctre.phoenix.motorcontrol.can.BaseTalonPIDSetConfiguration;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Configurables available to TalonSRX's PID
 */
public class TalonSRXPIDSetConfiguration extends BaseTalonPIDSetConfiguration {
    public TalonSRXPIDSetConfiguration() {
        super(FeedbackDevice.QuadEncoder);
    }
}


