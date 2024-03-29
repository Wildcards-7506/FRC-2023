package frc.robot.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.util.Logger;

public class CraneTeleopCommand extends CommandBase {
    double rotatorSetpoint;
    double extenderSetpoint;
    double wristSetpoint;
    private int prev_CraneState = -1;
    private boolean latch = false;
    private boolean release = false;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public CraneTeleopCommand() {
        addRequirements(Robot.crane);
    }

    @Override
    public void execute() {
        //State Selection
        if(Robot.controller1.getPOV() != prev_CraneState && !release){
            latch = Robot.controller1.getPOV() == -1 ? true : false;
            prev_CraneState = Robot.controller1.getPOV() != -1 ? Robot.controller1.getPOV() : prev_CraneState;
        } else if (latch && Robot.controller1.getPOV() == prev_CraneState){
            release = true;
        } else if (latch && release){
            prev_CraneState = -1;
            release = false;
        }

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
        if (prev_CraneState == 180) {
            extenderSetpoint = Constants.kExtenderGround;
            wristSetpoint = Constants.kWristGround + Constants.kWristCubeGroundOffset * Robot.limelight.getPipeline();
            if(Robot.crane.getWristEncoder() - wristSetpoint < 10 && Robot.crane.getExtenderEncoder() - extenderSetpoint < 1){
                rotatorSetpoint = Constants.kRotatorGround;
            } else {
                rotatorSetpoint = Constants.kRotatorGroundClear;
            }
        } else if (prev_CraneState == 270){
            rotatorSetpoint = Constants.kRotatorDoubleSub + (Constants.kRotatorDoubleCubeOffset * Robot.limelight.getPipeline());
            extenderSetpoint = Constants.kExtenderCollect;
            wristSetpoint = Constants.kWristDoubleSub + Constants.kWristCubeDoubleOffset * Robot.limelight.getPipeline();
        } else if (prev_CraneState == 90) {
            rotatorSetpoint = Constants.kRotatorMid;
            extenderSetpoint = Constants.kExtenderLo;
            wristSetpoint = Constants.kWristMid;
        } else if (prev_CraneState == 0) {
            rotatorSetpoint = Constants.kRotatorHi;
            if(Robot.limelight.getPipeline() == 0.0){
                extenderSetpoint = Constants.kExtenderHi;
            } else {
                extenderSetpoint = Constants.kExtenderLo;
            }
            wristSetpoint = Constants.kWristHi;
        } else if (PlayerConfigs.craneControl){
            if (Math.abs(PlayerConfigs.cranePos) > 0.2){
                rotatorSetpoint = Robot.crane.getRotatorLEncoder() + (30 * PlayerConfigs.cranePos);
            } else {
                rotatorSetpoint = Robot.crane.getRotatorLEncoder();
            }
        } else if (PlayerConfigs.singleSub) {
            rotatorSetpoint = Constants.kRotatorSingleSub;
            extenderSetpoint = Constants.kExtenderClosed;
            wristSetpoint = Constants.kWristSingleSub + Constants.kWristCubeSingleOffset * Robot.limelight.getPipeline();
        } else if(prev_CraneState == -1) {
            rotatorSetpoint = Constants.kRotatorClosed;
            extenderSetpoint = Constants.kExtenderClosed;
            wristSetpoint = Constants.kWristClosed;
        }

        Robot.crane.setRotator(rotatorSetpoint);
        Robot.crane.setWrist(wristSetpoint);
        SmartDashboard.putNumber("Rotator Setpoint", rotatorSetpoint);
        SmartDashboard.putNumber("Wrist Setpoint", wristSetpoint);

        //Extender
        if(PlayerConfigs.fineExtender){
            SmartDashboard.putString("Crane State", "Fine Control");
            if (Math.abs(PlayerConfigs.extendPos) > 0.2) {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder() + (3 * PlayerConfigs.extendPos));
                SmartDashboard.putNumber("Extender Setpoint", Robot.crane.getExtenderEncoder() + (3 * PlayerConfigs.extendPos));
            } else {
                Robot.crane.setExtender(Robot.crane.getExtenderEncoder());
            }
        } else if (prev_CraneState == 0 || prev_CraneState == 90){
            SmartDashboard.putString("Crane State", "Scoring");
            if (Robot.crane.getRotatorLEncoder() > Constants.kRotatorVertical) {
                Robot.crane.setExtender(extenderSetpoint);
                SmartDashboard.putNumber("Extender Setpoint", extenderSetpoint);
            } else {
                Robot.crane.setExtender(-3);
                SmartDashboard.putNumber("Extender Setpoint", -3);
            }
        } else if (prev_CraneState == 180 || prev_CraneState == 270){
            SmartDashboard.putString("Crane State", "Collecting");
            if (Robot.crane.getWristEncoder() > 20){
            Robot.crane.setExtender(extenderSetpoint);
            SmartDashboard.putNumber("Extender Setpoint", extenderSetpoint);
            } else {
                Robot.crane.setExtender(-3);
                SmartDashboard.putNumber("Extender Setpoint", -3);
            }
        } else if (prev_CraneState == -1){
            SmartDashboard.putString("Crane State", "Neutral");
            Robot.crane.setExtender(Constants.kExtenderClosed);
            SmartDashboard.putNumber("Extender Setpoint", Constants.kExtenderClosed);
        }

        Logger.info("ROTTR", 
        df.format(rotatorSetpoint)
        + " " + df.format(Robot.crane.getRotatorLEncoder())
        + " " + df.format(Robot.crane.getRotatorLEncoder() - rotatorSetpoint)
        + " (Setpoint, Position, Error)");
        Logger.info("EXTND", 
        df.format(extenderSetpoint)
        + " " + df.format(Robot.crane.getExtenderEncoder())
        + " " + df.format(Robot.crane.getExtenderEncoder() - extenderSetpoint)
        + " (Setpoint, Position, Error)");
        Logger.info("WRIST", 
        df.format(wristSetpoint)
        + " " + df.format(Robot.crane.getWristEncoder())
        + " " + df.format(Robot.crane.getWristEncoder() - wristSetpoint)
        + " (Setpoint, Position, Error)");
        
        Robot.crane.updateEncoderValues();
        Robot.crane.errorCheck();
    }
}
