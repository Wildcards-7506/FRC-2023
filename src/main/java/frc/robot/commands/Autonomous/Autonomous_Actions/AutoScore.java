package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoScoringAlign;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneWristPosition;

public class AutoScore extends SequentialCommandGroup {

  public AutoScore(int pipeline, double offset){
    addCommands(
      new AutoScoringAlign(pipeline),
      new AutoCraneStingerAction(-8+16*pipeline, true),
      new ParallelCommandGroup(
        new AutoCraneRotatorPosition(Constants.CraneConstants.kRotatorHi),
        new AutoCraneWristPosition(Constants.CraneConstants.kWristHi-10)
      ),
      new AutoCraneExtenderPosition(Constants.CraneConstants.kExtenderHi + offset),
      new AutoCraneStingerAction(12 - 24 * pipeline, false),
      new ParallelCommandGroup(
        new AutoCraneRotatorPosition(Constants.CraneConstants.kRotatorClosed),
        new AutoCraneExtenderPosition(-1),
        new AutoCraneWristPosition(Constants.CraneConstants.kWristClosed)
      )
    );
  }
} 