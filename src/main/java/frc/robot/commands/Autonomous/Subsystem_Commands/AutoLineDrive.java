package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoLineDrive extends CommandBase{
    
    double setpoint;
    double xspeed;
    double rampSpeed = 0.06;
    double prevSpeed = 0;
    
    /** Creates a new Auto Pitch Correction Command. */
    public AutoLineDrive(double setPoint) {
        this.setpoint = setPoint;
        this.xspeed = Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint)/(Robot.drivetrain.getWheelPositions().frontLeftMeters - setPoint);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Robot.drivetrain.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
        Logger.info("DLINE","Robot Driving...");
        Robot.drivetrain.resetEncoders();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {      
        double driveSpeed = this.rampSpeed * xspeed + (1 - rampSpeed) * prevSpeed;
        if(driveSpeed < xspeed){
            Robot.drivetrain.drive(driveSpeed, 0, 0, true);
        }
        else {
            driveSpeed = xspeed * Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint)/setpoint;
            Robot.drivetrain.drive(driveSpeed, 0, 0, true);
        }
        Robot.drivetrain.m_drive.feed();
        prevSpeed = driveSpeed;
        Logger.info("DLINE", 
            Double.toString(Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint)) + " Meters"
        );
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Logger.info("DLINE", "Robot Stopping...");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(Robot.drivetrain.getWheelPositions().frontLeftMeters - setpoint) < 0.1;
    }
}