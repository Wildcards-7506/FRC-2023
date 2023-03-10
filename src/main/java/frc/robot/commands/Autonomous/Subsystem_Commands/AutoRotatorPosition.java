package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoRotatorPosition extends CommandBase{
    
    double rotatorSetPoint;
    Timer timer = null;

    /** Creates a new Rotation Positioning Command. */
    public AutoRotatorPosition(double setPoint) {
        this.rotatorSetPoint = setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("Rotation Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setRotator(rotatorSetPoint);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Rotate Complete");
        Robot.crane.setRotator(rotatorSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getRotatorLEncoder() - rotatorSetPoint) <= 15;
    }
}