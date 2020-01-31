package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class DriveTrain implements Subsystem {
    private Spark leftMotors;
    private Spark rightMotors;
    private DifferentialDrive difDrive;

    private double leftSpeed = 0.0;
    private double rightSpeed = 0.0;

    public DriveTrain(){
        super();

        //DT initializations
        //leftMotors = new Spark();
        //rightMotors = newSpark();

        difDrive = new DifferentialDrive(leftMotors, rightMotors);

    }

    public void Drive(){
        difDrive.tankDrive(leftSpeed, rightSpeed);

    }

    public void Drive(double leftParameter, double rightParameter){
        leftSpeed = leftParameter;
        rightSpeed = rightParameter;

        Drive();
    }

    public void setLeftSpeed(double LeftSpeed){
        this.leftSpeed = leftSpeed;
        Drive();
    }

    public void setRightSpeed(double rightSpeed){
        this.rightSpeed = rightSpeed;
        Drive();
    }
}
