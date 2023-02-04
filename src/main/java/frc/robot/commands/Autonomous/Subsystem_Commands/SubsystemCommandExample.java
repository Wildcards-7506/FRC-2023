package frc.robot.commands.Autonomous.Subsystem_Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class SubsystemCommandExample extends CommandBase{
    //Declare required variables here, initialize if necessary
    //This example drives the robot forward for one second and stops.
    int time;
    Timer timer = null;

    /** Creates a new Subsyste, Command. */
    public SubsystemCommandExample(int setPoint) {
        //Use this. format below to set class variables as soon as the class is created
        this.time = setPoint;
    }

    // Called when the command is initially scheduled. This may involve setting a timer, updating the Limelight data, or setting the correct pipeline
    @Override
    public void initialize() {
        //Do initialization activity here
        //This section creates a new timer object and begins its timekeeping
        timer = new Timer();
        timer.start();
    }

    // The robot will loop through this section of code repeatedly until the isFinished criteria below are met. This is usually the "movement" portion of the command
    @Override
    public void execute() {
        //Sets the robot to full speed forward
        Robot.drivetrain.drive(1,0,0,false);
    }

    // Tells the robot what to do when the command is finished. This will usually entail stopping motors.
    @Override
    public void end(boolean interrupted) {
        //Stops the robot when the isFinished condition is true
        Robot.drivetrain.drive(0,0,0,false);
    }

    // Returns true when the command should end. Make sure to give multiple ways out of a command to avoid infinite loops if a system fails.
    @Override
    public boolean isFinished() {
        //If the time specified has passed, the command is ended
        return timer.get() >= 1;
    }
}