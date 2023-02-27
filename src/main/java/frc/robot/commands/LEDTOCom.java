package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class LEDTOCom extends CommandBase{
    private boolean lockLed = false;
    
    public LEDTOCom(){
        addRequirements(Robot.ledStrip);
    }
    
    @Override
    public void execute (){
        if (PlayerConfigs.signalCube){
            lockLed = true;
            Robot.ledStrip.solid(150);
        } else if (PlayerConfigs.signalCone){
            lockLed = true;
            Robot.ledStrip.solid(30);
        } else if (PlayerConfigs.switchPipeline){
            lockLed = false;
        } else if (Robot.limelight.getTV() == 1){
            if (Math.abs(Robot.limelight.getTX()) < 2){
                Robot.ledStrip.solid(60);
            } else {
                Robot.ledStrip.solid(90);
            }
        } else if (!lockLed){
            Robot.ledStrip.teamColor(Robot.teamColor); 
        }
    }
}
