package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
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
         System.out.println("Drop Wheel Started");
     }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        System.out.println("Drop Wheel Finished");
        Robot.drivetrain.setDropWheels(dropWheelSetPoint);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return  Math.abs(Robot.drivetrain.getDWL() - dropWheelSetPoint) >= 0.2 &&
                Math.abs(Robot.drivetrain.getDWR() - dropWheelSetPoint) >= 0.2;
    }
}