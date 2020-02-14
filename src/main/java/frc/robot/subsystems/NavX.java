package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class NavX implements Subsystem {


    static  double starterAngle;
    //static double actualAngle;

    private static AHRS navx;
    //double rotateToAngleRate;

    public NavX() {
        // navx intialization
        try {

            navx = new AHRS(SPI.Port.kMXP); // navX-MXP initialized with (SPI, I2C, TTL UART) and USB
            // //http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.

        } catch (RuntimeException ex) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }

        zeroAngle();
    }

    public static void zeroAngle(){
        //navx.zeroYaw();
        starterAngle = navx.getAngle();

    }

    //returns the angle the robot has changed since start of match in a -180 to 180 range
    public static double getCurrentAngle() {
        double currentAngle = (starterAngle - navx.getAngle() ) % 360;
        if(currentAngle > 180.0){
            currentAngle -= 360.0;
        }
        return currentAngle;
    }

//    @Override
//    public void initDefaultCommand() {
//    }

    public void log() {
        SmartDashboard.putNumber("navxStarterAngle", starterAngle);
        SmartDashboard.putNumber("navxRawAngle", navx.getAngle());
        SmartDashboard.putNumber("navxCurrentAngle", getCurrentAngle());
        //SmartDashboard.putBoolean("NavX Flag", RobotMap.NAVX_TURN_FLAG);


    }
}
