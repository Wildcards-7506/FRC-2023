package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLineDrive;

public class PlaceMoveStop extends SequentialCommandGroup {
  public Pose2d pose = new Pose2d(0.0,0.0,new Rotation2d(0));

  public PlaceMoveStop(){
    addCommands(
        new AutoScore(0,0),
        new AutoLineDrive(3)
      );
  }
} 