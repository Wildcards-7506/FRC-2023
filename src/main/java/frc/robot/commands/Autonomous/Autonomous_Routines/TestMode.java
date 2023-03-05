package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCSManuever;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoClawPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class TestMode extends SequentialCommandGroup {

  public TestMode(){
    
    addCommands(     
      // new AutoClawPosition(-1, true),
      // new ParallelCommandGroup(
      //     new AutoRotatorPosition(Constants.kRotatorHi),
      //     new AutoWristPosition(Constants.kWristHi)),
      // new AutoExtenderPosition(Constants.kExtenderHi + 2),
      // new AutoClawPosition(8, false),
      // new AutoExtenderPosition(-1),
      // new ParallelCommandGroup(
      //     new AutoRotatorPosition(Constants.kRotatorClosed),
      //     new AutoWristPosition(Constants.kWristClosed)),
      new AutoCSManuever()
    );
  }
}