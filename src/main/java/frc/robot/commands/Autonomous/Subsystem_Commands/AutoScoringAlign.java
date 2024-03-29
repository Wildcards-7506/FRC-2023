package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoScoringAlign extends CommandBase{
    
    int pipeline;
    double xOffset;
    double yOffset;

    /** Creates a new Scoring Alignment Command. */
    public AutoScoringAlign(int pipelineInput) {
        this.pipeline = pipelineInput;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.limelight.updateData();
        if(pipeline == 0){
            Robot.limelight.conePipeline();
        } else {Robot.limelight.cubePipeline();}
        Logger.info("ALIGN", "Alignment Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.limelight.updateData();
        Logger.info("ALIGN", Double.toString(Robot.limelight.getTX()) + " Degrees");
        xOffset = 0.2 * Robot.limelight.getTX()/Math.abs(Robot.limelight.getTX());
        Robot.drivetrain.drive(0, xOffset, 0, true);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("ALIGN", "Alignment Complete");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return  Robot.limelight.getTV() == 0.0 ||
                (Math.abs(Robot.limelight.getTX()) < 1) ||
                pipeline == 1.0;
    }
}