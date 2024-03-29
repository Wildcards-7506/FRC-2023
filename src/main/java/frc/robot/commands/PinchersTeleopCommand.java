package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.util.Logger;

public class PinchersTeleopCommand extends CommandBase {

    private boolean pinchRightUp = true;
    private boolean pinchLeftUp = true;
    private boolean prev_pinchRightUp = false;
    private boolean prev_pinchLeftUp = false;

    public PinchersTeleopCommand() {
        addRequirements(Robot.pinchers);
    }

    @Override
    public void execute() {
        if(PlayerConfigs.leftPinch != prev_pinchLeftUp){
            prev_pinchLeftUp = PlayerConfigs.leftPinch;
            if(PlayerConfigs.leftPinch){
                pinchLeftUp = !pinchLeftUp;
                Logger.info("PINCH", "Move Left Pincher");
            }
        }
        if(PlayerConfigs.rightPinch != prev_pinchRightUp){
            prev_pinchRightUp = PlayerConfigs.rightPinch;
            if(PlayerConfigs.rightPinch){
                pinchRightUp = !pinchRightUp;
                Logger.info("PINCH", "Move Right Pincher");
            }
        }

        if (!pinchLeftUp) {
            Robot.pinchers.grabLeft(4);
        } else {
            Robot.pinchers.setLeftZero();
        }

        if (!pinchRightUp) {
            Robot.pinchers.grabRight(4);
        } else {
            Robot.pinchers.setRightZero();
        }

        Robot.pinchers.updateSmartDashboard();
    }
}