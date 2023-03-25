package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoLook extends CommandBase{
    boolean dir;

    public AutoLook (boolean dir) {
        this.dir = dir;
    }

    @Override
    public void initialize () {
        System.out.println("Limelight pointed");
    }

    @Override
    public void execute () {
        if (this.dir)
            Robot.ll_rotator.lookForward();
        else
            Robot.ll_rotator.lookBackward();
    }

    @Override
    public boolean isFinished () {
        if (this.dir) {
            return (Robot.ll_rotator.getPosition() < 2);
        } else {
            return (Robot.ll_rotator.getPosition() > Constants.kLookForward);
        }
    }
}
