package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCraneRotatorPosition extends CommandBase{
    
    double rotatorSetPoint;
    Timer timer = null;

    /** Creates a new Rotation Positioning Command. */
    public AutoCraneRotatorPosition(double setPoint) {
        this.rotatorSetPoint = setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Logger.info("ROTTR","Rotation Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setRotator(rotatorSetPoint);
        Logger.info("ROTTR", 
            Double.toString(Robot.crane.getRotatorLEncoder())
            + " " + Double.toString(Robot.crane.getRotatorLEncoder() - rotatorSetPoint)
            + " (Position, Error)"
        );
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("ROTTR","Rotation Complete");
        Robot.crane.setRotator(rotatorSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getRotatorLEncoder() - rotatorSetPoint) <= 15;
    }
}