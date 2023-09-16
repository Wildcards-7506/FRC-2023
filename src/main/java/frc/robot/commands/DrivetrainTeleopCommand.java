package frc.robot.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;
import frc.robot.util.Logger;

public class DrivetrainTeleopCommand extends CommandBase{

    double rot, yspeed, xspeed;
    double prevRot = 0;
    double prevYspeed = 0;
    double prevXspeed = 0;
    boolean mecanumDrive = true;
    String payload;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public DrivetrainTeleopCommand() {
        addRequirements(Robot.drivetrain);
    }

    @Override
    public void execute(){
        double xInputSpeed = PlayerConfigs.fineControlToggle ? 
            PlayerConfigs.fineDriveSpeed * PlayerConfigs.fineControlX :
            PlayerConfigs.driveSpeed * PlayerConfigs.xMovement;
        double yInputSpeed = PlayerConfigs.fineControlToggle ? 
            PlayerConfigs.fineDriveSpeed * PlayerConfigs.fineControlY : 
            PlayerConfigs.driveSpeed * PlayerConfigs.yMovement;
        double inputRot = PlayerConfigs.fineControlToggle ? 
            PlayerConfigs.fineTurnSpeed * PlayerConfigs.fineTurnMovement : 
            PlayerConfigs.driveSpeed * PlayerConfigs.turnMovement * PlayerConfigs.turnSpeed;
        //Fine Control
        if(PlayerConfigs.fineControlToggle){
            xspeed = xInputSpeed;
            yspeed = yInputSpeed;
            rot = inputRot;
        } else {
            xspeed = PlayerConfigs.rampRate * xInputSpeed + (1 - PlayerConfigs.rampRate) * prevXspeed;
            yspeed = PlayerConfigs.rampRate * yInputSpeed + (1 - PlayerConfigs.rampRate) * prevYspeed;
            rot = PlayerConfigs.rampRate * inputRot + (1 - PlayerConfigs.rampRate) * prevRot;
        }            

        prevXspeed = xspeed;
        prevYspeed = yspeed;
        prevRot = rot;

        //Snap if needed, otherwise set drive motors
        if(PlayerConfigs.snapZero){
            Robot.drivetrain.snap(0);
        } else if(PlayerConfigs.snap90) {
            Robot.drivetrain.snap(90);
        } else if(PlayerConfigs.snap180) {
            Robot.drivetrain.snap(180);
        } else if(PlayerConfigs.snap270){
            Robot.drivetrain.snap(270);
        } else if((PlayerConfigs.align)){
            Robot.drivetrain.align(Robot.limelight.getTX());
        } else if (PlayerConfigs.brake) {
            Robot.drivetrain.drive(0, 0, 0, true, false);
        } else {
            Robot.drivetrain.drive(yspeed, xspeed, rot, true, true);
        }   
        payload = df.format(yspeed)
         + " " + df.format(xspeed)
         + " " + df.format(rot)
         + " (Y,X,R)";
        
         Logger.info("DRIVE", payload);
        Robot.drivetrain.m_frontLeft.errorCheck("FLDRV", "FLTRN");
        Robot.drivetrain.m_rearLeft.errorCheck("RLDRV", "RLTRN");
        Robot.drivetrain.m_frontRight.errorCheck("FRDRV", "FRTRN");
        Robot.drivetrain.m_rearRight.errorCheck("RRDRV", "RRTRN");
    }
}