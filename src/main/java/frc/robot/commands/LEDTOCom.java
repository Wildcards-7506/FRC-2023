package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class LEDTOCom extends CommandBase{
    
    public LEDTOCom(){
        addRequirements(Robot.ledSystem);
    }
    
    @Override
    public void execute (){
        //Strip
        // if (Robot.limelight.getTV() == 1 & 
        //     (PlayerConfigs.highGoal || PlayerConfigs.lowGoal || PlayerConfigs.align)){
        //     if (Math.abs(Robot.limelight.getTX()) < 2){
        //         Robot.ledSystem.solid(60,255,255);
        //     } else {
        //         Robot.ledSystem.solid(90,255,255);
        //     }
        // } else if ( PlayerConfigs.collectPos | PlayerConfigs.groundGrab) {
        //     if(Robot.crane.getStingerCurrent() > 20){
        //         Robot.ledSystem.solid(60,255,255);
        //     } else {
        //         Robot.ledSystem.rainbow();
        //     }
        // } else {
        //     Robot.ledSystem.teamColor(Robot.teamColor);
        // }

        //Fort Worth Finals
        if (Robot.limelight.getPipeline() == 0.0) {
            Robot.ledSystem.solid(30,255,255);
        } else {
            Robot.ledSystem.solid(150,255,255);
        }

        //Eyes
        // Robot.ledSystem.solidEyes(30 + 120 * (int)Robot.limelight.getPipeline(), Robot.teamColor); 
    } 
}
