package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;

public class CraneTeleopCommand extends CommandBase {
    double extenderSetpoint;

    public CraneTeleopCommand() {
        addRequirements(Robot.crane);
    }

    @Override
    public void execute() {
        //End Effector
        if (PlayerConfigs.intake) {
            if(Robot.limelight.getPipeline() == 0){
                Robot.crane.setStinger(-8);
            } else if(Robot.limelight.getPipeline() == 1){
                Robot.crane.setStinger(8);
            }
        } else if (PlayerConfigs.release) {
            if(Robot.limelight.getPipeline() == 0){
                Robot.crane.setStinger(8);
            } else if(Robot.limelight.getPipeline() == 1){
                Robot.crane.setStinger(-8);
            }
        } else {
            if(Robot.limelight.getPipeline() == 0){
                Robot.crane.setStinger(-1);
            } else if(Robot.limelight.getPipeline() == 1){
                Robot.crane.setStinger(1);
            }
        }

        //Craneworks
        if (PlayerConfigs.groundGrab) {
            Robot.crane.setRotator(Constants.kRotatorGround);
            extenderSetpoint = Constants.kExtenderGround;
            Robot.crane.setWrist(Constants.kWristGround + Constants.cubeOffset * Robot.limelight.getPipeline());
        } else if (PlayerConfigs.collectPos){
            Robot.crane.setRotator(Constants.kRotatorCollect + (Constants.kRotatorCubeOffset * Robot.limelight.getPipeline()));
            extenderSetpoint = Constants.kExtenderCollect;
            Robot.crane.setWrist(Constants.kWristCollect + Constants.cubeOffset * Robot.limelight.getPipeline());
        } else if (PlayerConfigs.lowGoal) {
            Robot.crane.setRotator(Constants.kRotatorMid);
            extenderSetpoint = Constants.kExtenderLo;
            Robot.crane.setWrist(Constants.kWristMid);
        } else if (PlayerConfigs.highGoal) {
            Robot.crane.setRotator(Constants.kRotatorHi);
            extenderSetpoint = Constants.kExtenderHi;
            Robot.crane.setWrist(Constants.kWristHi);
        } else if (PlayerConfigs.craneControl || PlayerConfigs.redundantCraneControl){
            if (Math.abs(PlayerConfigs.cranePos) > 0.2){
                Robot.crane.setRotator(Robot.crane.getRotatorLEncoder() + (30 * PlayerConfigs.cranePos));
            } else {
                Robot.crane.setRotator(Robot.crane.getRotatorLEncoder());
            }
        } else if (PlayerConfigs.cubeHold) {
            Robot.crane.setRotator(Constants.kRotatorCubeHold);
            Robot.crane.setWrist(Constants.kWristCubeHold);
        } else if(Robot.controller1.getPOV() == -1) {
            Robot.crane.setRotator(Constants.kRotatorClosed);
            extenderSetpoint = Constants.kExtenderClosed;
            Robot.crane.setWrist(Constants.kWristClosed);
        }

        //Extender
        if(PlayerConfigs.fineExtender){
            SmartDashboard.putString("Extender State", "Fine Control");
            if (Math.abs(PlayerConfigs.extendPos) > 0.2) {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder() + (3 * PlayerConfigs.extendPos));
                SmartDashboard.putNumber("extenderSetpoint", Robot.crane.getExtenderEncoder() + (12 * PlayerConfigs.extendPos));
            } else {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder());
            }
        } else if (PlayerConfigs.highGoal || PlayerConfigs.lowGoal){
            SmartDashboard.putString("Extender State", "Scoring");
            if (Robot.crane.getRotatorLEncoder() > 120) {
                Robot.crane.setExtender(extenderSetpoint);
                SmartDashboard.putNumber("extenderSetpoint", extenderSetpoint);
            } else {
                Robot.crane.setExtender(-3);
                SmartDashboard.putNumber("extenderSetpoint", -3);
            }
        } else if (PlayerConfigs.collectPos || PlayerConfigs.groundGrab){
            SmartDashboard.putString("Extender State", "Collecting");
            if (Robot.crane.getExtenderEncoder() > extenderSetpoint) {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder() + (12 * PlayerConfigs.extendPos));
                SmartDashboard.putNumber("extenderSetpoint", Robot.crane.getExtenderEncoder() + (12 * PlayerConfigs.extendPos));
            } else {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder());
                SmartDashboard.putNumber("extenderSetpoint", Robot.crane.getExtenderEncoder());
            }
        } else if (Robot.controller1.getPOV() == -1){
            SmartDashboard.putString("Extender State", "Neutral");
            Robot.crane.setExtender(Constants.kExtenderClosed);
            SmartDashboard.putNumber("extenderSetpoint", Constants.kExtenderClosed);
        }

        Robot.crane.updateEncoderValues();
    }
}
