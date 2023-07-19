package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoDropWheelPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoAngleCorrect;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoSnap;

public class AutoBalance extends SequentialCommandGroup {

  public AutoBalance(int angle){
    addCommands(
      new AutoSnap(angle),
      new AutoDropWheelPosition(Constants.kDropWheelDistance),
      new AutoAngleCorrect()
    );
  }
} 