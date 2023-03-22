package frc.robot.commands.Autonomous.Autonomous_Actions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoLineDrive extends CommandBase{
    
    double setpoint;
    double xspeed;
    double rampSpeed = 0.06;
    double prevSpeed = 0;
    
    /** Creates a new Auto Pitch Correction Command. */
    public AutoLineDrive(double setPoint) {
        this.setpoint = setPoint;
        this.xspeed = 0.3 * Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint)/(Robot.drivetrain.getWheelPositions().frontLeftMeters - setPoint);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Robot.drivetrain.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
        System.out.println("Drive initialized");
        Robot.drivetrain.resetEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {        
        double driveSpeed = this.rampSpeed * xspeed + (1 - rampSpeed) * prevSpeed;
        System.out.println(Robot.drivetrain.getWheelPositions().frontLeftMeters);
        Robot.drivetrain.drive(driveSpeed, 0.0, 0, false);
        Robot.drivetrain.m_drive.feed();
        prevSpeed = driveSpeed;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint) < 0.1;
    }
}