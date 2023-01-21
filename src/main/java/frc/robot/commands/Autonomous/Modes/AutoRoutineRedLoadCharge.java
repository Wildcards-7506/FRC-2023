package frc.robot.commands.Autonomous.Modes;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.AutoCommands;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;

public class AutoRoutineRedLoadCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String load_a = "paths/RedLoadCharge/red_load_a.wpilib.json";
  String load_b = "paths/RedLoadCharge/red_load_b.wpilib.json";
  String loadcharge_c = "paths/RedLoadCharge/red_loadcharge_c.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(load_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(load_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(loadcharge_c);

  //Commands
  private Command movementA = AutoCommands.drivetrainMotion(traj_path_a);
  private Command movementB = AutoCommands.drivetrainMotion(traj_path_b);
  private Command movementC = AutoCommands.drivetrainMotion(traj_path_c);

  public AutoRoutineRedLoadCharge(){
    
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