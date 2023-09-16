package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.CraneConstants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoPathDrive;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class TwoPieceAutoLeft extends SequentialCommandGroup {
  private String pathA = "paths/LeftOut.wpilib.json";
  private Trajectory trajA = AutoTrajectoryReader.generateTrajectoryFromFile(pathA);
  private Command movementA = AutoPathDrive.drivetrainMotion(trajA);
  private String pathB = "paths/LeftIn.wpilib.json";
  private Trajectory trajB = AutoTrajectoryReader.generateTrajectoryFromFile(pathB);
  private Command movementB = AutoPathDrive.drivetrainMotion(trajB);
  public Pose2d pose = trajA.getInitialPose();

  public TwoPieceAutoLeft(){
    addCommands(
      new AutoScore(0,0),
      new ParallelCommandGroup(
        new AutoCollect( 12, 1), 
        movementA
      ),
      new ParallelCommandGroup(
        movementB,
        new AutoCraneExtenderPosition(CraneConstants.kExtenderClosed)
      ),
      new AutoScore(1,CraneConstants.kExtenderHi)
    );
  }
} 