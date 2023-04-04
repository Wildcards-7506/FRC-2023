package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
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
    private CANSparkMax motorLeftFront;
    private CANSparkMax motorLeftBack;
    private CANSparkMax motorRightFront;
    private CANSparkMax motorRightBack;
    private CANSparkMax dropWheelLeft;
    private CANSparkMax dropWheelRight;
    public RelativeEncoder m_leftEncoder0;
    public RelativeEncoder m_rightEncoder0; 
    public RelativeEncoder m_leftEncoder1;
    public RelativeEncoder m_rightEncoder1; 
    public RelativeEncoder dwlEncoder;
    public RelativeEncoder dwrEncoder; 
    
    public final MecanumDrive m_drive;

    public AHRS gyro = new AHRS(SerialPort.Port.kUSB);

    public MecanumDriveOdometry odometry;
    public MecanumDriveWheelPositions wheelPositions = new MecanumDriveWheelPositions();

    public Drivetrain (int lf, int lb, int rf, int rb, int dwl, int dwr){
        motorLeftFront = new CANSparkMax(lf, MotorType.kBrushless);
        motorLeftBack = new CANSparkMax(lb, MotorType.kBrushless);
        motorRightFront = new CANSparkMax(rf, MotorType.kBrushless);
        motorRightBack = new CANSparkMax(rb, MotorType.kBrushless);
        dropWheelLeft = new CANSparkMax(dwl, MotorType.kBrushless);
        dropWheelRight = new CANSparkMax(dwr, MotorType.kBrushless);

        m_leftEncoder0 = motorLeftFront.getEncoder();
        m_rightEncoder0 = motorRightFront.getEncoder();
        m_leftEncoder1 = motorLeftBack.getEncoder();
        m_rightEncoder1 = motorRightBack.getEncoder();
        dwlEncoder = dropWheelLeft.getEncoder();
        dwrEncoder = dropWheelRight.getEncoder();

        motorRightBack.setInverted(true);
        motorRightFront.setInverted(true);
        dropWheelLeft.setInverted(true);
        dropWheelRight.setInverted(true);

        dropWheelLeft.enableSoftLimit(SoftLimitDirection.kForward, false);
        dropWheelLeft.enableSoftLimit(SoftLimitDirection.kReverse, false);
        dropWheelRight.enableSoftLimit(SoftLimitDirection.kForward, false);
        dropWheelRight.enableSoftLimit(SoftLimitDirection.kReverse, false);

        m_drive = new MecanumDrive(motorLeftFront, motorLeftBack, motorRightFront, motorRightBack);

        resetEncoders();

        odometry = new MecanumDriveOdometry(Constants.kinematics, Rotation2d.fromDegrees(getHeading()), getWheelPositions());
        m_leftEncoder0.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
        m_rightEncoder0.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
        m_leftEncoder1.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
        m_rightEncoder1.setPositionConversionFactor(Constants.kEncoderDistancePerPulse);
        dwlEncoder.setPositionConversionFactor(Constants.dropWheelGearRatio);
        dwrEncoder.setPositionConversionFactor(Constants.dropWheelGearRatio);

        motorLeftFront.burnFlash();
        motorLeftBack.burnFlash();
        motorRightFront.burnFlash();
        motorRightBack.burnFlash();
        dropWheelLeft.burnFlash();
        dropWheelRight.burnFlash();
    }

    //Every scheduler cycle, we pass our XBox controls so we can control the drivetrain and update its pose in the dashboards
    @Override
    public void periodic(){
        //Update the odometry in the periodic block
        updatePos();
        //System.out.println(odometry.getPoseMeters());
    }

    public void updatePos() {
        odometry.update(gyro.getRotation2d(), wheelPositions);
    }

    public Pose2d getPose(){
        return odometry.getPoseMeters();
    }

    /**
     * Resets the odometry to the specified pose.
     *
     * @param pose The pose to which to set the odometry.
     */
    public void resetOdometry(Pose2d pose) {
        odometry.resetPosition(gyro.getRotation2d(), getWheelPositions(), pose);
    }

    public MecanumDriveWheelSpeeds getWheelSpeeds(){
        return new MecanumDriveWheelSpeeds(
            m_leftEncoder0.getVelocity(), 
            m_leftEncoder1.getVelocity(), 
            m_rightEncoder0.getVelocity(), 
            m_rightEncoder1.getVelocity()
        ); 
    }

    public MecanumDriveWheelPositions getWheelPositions() {
        return new MecanumDriveWheelPositions(
            m_leftEncoder0.getPosition(),
            m_rightEncoder0.getPosition(),
            m_leftEncoder1.getPosition(),
            m_rightEncoder1.getPosition()
        );
    }

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
        if (fieldRelative) {
        m_drive.driveCartesian(-ySpeed, xSpeed, rot, new Rotation2d(Math.toRadians(gyro.getAngle())));
        } else {
        m_drive.driveCartesian(-ySpeed, xSpeed, rot);
        }
    }

    
    /** Sets the front left drive MotorController to a voltage. FOR USE IN AUTO ONLY */
    public void setDriveMotorControllersVolts(MecanumDriveMotorVoltages volts) {
        motorLeftFront.setVoltage(volts.frontLeftVoltage);
        motorRightFront.setVoltage(volts.rearLeftVoltage);
        motorLeftBack.setVoltage(volts.frontRightVoltage);
        motorRightBack.setVoltage(volts.rearRightVoltage);
    }

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

    public void setDropWheels(double level){
        double marginL = level - dwlEncoder.getPosition();
        double marginR = level - dwrEncoder.getPosition();

        if(Math.abs(marginL) > 0.1){
            dropWheelLeft.setVoltage(12*marginL/Math.abs(marginL));
        } else {dropWheelLeft.setVoltage(0);}
        if(Math.abs(marginR) > 0.1){
            dropWheelRight.setVoltage(12*marginR/Math.abs(marginR));
        } else {dropWheelRight.setVoltage(0);}
    }

    public void setLDropWheelVoltage (double voltAmt) {
        dropWheelLeft.setVoltage(voltAmt);
    }

    public void setRDropWheelVoltage (double voltAmt) {
        dropWheelRight.setVoltage(voltAmt);
    }

    public void resetEncoders() {
        m_leftEncoder0.setPosition(0);
        m_rightEncoder0.setPosition(0);
        m_leftEncoder1.setPosition(0);
        m_rightEncoder1.setPosition(0);
    }

    public void setMaxOutput(double maxOutput) {
        m_drive.setMaxOutput(maxOutput);
    }

    /** Zeroes the heading of the robot. */
    public void zeroHeading() {
        gyro.reset();
    }

    public double getHeading(){
        // get the property
        return -gyro.getAngle();
    }

    public double getPitch(){
        // get the property
        return gyro.getPitch();
    }

    /**
     * Returns the turn rate of the robot.
     *
     * @return The turn rate of the robot, in degrees per second
     */
    public double getTurnRate() {
        return -gyro.getRate();
    }

    public double getDWL(){
        return dwlEncoder.getPosition();
    }

    public double getDWR(){
        return dwrEncoder.getPosition();
    }
}
