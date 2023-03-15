package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class PinchersTOCom extends CommandBase {
    public PinchersTOCom() {
        addRequirements(Robot.pinchers);
    }

    @Override
    public void execute() {
        if (PlayerConfigs.leftPinch) {
            Robot.pinchers.grabLeft(4);
        } else {
            Robot.pinchers.setLeftZero();
        }

        if (PlayerConfigs.rightPinch) {
            Robot.pinchers.grabRight(4);
        } else {
            Robot.pinchers.setRightZero();
        }
    }
}