package frc.robot.utils;

import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickPovButton extends Button {

    private GenericHID m_joystick;
    private int m_povAngle;

    public JoystickPovButton(GenericHID joystick, int povAngle) {
        m_joystick = joystick;
        m_povAngle = povAngle;
    }

    public boolean get() {

        return m_joystick.getPOV() == m_povAngle;

    }
}
