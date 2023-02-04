package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;

public class AutoRoutineRedWallCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String wall_a = "paths/RedWallCharge/red_wall_a.wpilib.json";
  String wall_b = "paths/RedWallCharge/red_wall_b.wpilib.json";
  String wallcharge_c = "paths/RedWallCharge/red_wallcharge_c.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(wall_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(wall_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(wallcharge_c);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);
  private Command movementB = AutoDrive.drivetrainMotion(traj_path_b);
  private Command movementC = AutoDrive.drivetrainMotion(traj_path_c);

  public AutoRoutineRedWallCharge(){
    
    addCommands(
        //score
        movementA,
        //grab
        movementB,
        //score
        movementC
        //align
      );
  }
} 