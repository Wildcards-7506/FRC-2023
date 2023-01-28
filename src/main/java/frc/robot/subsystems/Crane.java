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

    @Override
    public void periodic() {
        
    }

    // public static double getRotatorEncoder() {

    // }

    // public static double getClawEncoder() {

    // }

    // public static double getExtenderEncoder() {

    // }

    // public static double setRotator() {

    // }

    // public static double setClaw() {

    // }

    //public static double setExtender() {

    // }
}
