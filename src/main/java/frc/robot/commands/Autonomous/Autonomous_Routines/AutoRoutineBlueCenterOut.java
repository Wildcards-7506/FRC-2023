package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;

public class AutoRoutineBlueCenterOut extends SequentialCommandGroup {
  // required PathWeaver file paths
  String centerout_a = "paths/BlueCenterOut/blue_centerout_a.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(centerout_a);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);

  public AutoRoutineBlueCenterOut(){
    
    addCommands(
        //score
        movementA
      );
  }
} 