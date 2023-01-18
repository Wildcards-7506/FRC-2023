package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.PlayerConfigs;
import frc.robot.Robot;

public class LEDTOCom extends CommandBase{
    private boolean lockLed=false;
    
    public LEDTOCom(){
        addRequirements(Robot.ledStrip);
    }
    
    @Override
    public void execute (){
        if (PlayerConfigs.signalCube){
            lockLed= true;
            Robot.ledStrip.solid(150);
        } else if (PlayerConfigs.signalCone){
            lockLed=true;
            Robot.ledStrip.solid(30);
        } else if (PlayerConfigs.toggleLeds){
            lockLed= false;
        }
        else if (lockLed = false){
            Robot.ledStrip.teamColor(Robot.teamColor); 

        }
    }
}
