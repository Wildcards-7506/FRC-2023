package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
            xspeed = Constants.kRampRate * xInputSpeed + (1 - Constants.kRampRate) * prevXspeed;
            yspeed = Constants.kRampRate * yInputSpeed + (1 - Constants.kRampRate) * prevYspeed;
            rot = Constants.kRampRate * inputRot + (1 - Constants.kRampRate) * prevRot;

        } else {
            //Tank Drive
            yspeed = Constants.kRampRate * PlayerConfigs.driveSpeed * PlayerConfigs.yMovement + (1 - Constants.kRampRate) * prevXspeed;
            xspeed = 0;
            rot = Constants.kRampRate * turnSpeed + (1 - Constants.kRampRate) * prevXspeed;
        }

        prevXspeed = xspeed;
        prevYspeed = yspeed;
        prevRot = rot;

        if (PlayerConfigs.modeSwitch){
            driveMode = !driveMode;
        }

        if (PlayerConfigs.brake) {
            driveMode = !driveMode;
        }

        //Set motors
        Robot.drivetrain.drive(yspeed, xspeed, rot, false);
        
    }
}