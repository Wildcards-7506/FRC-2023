package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLineDrive;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStrafe;

public class TestMode extends SequentialCommandGroup {

  public TestMode(){
    addCommands(
      // new AutoLineDrive(1),
      // new AutoLineDrive(-1), 
      new AutoStrafe(0.1, 3), 
      new AutoStrafe(-0.1, 3)
    );
  }
}
