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

        //Signal object
        PlayerConfigs.signalCone = Robot.controller0.getTriangleButton();
        PlayerConfigs.signalCube = Robot.controller0.getSquareButton();
        PlayerConfigs.toggleLeds = Robot.controller0.getCircleButton();
    } 

    public void getCoDriverConfig() {
        //Constants turn speed drive speed
        PlayerConfigs.fineTurnSpeed = 0.175;
        PlayerConfigs.fineDriveSpeed = 0.2;

        //Fine movement
        PlayerConfigs.fineControlX = Robot.controller1.getLeftX();
        PlayerConfigs.fineControlY = Robot.controller1.getLeftY();
        PlayerConfigs.fineTurnMovement = Robot.controller1.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller1.getLeftTriggerAxis() > .2;

        //Scoring and grabbing objects
        PlayerConfigs.groundGrab = Robot.controller1.getPOV() == 180;
        PlayerConfigs.highGoal = Robot.controller1.getPOV() == 0;
        PlayerConfigs.lowGoal = Robot.controller1.getPOV() == 90;
        PlayerConfigs.collectPos = Robot.controller1.getPOV() == 270;

        //Claw
        PlayerConfigs.openClaw = Robot.controller1.getLeftBumper();

        //Limelight Switch
        PlayerConfigs.switchPipeline = Robot.controller1.getYButton();
    }
}
