package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLineDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class TwoPieceAuto extends SequentialCommandGroup {

  public TwoPieceAuto(){
    
    addCommands(
      new AutoScore(0,0),
      new AutoCollect(4.5, 12, 1), 
      new ParallelCommandGroup(
        new AutoLineDrive(-4.5),
        new AutoCraneExtenderPosition(Constants.kExtenderClosed)
      ),
      new AutoScore(1,Constants.kExtenderHi)
    );
  }
} 