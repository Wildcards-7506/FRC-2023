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
    private CANSparkMax endEffector;
    private CANSparkMax rotatorLeader;
    private CANSparkMax rotatorFollower;
    private CANSparkMax extender;
    private CANSparkMax wrist;
    
    private RelativeEncoder clawEncoder;
    private RelativeEncoder rotatorLEncoder;
    private RelativeEncoder rotatorFEncoder;
    private RelativeEncoder extenderEncoder;
    private RelativeEncoder wristEncoder;

    public SparkMaxPIDController rotatorPID;
    public SparkMaxPIDController clawPID;
    public SparkMaxPIDController wristPID;

    public boolean rollerInUse;

    public Crane(int rotator_lead, int rotator_follow, int craneExtender, int craneEndEffector, int craneWrist, boolean usingRoller) {
        rollerInUse = usingRoller;

        endEffector = new CANSparkMax(craneEndEffector, MotorType.kBrushless);
        rotatorLeader = new CANSparkMax(rotator_lead, MotorType.kBrushless);
        rotatorFollower = new CANSparkMax(rotator_follow, MotorType.kBrushless);
        extender = new CANSparkMax(craneExtender, MotorType.kBrushless);
        wrist = new CANSparkMax(craneWrist, MotorType.kBrushless);

        rotatorLEncoder = rotatorLeader.getEncoder();
        rotatorFEncoder = rotatorFollower.getEncoder();
        extenderEncoder = extender.getEncoder();
        clawEncoder = endEffector.getEncoder();
        wristEncoder = wrist.getEncoder();

        rotatorLEncoder.setPositionConversionFactor(Constants.kRotateEncoderDistancePerPulse);
        rotatorFEncoder.setPositionConversionFactor(Constants.kRotateEncoderDistancePerPulse);
        extenderEncoder.setPositionConversionFactor(Constants.kExtendEncoderDistancePerPulse);
        clawEncoder.setPositionConversionFactor(Constants.kClawEncoderDistancePerPulse);
        wristEncoder.setPositionConversionFactor(Constants.kWristEncoderDistancePerPulse);

        rotatorLeader.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorLeader.enableSoftLimit(SoftLimitDirection.kReverse, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kReverse, true);
        extender.enableSoftLimit(SoftLimitDirection.kForward, true);
        extender.enableSoftLimit(SoftLimitDirection.kReverse, true);

        rotatorLeader.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        rotatorFollower.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        extender.setSmartCurrentLimit(Constants.kExtenderCurrentLimit);
        endEffector.setSmartCurrentLimit(Constants.kClawCurrentLimit);
        wrist.setSmartCurrentLimit(Constants.kWristCurrentLimit);

        if (!usingRoller) {
            endEffector.enableSoftLimit(SoftLimitDirection.kForward, true);
            endEffector.enableSoftLimit(SoftLimitDirection.kReverse, true);

            endEffector.setSoftLimit(SoftLimitDirection.kForward, 0);
            endEffector.setSoftLimit(SoftLimitDirection.kReverse, 85);
        } else {
            endEffector.enableSoftLimit(SoftLimitDirection.kForward, false);
            endEffector.enableSoftLimit(SoftLimitDirection.kReverse, false);
        }

        rotatorFollower.follow(rotatorLeader, true);

        rotatorLeader.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorLeader.setSoftLimit(SoftLimitDirection.kReverse, 0);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kReverse, 0);
        extender.setSoftLimit(SoftLimitDirection.kForward, 28);
        extender.setSoftLimit(SoftLimitDirection.kReverse, 0);

        rotatorPID = rotatorLeader.getPIDController();
        clawPID = endEffector.getPIDController();
        wristPID = wrist.getPIDController();

        rotatorPID.setP(Constants.kRotatorKP);
        clawPID.setP(Constants.kClawKP);
        wristPID.setP(Constants.kWristKP);

        rotatorPID.setOutputRange(-1, 1);
        wristPID.setOutputRange(-1, 1);
        if (!usingRoller) clawPID.setOutputRange(-1, 1);

        rotatorLeader.burnFlash();
        rotatorFollower.burnFlash();
        extender.burnFlash();
        endEffector.burnFlash();
        wrist.burnFlash();
    }

    @Override
    public void periodic() {
        setDefaultCommand(new CraneTOCom());
    }

    public void updateEncoderValues() {
        SmartDashboard.putNumber("Rotator Position", getRotatorLEncoder());
        SmartDashboard.putNumber("Extender Position", getExtenderEncoder());
        SmartDashboard.putNumber("Wrist Position", getWristEncoder());
    }

    public double getRotatorLEncoder() {
        return rotatorLEncoder.getPosition();
    }

    public double getRotatorFEncoder() {
        return rotatorFEncoder.getPosition();
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
        double arbFF = 0.46 * Math.cos(Math.toRadians(getRotatorLEncoder() - Constants.rotatorHorizontalOffset));
        rotatorPID.setReference(setPoint, ControlType.kPosition, 0, arbFF);
        SmartDashboard.putNumber("Rotator Setpoint", setPoint);
    }

    public void setClaw(double setPoint) {
        clawPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setExtender(double setPoint, boolean manual) {
        SmartDashboard.putNumber("Extender Setpoint", setPoint);
        if (Math.abs(setPoint - getExtenderEncoder()) > 0.5 && !manual) {
            double voltage = 12 * (setPoint - getExtenderEncoder() ) / Math.abs(setPoint - getExtenderEncoder());
            extender.setVoltage(-voltage);
        } else if (manual){
            extender.setVoltage(setPoint);
        } else {
            extender.setVoltage(0);
        }
    }

    public void setWrist(double setPoint) {
        SmartDashboard.putNumber("Wrist Setpoint", setPoint);
        wristPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setRoller (double setPoint) {
        endEffector.setVoltage(setPoint);
    }
}
