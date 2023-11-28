package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.subsystems.HDD.HDD;
import frc.robot.util.Logger;
import frc.robot.Robot;

public class LEDTeleopCommand extends CommandBase{
    
    public LEDTeleopCommand(){
        addRequirements(Robot.ledSystem);
    }
    
    @Override
    public void execute (){
        //Strip
        if (Robot.limelight.getTV() == 1 & 
            (PlayerConfigs.score || PlayerConfigs.align)){
            if (Math.abs(Robot.limelight.getTX()) < 2){
                Robot.ledSystem.solid(60,255,255);
            } else {
                Robot.ledSystem.solid(15,255,50);
            }
        } else if ( PlayerConfigs.doubleSub || PlayerConfigs.singleSub || PlayerConfigs.groundGrab) {
            if(Robot.crane.getStingerCurrent() > 20){
                Robot.ledSystem.solid(90,255,255);
                Logger.info("CRANE", "TARGET ACQUIRED");
            } else {
                Robot.ledSystem.rainbow();
                Logger.info("CRANE", "COLLECTING...");
            }
        } else {

            Robot.ledSystem.bars(
                130*((int)Math.max(Robot.crane.getSelectedCraneScoringPosition(HDD.grid.selectedCell.getID()),155)-155),
                (255-200*(int)Robot.limelight.getTX())*(int)Robot.limelight.getTV(),
                30 + 120 * HDD.grid.selectedCell.getTarget()
                );
        }

        //Eyes
        Robot.ledSystem.solidEyes(30 + 120 * HDD.grid.selectedCell.getTarget(), Robot.teamColor); 
    } 
}
