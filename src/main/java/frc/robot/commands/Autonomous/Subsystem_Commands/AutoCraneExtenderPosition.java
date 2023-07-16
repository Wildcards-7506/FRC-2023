package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import com.revrobotics.CANSparkMax.ControlType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.util.Logger;

public class AutoCraneExtenderPosition extends CommandBase{
    
    double extenderSetPoint;

    /** Creates a new Extention Positioning Command. */
    public AutoCraneExtenderPosition(double m_setPoint) {
        this.extenderSetPoint = m_setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Logger.info("EXTND","Extention Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.extenderPID.setReference(extenderSetPoint, ControlType.kPosition);
        Logger.info("EXTND", 
            Double.toString(Robot.crane.getExtenderEncoder())
            + " " + Double.toString(Robot.crane.getExtenderEncoder() - extenderSetPoint)
            + " (Position, Error)"
        );
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("EXTND","Extention Complete");
        Robot.crane.setExtender(this.extenderSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getExtenderEncoder() - extenderSetPoint) <= 1;
    }
}