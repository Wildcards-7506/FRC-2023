package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoPathDrive;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class TwoPieceAutoRight extends SequentialCommandGroup {
  private String pathA = "paths/RightOut.wpilib.json";
  private Trajectory trajA = AutoTrajectoryReader.generateTrajectoryFromFile(pathA);
  private Command movementA = AutoPathDrive.drivetrainMotion(trajA);
  private String pathB = "paths/RightIn.wpilib.json";
  private Trajectory trajB = AutoTrajectoryReader.generateTrajectoryFromFile(pathB);
  private Command movementB = AutoPathDrive.drivetrainMotion(trajB);
  public Pose2d pose = trajA.getInitialPose();

  public TwoPieceAutoRight(){
    addCommands(
      new AutoScore(0,0),
      new ParallelCommandGroup(
        new AutoCollect( 12, 1), 
        movementA
      ),
      new ParallelCommandGroup(
        movementB,
        new AutoCraneExtenderPosition(Constants.kExtenderClosed)
      ),
      new AutoScore(1,Constants.kExtenderHi)
    );
  }
} 