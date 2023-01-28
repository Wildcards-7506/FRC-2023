package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Crane extends SubsystemBase {
    private CANSparkMax claw;
    private CANSparkMax rotator;
    private CANSparkMax extender;

    public RelativeEncoder clawEncoder;
    public RelativeEncoder rotatorEncoder;
    public RelativeEncoder extenderEncoder;
    
    claw.enableSoftLimit();
}
