package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Constants;
import frc.robot.Robot;
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
        System.out.println("Limelight pointed");
        Robot.limelight.table.getEntry("pipeline").setNumber(this.pipeline);
    }

    @Override
    public void execute () {
        Robot.ll_rotator.setEyes(this.dir);
        Robot.limelight.table.getEntry("pipeline").setNumber(this.pipeline);
    }

    @Override
    public boolean isFinished () {
        return Math.abs(dir - Robot.ll_rotator.getPosition()) < 3;
    }
}
