package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class BlueWallOut extends SequentialCommandGroup {
  // required PathWeaver file paths
  String wall_a = "paths/BlueWallOut/blue_wall_a.wpilib.json";
  String wall_b = "paths/BlueWallOut/blue_wall_b.wpilib.json";
  String wallout_c = "paths/BlueWallOut/blue_wallout_c.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(wall_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(wall_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(wallout_c);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);
  private Command movementB = AutoDrive.drivetrainMotion(traj_path_b);
  private Command movementC = AutoDrive.drivetrainMotion(traj_path_c);

  public BlueWallOut(){
    
    addCommands(
        new AutoScore(0,-10),
        movementA,
        new AutoCollect(12),
        movementB,
        new AutoScore(1,90),
        movementC
      );
  }
} 