package frc.robot.ControlConfigs.Drivers;

import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class Shannon extends PlayerConfigs {
    
    public void getDriverConfig() {
        turnSpeed = 0.3;
        driveSpeed = 0.5;

        xMovement = Robot.controller0.getLeftX();
        yMovement = Robot.controller0.getLeftY();
        turnMovement = Robot.controller0.getRightX();

        modeSwitch = Robot.controller0.getL2Button();
        // snapZero = Robot.controller0.getL3Button();
        // snap180 = Robot.controller0.getR3Button();
        PlayerConfigs.snap0 = Robot.controller0.getPOV() == 0;
        PlayerConfigs.snap90 = Robot.controller0.getPOV() == 90;
        PlayerConfigs.snap180 = Robot.controller0.getPOV() == 180;
        PlayerConfigs.snap270 = Robot.controller0.getPOV() == 270;

        signalCone = Robot.controller0.getTriangleButton();
        signalCube = Robot.controller0.getSquareButton();
        toggleLeds = Robot.controller0.getCircleButton();
    }

    public void getCoDriverConfig() {
        fineTurnSpeed = 0.15;
        fineDriveSpeed = 0.25;

        fineControlX = Robot.controller1.getLeftX();
        fineControlY = Robot.controller1.getLeftY();
        fineTurnMovement = Robot.controller1.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller1.getRightTriggerAxis() > .2;

        collectPos = Robot.controller1.getLeftBumper();
        groundGrab = Robot.controller1.getLeftTriggerAxis() >= 0.15;
        highGoal = Robot.controller1.getRightBumper();
        lowGoal = Robot.controller1.getRightTriggerAxis() >= 0.15;
        openClaw = Robot.controller1.getAButton();

        switchPipeline = Robot.controller1.getLeftStickButton();
    }
}
