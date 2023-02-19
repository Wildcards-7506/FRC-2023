package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoWristPosition extends CommandBase{
    
    double wristSetPoint;

    /** Creates a new Extention Positioning Command. */
    public AutoWristPosition(double setPoint) {
        this.wristSetPoint = setPoint;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setWrist(wristSetPoint);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.crane.setWrist(wristSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getWristEncoder() - wristSetPoint) <= 2;
    }
}