package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.HDD.HDD;
import frc.robot.util.Logger;

public class Crane extends SubsystemBase {
    private CANSparkMax stinger;
    private CANSparkMax rotatorLeader;
    private CANSparkMax rotatorFollower;
    private CANSparkMax extender;
    private CANSparkMax wrist;
    
    private RelativeEncoder rotatorLEncoder;
    private RelativeEncoder rotatorFEncoder;
    private RelativeEncoder extenderEncoder;
    private RelativeEncoder wristEncoder;

    public SparkMaxPIDController rotatorPID;
    public SparkMaxPIDController wristPID;
    public SparkMaxPIDController extenderPID;


    public Crane(int rotator_lead, int rotator_follow, int craneExtender, int craneStinger, int craneWrist) {

        stinger = new CANSparkMax(craneStinger, MotorType.kBrushless);
        rotatorLeader = new CANSparkMax(rotator_lead, MotorType.kBrushless);
        rotatorFollower = new CANSparkMax(rotator_follow, MotorType.kBrushless);
        extender = new CANSparkMax(craneExtender, MotorType.kBrushless);
        wrist = new CANSparkMax(craneWrist, MotorType.kBrushless);

        extender.setInverted(false);

        rotatorLEncoder = rotatorLeader.getEncoder();
        rotatorFEncoder = rotatorFollower.getEncoder();
        extenderEncoder = extender.getEncoder();
        wristEncoder = wrist.getEncoder();

        rotatorLEncoder.setPositionConversionFactor(Constants.CraneConstants.kRotateEncoderDistancePerPulse);
        rotatorFEncoder.setPositionConversionFactor(Constants.CraneConstants.kRotateEncoderDistancePerPulse);
        extenderEncoder.setPositionConversionFactor(Constants.CraneConstants.kExtendEncoderDistancePerPulse);
        wristEncoder.setPositionConversionFactor(Constants.CraneConstants.kWristEncoderDistancePerPulse);

        rotatorLeader.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorLeader.enableSoftLimit(SoftLimitDirection.kReverse, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kReverse, true);
        extender.enableSoftLimit(SoftLimitDirection.kForward, true);
        extender.enableSoftLimit(SoftLimitDirection.kReverse, true);
        stinger.enableSoftLimit(SoftLimitDirection.kForward, false);
        stinger.enableSoftLimit(SoftLimitDirection.kReverse, false);

        rotatorLeader.setSmartCurrentLimit(Constants.CraneConstants.kRotateCurrentLimit);
        rotatorFollower.setSmartCurrentLimit(Constants.CraneConstants.kRotateCurrentLimit);
        extender.setSmartCurrentLimit(Constants.CraneConstants.kExtenderCurrentLimit);
        stinger.setSmartCurrentLimit(Constants.CraneConstants.kStingerCurrentLimit);
        wrist.setSmartCurrentLimit(Constants.CraneConstants.kWristCurrentLimit);

        rotatorFollower.follow(rotatorLeader, true);

        rotatorLeader.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorLeader.setSoftLimit(SoftLimitDirection.kReverse, 0);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kReverse, 0);
        extender.setSoftLimit(SoftLimitDirection.kForward, 0);
        extender.setSoftLimit(SoftLimitDirection.kReverse, -22);

        rotatorPID = rotatorLeader.getPIDController();
        wristPID = wrist.getPIDController();
        extenderPID = extender.getPIDController();

        rotatorPID.setP(Constants.CraneConstants.kRotatorKP);
        wristPID.setP(Constants.CraneConstants.kWristKP);
        extenderPID.setP(Constants.CraneConstants.kExtenderKP);

        rotatorPID.setOutputRange(-1, 1);
        wristPID.setOutputRange(-1, 1);
        extenderPID.setOutputRange(-1, 1);

        rotatorLeader.burnFlash();
        rotatorFollower.burnFlash();
        extender.burnFlash();
        stinger.burnFlash();
        wrist.burnFlash();
    }

    public double getRotatorLEncoder() {
        return rotatorLEncoder.getPosition();
    }

    public double getRotatorFEncoder() {
        return rotatorFEncoder.getPosition();
    }

    public double getExtenderEncoder() {
        return extenderEncoder.getPosition();
    }

    public double getWristEncoder() {
        return wristEncoder.getPosition();
    }

    public double getStingerCurrent(){
        return stinger.getOutputCurrent();
    }

    public void setRotator(double setPoint) {
        double arbFF = 0 * Math.cos(Math.toRadians(getRotatorLEncoder() - Constants.CraneConstants.rotatorHorizontalOffset));
        rotatorPID.setReference(setPoint, ControlType.kPosition, 0, arbFF);
        SmartDashboard.putNumber("Rotator Setpoint", setPoint);
    }

    public void setExtender(double setPoint) {
        extenderPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setWrist(double setPoint) {
        wristPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setStinger (double setPoint) {
        stinger.setVoltage(setPoint);
    }

    public double getSelectedCraneScoringPosition(int code){
        if (code > 17){
            return Constants.CraneConstants.kRotatorHi;
        } else if (code < 18 & code > 8){
            return Constants.CraneConstants.kRotatorMid;
        } else {
            return Constants.CraneConstants.kRotatorGroundClear;
        }
    }

    public double getSelectedExtenderScoringPosition(int code){
        if (code > 17){
            return Constants.CraneConstants.kExtenderHi * (HDD.grid.selectedCell.getTarget() - 1);
        } else if (code < 18 & code > 8){
            return Constants.CraneConstants.kExtenderLo;
        } else {
            return Constants.CraneConstants.kExtenderClosed;
        }
    }

    public double getSelectedWristScoringPosition(int code){
        if (code > 17){
            return Constants.CraneConstants.kWristHi;
        } else if (code < 18 & code > 8){
            return Constants.CraneConstants.kWristMid + (45 * HDD.grid.selectedCell.getTarget());
        } else {
            return Constants.CraneConstants.kRotatorGround + 100 * HDD.grid.selectedCell.getTarget();
        }
    }

    public void errorCheck(){
        if(stinger.getFaults()!=0){Logger.warn("STNGR: " + Short.toString(stinger.getFaults()));}
        if(wrist.getFaults()!=0){Logger.warn("WRIST: " + Short.toString(wrist.getFaults()));}
        if(extender.getFaults()!=0){Logger.warn("EXTND: " + Short.toString(extender.getFaults()));}
        if(rotatorFollower.getFaults()!=0){Logger.warn("ROTRF: " + Short.toString(rotatorFollower.getFaults()));}
        if(rotatorLeader.getFaults()!=0){Logger.warn("ROTRL: " + Short.toString(rotatorLeader.getFaults()));}
    }
}