package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoClawPosition extends CommandBase{
    
    double action;
    boolean actionFlag;
    Timer timer = null;

    /** Creates a new Claw Positioning Command. */
    public AutoClawPosition(double setPoint) {
        this.action = setPoint;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
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
        Robot.crane.setRoller(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.get() >= 0.5;
    }
}