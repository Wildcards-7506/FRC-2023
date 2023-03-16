package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoSnap extends CommandBase{
    
    int angle;
    double rotation;

    /** Creates a new Drivetrain Snap-to-angle Command. */
    public AutoSnap(int angle) {
        this.angle = angle;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("Alignment Started");
    }


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double rotation = 0.3 * Math.abs(angle-Robot.drivetrain.getHeading())/(angle-Robot.drivetrain.getHeading());
        Robot.drivetrain.drive(0.0, 0.0, rotation, false);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Snap Complete");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return  (Math.abs(angle-Robot.drivetrain.getHeading()) < 3);
    }
}