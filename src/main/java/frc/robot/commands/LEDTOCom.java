package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class LEDTOCom extends CommandBase{
    
    public LEDTOCom(){
        //addRequirements(Robot.ledStrip);
        addRequirements(Robot.ledEyes);
    }
    
    @Override
    public void execute (){
        if (Robot.limelight.getTV() == 1 & 
           (PlayerConfigs.highGoal || PlayerConfigs.lowGoal || PlayerConfigs.align)){
            if (Math.abs(Robot.limelight.getTX()) < 2){
                Robot.ledStrip.solid(60,255,255);
            } else {
                Robot.ledStrip.solid(90,255,255);
            }
        } else {
            Robot.ledStrip.solid(30 + 80 * (int)Robot.limelight.getPipeline(), 255, 255); 
        }
        Robot.ledEyes.solidEyes(30 + 80 * (int)Robot.limelight.getPipeline(), Robot.teamColor); 
    } 
}
