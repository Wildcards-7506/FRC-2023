package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class CraneTOCom extends CommandBase {
    
    public CraneTOCom() {
        addRequirements(Robot.crane);
    }

    @Override
    public void execute() {
        if (PlayerConfigs.openClaw) {
            if (Robot.crane.rollerInUse) {
                Robot.crane.setRoller(8);
            }
            Robot.crane.setClaw(Constants.kClawOpen);
            
        } else if (PlayerConfigs.rollerForward) {
            Robot.crane.setRoller(-8);
        } else {
            if (Robot.crane.rollerInUse) {
                Robot.crane.setRoller(0);
            } else {
                Robot.crane.setClaw(Constants.kClawClosed);
            }
        }

        if (PlayerConfigs.groundGrab) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            Robot.crane.setExtender(Constants.kExtenderGround);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Ground");
            
        } else if (PlayerConfigs.collectPos){
            Robot.crane.setRotator(Constants.kRotatorCollect);
            Robot.crane.setExtender(Constants.kExtenderCollect);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Collect");


        } else if (PlayerConfigs.lowGoal) {
            Robot.crane.setRotator(Constants.kRotatorMid);
            Robot.crane.setExtender(Constants.kExtenderMid);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Low");

            
        } else if (PlayerConfigs.highGoal) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            Robot.crane.setExtender(Constants.kExtenderHi);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Hi");


        } else if (Robot.crane.getRotatorLEncoder() < Constants.kRotatorCollect && Robot.crane.getExtenderEncoder() > Constants.kExtenderCollect) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            Robot.crane.setExtender(Constants.kExtenderClosed);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());

        } else if (Robot.crane.getRotatorLEncoder() > Constants.kRotatorCollect && Robot.crane.getExtenderEncoder() > Constants.kExtenderCollect) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            Robot.crane.setExtender(Constants.kExtenderHeightLimit);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());

        } else {
            Robot.crane.setRotator(Constants.kRotatorClosed);
            Robot.crane.setExtender(Constants.kExtenderClosed);
            Robot.crane.setWrist(Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Close");

        }
    }
}
