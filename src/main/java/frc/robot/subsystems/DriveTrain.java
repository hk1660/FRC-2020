package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;





public class DriveTrain implements Subsystem {
    private Spark leftMotors;
    private Spark leftMotors2;
    private Spark rightMotors;
    private Spark rightMotors2;
    private MecanumDrive mecDrive;



    private double strafeSpeed = 0.0;
    private double forwardSpeed = 0.0;
    private double turnSpeed = 0.0;
    private double angleParam = 0.0;

    public DriveTrain(){
        super();

        //DT initializations
        leftMotors = new Spark(RobotMap.DRIVE_FRONT_LEFT_CHANNEL);
        rightMotors = new Spark(RobotMap.DRIVE_FRONT_RIGHT_CHANNEL);
        rightMotors2 = new Spark(RobotMap.DRIVE_BACK_RIGHT_CHANNEL);
        leftMotors2 = new Spark(RobotMap.DRIVE_BACK_LEFT_CHANNEL);

        mecDrive = new MecanumDrive(leftMotors, leftMotors2, rightMotors, rightMotors2);

    }

    public void Drive(){
        mecDrive.driveCartesian(strafeSpeed, forwardSpeed, turnSpeed);

    }

    public void Drive(double strafeParameter, double forwardParameter, double turnParameter, double angleParameter){
        strafeSpeed = strafeParameter;
        forwardSpeed = forwardParameter;
        turnSpeed = turnParameter;
        this.angleParam = angleParameter;

        Drive();
    }

    public void setStrafeSpeed(double strafeSpeed){
        this.strafeSpeed = strafeSpeed;
        Drive();
    }

    public void setForwardSpeed(double forwardSpeed){
        this.forwardSpeed = forwardSpeed;
        Drive();
    }

    public void setTurnSpeed(double turnSpeed){
        this.turnSpeed = turnSpeed;
        Drive();
    }
}
