package frc.robot.ControlConfigs.Drivers;

import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class Lam extends PlayerConfigs{
    
    public void getDriverConfig() {
        //Constants
        PlayerConfigs.turnSpeed = 0.7;
        PlayerConfigs.driveSpeed = 0.5;
        PlayerConfigs.rampRate = 0.01;

        //Driving and rotation
        PlayerConfigs.xMovement = Robot.controller0.getLeftX();
        PlayerConfigs.yMovement = Robot.controller0.getLeftY();
        PlayerConfigs.turnMovement = Robot.controller0.getRightX();
        PlayerConfigs.modeSwitch = Robot.controller0.getR2Axis() > .2;
        PlayerConfigs.snapZero = Robot.controller0.getPOV() == 0;
        PlayerConfigs.snap90 = Robot.controller0.getPOV() == 90;
        PlayerConfigs.snap180 = Robot.controller0.getPOV() == 180;
        PlayerConfigs.snap270 = Robot.controller0.getPOV() == 270;

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
        PlayerConfigs.toggleLeds = Robot.controller0.getTouchpad();
    }

    public void getCoDriverConfig() {
        //Scoring and grabbing objects
        PlayerConfigs.groundGrab = Robot.controller1.getRightTriggerAxis() > .2;
        PlayerConfigs.highGoal = Robot.controller1.getPOV() == 0;
        PlayerConfigs.lowGoal = Robot.controller1.getPOV() == 180;
        PlayerConfigs.collectPos = Robot.controller1.getRightBumper();

        //Claw
        PlayerConfigs.intake = Robot.controller1.getLeftTriggerAxis() > 0.2;
        PlayerConfigs.release = Robot.controller1.getLeftBumper();
        PlayerConfigs.cranePos = Robot.controller1.getLeftY();
        PlayerConfigs.extendPos = Robot.controller1.getRightY();
        PlayerConfigs.craneControl = Robot.controller1.getAButton();

        //Limelight Switch
        PlayerConfigs.switchPipeline = Robot.controller1.getXButton();
    }
}
