package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class DrivetrainTOCom extends CommandBase{

    private boolean driveMode = true;
    double prevRot = 0;
    double prevYspeed = 0;
    double prevXspeed = 0;
    double turnSpeed;
    

    public DrivetrainTOCom() {
        addRequirements(Robot.drivetrain);
    }

    @Override
    public void execute(){
        double rot, yspeed, xspeed;
        turnSpeed = PlayerConfigs.turnMovement * PlayerConfigs.turnSpeed;

        if(driveMode){
            //Mecanum Drive
            double xInputSpeed = PlayerConfigs.fineControlToggle ? 
                PlayerConfigs.fineDriveSpeed * PlayerConfigs.fineControlX :
                PlayerConfigs.driveSpeed * PlayerConfigs.xMovement;
            double yInputSpeed = PlayerConfigs.fineControlToggle ? 
                PlayerConfigs.fineDriveSpeed * PlayerConfigs.fineControlY : 
                PlayerConfigs.driveSpeed * PlayerConfigs.yMovement;
            double inputRot = PlayerConfigs.fineControlToggle ? 
                PlayerConfigs.fineTurnSpeed * PlayerConfigs.fineTurnMovement : 
                PlayerConfigs.driveSpeed * turnSpeed;
            xspeed = DriveConstants.RAMP_RATE * xInputSpeed + (1 - DriveConstants.RAMP_RATE) * prevXspeed;
            yspeed = DriveConstants.RAMP_RATE * yInputSpeed + (1 - DriveConstants.RAMP_RATE) * prevYspeed;
            rot = DriveConstants.RAMP_RATE * inputRot + (1 - DriveConstants.RAMP_RATE) * prevRot;

        } else {
            //Tank Drive
            yspeed = DriveConstants.RAMP_RATE * PlayerConfigs.driveSpeed * PlayerConfigs.yMovement + (1 - DriveConstants.RAMP_RATE) * prevXspeed;
            xspeed = 0;
            rot = DriveConstants.RAMP_RATE * turnSpeed + (1 - DriveConstants.RAMP_RATE) * prevXspeed;
        }

        prevXspeed = xspeed;
        prevYspeed = yspeed;
        prevRot = rot;

        if (PlayerConfigs.modeSwitch){
            driveMode = !driveMode;
        }

        //Set motors
        Robot.drivetrain.drive(xspeed, yspeed, rot, driveMode);
        
    }
}