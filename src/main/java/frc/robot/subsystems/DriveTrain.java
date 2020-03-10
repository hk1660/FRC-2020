package frc.robot.subsystems;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DriveForwardJoyStick;
import frc.robot.utils.XboxOne;
import com.revrobotics.CANSparkMax;



public class DriveTrain implements Subsystem {
    private CANSparkMax leftMotors;
    private CANSparkMax leftMotors2;
    private CANSparkMax rightMotors;
    private CANSparkMax rightMotors2;
    private MecanumDrive mecDrive;
    private SendableChooser<Spark> spark;



    private double strafeSpeed = 0.0;
    public double forwardSpeed = 0.0;
    private double turnSpeed = 0.0;
    private double angleParam = 0.0;
    private int leftBack = 15;
    private int leftFront = 14;
    private int rightFront = 2;
    private int rightBack = 1;

    public DriveTrain(){
        super();
        //System.out.println("yer");

        //DT initializations
        leftMotors = new CANSparkMax(leftFront, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightMotors = new CANSparkMax(rightFront, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightMotors2 = new CANSparkMax(rightBack, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftMotors2 = new CANSparkMax(leftBack, CANSparkMaxLowLevel.MotorType.kBrushless);

        SmartDashboard.putString("Drivetrain Init", "Drivetrain Init");

        mecDrive = new MecanumDrive(leftMotors, leftMotors2, rightMotors, rightMotors2);
        mecDrive.setSafetyEnabled(false);

    }

    public void Drive(){
//        mecDrive.driveCartesian(strafeSpeed, forwardSpeed, turnSpeed);
        leftMotors.set(forwardSpeed);
        leftMotors2.set(forwardSpeed);
        rightMotors.set(forwardSpeed);
        rightMotors2.set(forwardSpeed);
        SmartDashboard.putNumber("Drive", this.forwardSpeed);
        SmartDashboard.putNumber("Left MotorCAN", leftMotors.get());

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
        SmartDashboard.putNumber("Right Stick Y", joy.getRightStickRaw_Y());
        SmartDashboard.putNumber("Forward Speed Y", this.forwardSpeed);


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
        SmartDashboard.putNumber("ForwardSpeedX", this.forwardSpeed);
        //SmartDashboard.putNumber("Strafepeed", strafeSpeed);
//        spark = new SendableChooser<Spark>();
//        spark.addOption("leftMotors",leftMotors);
//        spark.addOption("leftMotors2",leftMotors2);
//        spark.addOption("rightMotors",rightMotors);
//        spark.addOption("rightMotors2",rightMotors2);
        SmartDashboard.putData(spark);

    }
}
