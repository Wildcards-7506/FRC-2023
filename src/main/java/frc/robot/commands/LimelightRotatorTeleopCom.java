package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LLRConstants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class LimelightRotatorTeleopCom extends CommandBase {
    
    public LimelightRotatorTeleopCom () {
        addRequirements(Robot.ll_rotator);
    }

    @Override
    public void execute () {
        if (PlayerConfigs.groundGrab) {
            Robot.ll_rotator.setEyes(LLRConstants.kLookForward);
        } else if (PlayerConfigs.doubleSub) {
            Robot.ll_rotator.setEyes(LLRConstants.kLookCollect);
        } else {
            Robot.ll_rotator.setEyes(LLRConstants.kLookGoal);
        }

        Robot.ll_rotator.errorCheck();
    }
}
