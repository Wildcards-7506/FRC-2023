package frc.robot.commands.Autonomous.Modes;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.AutoCommands;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;

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
  private Command movementA = AutoCommands.drivetrainMotion(traj_path_a);
  private Command movementB = AutoCommands.drivetrainMotion(traj_path_b);
  private Command movementC = AutoCommands.drivetrainMotion(traj_path_c);

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