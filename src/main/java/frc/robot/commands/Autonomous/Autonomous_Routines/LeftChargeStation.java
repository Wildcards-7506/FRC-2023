package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoPathDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;

public class LeftChargeStation extends SequentialCommandGroup {
  // required PathWeaver file paths
  String leftCS = "paths/LeftCS.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(leftCS);
  
  //Commands
  private Command movementA = AutoPathDrive.drivetrainMotion(traj_path_a);

  public LeftChargeStation(){
    
    addCommands(
        new AutoScore(0,0),
        movementA,
        new AutoBalance(0)
      );
  }
} 