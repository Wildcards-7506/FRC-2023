package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoScoringAlign;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class AutoScore extends SequentialCommandGroup {

  public AutoScore(int pipeline, double offset){

    addCommands(
        new AutoScoringAlign(pipeline),
        new ParallelCommandGroup(
          new AutoRotatorPosition(Constants.kRotatorHi),
          new AutoWristPosition(Constants.kWristHi)
        ),
        new AutoExtenderPosition(Constants.kExtenderHi),
        new AutoStingerAction(-12, false),
        new AutoExtenderPosition(Constants.kExtenderClosed),
        //Return to Close
        new ParallelCommandGroup(
          new AutoRotatorPosition(Constants.kRotatorClosed),
          new AutoWristPosition(Constants.kRotatorClosed))
      );
  }
} 