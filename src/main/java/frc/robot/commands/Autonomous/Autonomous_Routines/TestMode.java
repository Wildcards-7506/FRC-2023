package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLineDrive;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoSnap;

public class TestMode extends SequentialCommandGroup {

  public TestMode(){
    addCommands(
      new AutoSnap(0),
      new AutoLineDrive(4)
    );
  }
}
