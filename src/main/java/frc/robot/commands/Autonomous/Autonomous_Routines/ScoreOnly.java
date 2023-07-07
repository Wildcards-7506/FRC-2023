package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;

public class ScoreOnly extends SequentialCommandGroup {

  public ScoreOnly(){
    addCommands(
      new AutoScore(0,0)
    );
  }
} 