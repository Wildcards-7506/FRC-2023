package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class LimelightRotatorTeleopCom extends CommandBase {
    
    public LimelightRotatorTeleopCom () {
        addRequirements(Robot.ll_rotator);
    }

    @Override
    public void execute () {
        if (PlayerConfigs.groundGrab) {
            Robot.ll_rotator.setEyes(Constants.kLookForward);
        } else if (PlayerConfigs.collectPos) {
            Robot.ll_rotator.setEyes(Constants.kLookCollect);
        } else {
            Robot.ll_rotator.setEyes(Constants.kLookGoal);
        }

        Robot.ll_rotator.errorCheck();
    }
}
