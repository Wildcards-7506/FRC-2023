package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCollect;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCSManuever;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoDropWheelPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoPitchCorrect;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoSnap;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class TestMode extends SequentialCommandGroup {

  public TestMode(){
    addCommands(
      new AutoSnap(0)//,
      //new AutoDropWheelPosition(Constants.kDropWheelDistance),
      //new AutoPitchCorrect(),
      //new AutoSnap(90)
    );
  }
}
