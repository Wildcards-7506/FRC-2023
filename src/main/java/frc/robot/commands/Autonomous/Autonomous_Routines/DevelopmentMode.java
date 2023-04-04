package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.*;
import frc.robot.commands.Autonomous.Subsystem_Commands.*;

public class DevelopmentMode extends SequentialCommandGroup {

  public DevelopmentMode(){
    addCommands(
      new AutoDropWheelPosition(Constants.kDropWheelDistance),
      // new AutoCSManuever(.3)
      new AutoBalance(0)
    );
  }
}
