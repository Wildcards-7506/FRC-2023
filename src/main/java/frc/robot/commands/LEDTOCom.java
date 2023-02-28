package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.Robot;

public class LEDTOCom extends CommandBase{
    private boolean lockLed = false;
    Timer timer;
    
    public LEDTOCom(){
        addRequirements(Robot.ledStrip);
    }

    @Override
    public void initialize(){
        timer = new Timer();
        timer.start();
    }
    
    @Override
    public void execute (){
        if(timer.get() % 0.5 > 0.25) {
            if (PlayerConfigs.signalCube){
                lockLed = true;
                Robot.ledStrip.solid(150,255,255);
            } else if (PlayerConfigs.signalCone){
                lockLed = true;
                Robot.ledStrip.solid(30,255,255);
            } else if (PlayerConfigs.switchPipeline){
                lockLed = false;
            } else if (Robot.limelight.getTV() == 1){
                if (Math.abs(Robot.limelight.getTX()) < 2){
                    Robot.ledStrip.solid(60,255,255);
                } else {
                    Robot.ledStrip.solid(90,255,255);
                }
            } else if (!lockLed){
                Robot.ledStrip.teamColor(Robot.teamColor); 
            }
        } else {
            Robot.ledStrip.solid(0,0,0);
        }
    }
}
