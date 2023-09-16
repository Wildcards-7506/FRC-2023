package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PincherConstants;
import frc.robot.util.Logger;

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

        pincherLEncoder.setPositionConversionFactor(PincherConstants.kPincherEncoderDistancePerPulse);
        pincherREncoder.setPositionConversionFactor(PincherConstants.kPincherEncoderDistancePerPulse);

        leftPincher.setSoftLimit(SoftLimitDirection.kForward, 0);
        leftPincher.setSoftLimit(SoftLimitDirection.kReverse, -270);
        rightPincher.setSoftLimit(SoftLimitDirection.kForward, -270);
        rightPincher.setSoftLimit(SoftLimitDirection.kReverse, 0);

        leftPincher.burnFlash();
        rightPincher.burnFlash();
        resetEncoders();
    }

    public void updateSmartDashboard () {
        SmartDashboard.putNumber("Left Pincher", pincherLEncoder.getPosition());
        SmartDashboard.putNumber("Right Pincher", pincherREncoder.getPosition());
    }

    public void grabLeft (double setPoint) {
        if (pincherLEncoder.getPosition() < 200) {
            leftPincher.setVoltage(setPoint);
        } else {
            leftPincher.setVoltage(0);
        }
    }

    public void grabRight (double setPoint) {
        if (pincherREncoder.getPosition() < 200) {
            rightPincher.setVoltage(setPoint);
        } else {
            rightPincher.setVoltage(0);
        }
    } 

    public void setLeftZero () {
        if (pincherLEncoder.getPosition() > 0) {
            leftPincher.setVoltage(-4);
        } else {
            leftPincher.setVoltage(0);
        }
    }

    public void setRightZero () {
        if (pincherREncoder.getPosition() > 0) {
            rightPincher.setVoltage(-4);
        } else {
            rightPincher.setVoltage(0);
        }
    }

    public void resetEncoders() {
        pincherLEncoder.setPosition(0);
        pincherREncoder.setPosition(0);
    }

    public void errorCheck(){
        if(rightPincher.getFaults()!=0){Logger.warn("PINCL: " + Short.toString(rightPincher.getFaults()));}
        if(leftPincher.getFaults()!=0){Logger.warn("PINCR: " + Short.toString(leftPincher.getFaults()));}
    }
}