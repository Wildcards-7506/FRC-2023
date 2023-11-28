package frc.robot.ControlConfigs.Drivers;

import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class Anthony extends PlayerConfigs {
    public void getDriverConfig() {
        //Constants
        PlayerConfigs.turnSpeed = 0.3;
        PlayerConfigs.driveSpeed = 0.5;
        PlayerConfigs.rampRate = 0.01;

        //Driving and rotation
        PlayerConfigs.xMovement = Robot.controller0.getLeftX();
        PlayerConfigs.yMovement = Robot.controller0.getLeftY();
        PlayerConfigs.turnMovement = Robot.controller0.getRightX();
        PlayerConfigs.snapZero = Robot.controller0.getAButton();
        PlayerConfigs.snap90 = Robot.controller0.getBButton();
        PlayerConfigs.snap180 = Robot.controller0.getYButton();
        PlayerConfigs.snap270 = Robot.controller0.getXButton();
        PlayerConfigs.align = Robot.controller0.getLeftBumper();

        //Constants turn speed drive speed
        PlayerConfigs.fineTurnSpeed = 0.175;
        PlayerConfigs.fineDriveSpeed = 0.2;

        //Fine movement
        PlayerConfigs.fineControlX = Robot.controller0.getLeftX();
        PlayerConfigs.fineControlY = Robot.controller0.getLeftY();
        PlayerConfigs.fineTurnMovement = Robot.controller0.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller0.getLeftTriggerAxis() > 0.2;

        //Scoring and grabbing objects
        PlayerConfigs.groundGrab = Robot.controller0.getPOV() == 180;
        PlayerConfigs.score = Robot.controller0.getPOV() == 0;
        PlayerConfigs.doubleSub = Robot.controller0.getPOV() == 90;
        PlayerConfigs.singleSub = Robot.controller0.getPOV() == 270;

        //Intake
        PlayerConfigs.intake = Robot.controller0.getRightTriggerAxis() > 0.2;
        PlayerConfigs.release = Robot.controller0.getRightBumper();
    } 

    public void getCoDriverConfig() {
        
        PlayerConfigs.craneControl = Robot.controller1.getLeftTriggerAxis() > 0.2;
        PlayerConfigs.cranePos = Robot.controller1.getLeftY();
        PlayerConfigs.fineExtender = Robot.controller1.getRightStickButton();
        PlayerConfigs.extendPos = Robot.controller1.getRightY();

        //Pinchers
        PlayerConfigs.leftPinch = Robot.controller1.getXButton();
        PlayerConfigs.rightPinch = Robot.controller1.getBButton();

        //Limelight Switch
        PlayerConfigs.switchPipeline = Robot.controller1.getStartButton();
    }
}
