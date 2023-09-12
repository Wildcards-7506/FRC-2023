package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public final class Constants {
    //Motor IDs
        //Drivetrain
        public static final int FRONT_LEFT_DRIVE = 0;
        public static final int FRONT_LEFT_STEER = 0;
        public static final int FRONT_RIGHT_DRIVE = 0;
        public static final int FRONT_RIGHT_STEER = 0;
        public static final int BACK_LEFT_DRIVE = 0;
        public static final int BACK_LEFT_STEER = 0;
        public static final int BACK_RIGHT_DRIVE = 0;
        public static final int BACK_RIGHT_STEER = 0;
        
        //Crane
        public static final int CRANE_EXTENDER = 19;
        public static final int CRANE_ROTATION_LEAD = 2;
        public static final int CRANE_ROTATION_FOLLOW = 3;
        public static final int CRANE_STINGER = 18;
        public static final int CRANE_WRIST = 17;

        //Pinchers
        public static final int PINCH_LEFT = 21;
        public static final int PINCH_RIGHT = 22;

        //Limelight Rotator
        public static final int LIMELIGHT_ROTATOR = 23;

    //Controller Assignments
        public static final int DRIVER_CONTROLLER_0 = 0;
        public static final int DRIVER_CONTROLLER_1 = 1;
        
        //Control Axes
        public static final int LEFT_STICK_X = 0;
        public static final int LEFT_STICK_Y = 1;
        public static final int RIGHT_STICK_X = 2;
        public static final int RIGHT_STICK_Y = 3;

        //Control D-Pad
        public static final int DPAD_X = 2;
        public static final int DPAD_Y = 3;

        //Control Buttons
        public static final int BUTTON_A = 2;
        public static final int BUTTON_B = 3;
        public static final int BUTTON_X = 1;
        public static final int BUTTON_Y = 4;
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;

        public static final int LEFT_TRIGGER = 7;
        public static final int RIGHT_TRIGGER = 8;

        public static final int BUTTON_BACK = 9;
        public static final int BUTTON_START = 10;
        public static final int LEFT_JOYSTICK_BUTTON = 11;
        public static final int RIGHT_JOYSTICK_BUTTON = 12;

    //Drive Constants   

        //Speed, Power, Movement Limits
        public static final int kDrivetrainCurrentLimit = 30;
        public static final double kDropWheelDistance = 0.5;
        public static final double kSnapRange = 1;
        public static final double kSnapSpeed = 0.08;
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

        //Movement Controller Constants
            //Change to match Forward/Backward SysID Results
        public static final double kPXController = 0.1;
            //Need to run horizontal SysID Test for this constant
        public static final double kPYController = 0.5;
            //Need to run spin SysID Test for this constant
        public static final double kPThetaController = 0.5;
            //No change Needed
        public static final double kAlignKP = 0.01;

            //Change these to match Forward/Back SysID Results
        public static final double ffKS = 1;
        public static final double ffKV = 0.8;
        public static final double ffKA = 0.15;
        
        //Wheel Controller Constants
            //Match kP from kPX Controller to start, adjust as needed
        public static final double kPFrontLeftVel = 0.5;
        public static final double kPRearLeftVel = 0.5;
        public static final double kPFrontRightVel = 0.5;
        public static final double kPRearRightVel = 0.5;
        
        //Robot Size Parameters
        public static final double kTrackwidthMeters = Units.inchesToMeters(20.176);
        public static final double kTrackLengthMeters = Units.inchesToMeters(21.911);;
        public static final double driveTrainGearRatio = 1.0/12;
        public static final double dropWheelGearRatio = 1.0/9/8;
        public static final double kEncoderDistancePerPulse = driveTrainGearRatio * Math.PI * Units.inchesToMeters(8);

        // Robot Movement Profile and Kinematics
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);

        public static MecanumDriveKinematics kinematics = new MecanumDriveKinematics(
            new Translation2d(kTrackLengthMeters / 2.0, kTrackwidthMeters / 2.0),
            new Translation2d(kTrackLengthMeters / 2.0, -kTrackwidthMeters / 2.0),
            new Translation2d(-kTrackLengthMeters / 2.0, kTrackwidthMeters / 2.0),
            new Translation2d(-kTrackLengthMeters / 2.0, -kTrackwidthMeters / 2.0));

        public static final  TrajectoryConfig kconfig =  
            new TrajectoryConfig(
                kMaxSpeedMetersPerSecond,
                kMaxAccelerationMetersPerSecondSquared)
                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(kinematics);

        public static final SimpleMotorFeedforward kFeedforward =
        new SimpleMotorFeedforward(ffKS, ffKV, ffKA);

    //Crane Constants
        public static final int kRotateCurrentLimit = 30;
        public static final double kRotateEncoderDistancePerPulse = 1.0/125 * 12.0/15 * 360;
        public static final double kRotatorKP = 0.007;
        public static final double kRotatorSingleSub = 40;
        public static final double kRotatorGroundClear = 15.0;
        public static final double kRotatorGround = -5.0;
        public static final double kRotatorHi = 160.0;
        public static final double kRotatorMid = 165.0;
        public static final double kRotatorDoubleSub = 85.0;
        public static final double kRotatorDoubleCubeOffset = -30;
        public static final double kRotatorSingleCubeOffset = -20;
        public static final double kRotatorVertical = 115.0;
        public static final double kRotatorClosed = 0.0;
        public static final double rotatorHorizontalOffset = 60;

        public static final int kExtenderCurrentLimit = 40;
        public static final double kExtendEncoderDistancePerPulse = 2.256*Math.PI/15.0;
        public static final double kExtenderKP = 0.1;
        public static final double kExtenderGround = -23.0;
        public static final double kExtenderLo = -4.0;
        public static final double kExtenderHi = -22.0;
        public static final double kExtenderCollect = -12.0;
        public static final double kExtenderClosed = -1.0;
        
        public static final int kStingerCurrentLimit = 40;

        public static final int kWristCurrentLimit = 10;
        public static final double kWristEncoderDistancePerPulse = 1.0/25 * 360;
        public static final double kWristKP = 0.1;
        public static final double kWristHi = -200.0;
        public static final double kWristMid = -190.0;
        public static final double kWristDoubleSub = -35.0;
        public static final double kWristGround = 90.0;
        public static final double kWristSingleSub= 90;
        public static final double kWristClosed = -10.0;
        public static final double kWristCubeDoubleOffset = 130.0;
        public static final double kWristCubeSingleOffset = -205;
        public static final double kWristCubeGroundOffset = 40;
    //Pincher constants
        public static final double kHookLeft = 90.0;
        public static final double kHookRight = -90.0;
        public static final double kPullLeft = 270;
        public static final double kPullRight = -270;
        public static final double kPincherEncoderDistancePerPulse = 1.0/64 * 360;
    
    //Limelight Rotator Constants
        public static final int kLookForward = -180;
        public static final int kLookCollect = -160;
        public static final int kLookGoal = 0;
        public static final int kLimeLightCurrentLimit = 10;
        public static final double kLimelightRotatorEncoderDistancePerPulse = 1.0 / 3 * 360;
        public static final double kLimeLightRotatorKP = 0.05;
}
