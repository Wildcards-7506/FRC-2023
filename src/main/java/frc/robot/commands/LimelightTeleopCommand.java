package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.util.Logger;

public class LimelightTeleopCommand extends CommandBase{

    private boolean prev_StartButton = false;

    public LimelightTeleopCommand(){
        addRequirements(Robot.limelight);
    }

    @Override
    public void execute(){
        Robot.limelight.updateData();
        if(PlayerConfigs.switchPipeline != prev_StartButton){
            prev_StartButton = PlayerConfigs.switchPipeline;
            if(PlayerConfigs.switchPipeline){
                Robot.limelight.switchCameraMode();
            }
        } else if(PlayerConfigs.signalCone){
            Robot.limelight.conePipeline();
            Logger.info("LIGHT", "Switch To Yellow");
        } else if(PlayerConfigs.signalCube){
            Robot.limelight.cubePipeline();
            Logger.info("LIGHT", "Switch To Purple");
        }
    }
}