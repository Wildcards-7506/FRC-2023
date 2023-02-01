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
import frc.robot.commands.CraneTOCom;

public class Crane extends SubsystemBase {
    private CANSparkMax clawAndRoller;
    private CANSparkMax rotator;
    private CANSparkMax rotator2;
    private CANSparkMax extender;
    private CANSparkMax wrist;
    
    private RelativeEncoder clawEncoder;
    private RelativeEncoder rotatorEncoder;
    private RelativeEncoder rotator2Encoder;
    private RelativeEncoder extenderEncoder;
    private RelativeEncoder wristEncoder;

    public SparkMaxPIDController rotatorPID;
    public SparkMaxPIDController clawPID;
    public SparkMaxPIDController wristPID;

    public boolean rollerOrClaw;

    // rollerOrShell = true means roller, false means shell
    public Crane(int rotator1, int rotator_2, int craneExtender, int clawRoller, int craneWrist, boolean usingRoller) {
        rollerOrClaw = usingRoller;

        clawAndRoller = new CANSparkMax(clawRoller, MotorType.kBrushless);
        rotator = new CANSparkMax(rotator1, MotorType.kBrushless);
        rotator2 = new CANSparkMax(rotator_2, MotorType.kBrushless);
        extender = new CANSparkMax(craneExtender, MotorType.kBrushless);
        wrist = new CANSparkMax(craneWrist, MotorType.kBrushless);

        rotator.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotator.enableSoftLimit(SoftLimitDirection.kReverse, true);
        rotator2.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotator2.enableSoftLimit(SoftLimitDirection.kReverse, true);
        extender.enableSoftLimit(SoftLimitDirection.kForward, true);
        extender.enableSoftLimit(SoftLimitDirection.kReverse, true);

        rotator.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        rotator2.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        extender.setSmartCurrentLimit(Constants.kExtenderCurrentLimit);
        clawAndRoller.setSmartCurrentLimit(Constants.kClawCurrentLimit);
        wrist.setSmartCurrentLimit(Constants.kWristCurrentLimit);

        if (!usingRoller) {
            clawAndRoller.enableSoftLimit(SoftLimitDirection.kForward, true);
            clawAndRoller.enableSoftLimit(SoftLimitDirection.kReverse, true);

            clawAndRoller.setSoftLimit(SoftLimitDirection.kForward, 0);
            clawAndRoller.setSoftLimit(SoftLimitDirection.kReverse, 85);
        } else {
            clawAndRoller.enableSoftLimit(SoftLimitDirection.kForward, false);
            clawAndRoller.enableSoftLimit(SoftLimitDirection.kReverse, false);
        }

        rotator2.follow(rotator, true);

        rotator.setSoftLimit(SoftLimitDirection.kForward, 0);
        rotator.setSoftLimit(SoftLimitDirection.kReverse, 330);
        rotator2.setSoftLimit(SoftLimitDirection.kForward, 0);
        rotator2.setSoftLimit(SoftLimitDirection.kReverse, 330);
        extender.setSoftLimit(SoftLimitDirection.kForward, 0);
        extender.setSoftLimit(SoftLimitDirection.kReverse, 28);

        rotatorEncoder = rotator.getEncoder();
        rotator2Encoder = rotator2.getEncoder();
        extenderEncoder = extender.getEncoder();
        clawEncoder = clawAndRoller.getEncoder();
        wristEncoder = wrist.getEncoder();

        rotatorPID = rotator.getPIDController();
        clawPID = clawAndRoller.getPIDController();
        wristPID = wrist.getPIDController();

        rotatorPID.setP(Constants.kRotatorKP);
        clawPID.setP(Constants.kClawKP);
        wristPID.setP(Constants.kWristKP);

        rotatorPID.setOutputRange(0, Constants.kRotatorMid);
        wristPID.setOutputRange(0, Constants.kRotatorMid);
        if (!usingRoller) clawPID.setOutputRange(0, Constants.kClawOpen);

        rotator.burnFlash();
        rotator2.burnFlash();
        extender.burnFlash();
        clawAndRoller.burnFlash();
        wrist.burnFlash();
    }

    @Override
    public void periodic() {
        setDefaultCommand(new CraneTOCom());
    }

    public void updateEncoderValues() {
        SmartDashboard.putNumber("Rotator 1 Position ", getRotatorEncoder());
        SmartDashboard.putNumber("Rotator 2 Position ", getRotator2Encoder());
        SmartDashboard.putNumber("Claw Position ", getClawEncoder());
        SmartDashboard.putNumber("Extender Position ", getExtenderEncoder());
        SmartDashboard.putNumber("Wrist Position ", getWristEncoder());
    }

    public double getRotatorEncoder() {
        return rotatorEncoder.getPosition();
    }

    public double getRotator2Encoder() {
        return rotator2Encoder.getPosition();
    }

    public double getClawEncoder() {
        return clawEncoder.getPosition();
    }

    public double getExtenderEncoder() {
        return extenderEncoder.getPosition();
    }

    public double getWristEncoder() {
        return wristEncoder.getPosition();
    }

    public void setRotator(double setPoint) {
        rotatorPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setClaw(double setPoint) {
        clawPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setExtender(double setPoint) {
        if (Math.abs(setPoint - getExtenderEncoder()) > 0.5) {
            double voltage = 12 * (setPoint -getExtenderEncoder() ) / Math.abs(setPoint - getExtenderEncoder());
            extender.setVoltage(voltage);
        } else {
            extender.setVoltage(0);
        }
    }

    public void setWrist(double setPoint) {
        wristPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setRoller (double setPoint) {
        clawAndRoller.setVoltage(setPoint);
    }
}
