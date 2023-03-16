package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class DrivetrainTOCom extends CommandBase{

    double rot, yspeed, xspeed;
    double prevRot = 0;
    double prevYspeed = 0;
    double prevXspeed = 0;
    boolean mecanumDrive = true;
    private boolean prev_MecanumDrive = false;


    public DrivetrainTOCom() {
        addRequirements(Robot.drivetrain);
    }

    @Override
    public void execute(){
        if(PlayerConfigs.modeSwitch != prev_MecanumDrive){
            prev_MecanumDrive = PlayerConfigs.modeSwitch;
            if(PlayerConfigs.modeSwitch){
                mecanumDrive = !mecanumDrive;
            }
        }
        
        //Mecanum Drive
        if(mecanumDrive){
            Robot.drivetrain.setDropWheels(0);
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
        //Tank Drive
        } else {
            if(PlayerConfigs.rampMode){
                Robot.drivetrain.setDropWheels(1.5);
            } else{
                Robot.drivetrain.setDropWheels(0.5);
            }
            //Need to add drop motors here
            double yInputSpeed = PlayerConfigs.fineControlToggle ? 
                PlayerConfigs.fineDriveSpeed * PlayerConfigs.fineControlY : 
                PlayerConfigs.driveSpeed * PlayerConfigs.yMovement;
            double inputRot = PlayerConfigs.fineControlToggle ? 
                PlayerConfigs.fineTurnSpeed * PlayerConfigs.fineTurnMovement : 
                PlayerConfigs.driveSpeed * PlayerConfigs.turnMovement * PlayerConfigs.turnSpeed;
            //Fine Control
            if(PlayerConfigs.fineControlToggle){
                xspeed = 0;
                yspeed = yInputSpeed;
                rot = inputRot;
            } else {
                xspeed = 0;
                yspeed = PlayerConfigs.rampRate * yInputSpeed + (1 - PlayerConfigs.rampRate) * prevYspeed;
                rot = PlayerConfigs.rampRate * inputRot + (1 - PlayerConfigs.rampRate) * prevRot;
            }    
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
            Robot.drivetrain.drive(0, 0, 0, true);
        } else {
            Robot.drivetrain.drive(yspeed, xspeed, rot, !PlayerConfigs.modeSwitch);
        }   
        
        //Robot.drivetrain.updateDisplay(!PlayerConfigs.modeSwitch);
    }
}