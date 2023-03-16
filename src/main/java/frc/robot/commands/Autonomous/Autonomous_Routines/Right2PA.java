package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLineDrive;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoSnap;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;

public class Right2PA extends SequentialCommandGroup {

  public Right2PA(){
    
    addCommands(
      new AutoScore(1,-Constants.kExtenderHi),
      new AutoSnap(0),
      new ParallelCommandGroup(
        new AutoCollect(-12),  
        new AutoLineDrive(4)
      ),
      new ParallelCommandGroup(
        new AutoLineDrive(0),
        new AutoExtenderPosition(Constants.kExtenderClosed)
      ),
      new AutoScore(1,3)
    );
  }
} 