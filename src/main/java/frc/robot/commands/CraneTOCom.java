package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class CraneTOCom extends CommandBase {
    double extenderSetpoint;

    public CraneTOCom() {
        addRequirements(Robot.crane);
    }

    @Override
    public void execute() {
        //End Effector
        if (PlayerConfigs.intake) {
            if (Robot.crane.rollerInUse) {
                if(Robot.limelight.getPipeline() == 0){
                    Robot.crane.setRoller(8);
                } else if(Robot.limelight.getPipeline() == 1){
                    Robot.crane.setRoller(-8);
                }
            } else {
                Robot.crane.setClaw(Constants.kClawOpen);
            }
        } else if (PlayerConfigs.release && Robot.crane.rollerInUse) {
            if(Robot.limelight.getPipeline() == 0){
                Robot.crane.setRoller(-8);
            } else if(Robot.limelight.getPipeline() == 1){
                Robot.crane.setRoller(8);
            }
        } else {
            if (Robot.crane.rollerInUse) {
                if(Robot.limelight.getPipeline() == 0){
                    Robot.crane.setRoller(1);
                } else if(Robot.limelight.getPipeline() == 1){
                    Robot.crane.setRoller(0);
                }
            } else {
                Robot.crane.setClaw(Constants.kClawClosed);
            }
        }

        //Craneworks
        if (PlayerConfigs.groundGrab) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            extenderSetpoint = -12.0;
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kWristGround + Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Ground");
        } else if (PlayerConfigs.collectPos){
            Robot.crane.setRotator(Constants.kRotatorCollect);
            extenderSetpoint = -15.0;
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kWristCollect + Constants.cubeOffset * Robot.limelight.getPipeline());
            SmartDashboard.putString("Arm Position", "Collect");
        } else if (PlayerConfigs.lowGoal) {
            Robot.crane.setRotator(Constants.kRotatorMid);
            extenderSetpoint = -2.0;
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kWristMid);
            SmartDashboard.putString("Arm Position", "Low");
        } else if (PlayerConfigs.highGoal) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            extenderSetpoint = -20.0;
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kWristHi);
            SmartDashboard.putString("Arm Position", "Hi");
        } else if (PlayerConfigs.craneControl){
            if (Math.abs(PlayerConfigs.cranePos) > 0.2){
                Robot.crane.setRotator(Robot.crane.getRotatorLEncoder() + (30 * PlayerConfigs.cranePos));
            } else {
                Robot.crane.setRotator(Robot.crane.getRotatorLEncoder());
            }
        } else {
            Robot.crane.setRotator(Constants.kRotatorClosed);
            extenderSetpoint = 0.0;
            if (Robot.crane.rollerInUse) Robot.crane.setWrist(Constants.kRotatorClosed);
            SmartDashboard.putString("Arm Position", "Close");
        }

        //Extender
        if(Robot.crane.getExtenderEncoder() > extenderSetpoint & Robot.crane.getExtenderEncoder() <= 0.0 & Math.abs(PlayerConfigs.extendPos) > 0.2){
            SmartDashboard.putString("Extender State", "Moving");
            Robot.crane.setExtender(12*PlayerConfigs.extendPos);
        } else if (Robot.crane.getExtenderEncoder() > 0 & PlayerConfigs.extendPos > 0.2){
            SmartDashboard.putString("Extender State", "Locked Zero");
            Robot.crane.setExtender(12*PlayerConfigs.extendPos);
        } else if (Robot.crane.getExtenderEncoder() < extenderSetpoint & PlayerConfigs.extendPos < -0.2){
            SmartDashboard.putString("Extender State", "Locked Extended");
            Robot.crane.setExtender(12*PlayerConfigs.extendPos);
        } else {
            SmartDashboard.putString("Extender State", "Neutral");
            Robot.crane.setExtender(0);
        }

        Robot.crane.updateEncoderValues();
    }
}
