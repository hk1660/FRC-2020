package frc.robot;

public class RobotMap {
    //Joystick Ports
    public final static int DRIVER_JOYSTICK_PORT = 0;
    public final static int MANIPULATOR_JOYSTICK_PORT = 1;

    //Drive Joystick
    // public final static int FORWARD_AXIS = RIGHT_Y_AXIS;
    // public final static int STRAFE_AXIS = RIGHT_X_AXIS;
    // public final static int TURN_AXIS = LEFT_X_AXIS;
    // public final static int MOVING_Y_AXIS = RIGHT_Y_AXIS;
    // public final static int MOVING_X_AXIS = RIGHT_X_AXIS;
    // public final static int HEADING_Y_AXIS = LEFT_Y_AXIS;
    // public final static int HEADING_X_AXIS = LEFT_X_AXIS;
    // public final static int FIELD_DRIVING_FLAG_ON_BUTTON = RB_BUTTON;
    // public final static int FIELD_DRIVING_FLAG_OFF_BUTTON = LB_BUTTON;
    // public final static int YAW_ZERO_BUTTON = START_BUTTON;
    // public final static int FACE_BACKWARD_POV = POV_DOWN;
    // public final static int FACE_LEFT_POV = POV_LEFT;
    // public final static int FACE_RIGHT_POV = POV_RIGHT;
    // public final static int FACE_FORWARD_POV = POV_UP;

    //Mani Joystick
    // public final static int LIFT_AXIS = LEFT_Y_AXIS;
    // public final static int EAT_BUTTON = A_BUTTON;
    // public final static int SPIT_BUTTON = B_BUTTON;
    // public final static int ZERO_ENCODER_BUTTON = BACK_BUTTON;
    // public final static int RELAX_MOTOR_BUTTON = START_BUTTON;
    // public final static int LIFT_BOTTOM_HEIGHT_POV = POV_DOWN;
    // public final static int LIFT_EXCHANGE_HEIGHT_POV = POV_LEFT;
    // public final static int LIFT_TIER2_HEIGHT_POV = POV_RIGHT;
    // public final static int LIFT_SWITCH_HEIGHT_POV = POV_UP;
    // public final static int REACH_BUTTON =  Y_BUTTON;
    // public final static int PULL_UP_BUTTON =  X_BUTTON;
    // public final static int FLIP_AXIS = RT_AXIS;
    // public final static int DIP_AXIS = LT_AXIS;
    // public final static int LOCK_BUTTON = 75;
    // public final static int LIFT_TOP_HEIGHT = LB_BUTTON;

    //public final static int COMPRESSOR_BUTTON = LB_BUTTON;
    // public final static int SCALE_HEIGHT_AXIS = RIGHT_Y_AXIS;
    // public final static int PRESSURE_OVERRIDE_BUTTON = RB_BUTTON;

    // Drive channels
    public final static int DRIVE_FRONT_LEFT_CHANNEL = 0;
    public final static int DRIVE_BACK_LEFT_CHANNEL = 1;
    public final static int DRIVE_FRONT_RIGHT_CHANNEL = 14;
    public final static int DRIVE_BACK_RIGHT_CHANNEL = 15;
/*
    //Manip channels
    //public final static int CARGO_ROLLER_CHANNEL = 6;
    //public final static int TONSIL_PRIME_CHANNEL = 7;
    //public final static int TONSIL_CHANNEL = 8;
    //public final static int WINCH_MOTOR_CHANNEL = 5;
    //public final static int SECOND_WINCH_MOTOR_CHANNEL = 7;
    //PCM Channels
    //public final static int COMPRESSOR_PORT  = 0;
    public final static int PISTON_IN_ELEV_CHANNEL = 0;
    public final static int PISTON_OUT_ELEV_CHANNEL = 1;
    public final static int PISTON_IN_FIXED_CHANNEL = 2;
    public final static int PISTON_OUT_FIXED_CHANNEL = 3;
    public final static int PISTON_IN_WINCH_CHANNEL = 4;
    public final static int PISTON_OUT_WINCH_CHANNEL = 5;


    public final static int LIFT_LIMIT_TOP_CHANNEL = 0;
*/
    //NavX angles
    public final static double LEFT_WALL_ANGLE = -90.0;
    public final static double RIGHT_WALL_ANGLE = 90.0;
    public final static double BACK_WALL_ANGLE = 179.99;
    public final static double FRONT_WALL_ANGLE = 0.0;

    public final static double LEFT_ROCKET_FRONT_ANGLE = -29.0;
    public final static double LEFT_ROCKET_BACK_ANGLE = -151.0;
    public final static double RIGHT_ROCKET_FRONT_ANGLE = 29.0;
    public final static double RIGHT_ROCKET_BACK_ANGLE = 151.0;


    public final static int PDP_ID = 0;

    //Levels
    //OG vals: Lvl 1 - 200.0, 1.5 - 24000.0, 2 - 57000.0, 2.5 - 82000.0, 3 - 109000.0, 3.5 - 135000.0
    public final static double LEVEL_0 = 0 * 1000;
    public final static double LEVEL_1 = 5.942 * 1000;
    public final static double LEVEL_1_5 = 52 * 1000;
    public final static double LEVEL_2 = 99.840 * 1000;
    public final static double EAT_CARGO_HEIGHT = 123 * 1000; //Dillan Height
    public final static double LEVEL_2_5 = 160 * 1000;
    public final static double LEVEL_3 = 187.5 * 1000;
    public final static double LEVEL_3_5 = 263 * 1000;


    //Digital Input Channels
    public final static int DIGITAL_PORT_LIMIT = 0;
    public final static int CARGO_BUTTON_CHANNEL = 2;

    //PWM Channels
    public final static int BLINKIN_PWM_CHANNEL = 2;


    //Flags
    public static boolean WINCH_PID_FLAG = true;
    public static boolean BB_FLAG = true;
    public static boolean LL_FLAG = false;
    public static boolean LOCK_FLAG = false;
    public static boolean NAVX_TURN_FLAG = false;
}


