package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoGroundTarget extends CommandBase{
    
    double setpoint;
    double intake;
    double xspeed;
    double ySpeed;
    double rampSpeed = 0.06;
    double prevSpeed = 0;
    
    /** Creates a new Auto Pitch Correction Command. */
    public AutoGroundTarget(double setPoint, double intakeSpeed) {
        this.setpoint = setPoint;
        this.intake = intakeSpeed;
        this.xspeed = 0.3 * Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint)/(Robot.drivetrain.getWheelPositions().frontLeftMeters - setPoint);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.drivetrain.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
        Logger.info("DTRGT", "Starting...");
        Robot.drivetrain.resetEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {      
        Robot.crane.setStinger(intake);
        ySpeed = Robot.limelight.getTX();  
        double driveSpeed = this.rampSpeed * xspeed + (1 - rampSpeed) * prevSpeed;
        Logger.info("DTRGT", Double.toString(Robot.limelight.getTX()) + " Degrees");
        Robot.drivetrain.drive(driveSpeed, ySpeed/40, 0, true);
        Robot.drivetrain.m_drive.feed();
        prevSpeed = driveSpeed;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("DTRGT", "Ending...");
        Robot.drivetrain.drive(0,0,0,true);
        Robot.crane.setStinger(intake/Math.abs(intake));
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint) < 0.1) ||
                Robot.crane.getStingerCurrent() > 30;
    }
}
