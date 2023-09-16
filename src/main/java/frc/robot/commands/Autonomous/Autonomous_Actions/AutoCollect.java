package frc.robot.commands.Autonomous.Autonomous_Actions;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoLook;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneWristPosition;

public class AutoCollect extends SequentialCommandGroup {
  int pipeline;
  
  public AutoCollect(double intake, double direction){
    addCommands(
      new ParallelCommandGroup(
        new AutoLook(Constants.LLRConstants.kLookCollect, 3),
        new AutoCraneRotatorPosition(Constants.CraneConstants.kRotatorGround),
        new AutoCraneExtenderPosition(Constants.CraneConstants.kExtenderGround),
        new AutoCraneWristPosition(Constants.CraneConstants.kWristGround),
        new AutoCraneStingerAction(intake, true)
      )
    );
  }
} 