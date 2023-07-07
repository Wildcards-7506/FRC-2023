package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import frc.robot.util.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCraneStingerAction extends CommandBase{
    
    double action;
    boolean actionFlag;
    Timer timer = null;

    /** Creates a new Claw Positioning Command. */
    public AutoCraneStingerAction(double setPoint, boolean intake) {
        this.action = setPoint;
        this.actionFlag = intake;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
        Logger.info("STING", "Claw Started");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
            Robot.crane.setStinger(action);
            Logger.info("STING", 
            Double.toString(Robot.crane.getStingerCurrent()) + " Amps");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
        if (actionFlag){
            if(timer.get() >= 5){
                Logger.info("STING", "Intake Complete - Target Missed");
            } else{
                Logger.info("STING", "Intake Complete - Target Acquired");
            }
            Robot.crane.setStinger(action/Math.abs(action));
        } else if (!actionFlag){
            Logger.info("STINGER", "Outtake Complete");
            Robot.crane.setStinger(0);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {                 
        //Roller Pickup, Current tripped
        return (actionFlag && Robot.crane.getStingerCurrent() > 20 && timer.get() >= 0.5)
                //Target Missed, timeout
                 || (actionFlag && timer.get() >= 5)
                //Outtaking, timeout
                 || (!actionFlag && timer.get() >= 0.5);
    }
}