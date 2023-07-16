package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoLook extends CommandBase{
    double dir;
    int pipeline;

    public AutoLook (double dir, int pipeline) {
        this.dir = dir;
        this.pipeline = pipeline;
    }

    @Override
    public void initialize () {
        Logger.info("LLROT","Limelight Moving");
        Robot.limelight.table.getEntry("pipeline").setNumber(this.pipeline);
    }

    @Override
    public void execute () {
        Robot.ll_rotator.setEyes(this.dir);
        Robot.limelight.table.getEntry("pipeline").setNumber(this.pipeline);
        Logger.info("LLROT",
        Double.toString(Robot.ll_rotator.getPosition())
        + " " + Double.toString(Robot.ll_rotator.getPosition() - this.dir)
        + " (Position, Error)");
    }
    
    @Override
    public void end(boolean interrupted){
        Logger.info("LLROT","Limelight Stopped");
    }

    @Override
    public boolean isFinished () {
        return Math.abs(dir - Robot.ll_rotator.getPosition()) < 3;
    }
}
