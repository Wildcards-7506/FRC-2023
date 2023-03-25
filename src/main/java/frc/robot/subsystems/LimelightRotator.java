package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LimelightRotator extends SubsystemBase{

    private CANSparkMax llRotator;
    private RelativeEncoder llRotatorEncoder;

    public LimelightRotator (int m) {
        llRotator = new CANSparkMax(m, MotorType.kBrushless);

        llRotatorEncoder = llRotator.getEncoder();
        llRotatorEncoder.setPositionConversionFactor(Constants.kLimelightRotatorEncoderDistancePerPulse);

        llRotator.setSoftLimit(SoftLimitDirection.kForward, Constants.kLookForward);
        llRotator.setSoftLimit(SoftLimitDirection.kReverse, Constants.kLookBackward);

        llRotator.burnFlash();
    }

    public void lookForward () {
        if (llRotatorEncoder.getPosition() > 10) {
            llRotator.setVoltage(2);
        } else {
            llRotator.setVoltage(0);
        }
    }

    public void lookBackward () {
        if (llRotatorEncoder.getPosition() > 180) {
            llRotator.setVoltage(-2);
        } else {
            llRotator.setVoltage(0);
        }
    }

    public double getPosition () {
        return llRotatorEncoder.getPosition();
    }
}
