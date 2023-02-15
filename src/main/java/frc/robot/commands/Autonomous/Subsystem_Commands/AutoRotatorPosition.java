package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoRotatorPosition extends CommandBase{
    
    double rotatorSetPoint;
    double wristSetPoint;
    Timer timer = null;

    /** Creates a new Rotation Positioning Command. */
    public AutoRotatorPosition(double setPoint, double wSetPoint) {
        this.rotatorSetPoint = setPoint;
        this.wristSetPoint = wSetPoint;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.crane.setRotator(rotatorSetPoint);
        Robot.crane.setWrist(wristSetPoint);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Robot.crane.setRotator(rotatorSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.crane.getRotatorLEncoder() - rotatorSetPoint) <= 2;
    }
}