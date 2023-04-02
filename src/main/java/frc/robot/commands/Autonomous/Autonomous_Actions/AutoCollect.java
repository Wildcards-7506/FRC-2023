package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoGroundTarget;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLook;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class AutoCollect extends SequentialCommandGroup {
  int pipeline;
  
  public AutoCollect(double distance, double intake){
    addCommands(
      new ParallelCommandGroup(
        new AutoLook(Constants.kLookCollect, 3),
        new AutoRotatorPosition(Constants.kRotatorGround-15),
        new AutoExtenderPosition(Constants.kExtenderGround-10),
        new AutoWristPosition(Constants.kWristGround+68),
        new AutoGroundTarget(distance, intake)
      ));
  }
} 