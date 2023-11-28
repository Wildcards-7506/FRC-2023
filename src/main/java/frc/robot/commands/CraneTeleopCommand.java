package frc.robot.commands;

import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CraneConstants;
import frc.robot.Robot;
import frc.robot.ControlConfigs.PlayerConfigs;
import frc.robot.subsystems.HDD.HDD;
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
        if(Robot.controller0.getPOV() != prev_CraneState && !release){
            latch = Robot.controller0.getPOV() == -1 ? true : false;
            prev_CraneState = Robot.controller0.getPOV() != -1 ? Robot.controller0.getPOV() : prev_CraneState;
        } else if (latch && Robot.controller0.getPOV() == prev_CraneState){
            release = true;
        } else if (latch && release){
            prev_CraneState = -1;
            release = false;
        }

        //End Effector
        if (PlayerConfigs.intake) {
            if(HDD.grid.selectedCell.getTarget() == 0){
                Robot.crane.setStinger(-8);
            } else if(HDD.grid.selectedCell.getTarget() == 1){
                Robot.crane.setStinger(8);
            }
        } else if (PlayerConfigs.release) {
            if(HDD.grid.selectedCell.getTarget() == 0){
                Robot.crane.setStinger(8);
            } else if(HDD.grid.selectedCell.getTarget() == 1){
                Robot.crane.setStinger(-8);
            }
        } else {
            if(HDD.grid.selectedCell.getTarget() == 0){
                Robot.crane.setStinger(-1);
            } else if(HDD.grid.selectedCell.getTarget() == 1){
                Robot.crane.setStinger(1);
            }
        }

        //Craneworks
        // GROUND PICKUP
        if (prev_CraneState == 180) {
            extenderSetpoint = CraneConstants.kExtenderGround;
            wristSetpoint = CraneConstants.kWristGround + CraneConstants.kWristCubeGroundOffset * HDD.grid.selectedCell.getTarget();
            if(Robot.crane.getWristEncoder() - wristSetpoint < 10 && Robot.crane.getExtenderEncoder() - extenderSetpoint < 1){
                rotatorSetpoint = CraneConstants.kRotatorGround;
            } else {
                rotatorSetpoint = CraneConstants.kRotatorGroundClear;
            }
        // DOUBLE SUBSTATION
        } else if (prev_CraneState == 270){
            rotatorSetpoint = CraneConstants.kRotatorDoubleSub + (CraneConstants.kRotatorDoubleCubeOffset * HDD.grid.selectedCell.getTarget());
            extenderSetpoint = CraneConstants.kExtenderCollect;
            wristSetpoint = CraneConstants.kWristDoubleSub + CraneConstants.kWristCubeDoubleOffset * HDD.grid.selectedCell.getTarget();
        // SINGLE SUBSTATION
        } else if (prev_CraneState == 90) {
            rotatorSetpoint = CraneConstants.kRotatorSingleSub;
            extenderSetpoint = CraneConstants.kExtenderClosed;
            wristSetpoint = CraneConstants.kWristSingleSub + CraneConstants.kWristCubeSingleOffset * HDD.grid.selectedCell.getTarget();
        // SCORING
        } else if (prev_CraneState == 0) {
            rotatorSetpoint = Robot.crane.getSelectedCraneScoringPosition(HDD.grid.getSelectedCell().getID());
            extenderSetpoint = Robot.crane.getSelectedExtenderScoringPosition(HDD.grid.getSelectedCell().getID());
            wristSetpoint = Robot.crane.getSelectedWristScoringPosition(HDD.grid.getSelectedCell().getID());
        //FINE CONTROL
        } else if (PlayerConfigs.craneControl){
            if (Math.abs(PlayerConfigs.cranePos) > 0.2){
                rotatorSetpoint = Robot.crane.getRotatorLEncoder() + (30 * PlayerConfigs.cranePos);
            } else {
                rotatorSetpoint = Robot.crane.getRotatorLEncoder();
            }
        } else if(prev_CraneState == -1) {
            rotatorSetpoint = CraneConstants.kRotatorClosed;
            extenderSetpoint = CraneConstants.kExtenderClosed;
            wristSetpoint = CraneConstants.kWristClosed;
        }

        Robot.crane.setRotator(rotatorSetpoint);
        Robot.crane.setWrist(wristSetpoint);

        SmartDashboard.putNumber("Rotator Setpoint", rotatorSetpoint);
        SmartDashboard.putNumber("Extender Setpoint", extenderSetpoint);
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
        } else if (prev_CraneState == 0){
            SmartDashboard.putString("Crane State", "Scoring");
            if (Robot.crane.getRotatorLEncoder() > CraneConstants.kRotatorVertical) {
                Robot.crane.setExtender(extenderSetpoint);
                SmartDashboard.putNumber("Extender Setpoint", extenderSetpoint);
            } else {
                Robot.crane.setExtender(-3);
                SmartDashboard.putNumber("Extender Setpoint", -3);
            }
        } else if (prev_CraneState == 180 || prev_CraneState == 270 || prev_CraneState == 90){
            SmartDashboard.putString("Crane State", "Collecting");
            if (Robot.crane.getWristEncoder() > 10){
            Robot.crane.setExtender(extenderSetpoint);
            SmartDashboard.putNumber("Extender Setpoint", extenderSetpoint);
            } else {
                Robot.crane.setExtender(-3);
                SmartDashboard.putNumber("Extender Setpoint", -3);
            }
        } else if (prev_CraneState == -1){
            SmartDashboard.putString("Crane State", "Neutral");
            Robot.crane.setExtender(CraneConstants.kExtenderClosed);
            SmartDashboard.putNumber("Extender Setpoint", CraneConstants.kExtenderClosed);
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
        
        Robot.crane.errorCheck();
    }
}