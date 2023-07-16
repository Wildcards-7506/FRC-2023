package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCraneWristPosition extends CommandBase{
    
    double wristSetPoint;

    /** Creates a new Extention Positioning Command. */
    public AutoCraneWristPosition(double setPoint) {
        this.wristSetPoint = setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Logger.info("WRIST","Wrist Movement Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setWrist(wristSetPoint);
        Logger.info("WRIST", 
            Double.toString(Robot.crane.getWristEncoder())
            + " " + Double.toString(Robot.crane.getWristEncoder() - wristSetPoint)
            + " (Position, Error)"
        );
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("WRIST","Wrist Movement Complete");
        Robot.crane.setWrist(wristSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getWristEncoder() - wristSetPoint) <= 20;
    }
}