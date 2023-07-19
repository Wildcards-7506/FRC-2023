package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.*;
import frc.robot.commands.Autonomous.Subsystem_Commands.*;

public class DevelopmentMode extends SequentialCommandGroup {
  public Pose2d pose = new Pose2d(0.0,0.0,new Rotation2d(0));

  public DevelopmentMode(){
    addCommands(
      new AutoLineDrive(4.5)
    );
  }
}
