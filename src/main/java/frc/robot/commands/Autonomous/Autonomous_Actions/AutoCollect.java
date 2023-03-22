package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoWristPosition;

public class AutoCollect extends SequentialCommandGroup {
  int pipeline;
  
  public AutoCollect(int setPoint){
    Robot.limelight.cubePipeline();
    addCommands(
      new ParallelCommandGroup(
        new AutoRotatorPosition(Constants.kRotatorClosed + 10),
        new AutoExtenderPosition(Constants.kExtenderGround),
        new AutoWristPosition(Constants.kWristGround),
        new AutoStingerAction(setPoint,true))
    );
  }
} 