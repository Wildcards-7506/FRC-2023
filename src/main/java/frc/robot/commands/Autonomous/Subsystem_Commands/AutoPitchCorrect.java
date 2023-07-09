package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoPitchCorrect extends CommandBase{
    
    boolean tilted = false;
    double angle;
    double driveSpeed;
    Timer time;

    /** Creates a new Auto Pitch Correction Command. */
    public AutoPitchCorrect() {}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Logger.info("PITCH", "Balance Started");
        driveSpeed = -0.6;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        angle = Robot.drivetrain.getPitch();
        Logger.info("PITCH", Double.toString(angle) + " Degrees");
        if(angle < -3 || angle > 3){
            tilted = true;
            driveSpeed = 0.2 * angle/15;
            time.reset();
        } else if(tilted) {
            time.start();
        }
        Robot.drivetrain.drive(driveSpeed, 0.0, 0, false);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("PITCH", "Balance Complete");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return time.get() > 1;
    }
}