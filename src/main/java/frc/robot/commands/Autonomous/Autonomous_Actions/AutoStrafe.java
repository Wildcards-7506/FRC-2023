package frc.robot.commands.Autonomous.Autonomous_Actions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoStrafe extends CommandBase{
    
    double setpoint;
    double runTime;
    Timer time = new Timer();
    
    /** Creates a new Auto Pitch Correction Command. */
    public AutoStrafe(double setPoint, double runTime) {
        this.setpoint = setPoint;
        this.runTime = runTime;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        time.reset();
        time.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {        
        Robot.drivetrain.drive(0, this.setpoint, 0, true);
        Robot.drivetrain.m_drive.feed();
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
        return time.get() > this.runTime;
    }
}