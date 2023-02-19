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
        //End Effector
        if (PlayerConfigs.intake) {
            if (Robot.crane.rollerInUse) {
                Robot.crane.setRoller(12);
            } else {
                Robot.crane.setClaw(Constants.kClawOpen);
            }
        } else if (PlayerConfigs.release && Robot.crane.rollerInUse) {
            Robot.crane.setRoller(-12);
        } else {
            if (Robot.crane.rollerInUse) {
                Robot.crane.setRoller(0);
            } else {
                Robot.crane.setClaw(Constants.kClawClosed);
            }
        }

        //Craneworks
        if (PlayerConfigs.groundGrab) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            Robot.crane.setExtender(Constants.kExtenderGround, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Constants.kRotatorGround + 45 - Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Ground");
        } else if (PlayerConfigs.collectPos){
            Robot.crane.setRotator(Constants.kRotatorCollect);
            Robot.crane.setExtender(Constants.kExtenderCollect, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Constants.kRotatorCollect + 45 - Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Collect");
        } else if (PlayerConfigs.lowGoal) {
            Robot.crane.setRotator(Constants.kRotatorMid);
            Robot.crane.setExtender(Constants.kExtenderMid, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Constants.kRotatorMid + 45 - Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Low");
        } else if (PlayerConfigs.highGoal) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            Robot.crane.setExtender(Constants.kExtenderHi, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Constants.kRotatorHi + 45 - Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Hi");
        } else if (PlayerConfigs.craneControl){
            System.out.println("Fine Control");
            Robot.crane.setRotator(Robot.crane.getRotatorLEncoder() + (20 * PlayerConfigs.cranePos));
            Robot.crane.setExtender(PlayerConfigs.extendPos, true);
        } else if (Robot.crane.getRotatorLEncoder() < Constants.kRotatorCollect && Robot.crane.getExtenderEncoder() > Constants.kExtenderCollect) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            Robot.crane.setExtender(Constants.kExtenderClosed, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Safety Closing");
        } else if (Robot.crane.getRotatorLEncoder() > Constants.kRotatorCollect && Robot.crane.getExtenderEncoder() > Constants.kExtenderHeightLimit) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            Robot.crane.setExtender(Constants.kExtenderClosed, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(-Robot.crane.getRotatorLEncoder());
            SmartDashboard.putString("Arm Position", "Safety Height");
        } else {
            Robot.crane.setRotator(Constants.kRotatorClosed);
            Robot.crane.setExtender(Constants.kExtenderClosed, false);
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kRotatorClosed);
            SmartDashboard.putString("Arm Position", "Close");
        }
    }
}
