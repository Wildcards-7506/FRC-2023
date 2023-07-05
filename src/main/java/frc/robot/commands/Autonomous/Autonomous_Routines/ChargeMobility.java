package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoDropWheelPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneExtenderPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneRotatorPosition;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneStingerAction;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCraneWristPosition;
import frc.robot.Constants;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoCSManuever;

public class ChargeMobility extends SequentialCommandGroup {

  public ChargeMobility(){
    
    addCommands(
      new ParallelCommandGroup(
        new AutoCraneStingerAction(8, true),
        new AutoCraneRotatorPosition(Constants.kRotatorDoubleSub + Constants.kRotatorDoubleCubeOffset),
        new AutoCraneWristPosition(Constants.kWristGround)
      ),
      new AutoCraneExtenderPosition(Constants.kExtenderCollect),
      new AutoCraneStingerAction(-8, false),
      new ParallelCommandGroup(
        new AutoCraneExtenderPosition(Constants.kExtenderClosed),
        new AutoDropWheelPosition(Constants.kDropWheelDistance)
      ),
      new ParallelCommandGroup(
        new AutoCraneRotatorPosition(Constants.kRotatorClosed),
        new AutoCraneWristPosition(Constants.kWristClosed),
        new AutoCSManuever(-0.6)
      )      
    );
  }
} 