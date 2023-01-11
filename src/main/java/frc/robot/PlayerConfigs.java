package frc.robot;

public class PlayerConfigs {

    //buttons used: left joystick, right joystick, circle, entire d pad, L1, L2, R1, R2
    
    //drivetrain
    public static double xMovement;
    public static double yMovement;
    public static double turnMovement;
    public static double turnSpeed;
    public static double driveSpeed;
    public static boolean modeSwitch;

    //crane
    public static double craneSpeed;
    public static double lowerCrane;
    public static double raiseCrane;
    public static int povPos;
    public static boolean frontOut;
    public static boolean openClaw;
    public static boolean closeClaw;

    //limelight
    public static boolean switchPipeline;

    public static void getDriverConfig(){
        //drivetrain
        xMovement = Robot.controller0.getLeftX();
        yMovement = Robot.controller0.getLeftY();
        turnMovement = Robot.controller0.getRightX();
        turnSpeed = 0.1;
        driveSpeed = 0.5;

        //Drivetrain change
        modeSwitch = Robot.controller0.getCircleButtonReleased();

        //limelight
        switchPipeline = Robot.controller0.getCircleButton();
    }

    /*Need to add Co-Driver controls
     * Arm Positioning (Ground, Middle Score, High Score, Shelf Pickup)
     * Arm Extension (Ground, Middle Score, High Score, Shelf Pickup)
     * Claw/Pincher Release
     * Fine-Detail Driving control (Scoring and Endgame)
    */

    public static void getCoDriverConfig(){  
        //crane controls
        lowerCrane = Robot.controller0.getL2Axis();
        raiseCrane = Robot.controller0.getR2Axis();
        povPos = Robot.controller0.getPOV();
        craneSpeed = 0.35;
        // i want to do a thing where once the crane passes a point, the controls are inverted once the person lets go of the button?
        // think that might be unnecessarily complicated, but would be really nice to have
        openClaw = Robot.controller0.getL1ButtonReleased();
        closeClaw = Robot.controller0.getR1ButtonReleased();
    }
}