package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;

public class RightChargeStation extends SequentialCommandGroup {
  // required PathWeaver file paths
  String rightCS = "pathsRightCS.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(rightCS);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);

  public RightChargeStation(){
    
    addCommands(
      new AutoScore(0,2),
      movementA,
      new AutoBalance(0)
    );
  }
} 