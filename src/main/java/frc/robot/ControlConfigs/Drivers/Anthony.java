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
        PlayerConfigs.modeSwitch = Robot.controller0.getSquareButton();
        PlayerConfigs.snapZero = Robot.controller0.getPOV() == 0;
        PlayerConfigs.snap90 = Robot.controller0.getPOV() == 270;
        PlayerConfigs.snap180 = Robot.controller0.getPOV() == 180;
        PlayerConfigs.snap270 = Robot.controller0.getPOV() == 90;

        //Constants turn speed drive speed
        PlayerConfigs.fineTurnSpeed = 0.175;
        PlayerConfigs.fineDriveSpeed = 0.2;

        //Fine movement
        PlayerConfigs.fineControlX = Robot.controller0.getLeftX();
        PlayerConfigs.fineControlY = Robot.controller0.getLeftY();
        PlayerConfigs.fineTurnMovement = Robot.controller0.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller0.getL2Button();

        //Signal object
        PlayerConfigs.signalCone = Robot.controller0.getTriangleButton();
        PlayerConfigs.signalCube = Robot.controller0.getSquareButton();
        PlayerConfigs.toggleLeds = Robot.controller0.getCircleButton();
    } 

    public void getCoDriverConfig() {
        //Scoring and grabbing objects
        PlayerConfigs.groundGrab = Robot.controller1.getAButton();
        PlayerConfigs.highGoal = Robot.controller1.getYButton();
        PlayerConfigs.lowGoal = Robot.controller1.getBButton();
        PlayerConfigs.collectPos = Robot.controller1.getXButton();

        //Claw or Roller
        PlayerConfigs.intake = Robot.controller1.getRightTriggerAxis() > 0.2;
        PlayerConfigs.release = Robot.controller1.getRightBumper();
        PlayerConfigs.craneControl = Robot.controller1.getLeftTriggerAxis() > 0.2;
        PlayerConfigs.cranePos = Robot.controller1.getPOV();

        //Limelight Switch
        PlayerConfigs.switchPipeline = Robot.controller1.getStartButton();
    }
}
