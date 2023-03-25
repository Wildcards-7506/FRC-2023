package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class LLRotatorTOCom extends CommandBase {
    
    public LLRotatorTOCom () {
        addRequirements(Robot.ll_rotator);
    }

    @Override
    public void execute () {
        if (PlayerConfigs.yMovement > 0) {
            Robot.ll_rotator.lookForward();
        } else if (PlayerConfigs.yMovement < 0) {
            Robot.ll_rotator.lookBackward();
        }
    }
}
