package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.Logger;

public class LimelightRotator extends SubsystemBase{

    private CANSparkMax llRotator;
    private RelativeEncoder llRotatorEncoder;
    private SparkMaxPIDController llRotatorPID;

    public LimelightRotator (int m) {
        llRotator = new CANSparkMax(m, MotorType.kBrushless);

        llRotatorEncoder = llRotator.getEncoder();
        llRotatorEncoder.setPositionConversionFactor(Constants.kLimelightRotatorEncoderDistancePerPulse);

        llRotator.enableSoftLimit(SoftLimitDirection.kForward, false);
        llRotator.enableSoftLimit(SoftLimitDirection.kReverse, false);
        llRotator.setSmartCurrentLimit(Constants.kLimeLightCurrentLimit);

        llRotatorPID = llRotator.getPIDController();
        llRotatorPID.setP(Constants.kLimeLightRotatorKP);
        llRotatorPID.setOutputRange(-1, 1);

        llRotator.burnFlash();
    }

    public void setEyes (double setPoint) {
        llRotatorPID.setReference(setPoint, ControlType.kPosition);
    }

    public double getPosition () {
        return llRotatorEncoder.getPosition();
    }

    public void errorCheck(){
        if(llRotator.getFaults()!=0){Logger.warn("LLROT: " + Short.toString(llRotator.getFaults()));}
    }
}
