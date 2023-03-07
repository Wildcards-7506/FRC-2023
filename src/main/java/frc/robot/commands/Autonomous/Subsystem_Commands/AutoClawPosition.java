package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoClawPosition extends CommandBase{
    
    double action;
    boolean actionFlag;
    Timer timer = null;

    /** Creates a new Claw Positioning Command. */
    public AutoClawPosition(double setPoint, boolean intake) {
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
        if(Robot.crane.rollerInUse){
            Robot.crane.setRoller(action);
        } else{
            Robot.crane.setClaw(action);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        System.out.println("Claw Complete");
        if (actionFlag){
            Robot.crane.setRoller(-1);
        } else if (!actionFlag){
            Robot.crane.setRoller(0);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //Roller Pickup, Current tripped
        if (actionFlag && Robot.crane.getRollerCurrent() > 8 && timer.get() >= 0.3) {System.out.println("Piece Acquired");}
                //Target Missed, timeout
        else if (actionFlag && timer.get() >= 5){System.out.println("Piece Missed");}
                //Outtaking, timeout
        else if (!actionFlag && timer.get() >= 1){System.out.println("Outtake");};
                 
                 
        //Roller Pickup, Current tripped
        return (actionFlag && Robot.crane.getRollerCurrent() > 30 && timer.get() >= 1)
                //Target Missed, timeout
                 || (actionFlag && timer.get() >= 5)
                //Outtaking, timeout
                 || (!actionFlag && timer.get() >= 1);
    }
}