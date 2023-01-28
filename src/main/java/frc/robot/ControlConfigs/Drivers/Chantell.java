package frc.robot.ControlConfigs.Drivers;

import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class Chantell extends PlayerConfigs{
    
    public void getDriverConfig() {

        PlayerConfigs.turnSpeed = 0.3;
        PlayerConfigs.driveSpeed = 0.5;

        PlayerConfigs.xMovement = Robot.controller0.getLeftX();
        PlayerConfigs.yMovement = Robot.controller0.getLeftY();
        PlayerConfigs.turnMovement = Robot.controller0.getRightX();

        PlayerConfigs.modeSwitch = Robot.controller0.getR2ButtonReleased();
        PlayerConfigs.snapZero = Robot.controller0.getL2ButtonReleased();
        PlayerConfigs.snap90 = Robot.controller0.getR2Button();
        PlayerConfigs.snap180 = Robot.controller0.getL1Button();
        PlayerConfigs.snap270 = Robot.controller0.getL2Button();

        PlayerConfigs.signalCone = Robot.controller0.getTriangleButton();
        PlayerConfigs.signalCube = Robot.controller0.getSquareButton();
        PlayerConfigs.toggleLeds = Robot.controller0.getCircleButton();
    }

    public void getCoDriverConfig() {
        PlayerConfigs.fineTurnSpeed = 0.15;
        PlayerConfigs.fineDriveSpeed = 0.25;

        PlayerConfigs.fineControlX = Robot.controller1.getLeftX();
        PlayerConfigs.fineControlY = Robot.controller1.getLeftY();
        PlayerConfigs.fineTurnMovement = Robot.controller1.getRightX();
        PlayerConfigs.fineControlToggle = Robot.controller1.getRightTriggerAxis() > .2;

        PlayerConfigs.collectPos = Robot.controller1.getAButton();
        PlayerConfigs.groundGrab = Robot.controller1.getBButton();
        PlayerConfigs.highGoal = Robot.controller1.getPOV() == 0;
        PlayerConfigs.lowGoal = Robot.controller1.getPOV() == 180;
        PlayerConfigs.openClaw = Robot.controller1.getLeftBumper();

        PlayerConfigs.switchPipeline = Robot.controller1.getRightStickButton();
    }
}
