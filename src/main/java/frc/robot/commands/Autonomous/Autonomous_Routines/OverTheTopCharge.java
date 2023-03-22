package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoDropWheelPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCSManuever;

public class OverTheTopCharge extends SequentialCommandGroup {

  public OverTheTopCharge(){
    
    addCommands(
        new ParallelCommandGroup(
          new AutoRotatorPosition(Constants.kRotatorCollect),
          new AutoExtenderPosition(Constants.kExtenderHi)
        ),
        new AutoStingerAction(-8, false),
        new AutoDropWheelPosition(Constants.kDropWheelDistance),
        new AutoCSManuever(),
        new AutoBalance(0)
      );
  }
} 