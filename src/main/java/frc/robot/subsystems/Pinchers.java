package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pinchers extends SubsystemBase{
    private CANSparkMax leftPincher;
    private CANSparkMax rightPincher;

    private RelativeEncoder pincherLEncoder;
    private RelativeEncoder pincherREncoder;

    public Pinchers(int pl, int pr) {

        leftPincher = new CANSparkMax(pl, MotorType.kBrushless);
        rightPincher = new CANSparkMax(pr, MotorType.kBrushless);

        pincherLEncoder = leftPincher.getEncoder();
        pincherREncoder = rightPincher.getEncoder();

        pincherLEncoder.setPositionConversionFactor(Constants.kPincherEncoderDistancePerPulse);
        pincherREncoder.setPosition(Constants.kPincherEncoderDistancePerPulse);

        leftPincher.setSoftLimit(SoftLimitDirection.kForward, 90);
        leftPincher.setSoftLimit(SoftLimitDirection.kReverse, 0);
        rightPincher.setSoftLimit(SoftLimitDirection.kForward, 90);
        rightPincher.setSoftLimit(SoftLimitDirection.kReverse, 0);
        resetEncoders();
    }

    public void grabLeft (double setPoint) {
        leftPincher.setVoltage(setPoint);
    }

    public void grabRight (double setPoint) {
        rightPincher.setVoltage(Constants.kGrabRight);
    }

    public void setLeftZero () {
        leftPincher.setVoltage(-4);
    }

    public void setRightZero () {
        rightPincher.setVoltage(-4);
    }

    public void resetEncoders() {
        pincherLEncoder.setPosition(0);
        pincherREncoder.setPosition(0);
    }
}