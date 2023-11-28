package frc.robot.ControlConfigs;

public class PlayerConfigs {

    //buttons used: left joystick, right joystick, circle, entire d pad, L1, L2, R1, R2
    
    //primary drivetrain controls
    public static double xMovement;
    public static double yMovement;
    public static double turnMovement;
    public static double turnSpeed;
    public static double driveSpeed;
    public static double rampRate;
    public static boolean snapZero;
    public static boolean brake;
    public static boolean snap90;
    public static boolean snap180;
    public static boolean snap270;
    public static boolean align;

    //secondary drive train controls
    public static double fineControlX;
    public static double fineControlY;
    public static double fineTurnMovement;
    public static double fineTurnSpeed;
    public static double fineDriveSpeed;
    public static boolean fineControlToggle;

    //crane
    public static boolean doubleSub;
    public static boolean groundGrab;
    public static boolean score;
    public static boolean singleSub;
    public static double cranePos;
    public static double extendPos;
    public static boolean craneControl;
    public static boolean redundantCraneControl;
    public static boolean fineExtender;

    //pinchers
    public static boolean leftPinch;
    public static boolean rightPinch;

    //limelight
    public static boolean switchPipeline;

    //Roller
    public static boolean intake;
    public static boolean release;

    public void getDriverConfig(){}

    public void getCoDriverConfig(){}
}