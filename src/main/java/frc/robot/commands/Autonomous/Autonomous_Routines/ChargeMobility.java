package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneWristPosition;
import frc.robot.Constants.CraneConstants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCSManuever;

public class ChargeMobility extends SequentialCommandGroup {
  public Pose2d pose = new Pose2d(0.0,0.0,new Rotation2d(Math.PI));

  public ChargeMobility(){
    addCommands(
      new ParallelCommandGroup(
        new AutoCraneStingerAction(8, true),
        new AutoCraneRotatorPosition(CraneConstants.kRotatorDoubleSub + CraneConstants.kRotatorDoubleCubeOffset),
        new AutoCraneWristPosition(CraneConstants.kWristGround)
      ),
      new AutoCraneExtenderPosition(CraneConstants.kExtenderCollect),
      new AutoCraneStingerAction(-8, false),
      new AutoCraneExtenderPosition(CraneConstants.kExtenderClosed),
      new ParallelCommandGroup(
        new AutoCraneRotatorPosition(CraneConstants.kRotatorClosed),
        new AutoCraneWristPosition(CraneConstants.kWristClosed),
        new AutoCSManuever(-0.6)
      )      
    );
  }
} 