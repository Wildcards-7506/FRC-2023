package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoClawPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoScoringAlign;

public class AutoScore extends SequentialCommandGroup {
  double action;

  public AutoScore(int pipeline, double offset){
    if (Robot.crane.rollerInUse){
      action = -4;
    } else {
      action = Constants.kClawOpen;
    }
    addCommands(
        new ParallelCommandGroup(
          new AutoScoringAlign(pipeline),
          new AutoRotatorPosition(Constants.kRotatorHi, Constants.kRotatorHi + offset),
          new AutoExtenderPosition(Constants.kExtenderHi)),
        new AutoClawPosition(action),
        //Return to Close
        new ParallelCommandGroup(
          new AutoClawPosition(0),
          new AutoRotatorPosition(Constants.kRotatorClosed, Constants.kRotatorClosed),
          new AutoExtenderPosition(Constants.kExtenderClosed))
      );
  }
} 