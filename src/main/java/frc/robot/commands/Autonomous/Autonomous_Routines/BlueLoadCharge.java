package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class BlueLoadCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String load_a = "paths/BlueLoadCharge/blue_load_a.wpilib.json";
  String load_b = "paths/BlueLoadCharge/blue_load_b.wpilib.json";
  String loadcharge_c = "paths/BlueLoadCharge/blue_loadcharge_c.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(load_a);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(load_b);
  private Trajectory traj_path_c = AutoTrajectoryReader.generateTrajectoryFromFile(loadcharge_c);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);
  private Command movementB = AutoDrive.drivetrainMotion(traj_path_b);
  private Command movementC = AutoDrive.drivetrainMotion(traj_path_c);

  public BlueLoadCharge(){
    
    addCommands(
        new AutoScore(0,2),
        movementA,
        new AutoCollect(12),  
        new ParallelCommandGroup(
          movementB,
          new AutoExtenderPosition(Constants.kExtenderClosed)
        ),
        new AutoScore(1,-6),
        movementC,
        new AutoBalance(0)
      );
  }
} 