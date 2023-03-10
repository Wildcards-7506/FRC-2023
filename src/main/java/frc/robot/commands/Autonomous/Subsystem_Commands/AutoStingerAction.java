package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoStingerAction extends CommandBase{
    
    double action;
    boolean actionFlag;
    Timer timer = null;

    /** Creates a new Claw Positioning Command. */
    public AutoStingerAction(double setPoint, boolean intake) {
        this.action = setPoint;
        this.actionFlag = intake;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
        System.out.println("Claw Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
            Robot.crane.setStinger(action);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Claw Complete");
        if (actionFlag){
            Robot.crane.setStinger(-1);
        } else if (!actionFlag){
            Robot.crane.setStinger(0);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {                 
        //Roller Pickup, Current tripped
        return (actionFlag && Robot.crane.getStingerCurrent() > 10 && timer.get() >= 0.5)
                //Target Missed, timeout
                 || (actionFlag && timer.get() >= 5)
                //Outtaking, timeout
                 || (!actionFlag && timer.get() >= 0.5);
    }
}