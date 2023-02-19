package frc.robot.ControlConfigs.Drivers;

import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class Shannon extends PlayerConfigs {
    
    public void getDriverConfig() {
        turnSpeed = 0.4;
        driveSpeed = 0.5;
        PlayerConfigs.rampRate = 0.01;

        PlayerConfigs.xMovement = Robot.controller0.getLeftX();
        PlayerConfigs.yMovement = Robot.controller0.getLeftY();
        PlayerConfigs.turnMovement = Robot.controller0.getRightX();

        PlayerConfigs.fineTurnSpeed = 0.15;
        PlayerConfigs.fineDriveSpeed = 0.25;

        PlayerConfigs.fineControlX = Robot.controller0.getLeftX();
        PlayerConfigs.fineControlY = Robot.controller0.getLeftY();
        PlayerConfigs.fineTurnMovement = Robot.controller0.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller0.getCrossButton();

        PlayerConfigs.modeSwitch = Robot.controller0.getL2Button();
        PlayerConfigs.snapZero = Robot.controller0.getPOV() == 0;
        PlayerConfigs.snap90 = Robot.controller0.getPOV() == 270;
        PlayerConfigs.snap180 = Robot.controller0.getPOV() == 180;
        PlayerConfigs.snap270 = Robot.controller0.getPOV() == 90;

        PlayerConfigs.signalCone = Robot.controller0.getTriangleButton();
        PlayerConfigs.signalCube = Robot.controller0.getSquareButton();
        PlayerConfigs.toggleLeds = Robot.controller0.getCircleButton();
    }

    public void getCoDriverConfig() {
        PlayerConfigs.collectPos = Robot.controller1.getLeftBumper();
        PlayerConfigs.groundGrab = Robot.controller1.getLeftTriggerAxis() >= 0.15;
        PlayerConfigs.highGoal = Robot.controller1.getRightBumper();
        PlayerConfigs.lowGoal = Robot.controller1.getRightTriggerAxis() >= 0.15;

        PlayerConfigs.intake = Robot.controller1.getAButton();
        PlayerConfigs.release = Robot.controller1.getBButton();
        PlayerConfigs.cranePos = Robot.controller1.getLeftY();
        PlayerConfigs.extendPos = Robot.controller1.getRightY();
        PlayerConfigs.craneControl = Robot.controller1.getYButton();

        PlayerConfigs.switchPipeline = Robot.controller1.getLeftStickButton();
    }
}
