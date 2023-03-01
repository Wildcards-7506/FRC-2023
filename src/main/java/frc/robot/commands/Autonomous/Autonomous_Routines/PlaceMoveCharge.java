package frc.robot.commands.Autonomous.Autonomous_Routines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoScore;
import frc.robot.commands.Autonomous.Subsystem_Commands.AutoCSManuever;
import frc.robot.commands.Autonomous.Autonomous_Actions.AutoBalance;

public class PlaceMoveCharge extends SequentialCommandGroup {

  public PlaceMoveCharge(){
    
    addCommands(
        new AutoScore(0, -10),
        new AutoCSManuever(),
        new AutoBalance(0)
      );
  }
} 