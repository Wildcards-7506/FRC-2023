package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.Logger;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.math.geometry.Rotation2d;

import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax motorFrontLeftDrive;
    private CANSparkMax motorFrontLeftSteer;
    private CANSparkMax motorFrontRightDrive;
    private CANSparkMax motorFrontRightSteer;
    private CANSparkMax motorBackLeftDrive;
    private CANSparkMax motorBackLeftSteer;
    private CANSparkMax motorBackRightDrive;
    private CANSparkMax motorBackRightSteer;
    public RelativeEncoder frontLeftEncoder;
    public RelativeEncoder frontRightEncoder; 
    public RelativeEncoder backLeftEncoder;
    public RelativeEncoder backRightEncoder; 
    
    //public final MecanumDrive m_drive;

    public AHRS gyro = new AHRS(SerialPort.Port.kUSB);

    public MecanumDriveOdometry odometry;
    public MecanumDriveWheelPositions wheelPositions = new MecanumDriveWheelPositions();

    public Drivetrain(int fld, int fls, int frd, int frs, int bld, int bls, int brd, int brs){
        motorFrontLeftDrive = new CANSparkMax(fld, MotorType.kBrushless);
        motorFrontLeftSteer = new CANSparkMax(fls, MotorType.kBrushless);
        motorFrontRightDrive = new CANSparkMax(frd, MotorType.kBrushless);
        motorFrontRightSteer = new CANSparkMax(frs, MotorType.kBrushless);
        motorBackLeftDrive = new CANSparkMax(bld, MotorType.kBrushless);
        motorBackLeftSteer = new CANSparkMax(bls, MotorType.kBrushless);
        motorBackRightDrive = new CANSparkMax(brd, MotorType.kBrushless);
        motorBackRightSteer = new CANSparkMax(brs, MotorType.kBrushless);

        frontLeftEncoder = motorFrontLeftSteer.getEncoder();
        frontRightEncoder = motorFrontRightSteer.getEncoder();
        backLeftEncoder = motorBackLeftSteer.getEncoder();
        backRightEncoder = motorBackRightSteer.getEncoder();

        resetEncoders();

        motorFrontLeftDrive.burnFlash();
        motorFrontLeftSteer.burnFlash();
        motorFrontRightDrive.burnFlash();
        motorFrontRightSteer.burnFlash();
        motorBackLeftDrive.burnFlash();
        motorBackLeftSteer.burnFlash();
        motorBackRightDrive.burnFlash();
        motorBackRightSteer.burnFlash();
    }

    //Every scheduler cycle, we pass our XBox controls so we can control the drivetrain and update its pose in the dashboards
    @Override
    public void periodic(){
        //Update the odometry in the periodic block
        // updatePos();
        //System.out.println(odometry.getPoseMeters());
    }

    // public void updatePos() {
    //     odometry.update(gyro.getRotation2d(), getWheelPositions());
    //     SmartDashboard.putNumber("Pitch", gyro.getRoll());
    // }

    public Pose2d getPose(){
        return odometry.getPoseMeters();
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */

    // public void resetOdometry(Pose2d pose) {
    //     Logger.info("DRIVE", "RESETTING TO " + pose);
    //     resetEncoders();
    //     zeroHeading();
    //     odometry.resetPosition(gyro.getRotation2d(), getWheelPositions(), pose);
    // }

    /**
     * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
     * speeds have no effect on the angular speed. THIS IS TO BE USED IN TELEOP
     *
     * @param xSpeed Speed of the robot in the x direction (forward/backwards).
     * @param ySpeed Speed of the robot in the y direction (sideways).
     * @param rot Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the field.
     */
    @SuppressWarnings("ParameterName")
    public void drive(double ySpeed, double xSpeed, double rot, boolean fieldRelative) {
        // if (fieldRelative) {
        // m_drive.driveCartesian(-ySpeed, xSpeed, rot, new Rotation2d(Math.toRadians(gyro.getAngle())));
        // } else {
        // m_drive.driveCartesian(-ySpeed, xSpeed, rot);
        // }
    }

    
    /** Sets the front left drive MotorController to a voltage. FOR USE IN AUTO ONLY */
    // public void setDriveMotorControllersVolts(MecanumDriveMotorVoltages volts) {
    //     motorLeftFront.setVoltage(volts.frontLeftVoltage);
    //     motorRightFront.setVoltage(volts.rearLeftVoltage);
    //     motorLeftBack.setVoltage(volts.frontRightVoltage);
    //     motorRightBack.setVoltage(volts.rearRightVoltage);
    // }

    public void snap(double angle){
        if(Math.abs(getHeading() % 360 - angle) > Constants.kSnapRange){
            double rotation = Math.abs((getHeading() % 360 - angle))/(getHeading() % 360 - angle) * Constants.kSnapSpeed;
            drive(0, 0, rotation, true);
        } else {
            drive(0, 0, 0, true);
        }
    }

    public void align(double distance){
        double rotation = 0.0;
        double strafe = 0.0;
        double linear = 0.0;
        if(Math.abs(getHeading() % 360) > Constants.kSnapRange){
            rotation = (getHeading() % 360) * Constants.kSnapSpeed;
        }
        if(Math.abs(distance) > 2){
            strafe = -distance * Constants.kAlignKP;
        }
        if(Math.abs(distance) < 2 & Math.abs(getHeading() % 360) < Constants.kSnapRange){
            linear = 0.1;
        }
        
        drive(linear, strafe, rotation, true);
    }

    public void resetEncoders() {
        frontLeftEncoder.setPosition(0);
        frontRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
    }

    // public void setMaxOutput(double maxOutput) {
    //     m_drive.setMaxOutput(maxOutput);
    // }

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        gyro.reset();
    }

    public double getHeading(){
        // get the property
        return -gyro.getAngle();
    }

    public double getRoll(){
        // get the property
        return gyro.getRoll();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return -gyro.getRate();
    }
}
