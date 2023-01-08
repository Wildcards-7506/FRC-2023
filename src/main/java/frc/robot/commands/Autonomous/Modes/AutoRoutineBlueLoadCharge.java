package frc.robot.commands.Autonomous.Modes;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.AutoCommands;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;

public class AutoRoutineBlueLoadCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String file_path_a = "paths/BlueLoadCharge/pathA.wpilib.json";
  String file_path_b = "paths/BlueLoadCharge/pathB.wpilib.json";
  String file_path_c = "paths/BlueLoadCharge/pathC.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(file_path_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(file_path_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(file_path_c);

  //Commands
  private Command movementA = AutoCommands.drivetrainMotion(traj_path_a);
  private Command movementB = AutoCommands.drivetrainMotion(traj_path_b);
  private Command movementC = AutoCommands.drivetrainMotion(traj_path_c);

  public AutoRoutineBlueLoadCharge(){
    
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