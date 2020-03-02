package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveForwardJoyStick;
import frc.robot.utils.XboxOne;


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
        //System.out.println("yer");

        //DT initializations
        leftMotors = new Spark(RobotMap.DRIVE_FRONT_LEFT_CHANNEL);
        rightMotors = new Spark(RobotMap.DRIVE_FRONT_RIGHT_CHANNEL);
        rightMotors2 = new Spark(RobotMap.DRIVE_BACK_RIGHT_CHANNEL);
        leftMotors2 = new Spark(RobotMap.DRIVE_BACK_LEFT_CHANNEL);

        SmartDashboard.putString("Drivetrain Init", "Drivetrain Init");

        mecDrive = new MecanumDrive(leftMotors, leftMotors2, rightMotors, rightMotors2);
        mecDrive.setSafetyEnabled(false);

    }

    public void Drive(){
        mecDrive.driveCartesian(strafeSpeed, forwardSpeed, turnSpeed);
        SmartDashboard.putString("Drive", "Drive");

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

    public void driveJoystick(XboxOne joy) {
        this.forwardSpeed = joy.getRightStickRaw_Y();
        if(Math.abs(joy.getLeftStickRaw_X()) > 0.1){
            RobotMap.NAVX_TURN_FLAG = false;
        }


        if(!RobotMap.NAVX_TURN_FLAG){
            this.turnSpeed =  joy.getLeftStickRaw_X();
        }
        Drive();
        SmartDashboard.putString("Joystick", "Running");


        //drive(joy.getRightStickRaw_X(), joy.getRightStickRaw_Y(), joy.getLeftStickRaw_X());
    }

    public void initDefaultCommand() {
        SmartDashboard.putString("Default", "Default");
        setDefaultCommand(new DriveForwardJoyStick());


    }



    public void log() {
        //SmartDashboard.putNumber("RawEncoderD", getEncoderVal());
        //SmartDashboard.putNumber("Distance", getDistance());
        //SmartDashboard.putNumber("TurnSpeed", turnSpeed);
        SmartDashboard.putNumber("ForwardSpeed", forwardSpeed);
        //SmartDashboard.putNumber("Strafepeed", strafeSpeed);

    }
}
