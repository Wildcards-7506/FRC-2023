package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pinchers extends SubsystemBase{
    private CANSparkMax leftPincher;
    private CANSparkMax rightPincher;

    private RelativeEncoder pincherLEncoder;
    private RelativeEncoder pincherREncoder;

    public Pinchers(int pl, int pr){

        leftPincher = new CANSparkMax(pl, MotorType.kBrushless);
        rightPincher = new CANSparkMax(pr, MotorType.kBrushless);

        pincherLEncoder = leftPincher.getEncoder();
        pincherREncoder = rightPincher.getEncoder();

        resetEncoders();

    }

    public void resetEncoders() {
        pincherLEncoder.setPosition(0);
        pincherREncoder.setPosition(0);
    }
}
