package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.util.Logger;
import frc.robot.subsystems.HDD.HDD;

public class LimelightTeleopCommand extends CommandBase{

    private boolean prev_StartButton = false;
    private int prev_TargetToggle = 0;

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
        } else if(HDD.grid.selectedCell.getTarget() != prev_TargetToggle){ 
            prev_TargetToggle = HDD.grid.selectedCell.getTarget();
            if(HDD.grid.selectedCell.getTarget() == 0){
                Robot.limelight.conePipeline();
                Logger.info("LIGHT", "Switch To Yellow");
            } else {
                Robot.limelight.cubePipeline();
                Logger.info("LIGHT", "Switch To Purple");
            }
        }
    }
}