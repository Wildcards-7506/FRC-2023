package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoStrafe;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoSnap;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoStrafe;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoLineDrive;

public class Right2PA extends SequentialCommandGroup {

  public Right2PA(){
    
    addCommands(
      new AutoScore(0,0),
      new AutoCollect(12), 
      new ParallelCommandGroup( 
        new AutoStingerAction(8, true),
        new AutoLineDrive(4.5)
      ),
      new ParallelCommandGroup(
        new AutoLineDrive(-4.5),
        new AutoExtenderPosition(Constants.kExtenderClosed)
      ),
      new AutoWristPosition(0),
      new AutoScore(0,3)
    );
  }
} 