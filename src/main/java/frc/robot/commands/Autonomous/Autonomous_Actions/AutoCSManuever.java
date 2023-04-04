package frc.robot.commands.Autonomous.Autonomous_Actions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCSManuever extends CommandBase{
    
    double moveSpeed;
    boolean ascending = false;
    boolean descending = false;
    double angle;
    double rotation;
    Timer timer = null;

    /** Creates a new Auto Pitch Correction Command. */
    public AutoCSManuever(double speed) {
        this.moveSpeed = speed;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        System.out.println("Traverse Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        angle = Robot.drivetrain.getPitch();
        if(angle > 3){ascending = true;}
        if(angle < -3){descending = true;}
        if(ascending & descending){
            timer.start();
        }
        Robot.drivetrain.drive(this.moveSpeed, 0.0, 0, false);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Traverse Complete");
        Robot.drivetrain.drive(0,0,0,true);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (timer.get() > 1);
    }
}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                