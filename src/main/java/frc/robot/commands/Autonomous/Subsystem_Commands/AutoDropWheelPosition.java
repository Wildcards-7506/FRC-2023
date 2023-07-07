package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoDropWheelPosition extends CommandBase{
    
    double dropWheelSetPoint;
    Timer timer = null;

    /** Creates a new Claw Positioning Command. */
    public AutoDropWheelPosition(double setPoint) {
        this.dropWheelSetPoint = setPoint;
    }

     // Called when the command is initially scheduled.
     @Override
     public void initialize() {
        Logger.info("DRWHL","Drop Wheel Movement Started");
     }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Robot.drivetrain.setDropWheels(dropWheelSetPoint);
        Logger.info("DRWHL", 
            Double.toString(Robot.drivetrain.getDWL())
            + " " + Double.toString(Robot.drivetrain.getDWL() - dropWheelSetPoint)
            + " " + Double.toString(Robot.drivetrain.getDWR())
            + " " + Double.toString(Robot.drivetrain.getDWR() - dropWheelSetPoint)
            + " (L Pos, L Err, R Pos, R Err)"
        );
    }

    // Returns true when the command should end.
    @Override
    public void end(boolean interrupted) {
        Logger.info("DRWHL","Drop Wheel Movement Finished");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return  Math.abs(Robot.drivetrain.getDWL() - dropWheelSetPoint) >= 0.2 &&
                Math.abs(Robot.drivetrain.getDWR() - dropWheelSetPoint) >= 0.2;
    }
}