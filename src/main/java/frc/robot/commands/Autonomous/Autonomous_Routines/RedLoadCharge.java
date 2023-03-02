package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class RedLoadCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String load_a = "paths/RedLoadCharge/red_load_a.wpilib.json";
  String load_b = "paths/RedLoadCharge/red_load_b.wpilib.json";
  String loadcharge_c = "paths/RedLoadCharge/red_loadcharge_c.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(load_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(load_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(loadcharge_c);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);
  private Command movementB = AutoDrive.drivetrainMotion(traj_path_b);
  private Command movementC = AutoDrive.drivetrainMotion(traj_path_c);

  public RedLoadCharge(){
    
    addCommands(
        new AutoScore(0,-10),
        movementA,
        new AutoCollect(90),
        movementB,
        new AutoScore(1,90),
        movementC,
        new AutoBalance(0)
      );
  }
} 