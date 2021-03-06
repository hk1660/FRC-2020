package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

/**
 * Have the robot drive mecanum style using the XBOXONE Joystick until interrupted.
 */
public class DriveForwardJoyStick implements Command {
    Set<Subsystem> dfj = new HashSet<Subsystem>();
    public DriveForwardJoyStick() {

        dfj.add(Robot.driveTrain);
        SmartDashboard.putString("DriveJoysticForward", "DriveJoysticForward");
    }

    // Called repeatedly when this Command is scheduled to run

    public  void execute() {
        Robot.driveTrain.setForwardSpeed(Robot.oi.getDriverStick().getRightStickRaw_Y());
    }
//public  void execute() {
//    Robot.driveTrain.setForwardSpeed(1);
//    SmartDashboard.putString("Execute", "Execute");
//}

    // Make this return true when this Command no longer needs to run execute()

    public boolean isFinished() {
        return false; // Runs until interrupted
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return dfj;
    }

    // Called once after isFinished returns true

    protected void end() {
        Robot.driveTrain.setForwardSpeed(0.0);
    }
}
