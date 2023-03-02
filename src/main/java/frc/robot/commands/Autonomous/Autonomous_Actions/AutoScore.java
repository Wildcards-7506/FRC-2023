package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoClawPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoScoringAlign;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class AutoScore extends SequentialCommandGroup {
  double action;

  public AutoScore(int pipeline, double offset){
    if (Robot.crane.rollerInUse){
      action = -12;
    } else {
      action = Constants.kClawOpen;
    }
    addCommands(
        new AutoScoringAlign(pipeline),
        new ParallelCommandGroup(
          new AutoRotatorPosition(Constants.kRotatorHi),
          new AutoWristPosition(Constants.kWristHi),
          new AutoExtenderPosition(Constants.kExtenderHi)),
        new AutoClawPosition(action, false),
        new AutoExtenderPosition(Constants.kExtenderClosed),
        //Return to Close
        new ParallelCommandGroup(
          new AutoClawPosition(0,false),
          new AutoRotatorPosition(Constants.kRotatorClosed),
          new AutoWristPosition(Constants.kRotatorClosed))
      );
  }
} 