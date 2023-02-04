package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoDrive;

public class AutoRoutineBlueCenterCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String centercharge_a = "paths/BlueCenterCharge/blue_centercharge_a.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(centercharge_a);

  //Commands
  private Command movementA = AutoDrive.drivetrainMotion(traj_path_a);

  public AutoRoutineBlueCenterCharge(){
    
    addCommands(
        //score
        movementA
        //align
      );
  }
} 