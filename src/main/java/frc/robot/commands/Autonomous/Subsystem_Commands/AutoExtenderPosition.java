package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoExtenderPosition extends CommandBase{
    
    double extenderSetPoint;

    /** Creates a new Extention Positioning Command. */
    public AutoExtenderPosition(double m_setPoint) {
        this.extenderSetPoint = m_setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("Extender Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double setter = 1 * Math.abs(Robot.crane.getExtenderEncoder() - extenderSetPoint)/(Robot.crane.getExtenderEncoder() - extenderSetPoint);
        Robot.crane.setExtender(-setter);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Extend Complete");
        Robot.crane.setExtender(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getExtenderEncoder() - extenderSetPoint) <= 0.1;
    }
}