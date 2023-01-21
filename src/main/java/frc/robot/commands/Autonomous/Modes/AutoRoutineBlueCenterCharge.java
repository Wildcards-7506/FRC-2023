package frc.robot.commands.Autonomous.Modes;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.AutoCommands;
import frc.robot.commands.Autonomous.AutoTrajectoryReader;

public class AutoRoutineBlueCenterCharge extends SequentialCommandGroup {
  // required PathWeaver file paths
  String centercharge_a = "paths/BlueCenterCharge/blue_centercharge_a.wpilib.json";
  
  // trajectories
  private Trajectory traj_path_a = AutoTrajectoryReader.generateTrajectoryFromFile(centercharge_a);

  //Commands
  private Command movementA = AutoCommands.drivetrainMotion(traj_path_a);

  public AutoRoutineBlueCenterCharge(){
    
    addCommands(
        //score
        movementA
        //align
      );
  }
} 