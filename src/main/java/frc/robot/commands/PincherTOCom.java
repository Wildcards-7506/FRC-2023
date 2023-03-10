package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class PincherTOCom extends CommandBase{

    public PincherTOCom() {
        addRequirements(Robot.Pinchers);
    }

    public void execute(){

        if(PlayerConfigs.leftPinch){

        } else if (PlayerConfigs.rightPinch){
            
        }

    }

}
