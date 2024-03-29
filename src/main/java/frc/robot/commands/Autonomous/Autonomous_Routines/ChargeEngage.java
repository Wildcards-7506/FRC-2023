package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneWristPosition;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCSManuever;

public class ChargeEngage extends SequentialCommandGroup {
  public Pose2d pose = new Pose2d(0.0,0.0,new Rotation2d(Math.PI));
  
  public ChargeEngage(){
    addCommands(
        new ParallelCommandGroup(
          new AutoCraneStingerAction(8, true),
          new AutoCraneRotatorPosition(Constants.kRotatorDoubleSub + Constants.kRotatorDoubleCubeOffset),
          new AutoCraneWristPosition(Constants.kWristDoubleSub + Constants.kWristCubeDoubleOffset)
        ),
        new AutoCraneExtenderPosition(Constants.kExtenderCollect),
        new AutoCraneStingerAction(-8, false),
        new AutoCraneExtenderPosition(Constants.kExtenderClosed),
        new ParallelCommandGroup(
          new AutoCraneRotatorPosition(Constants.kRotatorClosed),
          new AutoCraneWristPosition(Constants.kWristClosed),
          new AutoCSManuever(-0.6)
        ),
        new AutoBalance(0)
      );
  }
} 