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
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class Right2PA extends SequentialCommandGroup {
  // required PathWeaver file paths
  String out = "paths/RightOut.wpilib.json";
  String in = "paths/LeftOut.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(out);
  private Trajectory traj_path_b = AutoTrajectoryReader.generateTrajectoryFromFile(in);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);
  private Command movementB = AutoDrive.drivetrainMotion(traj_path_b);

  public Right2PA(){
    
    addCommands(
      new AutoScore(0,2),
      movementA,
      new AutoCollect(12),  
      new ParallelCommandGroup(
        movementB,
        new AutoExtenderPosition(Constants.kExtenderClosed)
      ),
      new AutoScore(1,-6)    
    );
  }
} 