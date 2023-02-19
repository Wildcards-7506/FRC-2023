package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoExtenderPosition extends CommandBase{
    
    double extenderSetPoint;

    /** Creates a new Extention Positioning Command. */
    public AutoExtenderPosition(double setPoint) {
        this.extenderSetPoint = setPoint;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setExtender(extenderSetPoint, false);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.crane.setExtender(extenderSetPoint, false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getExtenderEncoder() - extenderSetPoint) <= 0.1;
    }
}