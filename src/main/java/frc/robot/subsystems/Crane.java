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

        rotatorLEncoder.setPositionConversionFactor(Constants.kRotateEncoderDistancePerPulse);
        rotatorFEncoder.setPositionConversionFactor(Constants.kRotateEncoderDistancePerPulse);
        extenderEncoder.setPositionConversionFactor(Constants.kExtendEncoderDistancePerPulse);
        wristEncoder.setPositionConversionFactor(Constants.kWristEncoderDistancePerPulse);

        rotatorLeader.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorLeader.enableSoftLimit(SoftLimitDirection.kReverse, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kForward, true);
        rotatorFollower.enableSoftLimit(SoftLimitDirection.kReverse, true);
        extender.enableSoftLimit(SoftLimitDirection.kForward, true);
        extender.enableSoftLimit(SoftLimitDirection.kReverse, true);
        stinger.enableSoftLimit(SoftLimitDirection.kForward, false);
        stinger.enableSoftLimit(SoftLimitDirection.kReverse, false);

        rotatorLeader.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        rotatorFollower.setSmartCurrentLimit(Constants.kRotateCurrentLimit);
        extender.setSmartCurrentLimit(Constants.kExtenderCurrentLimit);
        stinger.setSmartCurrentLimit(Constants.kClawCurrentLimit);
        wrist.setSmartCurrentLimit(Constants.kWristCurrentLimit);

        rotatorFollower.follow(rotatorLeader, true);

        rotatorLeader.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorLeader.setSoftLimit(SoftLimitDirection.kReverse, 0);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kForward, 330);
        rotatorFollower.setSoftLimit(SoftLimitDirection.kReverse, 0);
        extender.setSoftLimit(SoftLimitDirection.kForward, 0);
        extender.setSoftLimit(SoftLimitDirection.kReverse, -28);
        extender.setSoftLimit(SoftLimitDirection.kForward, 0);
        extender.setSoftLimit(SoftLimitDirection.kReverse, -28);

        rotatorPID = rotatorLeader.getPIDController();
        wristPID = wrist.getPIDController();

        rotatorPID.setP(Constants.kRotatorKP);
        wristPID.setP(Constants.kWristKP);

        rotatorPID.setOutputRange(-1, 1);
        wristPID.setOutputRange(-1, 1);

        rotatorLeader.burnFlash();
        rotatorFollower.burnFlash();
        extender.burnFlash();
        stinger.burnFlash();
        wrist.burnFlash();
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
        double arbFF = 0 * Math.cos(Math.toRadians(getRotatorLEncoder() - Constants.rotatorHorizontalOffset));
        rotatorPID.setReference(setPoint, ControlType.kPosition, 0, arbFF);
        SmartDashboard.putNumber("Rotator Setpoint", setPoint);
    }

    public void setExtender(double setPoint) {
        extender.setVoltage(setPoint);
    }

    public void setWrist(double setPoint) {
        wristPID.setReference(setPoint, ControlType.kPosition);
    }

    public void setStinger (double setPoint) {
        stinger.setVoltage(setPoint);
    }
}
