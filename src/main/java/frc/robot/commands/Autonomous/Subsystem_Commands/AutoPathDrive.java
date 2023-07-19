package frc.robot.commands.Autonomous.Subsystem_Commands;

import frc.robot.Robot;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;

public class AutoPathDrive {
    //Returns a series of waypoints to the controller to run complex motion paths
    public static Command drivetrainMotion(Trajectory trajectory) {
    
        MecanumControllerCommand mecanumControllerCommand =
            new MecanumControllerCommand(
                trajectory,
                Robot.drivetrain::getPose,
                Constants.kFeedforward,
                Constants.kinematics,
    
                // Position contollers
                new PIDController(Constants.kPXController, 0, 0),
                new PIDController(Constants.kPYController, 0, 0),
                new ProfiledPIDController(
                    Constants.kPThetaController, 0, 0, Constants.kThetaControllerConstraints),
    
                // Needed for normalizing wheel speeds
                Constants.kMaxSpeedMetersPerSecond,
    
                // Velocity PID's
                new PIDController(Constants.kPFrontLeftVel, 0, 0),
                new PIDController(Constants.kPRearLeftVel, 0, 0),
                new PIDController(Constants.kPFrontRightVel, 0, 0),
                new PIDController(Constants.kPRearRightVel, 0, 0),
                Robot.drivetrain::getWheelSpeeds,
                Robot.drivetrain::setDriveMotorControllersVolts, // Consumer for the output motor voltages
                Robot.drivetrain);
    
        // Run path following command, then stop at the end.
        return mecanumControllerCommand.andThen(() -> Robot.drivetrain.drive(0, 0, 0, false));
      }
}