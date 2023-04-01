package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoLineDrive;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLook;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStrafe;

public class TestMode extends SequentialCommandGroup {

  public TestMode(){
    addCommands(
      new AutoScore(0, 0),
      new AutoCollect(-8),
      new ParallelCommandGroup(
        new AutoLineDrive(5),
        new AutoStingerAction(8, true)
      )
    );
  }
}
