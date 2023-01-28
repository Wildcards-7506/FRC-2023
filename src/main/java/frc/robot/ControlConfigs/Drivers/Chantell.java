package frc.robot.ControlConfigs.Drivers;

import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class Chantell extends PlayerConfigs{
    
    public void getDriverConfig() {
        turnSpeed = 0.3;
        driveSpeed = 0.5;

        xMovement = Robot.controller0.getLeftX();
        yMovement = Robot.controller0.getLeftY();
        turnMovement = Robot.controller0.getRightX();

        modeSwitch = Robot.controller0.getR2ButtonReleased();
        // snapZero = Robot.controller0.getL2ButtonReleased();
        // snap180 = Robot.controller0.getL1Button();
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

        collectPos = Robot.controller1.getAButton();
        groundGrab = Robot.controller1.getBButton();
        highGoal = Robot.controller1.getPOV() == 0;
        lowGoal = Robot.controller1.getPOV() == 180;
        openClaw = Robot.controller1.getLeftBumper();

        switchPipeline = Robot.controller1.getRightStickButton();
    }
}
